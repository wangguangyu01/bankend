package com.smart119.webapi.controller;

import com.alibaba.fastjson.JSONObject;
import com.smart119.common.service.BaiduMapService;
import com.smart119.common.utils.LngLonUtil;
import com.smart119.common.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName : ZbzhController
 * @Description : 坐标转换
 * @Author : Liangsl
 * @Date: 2021-02-04 09:24
 */
@RestController
@Api(value = "百度坐标转换高德坐标API", description = "百度坐标转换高德坐标API")
public class ZbzhController {

    @Autowired
    private BaiduMapService baiduMapService;

    /**
     * 百度坐标转高德坐标
     * @return
     */
    @ApiOperation("百度坐标转高德坐标")
    @PostMapping("/webapi/BdtoGd")
    public R BdtoGd(@RequestBody List<Map<String,Double>> zbList){

        String zbs = zbList.stream().map(o->o.get("lon")+","+o.get("lat")).collect(Collectors.joining("|"));
        JSONObject jsonObject =  baiduMapService.baiduZbToGaodeZb(zbs);
        if(jsonObject.get("status").equals("1")) {
            String resLocations = jsonObject.getString("locations");
            String[] resArr = resLocations.split(";");
            for(int i=0;i<zbList.size();i++){
                Map map = zbList.get(i);
                String[] zb = resArr[i].split(",");
                map.put("lon_result",Double.parseDouble(zb[0]));
                map.put("lat_result",Double.parseDouble(zb[1]));
            }
        }
        return R.ok(zbList);
    }
}
