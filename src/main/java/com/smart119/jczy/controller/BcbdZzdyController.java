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

import com.smart119.jczy.domain.BcbdZzdyDO;
import com.smart119.jczy.service.BcbdZzdyService;
import com.smart119.common.utils.PageUtils;
import com.smart119.common.utils.Query;
import com.smart119.common.utils.R;

/**
 * 编程编队-作战单元
 * 
 * @author Jiyu Yang
 * @email yangjiyu@sz000673.com
 * @date 2021-01-30 10:12:02
 */
 
@Controller
@RequestMapping("/jczy/bcbdZzdy")
public class BcbdZzdyController {
	@Autowired
	private BcbdZzdyService bcbdZzdyService;
	
	@GetMapping()
	@RequiresPermissions("jczy:bcbdZzdy:bcbdZzdy")
	String BcbdZzdy(){
	    return "jczy/bcbdZzdy/bcbdZzdy";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("jczy:bcbdZzdy:bcbdZzdy")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<BcbdZzdyDO> bcbdZzdyList = bcbdZzdyService.list(query);
		int total = bcbdZzdyService.count(query);
		PageUtils pageUtils = new PageUtils(bcbdZzdyList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("jczy:bcbdZzdy:add")
	String add(){
	    return "jczy/bcbdZzdy/add";
	}

	@GetMapping("/edit/{bcbdZzdyId}")
	@RequiresPermissions("jczy:bcbdZzdy:edit")
	String edit(@PathVariable("bcbdZzdyId") String bcbdZzdyId,Model model){
		BcbdZzdyDO bcbdZzdy = bcbdZzdyService.get(bcbdZzdyId);
		model.addAttribute("bcbdZzdy", bcbdZzdy);
	    return "jczy/bcbdZzdy/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("jczy:bcbdZzdy:add")
	public R save( BcbdZzdyDO bcbdZzdy){
		if(bcbdZzdyService.save(bcbdZzdy)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("jczy:bcbdZzdy:edit")
	public R update( BcbdZzdyDO bcbdZzdy){
		bcbdZzdyService.update(bcbdZzdy);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("jczy:bcbdZzdy:remove")
	public R remove( String bcbdZzdyId){
		if(bcbdZzdyService.remove(bcbdZzdyId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("jczy:bcbdZzdy:batchRemove")
	public R remove(@RequestParam("ids[]") String[] bcbdZzdyIds){
		bcbdZzdyService.batchRemove(bcbdZzdyIds);
		return R.ok();
	}
	
}
