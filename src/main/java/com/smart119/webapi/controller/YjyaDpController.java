package com.smart119.webapi.controller;

import com.smart119.common.utils.R;
import com.smart119.jczy.domain.YjyaClDO;
import com.smart119.jczy.domain.YjyaDO;
import com.smart119.jczy.service.YjyaClService;
import com.smart119.jczy.service.YjyaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName : YjyaDpController
 * @Description : 应急预案调派Controller
 * @Author : Liangsl
 * @Date: 2021-01-29 16:11
 */
@RestController
@Api(value = "（接警端）应急预案调派API", description = "（接警端）应急预案调派API")
@RequestMapping("/webapi/yjya")
public class YjyaDpController {
    @Autowired
    private YjyaService yjyaService;

    @Autowired
    private YjyaClService yjyaClService;

    /**
     * 查询应急预案
     * @return
     */
    @ApiOperation("查询应急预案")
    @GetMapping("/getYjyaDpList")
    public R getYjyaDpList(@ApiParam(value = "应急预案类型") @RequestParam(value="yjyalxlbdm",required = false)String yjyalxlbdm,@ApiParam(value = "警情id，没有穿空字符串") @RequestParam(value="jqTywysbm",required = true)String jqTywysbm){
        Map param = new HashMap();
        param.put("yjyalxlbdm",yjyalxlbdm);
        param.put("jqTywysbm",jqTywysbm);
        List<YjyaDO> yjyaDOList = yjyaService.getYjya4Dp(param);
        List<YjyaClDO> yjyaClDOList = yjyaClService.list(null);
        List<YjyaDO> result = new ArrayList<>();
        for(YjyaDO yjyaDO:yjyaDOList){
            List<YjyaClDO> yjyaClDOList1 = yjyaClDOList.parallelStream().filter(o->o.getYjyaTywysbm().equals(yjyaDO.getYjyaTywysbm())).collect(Collectors.toList());
            if(yjyaClDOList1!=null && yjyaClDOList1.size()>0){
                yjyaDO.setYjyaClDOList(yjyaClDOList1);
                result.add(yjyaDO);
            }
        }
        return R.ok(result);
    }
}
