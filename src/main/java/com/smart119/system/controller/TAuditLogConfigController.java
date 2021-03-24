package com.smart119.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.smart119.common.utils.PageUtils;
import com.smart119.common.utils.ParamChangeUtil;
import com.smart119.common.utils.R;
import com.smart119.system.domain.TAuditLogConfigEntity;
import com.smart119.system.service.TAuditLogConfigService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


/**
 * 审计日志配置表
 *
 */
@RestController
@RequestMapping("sys/tauditlogconfig")
@Api(value = "sys/tauditlogconfig", tags = "审计日志配置表管理 API")
@Slf4j
public class TAuditLogConfigController{
    @Autowired
    private TAuditLogConfigService tAuditLogConfigService;


    @PostMapping("/list")
    @ApiOperation(value = "查询审计日志配置表列表")
    @RequiresPermissions("sys:tauditlogconfig:tauditlogconfig")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "limit", value = "条数", dataType = "String",dataTypeClass = String.class,required = true,paramType = "query"),
            @ApiImplicitParam(name = "offset", value = "页数", required = true , dataType = "String",dataTypeClass = String.class,paramType = "query"),
            @ApiImplicitParam(name = "params", value = "json格式的查询参数", dataType = "String",dataTypeClass = String.class,paramType = "query")
    })
    public R list(@RequestParam Map<String, Object> params){
        //查询列表数据
        QueryWrapper queryWrapper = new QueryWrapper();
        Object sort = params.get("sort");
        if(!ObjectUtils.isEmpty(sort)){
            Object order = params.get("order");
            if(!ObjectUtils.isEmpty(order) && "desc".equals(order.toString().toLowerCase())){
                queryWrapper.orderByDesc(sort.toString());
            }else{
                queryWrapper.orderByAsc(sort.toString());
            }
        }
        Object offset = params.get("offset");
        Object limit = params.get("limit");
        if(!ObjectUtils.isEmpty(offset) && !ObjectUtils.isEmpty(limit)){
            queryWrapper.last("limit " + offset + "," + limit);
        }
        params.remove("sort");
        params.remove("order");
        params.remove("offset");
        params.remove("limit");
        params.forEach((s, o) -> {
            queryWrapper.eq(ParamChangeUtil.humpToLine2(s), o);
        });
        PageUtils pageUtils = new PageUtils(tAuditLogConfigService.list(queryWrapper), tAuditLogConfigService.count(queryWrapper));
        return R.ok(pageUtils);
    }


    /**
    * 信息
    */
    @ApiOperation("查询审计日志配置表详情")
    @GetMapping("/info/{id}")
    @RequiresPermissions("sys:tauditlogconfig:info")
    public R info(@PathVariable("id") @ApiParam(name = "id", value = "审计日志配置表id", required = true) Long id){
        log.debug("info start:id={}",id);
        TAuditLogConfigEntity tAuditLogConfig = tAuditLogConfigService.getById(id);
        return R.ok().setOnlyExtraNote(id);
    }

    /**
      * 保存
      */
    @ApiOperation("保存审计日志配置表详情")
    @PostMapping("/add")
    @RequiresPermissions("sys:tauditlogconfig:add")
    public R add(@RequestBody @ApiParam(name = "审计日志配置表对象", value = "传入json格式", required = true) TAuditLogConfigEntity tAuditLogConfig){
        log.debug("save start:TAuditLogConfigEntity={}",tAuditLogConfig);
        tAuditLogConfigService.save(tAuditLogConfig);
        return R.ok().setOnlyExtraNote(tAuditLogConfig.getId());
    }

    /**
     * 修改
     */
    @ApiOperation("修改审计日志配置表详情")
    @PostMapping("/edit")
    @RequiresPermissions("sys:tauditlogconfig:edit")
    public R edit(@RequestBody @ApiParam(name = "审计日志配置表对象", value = "传入json格式", required = true) TAuditLogConfigEntity tAuditLogConfig){
        log.debug("update start:TAuditLogConfigEntity={}",tAuditLogConfig);
        tAuditLogConfigService.updateById(tAuditLogConfig);
        return R.ok().setOnlyExtraNote(tAuditLogConfig.getId());
    }


}
