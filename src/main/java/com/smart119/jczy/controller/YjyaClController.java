package com.smart119.jczy.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smart119.jczy.domain.YjyaClDO;
import com.smart119.jczy.service.YjyaClService;
import com.smart119.common.utils.PageUtils;
import com.smart119.common.utils.Query;
import com.smart119.common.utils.R;

/**
 * 应急预案-车辆类型表
 * 
 * @author liangsl
 * @email 123564081@qq.com
 * @date 2021-01-29 16:04:24
 */
 
@Controller
@RequestMapping("/jczy/yjyaCl")
public class YjyaClController {
	@Autowired
	private YjyaClService yjyaClService;
	
	@GetMapping()
	@RequiresPermissions("jczy:yjyaCl:yjyaCl")
	String YjyaCl(){
	    return "jczy/yjyaCl/yjyaCl";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("jczy:yjyaCl:yjyaCl")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<YjyaClDO> yjyaClList = yjyaClService.list(query);
		int total = yjyaClService.count(query);
		PageUtils pageUtils = new PageUtils(yjyaClList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("jczy:yjyaCl:add")
	String add(){
	    return "jczy/yjyaCl/add";
	}

	@GetMapping("/edit/{yjyaClId}")
	@RequiresPermissions("jczy:yjyaCl:edit")
	String edit(@PathVariable("yjyaClId") String yjyaClId,Model model){
		YjyaClDO yjyaCl = yjyaClService.get(yjyaClId);
		model.addAttribute("yjyaCl", yjyaCl);
	    return "jczy/yjyaCl/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("jczy:yjyaCl:add")
	public R save( YjyaClDO yjyaCl){
		if(yjyaClService.save(yjyaCl)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("jczy:yjyaCl:edit")
	public R update( YjyaClDO yjyaCl){
		yjyaClService.update(yjyaCl);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("jczy:yjyaCl:remove")
	public R remove( String yjyaClId){
		if(yjyaClService.remove(yjyaClId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("jczy:yjyaCl:batchRemove")
	public R remove(@RequestParam("ids[]") String[] yjyaClIds){
		yjyaClService.batchRemove(yjyaClIds);
		return R.ok();
	}
	
}
