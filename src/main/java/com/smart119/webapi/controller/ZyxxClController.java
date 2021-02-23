package com.smart119.webapi.controller;

import com.smart119.common.controller.BaseController;
import com.smart119.common.utils.PageUtils;
import com.smart119.common.utils.Query;
import com.smart119.common.utils.R;
import com.smart119.webapi.domain.ZyxxClDO;
import com.smart119.webapi.service.ZyxxClService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author Jiyu Yang
 * @email yangjiyu@sz000673.com
 * @date 2021-02-08 19:46:54
 */
 
@Controller
@RequestMapping("/webapi/zyxxCl")
public class ZyxxClController extends BaseController {
	@Autowired
	private ZyxxClService zyxxClService;
	
	@GetMapping()
	@RequiresPermissions("webapi:zyxxCl:zyxxCl")
	String ZyxxCl(){
	    return "webapi/zyxxCl/zyxxCl";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("webapi:zyxxCl:zyxxCl")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ZyxxClDO> zyxxClList = zyxxClService.list(query);
		int total = zyxxClService.count(query);
		PageUtils pageUtils = new PageUtils(zyxxClList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("webapi:zyxxCl:add")
	String add(){
	    return "webapi/zyxxCl/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("webapi:zyxxCl:edit")
	String edit(@PathVariable("id") String id,Model model){
		ZyxxClDO zyxxCl = zyxxClService.get(id);
		model.addAttribute("zyxxCl", zyxxCl);
	    return "webapi/zyxxCl/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("webapi:zyxxCl:add")
	public R save( ZyxxClDO zyxxCl){
		if(zyxxClService.save(zyxxCl)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("webapi:zyxxCl:edit")
	public R update( ZyxxClDO zyxxCl){
		zyxxClService.update(zyxxCl);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("webapi:zyxxCl:remove")
	public R remove( String id){
		if(zyxxClService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("webapi:zyxxCl:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		zyxxClService.batchRemove(ids);
		return R.ok();
	}
	
}
