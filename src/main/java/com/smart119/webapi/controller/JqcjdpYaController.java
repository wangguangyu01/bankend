package com.smart119.webapi.controller;

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

import com.smart119.webapi.domain.JqcjdpYaDO;
import com.smart119.webapi.service.JqcjdpYaService;
import com.smart119.common.utils.PageUtils;
import com.smart119.common.utils.Query;
import com.smart119.common.utils.R;

/**
 * 警情处警调派-应急预案
 * 
 * @author yanyu
 * @email yanyu@sz000673.com
 * @date 2021-01-30 12:42:04
 */
 
@Controller
@RequestMapping("/webapi/jqcjdpYa")
public class JqcjdpYaController {
	@Autowired
	private JqcjdpYaService jqcjdpYaService;
	
	@GetMapping()
	@RequiresPermissions("webapi:jqcjdpYa:jqcjdpYa")
	String JqcjdpYa(){
	    return "webapi/jqcjdpYa/jqcjdpYa";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("webapi:jqcjdpYa:jqcjdpYa")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<JqcjdpYaDO> jqcjdpYaList = jqcjdpYaService.list(query);
		int total = jqcjdpYaService.count(query);
		PageUtils pageUtils = new PageUtils(jqcjdpYaList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("webapi:jqcjdpYa:add")
	String add(){
	    return "webapi/jqcjdpYa/add";
	}

	@GetMapping("/edit/{jqcjdpYaId}")
	@RequiresPermissions("webapi:jqcjdpYa:edit")
	String edit(@PathVariable("jqcjdpYaId") String jqcjdpYaId,Model model){
		JqcjdpYaDO jqcjdpYa = jqcjdpYaService.get(jqcjdpYaId);
		model.addAttribute("jqcjdpYa", jqcjdpYa);
	    return "webapi/jqcjdpYa/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("webapi:jqcjdpYa:add")
	public R save( JqcjdpYaDO jqcjdpYa){
		if(jqcjdpYaService.save(jqcjdpYa)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("webapi:jqcjdpYa:edit")
	public R update( JqcjdpYaDO jqcjdpYa){
		jqcjdpYaService.update(jqcjdpYa);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("webapi:jqcjdpYa:remove")
	public R remove( String jqcjdpYaId){
		if(jqcjdpYaService.remove(jqcjdpYaId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("webapi:jqcjdpYa:batchRemove")
	public R remove(@RequestParam("ids[]") String[] jqcjdpYaIds){
		jqcjdpYaService.batchRemove(jqcjdpYaIds);
		return R.ok();
	}
	
}
