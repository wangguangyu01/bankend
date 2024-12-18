package com.smart119.system.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.smart119.common.dao.SystemConfigDao;
import com.smart119.common.domain.SystemConfig;
import com.smart119.common.utils.DateUtils;
import com.smart119.system.dao.OderPayDao;
import com.smart119.system.domain.OderPay;
import com.smart119.system.dto.MoneyBackAmountDTO;
import com.smart119.system.dto.MoneyBackDTO;
import com.smart119.system.service.OderPayService;
import com.wechat.pay.contrib.apache.httpclient.WechatPayHttpClientBuilder;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Validator;
import com.wechat.pay.contrib.apache.httpclient.cert.CertificatesManager;
import com.wechat.pay.contrib.apache.httpclient.util.CertSerializeUtil;
import com.wechat.pay.contrib.apache.httpclient.util.PemUtil;
import com.wechat.pay.java.core.Config;
import com.wechat.pay.java.core.RSAAutoCertificateConfig;
import com.wechat.pay.java.core.RSAConfig;
import com.wechat.pay.java.service.refund.RefundService;
import com.wechat.pay.java.service.refund.model.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.bouncycastle.openssl.jcajce.JcaPEMWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.*;
import java.math.BigInteger;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.cert.X509Certificate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static org.apache.http.HttpHeaders.ACCEPT;
import static org.apache.http.entity.ContentType.APPLICATION_JSON;


@Service
@Slf4j
public class OderPayServiceImpl implements OderPayService {

    @Resource
    private OderPayDao oderPayDao;

    @Resource
    private RestTemplate restTemplate;


    @Autowired
    private SystemConfigDao systemConfigMapper;

    @Autowired
    private  ResourceLoader resourceLoader;







    @Override
    public OderPay queryByTradeNo(String tradeNo, String openId) {
        return oderPayDao.queryByTradeNo(tradeNo, openId);
    }

    @Override
    public Refund moneyBack(OderPay oderPay) throws Exception {

        String out_refund_no = DateUtils.format(new Date(), "yyyyMMddHHmmssSSS");
        JSONObject jsonObject = null;
        MoneyBackAmountDTO moneyBackAmountDTO = MoneyBackAmountDTO.builder()
                .currency("CNY")
                .refund(NumberUtils.toInt(oderPay.getPrice(), 0))
                .total(NumberUtils.toInt(oderPay.getPrice(), 0))
                .build();
        List<X509Certificate> certificates = this.getWechatPayCertificates();

        LambdaQueryWrapper<SystemConfig> systemConfigLambdaQueryWrapper = new LambdaQueryWrapper<>();
        systemConfigLambdaQueryWrapper.eq(SystemConfig::getSysConfigKey, "weixinMchid");
        SystemConfig systemConfigMchid = systemConfigMapper.selectOne(systemConfigLambdaQueryWrapper);
        File apiclientKey = resourceLoader.getResource("classpath:mchidCert/apiclient_key.pem").getFile();

        LambdaQueryWrapper<SystemConfig> api3KeyQueryWrapper = new LambdaQueryWrapper<>();
        api3KeyQueryWrapper.eq(SystemConfig::getSysConfigKey, "weixinCertKeyApi3");
        SystemConfig api3Key = systemConfigMapper.selectOne(api3KeyQueryWrapper);

        Config config =
                new RSAAutoCertificateConfig.Builder()
                        .merchantId(systemConfigMchid.getSysConfigValue())
                        .privateKeyFromPath(apiclientKey.getPath())
                        .merchantSerialNumber(OderPayService.serialNo)
                        .apiV3Key(api3Key.getSysConfigValue())
                        .build();
        CreateRequest createRequest = new CreateRequest();
        createRequest.setTransactionId(oderPay.getTransactionId());
        //createRequest.setFundsAccount(ReqFundsAccount.AVAILABLE);
        createRequest.setNotifyUrl(OderPayService.notify_url);
        createRequest.setOutRefundNo(out_refund_no);
        createRequest.setOutTradeNo(oderPay.getTradeNo());
        createRequest.setReason("取消参与活动");
        createRequest.setGoodsDetail(null);
        createRequest.setSubMchid(null);
        AmountReq amountReq = new AmountReq();
        amountReq.setCurrency("CNY");
        amountReq.setFrom(null);
        amountReq.setRefund(NumberUtils.toLong(oderPay.getPrice(), 0L));
        amountReq.setTotal(NumberUtils.toLong(oderPay.getPrice(), 0L));
        createRequest.setAmount(amountReq);
        Refund refund = new RefundService.Builder().config(config).build().create(createRequest);
        log.info("refund--->{}", refund);

        return refund;
    }


    @Override
    public List<X509Certificate> getWechatPayCertificates() throws Exception {
        List<X509Certificate> x509Certificates = new ArrayList<>();
        File file = resourceLoader.getResource("classpath:mchidCert/apiclient_key.pem").getFile();
        LambdaQueryWrapper<SystemConfig> systemConfigLambdaQueryWrapper = new LambdaQueryWrapper<>();
        systemConfigLambdaQueryWrapper.eq(SystemConfig::getSysConfigKey, "weixinMchid");
        SystemConfig systemConfigMchid = systemConfigMapper.selectOne(systemConfigLambdaQueryWrapper);
        LambdaQueryWrapper<SystemConfig> api3KeyQueryWrapper = new LambdaQueryWrapper<>();
        api3KeyQueryWrapper.eq(SystemConfig::getSysConfigKey, "weixinCertKeyApi3");
        SystemConfig api3Key = systemConfigMapper.selectOne(api3KeyQueryWrapper);
        String api3KeyStr = api3Key.getSysConfigValue();
        PrivateKey privateKey = PemUtil.loadPrivateKey(new FileInputStream(file));
        WechatPayHttpClientBuilder builder = WechatPayHttpClientBuilder.create()
                .withMerchant(systemConfigMchid.getSysConfigValue(), OderPayService.serialNo, privateKey)
                .withValidator((response) -> true);
        CloseableHttpClient httpClient = builder.build();
        HttpGet httpGet = new HttpGet(OderPayService.certificates);
        httpGet.addHeader(ACCEPT, APPLICATION_JSON.toString());
        CloseableHttpResponse response = httpClient.execute(httpGet);
        if (response.getStatusLine().getStatusCode() == 200) {
            String body = EntityUtils.toString(response.getEntity());
            log.info("getWechatPayCertificates response --->{}", body);
            if (StringUtils.isNotBlank(body)) {
                Map<BigInteger, X509Certificate> newMap =  CertSerializeUtil.deserializeToCerts(api3KeyStr.getBytes("UTF-8"), body);
                Set<Map.Entry<BigInteger, X509Certificate>> entries =  newMap.entrySet();
                for (Map.Entry<BigInteger, X509Certificate> entry: entries) {
                    BigInteger serialNumber = entry.getKey();
                    X509Certificate x509Certificate = entry.getValue();
                    x509Certificates.add(x509Certificate);
                    StringWriter writer = new StringWriter();
                    JcaPEMWriter pemWriter = new JcaPEMWriter(writer);
                    pemWriter.writeObject(x509Certificate);
                    pemWriter.flush();
                    pemWriter.close();
                    String pemStr = writer.toString();
                    System.out.println(pemStr);

                }
            }
        }

        return x509Certificates;
    }




}
