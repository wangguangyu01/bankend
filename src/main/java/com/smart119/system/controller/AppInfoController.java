package com.smart119.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart119.common.controller.BaseController;
import com.smart119.common.utils.PageUtils;
import com.smart119.common.utils.StringUtils;
import com.smart119.system.domain.AppInfoDO;
import com.smart119.system.service.AppInfoService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.smart119.common.utils.R;

/**
 * 
 * 应用管理
 * @author zhangshunhua
 * @email zhangshunhua@sz000673.com
 * @date 2021-03-22 16:30:18
 */
@RequestMapping("/sys/appInfo")
@Controller
@Slf4j
public class AppInfoController extends BaseController {

	private String prefix="system/appInfo";


	@Autowired
	private AppInfoService appInfoService;


	@GetMapping()
	@RequiresPermissions("sys:appInfo:appInfo")
	String appInfo(Model model) {
		return prefix + "/appInfo";
	}



	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("sys:appInfo:appInfo")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
		IPage<AppInfoDO> page = appInfoService.queryPage(params);
		PageUtils result = new PageUtils(page);
		return result;
	}


	@GetMapping("/add")
	@RequiresPermissions("sys:appInfo:add")
	public String add(){
		return prefix + "/add";
	}



	@GetMapping("/edit/{id}")
	@RequiresPermissions("sys:appInfo:edit")
	public String edit(@PathVariable("id") Integer id,Model model){
		AppInfoDO appInfo = appInfoService.queryById(id);
		model.addAttribute("appInfo",appInfo);
		return prefix + "/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("sys:appInfo:add")
	public R save(AppInfoDO appInfo){
		appInfo.setCreateUser(getUser().getUserId().toString());
		appInfo.setCreateDate(new Date());
		if(appInfoService.save(appInfo) > 0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@PostMapping("/update")
	@RequiresPermissions("sys:appInfo:edit")
	public R update(AppInfoDO appInfo){
		if(appInfoService.update(appInfo)>0){
			return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@PostMapping( "/remove")
	@RequiresPermissions("sys:appInfo:remove")
	public R remove(Integer id){
		if(appInfoService.remove(id)>0){
			return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@PostMapping( "/batchRemove")
	@RequiresPermissions("sys:appInfo:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		appInfoService.batchRemove(ids);
		return R.ok();
	}
	
}
