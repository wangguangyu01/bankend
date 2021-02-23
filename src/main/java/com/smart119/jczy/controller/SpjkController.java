package com.smart119.jczy.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.smart119.common.service.DictService;
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

import com.smart119.jczy.domain.SpjkDO;
import com.smart119.jczy.service.SpjkService;
import com.smart119.common.utils.PageUtils;
import com.smart119.common.utils.Query;
import com.smart119.common.utils.R;

/**
 * 视频监控
 * 
 * @author thrz
 * @email thrz@sz000673.com
 * @date 2021-01-20 10:17:15
 */
 
@Controller
@RequestMapping("/jczy/spjk")
public class SpjkController {
	@Autowired
	private SpjkService spjkService;
	@Autowired
	private DictService dictService;
	
	@GetMapping()
	@RequiresPermissions("jczy:spjk:spjk")
	String Spjk(){
	    return "jczy/spjk/spjk";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("jczy:spjk:spjk")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<SpjkDO> spjkList = spjkService.list(query);
		int total = spjkService.count(query);
		PageUtils pageUtils = new PageUtils(spjkList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("jczy:spjk:add")
	String add(){
	    return "jczy/spjk/add";
	}

	@GetMapping("/edit/{spjkTywysbm}")
	@RequiresPermissions("jczy:spjk:edit")
	String edit(@PathVariable("spjkTywysbm") String spjkTywysbm,Model model){
		SpjkDO spjk = spjkService.get(spjkTywysbm);
		model.addAttribute("spjk", spjk);
	    return "jczy/spjk/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("jczy:spjk:add")
	public R save( SpjkDO spjk){
		String id = UUID.randomUUID().toString().replace("-", "");
		spjk.setSpjkTywysbm(id);
		if(spjkService.save(spjk)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("jczy:spjk:edit")
	public R update( SpjkDO spjk){
		spjkService.update(spjk);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("jczy:spjk:remove")
	public R remove( String spjkTywysbm){
		if(spjkService.remove(spjkTywysbm)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("jczy:spjk:batchRemove")
	public R remove(@RequestParam("ids[]") String[] spjkTywysbms){
		spjkService.batchRemove(spjkTywysbms);
		return R.ok();
	}
	
}
