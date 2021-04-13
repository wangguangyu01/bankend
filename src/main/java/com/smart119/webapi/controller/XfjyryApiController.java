package com.smart119.webapi.controller;

import com.smart119.common.utils.PageUtils;
import com.smart119.common.utils.Query;
import com.smart119.jczy.domain.XfjyryDO;
import com.smart119.jczy.service.XfjyryService;
import com.smart119.system.domain.DeptDO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName : XfjyryController
 * @Description : 消防救援人员api
 * @Author : Liangsl
 * @Date: 2021-02-02 20:42
 */
@RestController
@Validated
@Api(value = "消防救援人员API", description = "消防救援人员API")
public class XfjyryApiController {
    @Autowired
    private XfjyryService xfjyryService;

    @ApiOperation("查询消防救援人员")
    @GetMapping("/webapi/getXfjyryList")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<XfjyryDO> xfjyryList = xfjyryService.list(query);
        int total = xfjyryService.count(query);
        PageUtils pageUtils = new PageUtils(xfjyryList, total);
        return pageUtils;
    }
}
