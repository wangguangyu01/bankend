package com.smart119.zb.controller;

import java.util.*;

import com.smart119.common.controller.BaseController;
import com.smart119.system.domain.DeptDO;
import com.smart119.system.service.DeptService;
import com.smart119.zb.domain.ZbDO;
import com.smart119.zb.service.ZbService;
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

import com.smart119.zb.domain.ZbzwDO;
import com.smart119.zb.service.ZbzwService;
import com.smart119.common.utils.PageUtils;
import com.smart119.common.utils.Query;
import com.smart119.common.utils.R;

/**
 * 值班职务
 * 
 * @author liangsl
 * @email liangshengli@sz000673.com
 * @date 2021-05-08 19:01:02
 */
 
@Controller
@RequestMapping("/zb/zbzw")
public class ZbzwController extends BaseController{
	@Autowired
	private ZbzwService zbzwService;
	@Autowired
	private DeptService deptService;
	@Autowired
	private ZbService zbService;
	
	@GetMapping("/{deptId}")
	@RequiresPermissions("zb:zbzw:zbzw")
	String Zbzw(@PathVariable("deptId") String deptId,Model model){
		System.out.println(deptId);
		DeptDO deptDO = deptService.get(Long.valueOf(deptId));
		String zwlx="1";
		if(deptDO.getXfjyjgxzdm().startsWith("3")){
			zwlx="1";
		}else if(deptDO.getXfjyjgxzdm().startsWith("5")){
			zwlx="2";
		}else if(deptDO.getXfjyjgxzdm().startsWith("9")){
			zwlx="3";
		}
		model.addAttribute("zwlx", zwlx);
		return "zb/zbzw/zbzw";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("zb:zbzw:zbzw")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ZbzwDO> zbzwList = zbzwService.list(query);
		int total = zbzwService.count(query);
		PageUtils pageUtils = new PageUtils(zbzwList, total);
		return pageUtils;
	}
	
	@GetMapping("/add/{zwlx}")
	@RequiresPermissions("zb:zbzw:add")
	String add(@PathVariable("zwlx") String zwlx,Model model){
		model.addAttribute("zwlx", zwlx);
	    return "zb/zbzw/add";
	}

	@GetMapping("/edit/{zbzwId}")
	@RequiresPermissions("zb:zbzw:edit")
	String edit(@PathVariable("zbzwId") String zbzwId,Model model){
		ZbzwDO zbzw = zbzwService.get(zbzwId);
		model.addAttribute("zbzw", zbzw);
	    return "zb/zbzw/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("zb:zbzw:add")
	public R save( ZbzwDO zbzw){
		String id = UUID.randomUUID().toString().replace("-", "");
		zbzw.setZbzwId(id);
		zbzw.setCdate(new Date());
		zbzw.setCperson(getUserId()+"");
		zbzw.setStatus("0");
		if(zbzwService.save(zbzw)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("zb:zbzw:edit")
	public R update( ZbzwDO zbzw){
		zbzwService.update(zbzw);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("zb:zbzw:remove")
	public R remove( String zbzwId){
		Map map = new HashMap();
		map.put("zbzwId",zbzwId);
		List<ZbDO> zbDOList = zbService.list(map);
		if(zbDOList!=null && zbDOList.size()>0){
			return R.error("该职务正在使用，不允许删除！");
		}else{
			if(zbzwService.remove(zbzwId)>0){
				return R.ok();
			}
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("zb:zbzw:batchRemove")
	public R remove(@RequestParam("ids[]") String[] zbzwIds){
		zbzwService.batchRemove(zbzwIds);
		return R.ok();
	}
	
}
