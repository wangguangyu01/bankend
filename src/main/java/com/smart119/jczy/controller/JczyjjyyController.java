package com.smart119.jczy.controller;

import com.smart119.common.utils.PageUtils;
import com.smart119.common.utils.Query;
import com.smart119.common.utils.R;
import com.smart119.jczy.domain.JjyyDO;
import com.smart119.jczy.service.JczyjjyyService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 
 * 
 * @author thrz
 * @email thrz@sz000673.com
 * @date 2021-02-03 15:35:11
 */
 
@Controller
@RequestMapping("/jczy/jjyy")
public class JczyjjyyController {
	@Autowired
	private JczyjjyyService jjyyService;
	
	@GetMapping()
	@RequiresPermissions("jczy:jjyy:jjyy")
	String Jjyy(){
	    return "jczy/jjyy/jjyy";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("jczy:jjyy:jjyy")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<JjyyDO> jjyyList = jjyyService.list(query);
		int total = jjyyService.count(query);
		PageUtils pageUtils = new PageUtils(jjyyList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("jczy:jjyy:add")
	String add(){
	    return "jczy/jjyy/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("jczy:jjyy:edit")
	String edit(@PathVariable("id") String id,Model model){
		JjyyDO jjyy = jjyyService.get(id);
		jjyy.setCdate(new Date());
		model.addAttribute("jjyy", jjyy);
	    return "jczy/jjyy/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("jczy:jjyy:add")
	public R save( JjyyDO jjyy){
		jjyy.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		jjyy.setCdate(new Date());
		jjyy.setStatus(0);
		if(jjyyService.save(jjyy)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("jczy:jjyy:edit")
	public R update( JjyyDO jjyy){
		jjyyService.update(jjyy);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("jczy:jjyy:remove")
	public R remove( String id){
		if(jjyyService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("jczy:jjyy:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		jjyyService.batchRemove(ids);
		return R.ok();
	}
	
}
