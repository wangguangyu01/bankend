package com.smart119.jczy.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.smart119.common.controller.BaseController;
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

import com.smart119.jczy.domain.JqlbZjlyDO;
import com.smart119.jczy.service.JqlbZjlyService;
import com.smart119.common.utils.PageUtils;
import com.smart119.common.utils.Query;
import com.smart119.common.utils.R;

/**
 * 警情类别-专家领域 对照表
 * 
 * @author shilei
 * @email shichengyuan@sz000673.com
 * @date 2021-09-15 11:35:18
 */
 
@Controller
@RequestMapping("/jczy/jqlbZjly")
public class JqlbZjlyController extends BaseController {
	@Autowired
	private JqlbZjlyService jqlbZjlyService;
	
	@GetMapping()
	@RequiresPermissions("jczy:jqlbZjly:jqlbZjly")
	String JqlbZjly(){
	    return "jczy/jqlbZjly/jqlbZjly";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("jczy:jqlbZjly:jqlbZjly")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<JqlbZjlyDO> jqlbZjlyList = jqlbZjlyService.list(query);
		int total = jqlbZjlyService.count(query);
		PageUtils pageUtils = new PageUtils(jqlbZjlyList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("jczy:jqlbZjly:add")
	String add(){
	    return "jczy/jqlbZjly/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("jczy:jqlbZjly:edit")
	String edit(@PathVariable("id") String id,Model model){
		JqlbZjlyDO jqlbZjly = jqlbZjlyService.get(id);
		model.addAttribute("jqlbZjly", jqlbZjly);
	    return "jczy/jqlbZjly/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("jczy:jqlbZjly:add")
	public R save( JqlbZjlyDO jqlbZjly){
		if(jqlbZjlyService.save(jqlbZjly)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("jczy:jqlbZjly:edit")
	public R update( JqlbZjlyDO jqlbZjly){
		jqlbZjly.setUpdateUser(getUser().getUserId().toString());
		jqlbZjly.setUpdateDate(new Date());
		jqlbZjlyService.update(jqlbZjly);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("jczy:jqlbZjly:remove")
	public R remove( String id){
		if(jqlbZjlyService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("jczy:jqlbZjly:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		jqlbZjlyService.batchRemove(ids);
		return R.ok();
	}


	/**
	 * 查询字典表 消防专家领域类别 参数项
	 * @param params
	 * @return
	 */
	@ResponseBody
	@GetMapping("/getZjlyType")
	public R getZjlyType(@RequestParam Map<String, Object> params){
		List<Map<String,Object>> list = jqlbZjlyService.getZjlyType();
		return R.ok(list);
	}
	
}
