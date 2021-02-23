package com.smart119.webapi.controller;

import com.alibaba.fastjson.JSONObject;
import com.smart119.common.service.BaiduMapService;
import com.smart119.common.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName : WeatherController
 * @Description : 天气Controller
 * @Author : Liangsl
 * @Date: 2021-01-29 13:09
 */
@Api(value = "（所有端）查询天气API", description = "（所有端）查询天气API")
@RestController
@RequestMapping("/webapi/weather")
public class WeatherController {
    @Autowired
    private BaiduMapService baiduMapService;

    @GetMapping("/getWeather")
    @ApiOperation("查询天气")
    public R getWeather(){
        JSONObject jsonObject = baiduMapService.getWeather("371300");
        return R.ok(jsonObject);
    }
}
