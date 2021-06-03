package com.smart119.common.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.smart119.common.config.BootdoConfig;
import com.smart119.common.redis.shiro.RedisManager;
import com.smart119.common.service.BaiduMapService;
import com.smart119.common.utils.LngLonUtil;
import com.smart119.jczy.domain.BrqyDO;
import com.smart119.jczy.service.BrqyService;
import com.smart119.webapi.dao.GaodeKeyDao;
import com.smart119.webapi.domain.GaodeKeyDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import java.util.*;

/**
 * @ClassName : BaiduMapServiceImpl
 * @Description : baidu
 * @Author : Liangsl
 * @Date: 2021-01-24 15:27
 */
@Service
public class BaiduMapServiceImpl implements BaiduMapService{
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private BootdoConfig bootdoConfig;

    @Autowired
    private BrqyService brqyService;

    @Autowired
    private RedisManager redisManager;

    @Autowired
    private GaodeKeyDao gaodeKeyDao;

    @Value("${map.gao.url:https://restapi.amap.com/}")
    private String routeRecommendationGaodePrefix;

    /**
     *
     * @param fromZb 格式 40.45,116.34|40.54,116.35
     * @param toZb 40.34,116.45
     * @return  返回格式JSONArray [{"duration":{"text":"12分钟","value":739},"distance":{"text":"19.7公里","value":19715}},{"duration":{"text":"29分钟","value":1711},"distance":{"text":"45.6公里","value":45632}}]
     */
    @Override
    public JSONArray getDistanceAndDuration(String fromZb, String toZb) {

        RestTemplate restTemplate = new RestTemplate();
        Map<String,Object> map = new HashMap<>();
        map.put("origins",fromZb);
        map.put("destinations",toZb);
        map.put("tactics","13");
        map.put("ak", bootdoConfig.getBaiduMapApiKey());

        String res = restTemplate.getForObject("http://api.map.baidu.com/routematrix/v2/driving?output=json&origins={origins}&destinations={destinations}&tactics={tactics}&ak={ak}",String.class,map);

        JSONObject jsonObject =JSONObject.parseObject(res);
        if(jsonObject.get("status").equals(0)){
            JSONArray jsonArray =  jsonObject.getJSONArray("result");
            return jsonArray;
        }
        return null;
    }

    @Override
    public JSONObject getWeather(String district) {

        RestTemplate restTemplate = new RestTemplate();
        Map<String,Object> map = new HashMap<>();
        map.put("district_id",district);
        map.put("ak", bootdoConfig.getBaiduMapApiKey());

        String res = restTemplate.getForObject("http://api.map.baidu.com/weather/v1/?district_id={district_id}&data_type=all&ak={ak}",String.class,map);
        JSONObject jsonObject =JSONObject.parseObject(res);
        if(jsonObject.get("status").equals(0)){
            return jsonObject.getJSONObject("result");
        }
        return null;
    }

    /**
     * @Description:地址补全
     * @Param: [addrName, city]
     * @return: com.alibaba.fastjson.JSONArray
     * @Author: yanyu
     * @Date: 2021/1/29
     */
    @Override
    public JSONArray getMapAddrByName(String addrName,String city) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String,Object> map = new HashMap<>();
        map.put("addrName",addrName);
        map.put("city",city);
        map.put("ak", bootdoConfig.getBaiduMapApiKey());

        String res = restTemplate.getForObject("http://api.map.baidu.com/place/v2/suggestion?query={addrName}&region={city}&city_limit=true&output=json&ak={ak}",String.class,map);

