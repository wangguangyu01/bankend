package com.smart119.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.smart119.common.utils.PageUtils;
import com.smart119.common.utils.ParamChangeUtil;
import com.smart119.common.utils.R;
import com.smart119.system.domain.TElementDirectionEntity;
import com.smart119.system.service.TElementDirectionService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 日志操作类型字典表
 *
 * @author raysdata
 * @email test@raysdata.com
 * @date 2020-08-18 11:04:46
 */
@RestController
@RequestMapping("sys/telementdirection")
@Api(value = "sys/telementdirection", tags = "日志操作类型字典表管理 API")
@Slf4j
public class TElementDirectionController {
    @Autowired
    private TElementDirectionService tElementDirectionService;

    @PostMapping("/list")
    @ApiOperation(value = "日志操作类型字典表列表")
    @RequiresPermissions("sys:tauditlog:tauditlog")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "limit", value = "条数", required = true    , dataType = "String",dataTypeClass = String.class,paramType = "body"),
            @ApiImplicitParam(name = "offset", value = "页数", required = true    , dataType = "String",dataTypeClass = String.class,paramType = "body"),
            @ApiImplicitParam(name = "params", value = "json格式的查询参数",  dataType = "String",dataTypeClass = String.class,paramType = "body")
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
        PageUtils pageUtils = new PageUtils(tElementDirectionService.list(queryWrapper), tElementDirectionService.count(queryWrapper));
        return R.ok(pageUtils);
    }

    /**
     * 信息
     */
    @ApiOperation("查询日志操作类型字典表详情")
    @GetMapping("/info/{id}")
    @RequiresPermissions("sys:tauditlog:info")
    public R info(@PathVariable("id") @ApiParam(name = "id", value = "日志操作类型字典表id", required = true) Integer id) {
        log.debug("info start:id={}", id);
        return R.ok(tElementDirectionService.getById(id)).setOnlyExtraNote(id);
    }

    /**
     * 保存
     */
    @ApiOperation("保存日志操作类型字典表详情")
    @PostMapping("/add")
    @RequiresPermissions("sys:tauditlog:add")
    public R add(@RequestBody @ApiParam(name = "日志操作类型字典表对象", value = "传入json格式", required = true) TElementDirectionEntity tElementDirection) {
        tElementDirectionService.save(tElementDirection);
        return R.ok().setOnlyExtraNote(tElementDirection.getId());
    }

    /**
     * 修改
     */
    @ApiOperation("修改日志操作类型字典表详情")
    @PostMapping("/edit")
    @RequiresPermissions("sys:tauditlog:edit")
    public R edit(@RequestBody @ApiParam(name = "日志操作类型字典表对象", value = "传入json格式", required = true) TElementDirectionEntity tElementDirection) {
        log.debug("update start:TElementDirectionEntity={}", tElementDirection);
        tElementDirectionService.updateById(tElementDirection);
        return R.ok().setOnlyExtraNote(tElementDirection.getId());
    }

    /**
     * 删除
     */
    @ApiOperation("删除日志操作类型字典表")
    @PostMapping("/remove/{id}")
    @RequiresPermissions("sys:tauditlog:remove")
    public R remove(@PathVariable("id") @ApiParam(name = "id", value = "日志操作类型字典表id", required = true) Integer id) {
        log.debug("delete start:id={}", id);
        tElementDirectionService.removeById(id);
        return R.ok().setOnlyExtraNote(id);
    }

}
