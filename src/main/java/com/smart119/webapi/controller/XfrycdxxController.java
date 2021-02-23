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

import com.smart119.webapi.domain.XfrycdxxDO;
import com.smart119.webapi.service.XfrycdxxService;
import com.smart119.common.utils.PageUtils;
import com.smart119.common.utils.Query;
import com.smart119.common.utils.R;

/**
 * 消防人员出动信息
 * 
 * @author thrz
 * @email thrz@sz000673.com
 * @date 2021-01-28 17:21:22
 */
 
@Controller
@RequestMapping("/webapi/xfrycdxx")
public class XfrycdxxController {
	@Autowired
	private XfrycdxxService xfrycdxxService;
	
	@GetMapping()
	@RequiresPermissions("webapi:xfrycdxx:xfrycdxx")
	String Xfrycdxx(){
	    return "webapi/xfrycdxx/xfrycdxx";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("webapi:xfrycdxx:xfrycdxx")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<XfrycdxxDO> xfrycdxxList = xfrycdxxService.list(query);
		int total = xfrycdxxService.count(query);
		PageUtils pageUtils = new PageUtils(xfrycdxxList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("webapi:xfrycdxx:add")
	String add(){
	    return "webapi/xfrycdxx/add";
	}

	@GetMapping("/edit/{xfryCddm}")
	@RequiresPermissions("webapi:xfrycdxx:edit")
	String edit(@PathVariable("xfryCddm") String xfryCddm,Model model){
		XfrycdxxDO xfrycdxx = xfrycdxxService.get(xfryCddm);
		model.addAttribute("xfrycdxx", xfrycdxx);
	    return "webapi/xfrycdxx/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("webapi:xfrycdxx:add")
	public R save( XfrycdxxDO xfrycdxx){
		if(xfrycdxxService.save(xfrycdxx)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("webapi:xfrycdxx:edit")
	public R update( XfrycdxxDO xfrycdxx){
		xfrycdxxService.update(xfrycdxx);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("webapi:xfrycdxx:remove")
	public R remove( String xfryCddm){
		if(xfrycdxxService.remove(xfryCddm)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("webapi:xfrycdxx:batchRemove")
	public R remove(@RequestParam("ids[]") String[] xfryCddms){
		xfrycdxxService.batchRemove(xfryCddms);
		return R.ok();
	}
	
}
