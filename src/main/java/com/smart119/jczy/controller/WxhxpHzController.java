package com.smart119.jczy.controller;

import com.smart119.common.controller.BaseController;
import com.smart119.common.utils.PageUtils;
import com.smart119.common.utils.Query;
import com.smart119.common.utils.R;
import com.smart119.jczy.domain.WxhxpHzDO;
import com.smart119.jczy.service.WxhxpHzService;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author shichengyuan
 * @email shichengyuan@sz000673.com
 * @date 2021-09-15 08:15:15
 */

@Controller
@RequestMapping("/jczy/wxhxpHz")
public class WxhxpHzController   extends BaseController{

	@Autowired
	private WxhxpHzService wxhxpHzService;


	@GetMapping()
	@RequiresPermissions("jczy:wxhxpHz:wxhxpHz")
	String WxhxpHz(){
		return "jczy/wxhxpHz/wxhxpHz";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("jczy:wxhxpHz:wxhxpHz")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<WxhxpHzDO>  xwZxList = wxhxpHzService.list(query);
		int total = wxhxpHzService.count(query);
		PageUtils pageUtils = new PageUtils(xwZxList, total);
		return pageUtils;
	}

	@GetMapping("/add")
	@RequiresPermissions("jczy:wxhxpHz:add")
	String add(){
		return "jczy/wxhxpHz/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("jczy:wxhxpHz:edit")
	String edit(@PathVariable("id") String id,Model model){
		WxhxpHzDO wxhxpHz = wxhxpHzService.get(id);
		model.addAttribute("wxhxpHz", wxhxpHz);
		return "jczy/wxhxpHz/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("jczy:wxhxpHz:add")
	public R save(@RequestPart(value = "file", required = false) MultipartFile[] file, @Validated WxhxpHzDO wxhxpHz){
//		String id = UUID.randomUUID().toString().replace("-", "");
//		wxhxpHz.setId(id);

		if(wxhxpHzService.save(wxhxpHz)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("jczy:wxhxpHz:edit")
	public R update(@Validated WxhxpHzDO wxhxpHz){
		wxhxpHzService.update(wxhxpHz);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("jczy:wxhxpHz:remove")
	public R remove(String id){
		if(wxhxpHzService.remove(id)>0){
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("jczy:wxhxpHz:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		wxhxpHzService.batchRemove(ids);
		return R.ok();
	}

}