        JSONObject jsonObject =JSONObject.parseObject(res);
        if(jsonObject.get("status").equals(0)){
            JSONArray jsonArray =  jsonObject.getJSONArray("result");
            String jsonString=jsonArray.toJSONString().replaceAll("address","value");
            return JSONArray.parseArray(jsonString);
        }
        return null;
    }


    /**
     * @Description 路线推荐_百度api
     * @Param: [addrName, city]
     * @return: com.alibaba.fastjson.JSONArray
     * @Author: shilei
     * @Date: 2021/1/31
     */
    @Override
    public Object routeRecommendationBaidu(String fromZb, String toZb) {

        RestTemplate restTemplate = new RestTemplate();
        Map<String,Object> map = new HashMap<>();
        map.put("origin",fromZb);
        map.put("destination",toZb);
        map.put("ak", bootdoConfig.getBaiduMapApiKey());
        String res = restTemplate.getForObject("http://api.map.baidu.com/directionlite/v1/driving?origin={origin}&destination={destination}&ak={ak}",String.class,map);
        JSONObject jsonObject =JSONObject.parseObject(res);
        if(jsonObject.get("status").equals(0)){
            Object obj =  jsonObject.get("result");
            return obj;
        }
        return null;
    }


    /**
     * @Description 路线推荐_高德api
     * @Param: [addrName, city]
     * @return: com.alibaba.fastjson.JSONArray
     * @Author: shilei
     * @Date: 2021/1/31
     */
    @Override
    public Object routeRecommendationGaode(String fromZb, String toZb) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String,Object> map = new HashMap<>();
        map.put("origin",fromZb);
        map.put("destination",toZb);
        map.put("avoidpolygons",getBrqy());
        map.put("key",bootdoConfig.getGaodeMapApiKey());
        String res = restTemplate.getForObject("https://restapi.amap.com/v4/direction/truck?origin={origin}&destination={destination}&avoidpolygons={avoidpolygons}&key={key}&size=4",String.class,map);
        JSONObject jsonObject =JSONObject.parseObject(res);
        if(jsonObject.get("errcode").equals(0)){
            Object obj =  jsonObject.get("data");
            return decompose(obj);
        }
        return null;
    }

    //解析高德地图货车路径规划接口返回的坐标点
    public JSONObject decompose(Object obj){
        JSONObject data = JSON.parseObject(obj.toString());
        JSONObject roult = JSON.parseObject(data.get("route").toString());
        JSONArray paths = JSONArray.parseArray(roult.get("paths").toString());
        JSONArray newPaths = new JSONArray();
        for(Object pathObj:paths){
            String allPolylineStr = "";
            JSONObject path = JSON.parseObject(pathObj.toString());
            JSONArray steps = JSONArray.parseArray(path.get("steps").toString());
            JSONArray newSteps = new JSONArray();
            for(Object stepObj:steps){
                JSONObject step = JSON.parseObject(stepObj.toString());
                String polylineStr = step.get("polyline").toString();
                if("".equals(allPolylineStr)){
                    allPolylineStr += polylineStr;
                }else{
                    allPolylineStr += ";"+polylineStr;
                }
//                step.put("polyline",polylineStr);
//                newSteps.add(step);
            }
            path.put("allPolylineStr",changeCoordinate(allPolylineStr));
            newPaths.add(path);
        }
        roult.put("paths",newPaths);
        data.put("route",roult);
        return  data;
    }

