package com.smart119.jczy.controller;

import com.smart119.common.controller.BaseController;
import com.smart119.common.utils.PageUtils;
import com.smart119.common.utils.Query;
import com.smart119.common.utils.R;
import com.smart119.jczy.domain.WblxrDO;
import com.smart119.jczy.service.WblxrService;
import com.smart119.system.service.DeptService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 外部联系人
 *
 * @author scy
 * @email shichengyuan@sz000673.com
 * @date 2021-03-24 14:31:16
 */

@Controller
@RequestMapping("/jczy/wblxr")
public class WblxrController  extends BaseController {
	@Autowired
	private WblxrService wblxrService;

	@Autowired
	private DeptService deptService;

	@GetMapping()
	@RequiresPermissions("jczy:wblxr:wblxr")
	String Wblxr(){
		return "jczy/wblxr/wblxr";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("jczy:wblxr:wblxr")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<WblxrDO> wblxrList = wblxrService.list(query);
		int total = wblxrService.count(query);
		PageUtils pageUtils = new PageUtils(wblxrList, total);
		return pageUtils;
	}

	@GetMapping("/add")
	@RequiresPermissions("jczy:wblxr:add")
	String add(){
		return "jczy/wblxr/add";
	}

	@GetMapping("/edit/{wblxrId}")
	@RequiresPermissions("jczy:wblxr:edit")
	String edit(@PathVariable("wblxrId") String wblxrId,Model model){
		WblxrDO wblxr = wblxrService.get(wblxrId);
		model.addAttribute("wblxr", wblxr);
		return "jczy/wblxr/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("jczy:wblxr:add")
	public R save( WblxrDO wblxr){
		if(wblxrService.save(wblxr)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("jczy:wblxr:edit")
	public R update( WblxrDO wblxr){
		wblxrService.update(wblxr);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("jczy:wblxr:remove")
	public R remove( String wblxrId){
		if(wblxrService.remove(wblxrId)>0){
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("jczy:wblxr:batchRemove")
	public R remove(@RequestParam("ids[]") String[] wblxrIds){
		wblxrService.batchRemove(wblxrIds);
		return R.ok();
	}

}
