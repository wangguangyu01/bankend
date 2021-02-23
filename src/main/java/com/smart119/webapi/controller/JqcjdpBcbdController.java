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

import com.smart119.webapi.domain.JqcjdpBcbdDO;
import com.smart119.webapi.service.JqcjdpBcbdService;
import com.smart119.common.utils.PageUtils;
import com.smart119.common.utils.Query;
import com.smart119.common.utils.R;

/**
 * 警情处警调派-编程编队
 * 
 * @author yanyu
 * @email yanyu@sz000673.com
 * @date 2021-01-30 12:40:38
 */
 
@Controller
@RequestMapping("/webapi/jqcjdpBcbd")
public class JqcjdpBcbdController {
	@Autowired
	private JqcjdpBcbdService jqcjdpBcbdService;
	
	@GetMapping()
	@RequiresPermissions("webapi:jqcjdpBcbd:jqcjdpBcbd")
	String JqcjdpBcbd(){
	    return "webapi/jqcjdpBcbd/jqcjdpBcbd";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("webapi:jqcjdpBcbd:jqcjdpBcbd")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<JqcjdpBcbdDO> jqcjdpBcbdList = jqcjdpBcbdService.list(query);
		int total = jqcjdpBcbdService.count(query);
		PageUtils pageUtils = new PageUtils(jqcjdpBcbdList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("webapi:jqcjdpBcbd:add")
	String add(){
	    return "webapi/jqcjdpBcbd/add";
	}

	@GetMapping("/edit/{jqcjdpBcbdId}")
	@RequiresPermissions("webapi:jqcjdpBcbd:edit")
	String edit(@PathVariable("jqcjdpBcbdId") String jqcjdpBcbdId,Model model){
		JqcjdpBcbdDO jqcjdpBcbd = jqcjdpBcbdService.get(jqcjdpBcbdId);
		model.addAttribute("jqcjdpBcbd", jqcjdpBcbd);
	    return "webapi/jqcjdpBcbd/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("webapi:jqcjdpBcbd:add")
	public R save( JqcjdpBcbdDO jqcjdpBcbd){
		if(jqcjdpBcbdService.save(jqcjdpBcbd)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("webapi:jqcjdpBcbd:edit")
	public R update( JqcjdpBcbdDO jqcjdpBcbd){
		jqcjdpBcbdService.update(jqcjdpBcbd);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("webapi:jqcjdpBcbd:remove")
	public R remove( String jqcjdpBcbdId){
		if(jqcjdpBcbdService.remove(jqcjdpBcbdId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("webapi:jqcjdpBcbd:batchRemove")
	public R remove(@RequestParam("ids[]") String[] jqcjdpBcbdIds){
		jqcjdpBcbdService.batchRemove(jqcjdpBcbdIds);
		return R.ok();
	}
	
}
