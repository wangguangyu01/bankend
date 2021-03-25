package com.smart119;

import com.alibaba.fastjson.JSONObject;
import com.smart119.util.AESUtil;
import com.smart119.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@SpringBootTest
public class CMCCApiTest {

    String urlHead = "http://10.17.254.121:8002/cmsd-store-web/openAPI";
    String appId = "test";
    String appSecret = "5f0ef9c09709e34568a72399d4b8d4a5";

    /**
     * 状态码 - 查询成功
     */
    String respCode_OK = "0";

    /**
     * 定位接口
     * 根据手机号 获取 手机所在位置定位信息
     */
    @Test
    public void getPosition() throws Exception {
        String phone = "18600632256";
        String apiUrl = urlHead + "/strategy/getPosition";
        //参数拼接
        JSONObject jsonObj = new JSONObject();
        String timeStamp = DateFormatUtils.format(new Date(),"yyyyMMddHHmmss");
        jsonObj.put("timeStamp",timeStamp);
        jsonObj.put("mac", MD5Util.md5Encode(appId+"-"+timeStamp+"-"+ appSecret));
        jsonObj.put("appId",appId);
        jsonObj.put("custid", AESUtil.encrypt(phone,AESUtil.password));


        System.out.println(jsonObj.toJSONString());

        //获取返回值
        RestTemplate restTemplate = new RestTemplate();
        //设置请求header 为 APPLICATION_FORM_URLENCODED
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // 请求体，包括请求数据 body 和 请求头 headers
        HttpEntity httpEntity = new HttpEntity(jsonObj, headers);

        String apiResult = null;
        try {
            //使用 exchange 发送请求，以String的类型接收返回的数据
            ResponseEntity<String> strbody = restTemplate.exchange(apiUrl, HttpMethod.POST, httpEntity, String.class);
            System.out.println(strbody.getBody());
            apiResult = strbody.getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(StringUtils.isBlank(apiResult)){
            System.out.println("请求返回值为NULL");
            return;
        }

        JSONObject resultJson = JSONObject.parseObject(apiResult);

        String respCode = resultJson.getString("respCode");

        if(!StringUtils.equals("0",respCode_OK)){
            System.out.println("响应码错误");
            return;
        }
        Double longitude = resultJson.getJSONObject("result").getDouble("longitude");
        Double latitude = resultJson.getJSONObject("result").getDouble("latitude");
        System.out.println("经度："+latitude+"       纬度："+longitude);

    }

}
