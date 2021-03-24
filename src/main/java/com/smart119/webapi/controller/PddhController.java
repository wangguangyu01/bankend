package com.smart119.webapi.controller;

import com.smart119.common.utils.PageUtils;
import com.smart119.jczy.domain.BlackListDO;
import io.swagger.annotations.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 *变成编队管理接
 *
 * @author Jiyu Yang
 * @email yangjiyu@sz000673.com
 * @date 2021-01-28 12:05:01
 */
@Api(value = "排队电话管理", description = "接警端排队电话管理API")
@Controller
@RequestMapping("/webapi/pddh")
public class PddhController {


    @ApiOperation("排队电话查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "limit", value = "条数", required = true, dataType = "String",dataTypeClass = String.class,paramType = "header"),
            @ApiImplicitParam(name = "offset", value = "页数", required = true, dataType = "String",dataTypeClass = String.class,paramType = "header")
    })
    @ResponseBody
    @GetMapping("/list")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "查询成功",response= BlackListDO.class)})
    public PageUtils list(@RequestParam Map<String, Object> params){
        //查询列表数据
        List<Map<String,Object>> pddhist = new ArrayList<>();
        Map<String,Object> pdMap1 = new LinkedHashMap<>();
        pdMap1.put("dhhm","18646151892");
        pdMap1.put("pdsh","39秒");
        Map<String,Object> pdMap2 = new LinkedHashMap<>();
        pdMap2.put("dhhm","18646132232");
        pdMap2.put("pdsh","19秒");
        pddhist.add(pdMap1);
        pddhist.add(pdMap2);
        PageUtils pageUtils = new PageUtils(pddhist,2);
        return pageUtils;
    }

    @ApiOperation("早释放电话查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "limit", value = "条数", required = true, dataType = "String", dataTypeClass = String.class,paramType = "header"),
            @ApiImplicitParam(name = "offset", value = "页数", required = true, dataType = "String",dataTypeClass = String.class,paramType = "header")
    })
    @ResponseBody
    @GetMapping("/zslist")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "查询成功",response= BlackListDO.class)})
    public PageUtils zslist(@RequestParam Map<String, Object> params){
        //查询列表数据
        List<Map<String,Object>> pddhist = new ArrayList<>();
        Map<String,Object> pdMap1 = new LinkedHashMap<>();
        pdMap1.put("zshm","18654585245");
        pdMap1.put("hrsj","2021-2-7 :19:19:22");
        Map<String,Object> pdMap2 = new LinkedHashMap<>();
        pdMap2.put("zshm","18215485452");
        pdMap2.put("hrsj","2021-2-7 :19:20:12");
        Map<String,Object> pdMap3 = new LinkedHashMap<>();
        pdMap3.put("zshm","13136521574");
        pdMap3.put("hrsj","2021-2-7 :19:23:42");
        pddhist.add(pdMap1);
        pddhist.add(pdMap2);
        pddhist.add(pdMap3);
        PageUtils pageUtils = new PageUtils(pddhist,3);
        return pageUtils;
    }

}
