package com.smart119.iot.controller;

import com.smart119.common.utils.PageUtils;
import com.smart119.common.utils.R;
import com.smart119.iot.domain.DeviceDO;
import com.smart119.iot.service.DeviceService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

/**
 * 物联设备
 * 
 * @author zhangshunhua
 * @email zhangshunhua@sz000673.com
 * @date 2021-03-23 16:52:25
 */
@Api(tags = "物联设备管理")
@Controller
@RequestMapping("/iot/device")
@Slf4j
public class DeviceController {


	@Autowired
	private DeviceService deviceService;


	@GetMapping()
	@RequiresPermissions("iot:device:device")
	String Device(){
	    return "iot/device/device";
	}




	@ApiOperation(value = "物联设备分页列表查询")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "limit", value = "条数", required = true, paramType = "body"),
			@ApiImplicitParam(name = "offset", value = "页数", required = true,paramType = "body"),
			@ApiImplicitParam(name = "params", value = "json格式的查询参数",  paramType = "body")
	})
	@ResponseBody
	@PostMapping("/list")
	@RequiresPermissions("iot:device:device")
	public PageUtils list(@RequestBody Map<String, Object> params){
		//查询列表数据
		PageUtils page = deviceService.queryPage(params);
		return page;
	}


	@GetMapping("/add")
	@RequiresPermissions("iot:device:add")
	public String add(){
		return "iot/device/add";
	}



	@ApiOperation(value = "查询物联设备详情")
	@ApiParam(name = "id", value = "主键id", required = true)
	@GetMapping("/edit/{id}")
	@RequiresPermissions("iot:device:edit")
	public R edit(@PathVariable("id") String id,Model model){
		DeviceDO device = deviceService.queryById(id);
		return R.ok(device);
	}
	
	/**
	 * 保存
	 */
	@ApiOperation(value = "保存物联设备信息")
	@ApiParam(name = "Device对象", value = "传入Device对象的json格式", required = true)
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("iot:device:add")
	public R save( DeviceDO device){
		device.setCreateTime(new Date());
		device.setUpdateTime(new Date());
		if(deviceService.save(device)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ApiOperation(value = "修改物联设备信息")
	@ApiParam(name = "Device对象", value = "传入Device对象的json格式", required = true)
	@PostMapping("/update")
	@RequiresPermissions("iot:device:edit")
	public R update(@RequestBody DeviceDO device){
		deviceService.update(device);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ApiOperation(value = "删除物联设备信息")
	@ApiParam(name = "id", value = "传入主键", required = true)
	@PostMapping( "/remove")
	@RequiresPermissions("iot:device:remove")
	public R remove(@RequestBody String id){
		if(deviceService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@ApiOperation(value = "批量删除物联设备信息")
	@ApiParam(name = "ids", value = "传入主键数组", required = true)
	@PostMapping( "/batchRemove")
	@RequiresPermissions("iot:device:batchRemove")
	public R remove(@RequestBody String[] ids){
		deviceService.batchRemove(ids);
		return R.ok();
	}
	
}
