package com.smart119.jczy.controller;

import java.util.*;

import com.smart119.common.controller.BaseController;
import com.smart119.common.service.DictService;
import com.smart119.system.domain.DeptDO;
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

import com.smart119.jczy.domain.ShlddwDO;
import com.smart119.jczy.service.ShlddwService;
import com.smart119.common.utils.PageUtils;
import com.smart119.common.utils.Query;
import com.smart119.common.utils.R;

/**
 * 社会联动单位管理
 * 
 * @author shilei
 * @email yangjiyu@sz000673.com
 * @date 2021-03-20 14:24:12
 */
 
@Controller
@RequestMapping("/jczy/shlddw")
public class ShlddwController  extends BaseController {
	@Autowired
	private ShlddwService shlddwService;
	@Autowired
	private DictService dictService;
	@Autowired
	private DeptService deptService;

	@GetMapping()
	@RequiresPermissions("jczy:shlddw:shlddw")
	String Shlddw(){
	    return "jczy/shlddw/shlddw";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("jczy:shlddw:shlddw")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<DeptDO> deptList = new ArrayList<>();
		if(params.get("deptId")!=null && !params.get("deptId").equals("")){
			deptList = deptService.listChildren(Long.valueOf(params.get("deptId").toString()));
		}else{
			deptList = deptService.listChildren(getUser().getDeptId());
		}
		query.put("deptList",deptList);
		List<ShlddwDO> shlddwList = shlddwService.list(query);
		int total = shlddwService.count(query);
		PageUtils pageUtils = new PageUtils(shlddwList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("jczy:shlddw:add")
	String add(){
	    return "jczy/shlddw/add";
	}

	@GetMapping("/edit/{shlddwTywysbm}")
	@RequiresPermissions("jczy:shlddw:edit")
	String edit(@PathVariable("shlddwTywysbm") String shlddwTywysbm,Model model){
		ShlddwDO shlddw = shlddwService.get(shlddwTywysbm);
		String city = dictService.findParentValue(shlddw.getXzqhdm());
		String province = dictService.findParentValue(city);
		model.addAttribute("province", province);  //籍贯代码-省
		model.addAttribute("city", city);  //籍贯代码-市
		model.addAttribute("shlddw", shlddw);
	    return "jczy/shlddw/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("jczy:shlddw:add")
	public R save( ShlddwDO shlddw){
		String id = UUID.randomUUID().toString().replace("-", "");
		shlddw.setShlddwTywysbm(id);
		shlddw.setCdate(new Date());
		shlddw.setStatus("0");
		shlddw.setCperson(getUserId().toString());
		if(shlddwService.save(shlddw)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("jczy:shlddw:edit")
	public R update( ShlddwDO shlddw){
		shlddwService.update(shlddw);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("jczy:shlddw:remove")
	public R remove( String shlddwTywysbm){
		if(shlddwService.remove(shlddwTywysbm)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("jczy:shlddw:batchRemove")
	public R remove(@RequestParam("ids[]") String[] shlddwTywysbms){
		shlddwService.batchRemove(shlddwTywysbms);
		return R.ok();
	}
	
}
