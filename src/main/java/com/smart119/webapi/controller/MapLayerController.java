package com.smart119.webapi.controller;

import com.smart119.common.config.Constant;
import com.smart119.common.utils.R;
import com.smart119.jczy.domain.*;
import com.smart119.jczy.service.*;
import com.smart119.system.domain.DeptDO;
import com.smart119.system.service.DeptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName : MapController
 * @Description : 图层Controller
 * @Author : Liangsl
 * @Date: 2021-01-29 11:13
 */
@Api(value = "（接警端和中队端）图层API", description = "（接警端和中队端）图层API")
@RestController
@RequestMapping("/webapi/maplayer")
public class MapLayerController {

    @Autowired
    private DeptService deptService;

    @Autowired
    private XhsService xhsService;

    @Autowired
    private XfshService xfshService;

    @Autowired
    private XfscService xfscService;

    @Autowired
    private WxxfzService wxxfzService;

    @Autowired
    private XfmtService xfmtService;

    @Autowired
    private TrsyService trsyService;

    /**
     * 组织机构-地图
     * @param jd
     * @param wd
     * @param distance
     * @return
     */
    @ApiOperation("查询执勤实力接口")
    @GetMapping("/getDept4Map")
    public R getDept4Map(@ApiParam(value = "警情经度") @RequestParam(value="jd",required = false)Double jd, @ApiParam(value = "警情纬度") @RequestParam(value="wd",required = false)Double wd, @ApiParam(value = "半径") @RequestParam(value="distance",required = false)Double distance){
        List<DeptDO> deptDOList = null;
        if(jd!=null && wd!=null && distance!=null){
            deptDOList =  deptService.getDeptByRange(jd,wd,distance);
        }else{
            deptDOList = deptService.getDeptByXFJYJGXZDM(Constant.DEPT_JYZ_CODE_PREFIX);
        }
        return R.ok(deptDOList);
    }

    /**
     * 消防水鹤-地图
     * @param jd
     * @param wd
     * @param distance
     * @return
     */
    @ApiOperation("查询消防水鹤接口")
    @GetMapping("/getXfsh4Map")
    public R getXfsh4Map(@ApiParam(value = "警情经度") @RequestParam(value="jd",required = false)Double jd, @ApiParam(value = "警情纬度") @RequestParam(value="wd",required = false)Double wd, @ApiParam(value = "半径") @RequestParam(value="distance",required = false)Double distance){
        List<XfshDO> xfshDOList = null;
        if(jd!=null && wd!=null && distance!=null){
            xfshDOList =  xfshService.getXfshByRange(jd,wd,distance);
        }else{
            xfshDOList = xfshService.list(null);
        }
        return R.ok(xfshDOList);
    }

    /**
     * 消火栓-地图
     * @param jd
     * @param wd
     * @param distance
     * @return
     */
    @ApiOperation("查询消火栓接口")
    @GetMapping("/getXhs4Map")
    public R getXhs4Map(@ApiParam(value = "警情经度") @RequestParam(value="jd",required = false)Double jd, @ApiParam(value = "警情纬度") @RequestParam(value="wd",required = false)Double wd, @ApiParam(value = "半径") @RequestParam(value="distance",required = false)Double distance){
        List<XhsDO> xfshDOList = null;
        if(jd!=null && wd!=null && distance!=null){
            xfshDOList =  xhsService.getXhsListByRange(jd,wd,distance);
        }else{
            xfshDOList = xhsService.list(null);
        }
        return R.ok(xfshDOList);
    }

    /**
     * 消防水池-地图
     * @param jd
     * @param wd
     * @param distance
     * @return
     */
    @ApiOperation("查询消防水池接口")
    @GetMapping("/getXfsc4Map")
    public R getXfsc4Map(@ApiParam(value = "警情经度") @RequestParam(value="jd",required = false)Double jd, @ApiParam(value = "警情纬度") @RequestParam(value="wd",required = false)Double wd, @ApiParam(value = "半径") @RequestParam(value="distance",required = false)Double distance){
        List<XfscDO> xfscDOList = null;
        if(jd!=null && wd!=null && distance!=null){
            xfscDOList =  xfscService.getXfscByRange(jd,wd,distance);
        }else{
            xfscDOList = xfscService.list(null);
        }
        return R.ok(xfscDOList);
    }

    /**
     * 微型消防站-地图
     * @param jd
     * @param wd
     * @param distance
     * @return
     */
    @GetMapping("/getWxxfz4Map")
    @ApiOperation("查询微型消防站接口")
    public R getWxxfz4Map(@ApiParam(value = "警情经度") @RequestParam(value="jd",required = false)Double jd, @ApiParam(value = "警情纬度") @RequestParam(value="wd",required = false)Double wd, @ApiParam(value = "半径") @RequestParam(value="distance",required = false)Double distance){
        List<WxxfzDO> wxxfzDOList = null;
        if(jd!=null && wd!=null && distance!=null){
            wxxfzDOList =  wxxfzService.getWxxfzByRange(jd,wd,distance);
        }else{
            wxxfzDOList = wxxfzService.list(null);
        }
        return R.ok(wxxfzDOList);
    }

