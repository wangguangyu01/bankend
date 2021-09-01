package com.smart119.webapi.controller;


import com.alibaba.fastjson.JSON;
import com.smart119.common.controller.BaseController;
import com.smart119.common.utils.PageUtils;
import com.smart119.common.utils.Query;
import com.smart119.common.utils.R;
import com.smart119.jczy.domain.FzjcDO;
import com.smart119.jczy.service.FzjcService;
import com.smart119.system.mq.RabbitMQClient;
import com.smart119.webapi.domain.FzjctsDO;
import com.smart119.webapi.domain.JbxxDO;
import com.smart119.webapi.service.FzjctsService;
import com.smart119.webapi.service.JbxxService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

@Controller
@RequestMapping("/webapi/jjtjxxll")
@Api(value = "辅助决策API", description = "辅助决策API")
public class jqtjxxController extends BaseController{

    @Resource
    private FzjcService fzjcService;

    @Resource
    private FzjctsService fzjctsService;

    //推送
    @Autowired
    private RabbitMQClient rabbitMQClient;

    //推送
    @Autowired
    private JbxxService jbxxService;

    /////跳转页面
    @GetMapping()
    //@RequiresPermissions("webapi:fzjc:jqtjlist")
    String hourlist(){
        return "jczy/report/hourlist";
    }
    @GetMapping("hourtjlist")
        //@RequiresPermissions("webapi:fzjc:jqtjlist")
    String hourtjlist(){
        return "jczy/report/hourtjlist";
    }
    //值班信息统计信息
    @GetMapping("/getZbFile")
    @ResponseBody
    public Map<String,Object> getZbFile(String startDate,String endDate) throws IOException, ParseException {
        Map<String,Object>map=new HashMap<>();
        map.put("startDate",startDate);
        map.put("endDate",endDate);
        map.put("zd2","");
        map.put("zd3","");
        map.put("zd4","");
        map.put("zd5","");
        Map<String,Object>mapp=fzjctsService.getZBbaotit(map.get("startDate").toString(),map.get("endDate").toString(),map);
        map.put("zd1",mapp.get("xfjcj").toString());
        map.put("time",mapp.get("time").toString());
        return  map;
    }
    @GetMapping("/getFile")
    @ResponseBody
    public String getFile(String time,String startDate,String endDate) throws IOException, ParseException {
        Map<String,Object>map=new HashMap<>();
        //map.put("time",time);
        map.put("startDate",startDate);
        map.put("endDate",endDate);
        map.put("zd2","");
        map.put("zd3","");
        map.put("zd4","");
        map.put("zd5","");
        Map<String,Object>mapp=fzjctsService.getZBbaotit(map.get("startDate").toString(),map.get("endDate").toString(),map);
        map.put("zd1",mapp.get("xfjcj").toString());
        map.put("time",mapp.get("time").toString());
        return  fzjctsService.uplodadRepFile(map,"report.ftl");
    }
    @GetMapping("/getFileExle")
    @ResponseBody
    public void  getFileExle(String time1,String time2,HttpServletResponse response, HttpServletRequest request) throws IOException {
        Map<String,Object>map=new HashMap<>();
        map.put("time","2021-08-02--2021-08-03");
        map.put("startDate","2021-06-02");
        map.put("enDate","2021-08-03");
        map.put("org","临沂消防支队");
        map.put("title","标题");
        map.put("zd1","字段1");
        map.put("zd2","字段2");
        map.put("zd3","");
        map.put("zd4","");
        map.put("zd5","");
        map.put("zd6","");
        map.put("zd7","");
        map.put("zd8","");
        map.put("zd9","");
        map.put("zd10","");
        map.put("zd11","");
        map.put("zd12","");
        map.put("zd13","");
        map.put("zd14","");
        map.put("zd15","");
        map.put("zd16","");
        map.put("time1",time1);
        map.put("time2",time2);
          fzjctsService.uplodadRepFileExle(map, response, request);
    }

    //警情时段分布
    @GetMapping("/getHourList")
    @ResponseBody
    public Map<String,Object> getHourList(String startDate,String endDate) throws IOException {
        Map<String,Object>map=new HashMap<>();
        map.put("startDate",startDate);
        map.put("endDate",endDate);
        map.put("rows",fzjctsService.getHourList(map));
        return  map;
    }
    @GetMapping("/getHourFile")
    @ResponseBody
    public String getHourFile(String time,String startDate,String endDate,String org,String gettime) throws IOException {
        Map<String,Object>map=new HashMap<>();
        map.put("time",time);
        map.put("gettime",gettime);
        map.put("startDate",startDate);
        map.put("endDate",endDate);
        map.put("org",org);
        map.put("list",fzjctsService.getHourList(map));
        return  fzjctsService.uplodadRepFile(map,"report3.ftl");
    }
    @GetMapping("/getUpload")
    @ResponseBody
    public void downloadTemplate(HttpServletResponse response, HttpServletRequest request,String filename, String templeteName) throws IOException {
         fzjctsService.downloadTemplate(response,request,filename,templeteName);
    }
}