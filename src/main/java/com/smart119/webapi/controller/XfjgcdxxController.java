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

import com.smart119.webapi.domain.XfjgcdxxDO;
import com.smart119.webapi.service.XfjgcdxxService;
import com.smart119.common.utils.PageUtils;
import com.smart119.common.utils.Query;
import com.smart119.common.utils.R;

/**
 * 消防机构出动信息
 * 
 * @author thrz
 * @email thrz@sz000673.com
 * @date 2021-01-28 17:21:21
 */
 
@Controller
@RequestMapping("/webapi/xfjgcdxx")
public class XfjgcdxxController {
	@Autowired
	private XfjgcdxxService xfjgcdxxService;
	
	@GetMapping()
	@RequiresPermissions("webapi:xfjgcdxx:xfjgcdxx")
	String Xfjgcdxx(){
	    return "webapi/xfjgcdxx/xfjgcdxx";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("webapi:xfjgcdxx:xfjgcdxx")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<XfjgcdxxDO> xfjgcdxxList = xfjgcdxxService.list(query);
		int total = xfjgcdxxService.count(query);
		PageUtils pageUtils = new PageUtils(xfjgcdxxList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("webapi:xfjgcdxx:add")
	String add(){
	    return "webapi/xfjgcdxx/add";
	}

	@GetMapping("/edit/{xfjgCddm}")
	@RequiresPermissions("webapi:xfjgcdxx:edit")
	String edit(@PathVariable("xfjgCddm") String xfjgCddm,Model model){
		XfjgcdxxDO xfjgcdxx = xfjgcdxxService.get(xfjgCddm);
		model.addAttribute("xfjgcdxx", xfjgcdxx);
	    return "webapi/xfjgcdxx/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("webapi:xfjgcdxx:add")
	public R save( XfjgcdxxDO xfjgcdxx){
		if(xfjgcdxxService.save(xfjgcdxx)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("webapi:xfjgcdxx:edit")
	public R update( XfjgcdxxDO xfjgcdxx){
		xfjgcdxxService.update(xfjgcdxx);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("webapi:xfjgcdxx:remove")
	public R remove( String xfjgCddm){
		if(xfjgcdxxService.remove(xfjgCddm)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("webapi:xfjgcdxx:batchRemove")
	public R remove(@RequestParam("ids[]") String[] xfjgCddms){
		xfjgcdxxService.batchRemove(xfjgCddms);
		return R.ok();
	}
	
}