//    public String changeCoordinate(String polylineStr){
//        String retStr = "";
//        String[] coordinates = polylineStr.split(";");
//        for(String coordinate:coordinates){
//            double[] gps = LngLonUtil.gcj02_To_Bd09(coordinate);
//            String zbd = gps[1]+","+gps[0];  //经度,纬度  高德格式
//            //String zbd = gps[1]+","+gps[0];  //纬度,经度  百度格式
//            if("".equals(retStr)){
//                retStr += zbd;
//            }else{
//                retStr += ";"+zbd;
//            }
//        }
//        return retStr;
//    }


    //将高德货车路径规划的坐标点转化为百度坐标
    public String changeCoordinate(String allPolylineStr){
        String allPolylineStrZh = "";
        String allPolylineStr40 = "";
        String[] allPolylines = allPolylineStr.split(";");
        for(int i=0;i<allPolylines.length;i++){
            if("".equals(allPolylineStr40)){
                allPolylineStr40 += allPolylines[i];
            }else{
                allPolylineStr40 += ";"+allPolylines[i];
            }
            if(i!=0 && (i+1)%40==0){
                if("".equals(allPolylineStrZh)){
                    allPolylineStrZh += gaodeZbToBaiduZb(allPolylineStr40);//转化方法(allPolylineStr40);
                }else{
                    allPolylineStrZh += ";"+gaodeZbToBaiduZb(allPolylineStr40);//转化方法(allPolylineStr40);
                }
                allPolylineStr40 = "";
            }
        }
        allPolylineStrZh += ";"+gaodeZbToBaiduZb(allPolylineStr40);//转化方法(allPolylineStr40);
        return allPolylineStrZh;
    }




    /**
     * 将百度坐标转化为高德坐标
     * @param locations  格式 经度,纬度|经度,纬度  116.481499,39.990475|116.481499,39.99037
     * @return
     */
    @Override
    public JSONObject baiduZbToGaodeZb(String locations){
        RestTemplate restTemplate = new RestTemplate();
        Map<String,Object> map = new HashMap<>();
        map.put("locations",locations);
        map.put("key", bootdoConfig.getGaodeMapApiKey());
        String res = restTemplate.getForObject("http://restapi.amap.com/v3/assistant/coordinate/convert?key={key}&locations={locations}&coordsys=baidu",String.class,map);
        JSONObject jsonObject =JSONObject.parseObject(res);
        return jsonObject;
    }


    /**
     * 将高德坐标转化为百度坐标
     * @param locations  格式 经度,纬度;经度,纬度  116.481499,39.990475;116.481499,39.99037
     * @return 字符串 格式：经度,纬度;经度,纬度  116.481499,39.990475;116.481499,39.99037
     */
    @Override
    public String gaodeZbToBaiduZb(String locations) {
        String retStr = "";
        RestTemplate restTemplate = new RestTemplate();
        Map<String,Object> map = new HashMap<>();
        map.put("coords",locations);
        map.put("ak", bootdoConfig.getBaiduMapApiKey());
        String res = restTemplate.getForObject("http://api.map.baidu.com/geoconv/v1/?coords={coords}&from=3&to=5&ak={ak}",String.class,map);
        JSONObject jsonObject =JSONObject.parseObject(res);
        if("0".equals(jsonObject.get("status").toString())){
            JSONArray allZb = JSONArray.parseArray(jsonObject.get("result").toString());
            for(Object obj:allZb){
                JSONObject zb = JSON.parseObject(obj.toString());
                if("".equals(retStr)){
                    retStr += zb.get("x")+","+zb.get("y");
                }else{
                    retStr += ";"+zb.get("x")+","+zb.get("y");
                }
            }
            return retStr;
        }else{
            return retStr;
        }

    }


    /**
     * 路线推荐高德轿车
     * @param fromZb
     * @param toZb
     * @return
     */
    @Override
    public Object routeRecommendationGaodeJc(String fromZb, String toZb) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String,Object> map = new HashMap<>();
        map.put("origin",fromZb);
        map.put("destination",toZb);
        map.put("avoidpolygons",getBrqy());
        map.put("key",bootdoConfig.getGaodeMapApiKey());
        String res = restTemplate.getForObject("https://restapi.amap.com/v3/direction/driving?origin={origin}&destination={destination}&avoidpolygons={avoidpolygons}&key={key}&strategy=0",String.class,map);
        JSONObject jsonObject =JSONObject.parseObject(res);
        if(jsonObject.get("status").equals("1")){
            Object obj =  jsonObject;
            return decompose(obj);
        }
        return null;
    }





    /**
     * 查询所有启用的避让区域
     * @return
     */
    public String getBrqy(){
        String brqyStr = "";
        Map<String,Object> map = new HashMap<>();
        map.put("status","0");
        List<BrqyDO> brqyList = brqyService.list(map);
        for(BrqyDO brqy:brqyList){
            String jwdZb = "";
            String[] zbs = brqy.getCoordinatesGaode().split(";");
            for(String zb:zbs){
                String[] aa = zb.split(",");
                if(jwdZb.equals("")){
                    jwdZb += aa[1].substring(0,9)+","+aa[0].substring(0,9);
                }else{
                    jwdZb += ";"+aa[1].substring(0,9)+","+aa[0].substring(0,9);
                }
            }
            if(brqyStr.equals("")){
                brqyStr += jwdZb;
            }else{
                brqyStr += "|"+jwdZb;
            }
        }
        return brqyStr;
    }


    @Override
    public String getGdRegionCenterCoordinates(String adcode) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String,Object> map = new HashMap<>();
        map.put("extensions", "all");
        map.put("subdistrict", 0);
        if ("371301".equals(adcode)) {
            adcode = "371300";
        }
        map.put("keywords", adcode);
        Set<String> gaodeKeys = getGaodeKeyStrings();
        Iterator<String> it = gaodeKeys.iterator();
        while (it.hasNext()) {
            String gaodeKey = it.next();
            map.put("ak", gaodeKey);
            String res = restTemplate.getForObject(routeRecommendationGaodePrefix + "v3/config/district?keywords={keywords}&subdistrict={subdistrict}&extensions={extensions}&key={ak}",
                    String.class, map);
            JSONObject jsonObject =JSONObject.parseObject(res);
            if(jsonObject.get("status").equals("1")){
                JSONArray jsonArray =  jsonObject.getJSONArray("districts");
                if (!ObjectUtils.isEmpty(jsonArray)) {
                    Map districtMap = (Map)jsonArray.get(0);
                    String center = (String)districtMap.get("center");
                    return center;
                }
            }
        }

        return null;
    }


    /**
     * 获取高德的key的集合
     * @return
     */
    private Set<String> getGaodeKeyStrings() {
        Set<String> stringSet = redisManager.getSetAllElement("resource:gaodekey");
        List<String> gaodeKeyList = new ArrayList<>();
        if (ObjectUtils.isEmpty(stringSet)) {
            GaodeKeyDO gaodeKeyParam = new GaodeKeyDO();
            gaodeKeyParam.setType(1);
            gaodeKeyParam.setStatus(0);
            gaodeKeyList = gaodeKeyDao.queryListUseable(gaodeKeyParam);
            if (!gaodeKeyList.isEmpty()) {
                redisManager.setAddElement("resource:gaodekey", gaodeKeyList);
            }
        }
        gaodeKeyList.add(bootdoConfig.getGaodeMapApiKey());
        stringSet.addAll(gaodeKeyList);
        return stringSet;
    }




}
