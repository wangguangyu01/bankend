package com.smart119.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.smart119.common.utils.PageUtils;
import com.smart119.common.utils.StringUtils;
import com.smart119.system.domain.AppInfoDO;
import com.smart119.system.service.AppInfoService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.smart119.common.utils.R;

/**
 * 
 * 
 * @author zhangshunhua
 * @email zhangshunhua@sz000673.com
 * @date 2021-03-22 16:30:18
 */
@Api(tags = "管理")
@RestController
@RequestMapping("/sys/appInfo")
@Slf4j
public class AppInfoController {


	@Autowired
	private AppInfoService appInfoService;


	@GetMapping()
	@RequiresPermissions("sys:appInfo:appInfo")
	R AppInfo(){
	    return R.ok();
	}




	@ApiOperation(value = "分页列表查询")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "limit", value = "条数", required = true, paramType = "body"),
			@ApiImplicitParam(name = "offset", value = "页数", required = true,paramType = "body"),
			@ApiImplicitParam(name = "params", value = "json格式的查询参数",  paramType = "body")
	})
	@PostMapping("/list")
	@RequiresPermissions("sys:appInfo:list")
	public R list(@RequestBody Map<String, Object> params){
		//查询列表数据
		PageUtils page = appInfoService.queryPage(params);
		return R.ok(page);
	}


	@GetMapping("/add")
	@RequiresPermissions("sys:appInfo:add")
	public R add(){
		return R.ok();
	}



	@ApiOperation(value = "查询详情")
	@ApiParam(name = "id", value = "主键id", required = true)
	@GetMapping("/edit/{id}")
	@RequiresPermissions("sys:appInfo:edit")
	public R edit(@PathVariable("id") Integer id,Model model){
		AppInfoDO appInfo = appInfoService.queryById(id);
		return R.ok(appInfo);
	}
	
	/**
	 * 保存
	 */
	@ApiOperation(value = "保存信息")
	@ApiParam(name = "AppInfo对象", value = "传入AppInfo对象的json格式", required = true)
	@PostMapping("/save")
	@RequiresPermissions("sys:appInfo:add")
	public R save( AppInfoDO appInfo){
		if(appInfoService.save(appInfo)){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ApiOperation(value = "修改信息")
	@ApiParam(name = "AppInfo对象", value = "传入AppInfo对象的json格式", required = true)
	@PostMapping("/update")
	@RequiresPermissions("sys:appInfo:edit")
	public R update(@RequestBody AppInfoDO appInfo){
		appInfoService.update(appInfo);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ApiOperation(value = "删除信息")
	@ApiParam(name = "id", value = "传入主键", required = true)
	@PostMapping( "/remove")
	@RequiresPermissions("sys:appInfo:remove")
	public R remove(@RequestBody Integer id){
		if(appInfoService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@ApiOperation(value = "批量删除信息")
	@ApiParam(name = "ids", value = "传入主键数组", required = true)
	@PostMapping( "/batchRemove")
	@RequiresPermissions("sys:appInfo:batchRemove")
	public R remove(@RequestBody Integer[] ids){
		appInfoService.batchRemove(ids);
		return R.ok();
	}

	@ApiOperation(value = "查询应用的所有记录")
	@PostMapping("/all")
	@RequiresPermissions("sys:appInfo:all")
	public R list(@RequestBody AppInfoDO appInfo){
		//查询列表数据
		List<AppInfoDO> result=new ArrayList<>(0);
		if(StringUtils.isNotBlank(appInfo.getStatus())){
			QueryWrapper wrapper=new QueryWrapper();
			wrapper.eq("status",appInfo.getStatus());
			result=appInfoService.list(wrapper);
		}
		return R.ok(result);
	}
	
}
