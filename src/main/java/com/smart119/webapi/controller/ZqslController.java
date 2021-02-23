package com.smart119.webapi.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.smart119.common.config.Constant;
import com.smart119.common.domain.Distance;
import com.smart119.common.domain.Duration;
import com.smart119.common.service.BaiduMapService;
import com.smart119.common.utils.R;
import com.smart119.jczy.domain.XfclDO;
import com.smart119.jczy.domain.XfclSxzDO;
import com.smart119.jczy.service.XfclService;
import com.smart119.jczy.service.XfclSxService;
import com.smart119.jczy.service.XfclSxzService;
import com.smart119.system.domain.DeptDO;
import com.smart119.system.service.DeptService;
import com.smart119.webapi.service.JqcjdpDzService;
import com.smart119.webapi.service.JqcjdpService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName : ZqslController
 * @Description : 执勤实力Controller
 * @Author : Liangsl
 * @Date: 2021-01-29 12:06
 */
@RestController
@Api(value = "（接警端）执勤实力API", description = "（接警端）执勤实力API")
@RequestMapping("/webapi/zqsl")
public class ZqslController {
    @Autowired
    private DeptService deptService;

    @Autowired
    private XfclService xfclService;

    @Autowired
    private XfclSxService xfclSxService;

    @Autowired
    private XfclSxzService xfclSxzService;


    @Autowired
    private BaiduMapService baiduMapService;


    @Autowired
    private JqcjdpDzService jqcjdpDzService;


    /**
     * 查询所有救援站
     * @return
     */
    @ApiOperation("查询所有救援站")
    @GetMapping("/getXfDzList")
    public R getXfDzList(){
        List<DeptDO> deptDOList = deptService.getDeptByXFJYJGXZDM(Constant.DEPT_JYZ_CODE_PREFIX);
        return R.ok(deptDOList);
    }


    /**
     * 查询所有力量
     * @return
     */
    @GetMapping("/getCars")
    @ApiOperation("查询所有力量")
    public R getCars(@ApiParam(value = "警情坐标，格式为 坐标纬度,坐标经度") @RequestParam(value="zb",required = false)String zb,@RequestParam(value="jqTywysbm",required = false)String jqTywysbm){
        List<DeptDO> resultList = new ArrayList<>();
        List<DeptDO> deptDOList = deptService.getDeptByXFJYJGXZDM(Constant.DEPT_JYZ_CODE_PREFIX);

        List<XfclDO> xfclDOList = xfclService.list(null);

        List<XfclSxzDO> xfclSxzDOList = xfclSxzService.getSxzlist();

        //已调派的车辆
        List<Map<String,Object>> dpCarList = null;
        if(jqTywysbm!=null && !jqTywysbm.equals("")){
             dpCarList = jqcjdpDzService.getDzCarListByJdId(jqTywysbm);
        }

        //消防车辆特殊属性值
        for(XfclDO xfclDO:xfclDOList){
            List<XfclSxzDO> xfclSxzDOList1 = xfclSxzDOList.parallelStream().filter(m->m.getXfclId().equals(xfclDO.getXfclTywysbm())).collect(Collectors.toList());
            if(xfclSxzDOList1!=null && xfclSxzDOList1.size()>0){
                //xfclDO.setXfclSxzDOList(xfclSxzDOList1);
                String sxStr = "";
                for(XfclSxzDO xfclSxzDO:xfclSxzDOList1){
                    sxStr+=xfclSxzDO.getClsx()+":"+xfclSxzDO.getSxz()+xfclSxzDO.getDw()+" ";
                }
                xfclDO.setSxStr(sxStr);
            }

            //已调派的车辆增加调派状态
            if(dpCarList!=null && dpCarList.size()>0){
                int flag = dpCarList.parallelStream().filter(o->o.get("XFJYCL_TYWYSBM").equals(xfclDO.getXfclTywysbm())).collect(Collectors.toList()).size();
                if(flag>0){
                    xfclDO.setDpFlag(1);
                }
            }
        }


        if(zb==null || zb.equals("")){
            for(DeptDO deptDO:deptDOList){
                List<XfclDO> clList = xfclDOList.parallelStream().filter(o->o.getXfjyjgTywysbm().equals(deptDO.getXfjyjgTywysbm())).collect(Collectors.toList());
                if(clList.size()>0){
                    deptDO.setXfclDOList(clList);
                    resultList.add(deptDO);
                }
            }
        }else{
            String jyzZbs = deptDOList.stream().map(o->o.getDqwd()+","+o.getDqjd()).collect(Collectors.joining("|"));

            JSONArray jsonArray = baiduMapService.getDistanceAndDuration(jyzZbs,zb);

            for(int i=0;i<deptDOList.size();i++){
                DeptDO deptDO = deptDOList.get(i);
                deptDO.setDistance(JSON.toJavaObject(jsonArray.getJSONObject(i).getJSONObject("distance"), Distance.class));
                deptDO.setDuration(JSON.toJavaObject(jsonArray.getJSONObject(i).getJSONObject("duration"), Duration.class));
                List<XfclDO> clList = xfclDOList.parallelStream().filter(o->o.getXfjyjgTywysbm().equals(deptDO.getXfjyjgTywysbm())).collect(Collectors.toList());
                if(clList.size()>0){
                    deptDO.setXfclDOList(clList);
                    resultList.add(deptDO);
                }
            }
            resultList = resultList.stream().sorted(Comparator.comparing(o->o.getDistance().getValue())).collect(Collectors.toList());
        }

        return R.ok(resultList);
    }


}
