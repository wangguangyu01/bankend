package com.smart119.common.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.smart119.common.dto.GpsDto;
import com.smart119.common.redis.shiro.RedisManager;
import com.smart119.common.service.ExliveService;
import com.smart119.jczy.dao.XfclDao;
import com.smart119.jczy.domain.XfclDO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * EXLIVE平台动态数据
 *
 * @author wangguangyu
 * @email 1992lcg@163.com
 * @date 2021-07-07 15:45:15
 */
@Service
@Slf4j
public class ExliveServiceImpl implements ExliveService {


    @Resource
    private XfclDao xfclDao;


    @Value("${exlive.url}")
    private String exliveUrl;


    @Value("${exlive.username}")
    private String username;

    @Value("${exlive.password}")
    private String password;

    @Autowired
    private RedisManager redisManager;


    @Autowired
    private RestTemplate restTemplate;

    /**
     * 用户登录
     *
     * @return
     * @throws JsonProcessingException
     */
    @Override
    public GpsDto clLoginSystem() throws JsonProcessingException {
        long startTimeMillis = System.currentTimeMillis();
        log.info("GPS接口登录请求开始时间---》{}", startTimeMillis);
        //http://60.195.248.67:89/gpsonline/GPSAPI?version=1&method=vLoginSystem&name=辽HB929&pwd=000000
        String url = exliveUrl + "?version=1&method=loginSystem&name=" + username + "&pwd=" + password;
        String res = restTemplate.postForObject(url, null, String.class);
        JSONObject jsonObject = JSONObject.parseObject(res);
        log.info("GPS接口登录返回数据{}", String.valueOf(jsonObject));
        GpsDto gpsDto = new GpsDto();
        if (!ObjectUtils.isEmpty(jsonObject)) {
            if (!ObjectUtils.isEmpty(jsonObject.get("uid"))) {
                String uid = String.valueOf(jsonObject.get("uid"));
                gpsDto.setUid(uid);
            }
            String uKey = (String) jsonObject.get("uKey");
            gpsDto.setUKey(uKey);
            redisManager.setex("resource:gps", 24 * 60 * 60, JSONObject.toJSONString(gpsDto));
        }
        System.out.println(gpsDto.toString());
        long endTimeMillis = System.currentTimeMillis();
        log.info("GPS接口登录请求结束时间---》{}", endTimeMillis);
        long consumptionTimeMillis = endTimeMillis - startTimeMillis;
        log.info("GPS接口登录请求消耗时间---》{}", consumptionTimeMillis);
        return gpsDto;
    }


    /**
     * 分组车辆数据
     *
     * @param uid  用户id
     * @param uKey 用户授权
     * @return
     * @throws JsonProcessingException
     */
    @Override
    public List<String> loadVehicles(String uid, String uKey) throws JsonProcessingException {
        long startTimeMillis = System.currentTimeMillis();
        log.info("GPS接口请求车辆分组开始时间---》{}", startTimeMillis);
        String url = exliveUrl + "?version=1&method=loadVehicles&uid=" + uid + "&uKey=" + uKey;
        String res = restTemplate.postForObject(url, null, String.class);
        JSONObject jsonObject = JSONObject.parseObject(res);
        List<String> vehiclesList = new ArrayList<>();
        if (!jsonObject.isEmpty() && jsonObject.containsKey("groups")) {
            JSONArray jsonArray = jsonObject.getJSONArray("groups");
            if (!jsonArray.isEmpty()) {
                List<Map> mapList = jsonArray.toJavaList(Map.class);
                for (Map map : mapList) {
                    if (!map.isEmpty() && map.containsKey("vehicles")) {
                        String vehicles = String.valueOf(map.get("vehicles"));
                        List<Map> vehicleList = JSONArray.parseArray(vehicles, Map.class);
                        for (Map vehicleMap : vehicleList) {
                            String vehicleStr = JSONObject.toJSONString(vehicleMap);
                            System.out.println(vehicleStr);
                            vehiclesList.add(vehicleStr);
                        }
                    }
                }
            }
        }
        long endTimeMillis = System.currentTimeMillis();
        log.info("GPS接口请求车辆分组请求结束时间---》{}", endTimeMillis);
        long consumptionTimeMillis = endTimeMillis - startTimeMillis;
        log.info("GPS接口请求车辆分组请求消耗时间---》{}", consumptionTimeMillis);
        return vehiclesList;
    }