    /**
     * 消防码头-地图
     * @param jd
     * @param wd
     * @param distance
     * @return
     */
    @GetMapping("/getXfmt4Map")
    @ApiOperation("查询消防码头接口")
    public R getXfmt4Map(@ApiParam(value = "警情经度") @RequestParam(value="jd",required = false)Double jd, @ApiParam(value = "警情纬度") @RequestParam(value="wd",required = false)Double wd, @ApiParam(value = "半径") @RequestParam(value="distance",required = false)Double distance){
        List<XfmtDO> xfmtDOList = null;
        if(jd!=null && wd!=null && distance!=null){
            xfmtDOList =  xfmtService.getXfmtByRange(jd,wd,distance);
        }else{
            xfmtDOList = xfmtService.list(null);
        }
        return R.ok(xfmtDOList);
    }

    /**
     * 天然水源-地图
     * @param jd
     * @param wd
     * @param distance
     * @return
     */
    @GetMapping("/getTrsy4Map")
    @ApiOperation("查询天然水源接口")
    public R getTrsy4Map(@ApiParam(value = "警情经度") @RequestParam(value="jd",required = false)Double jd, @ApiParam(value = "警情纬度") @RequestParam(value="wd",required = false)Double wd, @ApiParam(value = "半径") @RequestParam(value="distance",required = false)Double distance){
        List<TrsyDO> trsyDOList = null;
        if(jd!=null && wd!=null && distance!=null){
            trsyDOList =  trsyService.getTrsyByRange(jd,wd,distance);
        }else{
            trsyDOList = trsyService.list(null);
        }
        return R.ok(trsyDOList);
    }

    /**
     * 水源统计-地图
     * @param jd
     * @param wd
     * @param distance
     * @return
     */
    @GetMapping("/waterStatistics")
    @ApiOperation("查询水源统计接口")
    public R waterStatistics(@ApiParam(value = "警情经度") @RequestParam(value="jd",required = true)Double jd, @ApiParam(value = "警情纬度") @RequestParam(value="wd",required = true)Double wd, @ApiParam(value = "半径") @RequestParam(value="distance",required = true)Double distance){
        Map result = new HashMap<>();
        List<XfshDO> xfshDOList =  xfshService.getXfshByRange(jd,wd,distance);
        Map xfshMap = new HashMap();
        xfshMap.put("num",xfshDOList.size());
        xfshMap.put("ky",xfshDOList.parallelStream().filter(o->o.getSykyztlbdm().equals("2")).collect(Collectors.toList()).size());
        result.put("xfsh",xfshMap);

        List<XhsDO> xhsDOList =  xhsService.getXhsListByRange(jd,wd,distance);
        Map xhsMap = new HashMap();
        xhsMap.put("num",xhsDOList.size());
        xhsMap.put("ky",xhsDOList.parallelStream().filter(o->o.getXfsszkflydm().equals("10")).collect(Collectors.toList()).size());
        result.put("xhs",xhsMap);

        List<XfscDO> xfscDOList =  xfscService.getXfscByRange(jd,wd,distance);
        Map xfscMap = new HashMap();
        xfscMap.put("num",xfscDOList.size());
        xfscMap.put("ky",xfscDOList.parallelStream().filter(o->o.getSykyztlbdm().equals("2")).collect(Collectors.toList()).size());
        result.put("xfsc",xfscMap);

        List<XfmtDO> xfmtDOList =  xfmtService.getXfmtByRange(jd,wd,distance);
        Map xfmtMap = new HashMap();
        xfmtMap.put("num",xfmtDOList.size());
        xfmtMap.put("ky",xfmtDOList.parallelStream().filter(o->o.getSykyztlbdm().equals("2")).collect(Collectors.toList()).size());
        result.put("xfmt",xfmtMap);

        List<TrsyDO> trsyDOList =  trsyService.getTrsyByRange(jd,wd,distance);

        Map trsyMap = new HashMap();
        trsyMap.put("num",trsyDOList.size());
        trsyMap.put("ky",trsyDOList.parallelStream().filter(o->o.getSykyztlbdm().equals("2")).collect(Collectors.toList()).size());
        result.put("trsy",trsyMap);

        return R.ok(result);
    }

    /**
     * 队站统计-地图
     * @param jd
     * @param wd
     * @param distance
     * @return
     */
    @GetMapping("/dzStatistics")
    @ApiOperation("查询队站统计接口")
    public R dzStatistics(@ApiParam(value = "警情经度") @RequestParam(value="jd",required = true)Double jd, @ApiParam(value = "警情纬度") @RequestParam(value="wd",required = true)Double wd, @ApiParam(value = "半径") @RequestParam(value="distance",required = true)Double distance){
        Map result = new HashMap<>();
        List<DeptDO> deptDOList =  deptService.getDeptByRange(jd,wd,distance);
        result.put("xfdz",deptDOList.size());
        List<WxxfzDO> wxxfzDOList =  wxxfzService.getWxxfzByRange(jd,wd,distance);
        result.put("wxxfz",wxxfzDOList.size());

        return R.ok(result);
    }



}
