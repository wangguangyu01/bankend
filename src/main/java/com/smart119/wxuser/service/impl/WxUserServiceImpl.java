package com.tencent.wxcloudrun.service.impl;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.digest.SM3;
import cn.hutool.crypto.symmetric.SM4;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import com.alibaba.fastjson.JSONObject;
import com.tencent.wxcloudrun.dao.WxUserMapper;
import com.tencent.wxcloudrun.dto.UserOpenInfoDto;
import com.tencent.wxcloudrun.model.WxUser;
import com.tencent.wxcloudrun.service.WxUserService;
import com.tencent.wxcloudrun.utils.JacksonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class WxUserServiceImpl implements WxUserService {

    @Value("${weixin.secret}")
    private String weixinSecret;

    @Value("${weixin.appid}")
    private String weixinAppId;


    @Value("${weixin.env}")
    private String weixinEnv;

    @Value("${weixin.url}")
    private String weixinUrl;


    @Autowired
    public RestTemplate restTemplate;

    @Autowired
    private WxUserMapper wxUserMapper;


    @Value("${slatKey:wx7290Wsqklollnk}")
    private String slatKey;


    /**
     * 微信登录wx.login获取的code
     *
     * @param code
     * @return
     */
    @Override
    public Map<String,Object> queryWxUserInfo(String code) {

        Map<String,Object> map = new HashMap<>();
        map.put("openid", "");
        map.put("flag", false);
        String url = weixinUrl + "sns/jscode2session?appid=" + weixinAppId + "&secret=" + weixinSecret + "&js_code=" + code + "&grant_type=authorization_code";
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        if (responseEntity.getStatusCodeValue() == 200) {
            String body = responseEntity.getBody();
            if (!ObjectUtils.isEmpty(body)) {
                UserOpenInfoDto userOpenInfoDto = JSONObject.parseObject(body, UserOpenInfoDto.class);
                String openid = userOpenInfoDto.getOpenid();
                SymmetricCrypto sm4 =  SmUtil.sm4(slatKey.getBytes());
                String decryptStr = sm4.encryptHex(openid,  CharsetUtil.CHARSET_UTF_8);
                if (StringUtils.isNotBlank(decryptStr)) {
                    WxUser wxUser = wxUserMapper.selectById(decryptStr);
                    if (!ObjectUtils.isEmpty(wxUser)) {
                        map.put("openid", decryptStr);
                        map.put("flag", true);
                    } else {
                        map.put("openid", decryptStr);
                        map.put("flag", false);
                    }
                }
            }
        }
        return map;
    }


    @Override
    public int addWxUser(WxUser wxUser) {
        return wxUserMapper.insert(wxUser);
    }
}

