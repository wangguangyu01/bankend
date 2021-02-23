package com.smart119.webapi.controller;

import com.smart119.common.utils.R;
import com.smart119.jczy.domain.XfclDO;
import com.smart119.jczy.domain.XfclSxzDO;
import com.smart119.jczy.service.XfclService;
import com.smart119.jczy.service.XfclSxzService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName : XfclApiController
 * @Description : 消防车辆api
 * @Author : Liangsl
 * @Date: 2021-01-30 15:08
 */
@RestController
@Api(value = "（中队端）消防车辆API", description = "（中队端）消防车辆API")
@RequestMapping("/webapi/xfcl")
public class XfclApiController {
    @Autowired
    private XfclService xfclService;
    @Autowired
    private XfclSxzService xfclSxzService;

    @ApiOperation("查询救援站车辆")
    @GetMapping("getXfclByDept")
    public R getXfclByDept(@ApiParam(value = "消防救援机构_通用唯一识别码") @RequestParam(value="xfjyjgTywysbm",required = true)String xfjyjgTywysbm){
        Map param = new HashMap();
        param.put("xfjyjgTywysbm",xfjyjgTywysbm);
        List<XfclDO> xfclDOList = xfclService.list(param);
        List<XfclSxzDO> xfclSxzDOList = xfclSxzService.getSxzlist();
        for(XfclDO xfclDO:xfclDOList){
            List<XfclSxzDO> xfclSxzDOList1 =  xfclSxzDOList.stream().filter(o->o.getXfclId().equals(xfclDO.getXfclTywysbm())).collect(Collectors.toList());
            xfclDO.setXfclSxzDOList(xfclSxzDOList1);
        }
        return R.ok(xfclDOList);
    }
}
