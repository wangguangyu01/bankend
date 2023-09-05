package com.smart119.system.service;

import com.alibaba.fastjson.JSONObject;
import com.smart119.system.domain.OderPay;
import com.wechat.pay.java.service.refund.model.Refund;

import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Map;

public interface OderPayService {


    /**
     * 退款回调地址
     */
    public static final String notify_url = "https://springboot-u4yq-39835-6-1317513730.sh.run.tcloudbase.com/api/returnPlay";


    /**
     * 退款地址
     */
    public static final String refunds_url = "https://api.mch.weixin.qq.com/v3/refund/domestic/refunds";

    /**
     * 支付api证书序列号
     */
    public static final String  serialNo = "6E8DA7447FACAF1C13A10B7801D951A613308E4B";



    public static final String certificates = "https://api.mch.weixin.qq.com/v3/certificates";





    /**
     * 查询订单号
     * @param tradeNo 订单号
     * @param openId  小程序用户的唯一标识
     * @return
     */
    public OderPay queryByTradeNo(String tradeNo, String openId);


    /**
     * 退款
     * @param oderPay
     * @return
     */
    Refund moneyBack(OderPay oderPay) throws Exception;



    List<X509Certificate> getWechatPayCertificates() throws Exception;


}
