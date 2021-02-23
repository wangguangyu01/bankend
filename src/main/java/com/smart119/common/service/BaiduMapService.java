package com.smart119.common.service;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public interface BaiduMapService {

    JSONArray getDistanceAndDuration(String fromZb, String toZb);

    JSONObject getWeather(String district);

    JSONArray getMapAddrByName(String addrName,String city);

    Object routeRecommendationBaidu(String fromZb, String toZb);

    Object routeRecommendationGaode(String fromZb, String toZb);

    JSONObject baiduZbToGaodeZb(String locations);

    String gaodeZbToBaiduZb(String locations);

    Object routeRecommendationGaodeJc(String fromZb, String toZb);


}
