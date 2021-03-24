package com.smart119.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.smart119.common.utils.PageUtils;
import com.smart119.common.utils.R;
import com.smart119.common.utils.StringUtils;
import com.smart119.system.domain.TAuditLogConfigEntity;
import com.smart119.system.service.TAuditLogConfigService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


/**
 * 审计日志配置表
 *
 */
@Controller
@RequestMapping("sys/tauditlogconfig")
@Api(value = "sys/tauditlogconfig", tags = "审计日志配置表管理 API")
@Slf4j
public class TAuditLogConfigController{
    @Autowired
    private TAuditLogConfigService tAuditLogConfigService;

    @GetMapping
    @RequiresPermissions("sys:tauditlogconfig:tauditlogconfig")
    public String tauditlogconfig(){
        return "system/auditLogConfig/auditLogConfig";
    }

    @GetMapping("/list")
    @ApiOperation(value = "查询审计日志配置表列表")
    @RequiresPermissions("sys:tauditlogconfig:tauditlogconfig")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "limit", value = "条数", required = true, dataType = "String",dataTypeClass = String.class,paramType = "query"),
            @ApiImplicitParam(name = "offset", value = "页数", required = true, dataType = "String",dataTypeClass = String.class,paramType = "query"),
            @ApiImplicitParam(name = "sort", value = "排序", dataType = "String",dataTypeClass = String.class,paramType = "query"),
            @ApiImplicitParam(name = "keyword", value = "检索关键词",dataType = "String",dataTypeClass = String.class,paramType = "query")
    })
    @ResponseBody
    public PageUtils list(@RequestParam Map<String, Object> params){
        QueryWrapper<TAuditLogConfigEntity> queryWrapper = new QueryWrapper();
        Object sort = params.get("sort");
        if(!ObjectUtils.isEmpty(sort)){
            Object order = params.get("order");
            if(!ObjectUtils.isEmpty(order) && "desc".equals(order.toString().toLowerCase())){
                queryWrapper.orderByDesc(sort.toString());
            }else{
                queryWrapper.orderByAsc(sort.toString());
            }
        } else {
            //默认排序字段
            queryWrapper.orderByDesc("create_time");
        }
        Object offsetObj = params.get("offset");
        Object limitObj = params.get("limit");
        int offset;
        if(ObjectUtils.isEmpty(offsetObj)){
            offset = 0;
        }else{
            offset = Integer.valueOf(offsetObj.toString());
        }
        int limit;
        if(ObjectUtils.isEmpty(limitObj)){
            limit = 10;
        }else{
            limit = Integer.valueOf(limitObj.toString());
        }
        queryWrapper.last("limit " + offset + "," + limit);
        //remove后 切面记录审计日志时会缺少参数
//        params.remove("sort");
//        params.remove("order");
//        params.remove("offset");
//        params.remove("limit");
        Object keywordObj = params.get("keyword");
        QueryWrapper<TAuditLogConfigEntity> countWrapper = null;
        if(!ObjectUtils.isEmpty(keywordObj) && StringUtils.isNotBlank(keywordObj.toString())){
            countWrapper = new QueryWrapper();
            String v = keywordObj.toString();
            //在这里维护需要检索的字段
            String[] key = {"url", "operation_type", "bussiness", "operation_code", "tag"};
            for (String k : key) {
                queryWrapper.like(k, v).or();
                countWrapper.like(k, v).or();
            }
        }
        PageUtils pageUtils = new PageUtils(tAuditLogConfigService.list(queryWrapper), tAuditLogConfigService.count(countWrapper));
        return pageUtils;
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