    @Override
    public List<String> loadLocation() throws JsonProcessingException {
        GpsDto gpsDto = null;
        if (!redisManager.exist("resource:gps")) {
            gpsDto = this.clLoginSystem();
        } else {
            String gpsLoginInfo = redisManager.get("resource:gps");
            gpsDto = JSON.parseObject(gpsLoginInfo, GpsDto.class);
            if (ObjectUtils.isEmpty(gpsDto.getUid()) && ObjectUtils.isEmpty(gpsDto.getUKey())) {
                gpsDto = this.clLoginSystem();
            }
        }
        List<String> vehicleList = this.loadVehicles(gpsDto.getUid(), gpsDto.getUKey());
        return vehicleList;
    }


    @Override
    public List<String> queryXfclVidAndVkey() throws JsonProcessingException {
        List<String> vehicleList = this.loadLocation();
        for (String vehicleStr : vehicleList) {
            JSONObject vehicleJson = JSONObject.parseObject(vehicleStr);
            String xfclGpsName = String.valueOf(vehicleJson.get("name"));
            redisManager.set("resource:gps:"+xfclGpsName, vehicleStr);
        }
        return vehicleList;
    }



    @Override
    public void updateGpsPostion() throws JsonProcessingException {
        long startTimeMillis = System.currentTimeMillis();
        log.info("GPS接口更新车辆位置开始时间---》{}", startTimeMillis);
        List<String> vehicleList = queryXfclVidAndVkey();
        List<XfclDO> xfclDOS = xfclDao.selectList(null);
        for (XfclDO xfclDO: xfclDOS) {
            for (String vehicleStr : vehicleList) {
                JSONObject vehicleJson = JSONObject.parseObject(vehicleStr);
                String gprs = String.valueOf(vehicleJson.get("gprs"));
                if (StringUtils.equals(xfclDO.getDeviceId(), gprs)) {
                    String url = exliveUrl + "?version=1&method=loadLocation&vid=" + String.valueOf(vehicleJson.get("id"))
                            + "&vKey=" + String.valueOf(vehicleJson.get("vKey"));
                    String res = restTemplate.postForObject(url, null, String.class);
                    JSONObject jsonObject = JSONObject.parseObject(res);
                    if (!jsonObject.isEmpty() && jsonObject.containsKey("locs")) {
                        JSONArray locsArray = jsonObject.getJSONArray("locs");
                        StringBuffer stringBuffer = new StringBuffer(10);
                        if (!locsArray.isEmpty()) {
                            List<Map> mapList = locsArray.toJavaList(Map.class);
                            for (Map map : mapList) {

                                //纬度
                                String lat = String.valueOf(map.get("lat"));
                                double latDouble = NumberUtils.toDouble(lat);
                                String lat_xz = String.valueOf(map.get("lat_xz"));
                                double lat_xzDouble = NumberUtils.toDouble(lat_xz);

                                //经度
                                String lng = String.valueOf(map.get("lng"));
                                double lngDouble = NumberUtils.toDouble(lng);
                                String lng_xz = String.valueOf(map.get("lng_xz"));
                                double lng_xzDouble = NumberUtils.toDouble(lng_xz);
                                xfclDO.setClwzDqjd(lngDouble + lng_xzDouble);
                                xfclDO.setClwzDqwd(latDouble + lat_xzDouble);
                                xfclDO.setClwzWzcjsj(new Date());
                                xfclDO.setGpsVid(String.valueOf(vehicleJson.get("id")));
                                xfclDO.setGpsVkey((String) vehicleJson.get("vKey"));
                                xfclDO.setGpsState(String.valueOf(map.get("state")));
                                int direct = NumberUtils.toInt(String.valueOf(map.get("direct")), 0);
                                String gpsDirect = "";
                                if (direct == 0) {
                                    gpsDirect = "正北方向";
                                } else if (direct > 0 && direct < 90) {
                                    gpsDirect = "偏东方向" + direct;
                                } else if (direct == 90) {
                                    gpsDirect = "正东方向";
                                } else if (direct > 90 && direct < 180) {
                                    gpsDirect = "偏南方向" + (direct % 90);
                                } else if (direct == 180) {
                                    gpsDirect = "正南方向";
                                } else if (direct > 180 && direct < 270) {
                                    gpsDirect = "偏西方向" + (direct % 180);
                                } else if (direct == 270) {
                                    gpsDirect = "正西方向";
                                } else if (direct > 270 && direct < 360) {
                                    gpsDirect = "偏北方向" + (direct % 270);
                                }
                                xfclDO.setGpsDirect(gpsDirect);
                                xfclDao.updateById(xfclDO);
                            }
                        }
                    }

                }
            }
        }
        long endTimeMillis = System.currentTimeMillis();
        log.info("GPS接口更新车辆位置请求结束时间---》{}", endTimeMillis);
        long consumptionTimeMillis = endTimeMillis - startTimeMillis;
        log.info("GPS接口更新车辆位置请求消耗时间---》{}", consumptionTimeMillis);
    }

















}
