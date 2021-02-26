package com.smart119.jczy.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.smart119.common.controller.BaseController;
import com.smart119.system.domain.UserDO;
import com.smart119.system.service.DeptService;
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

import com.smart119.jczy.domain.ZxDO;
import com.smart119.jczy.service.ZxService;
import com.smart119.common.utils.PageUtils;
import com.smart119.common.utils.Query;
import com.smart119.common.utils.R;

/**
 * 坐席
 * 
 * @author shilei
 * @email yangjiyu@sz000673.com
 * @date 2021-02-25 10:15:41
 */
 
@Controller
@RequestMapping("/jczy/zx")
public class ZxController extends BaseController {
	@Autowired
	private ZxService zxService;

	@Autowired
	private DeptService deptService;
	
	@GetMapping()
	@RequiresPermissions("jczy:zx:zx")
	String Zx(){
	    return "jczy/zx/zx";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("jczy:zx:zx")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ZxDO> zxList = zxService.list(query);
		int total = zxService.count(query);
		PageUtils pageUtils = new PageUtils(zxList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("jczy:zx:add")
	String add(){
	    return "jczy/zx/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("jczy:zx:edit")
	String edit(@PathVariable("id") String id,Model model){
		ZxDO zx = zxService.get(id);
		model.addAttribute("xfjyjgTywysbmName", deptService.findNameByTYWYSBM(zx.getXfjyjgTywysbm()));
		model.addAttribute("zx", zx);
	    return "jczy/zx/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("jczy:zx:add")
	public R save( ZxDO zx){
		String id = UUID.randomUUID().toString().replace("-", "");
		zx.setId(id);
		zx.setCdate(new Date());
		zx.setCperson(getUserId().toString());
		zx.setStatus("0");
		if(zxService.save(zx)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("jczy:zx:edit")
	public R update( ZxDO zx){
		zxService.update(zx);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("jczy:zx:remove")
	public R remove( String id){
		if(zxService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("jczy:zx:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		zxService.batchRemove(ids);
		return R.ok();
	}
	
}
