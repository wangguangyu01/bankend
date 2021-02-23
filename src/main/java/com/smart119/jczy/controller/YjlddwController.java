package com.smart119.jczy.controller;

import java.util.*;

import com.smart119.common.controller.BaseController;
import com.smart119.common.domain.AttachmentDO;
import com.smart119.common.service.AttachmentService;
import com.smart119.common.service.DictService;
import com.smart119.system.domain.DeptDO;
import com.smart119.system.service.DeptService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.smart119.jczy.domain.YjlddwDO;
import com.smart119.jczy.service.YjlddwService;
import com.smart119.common.utils.PageUtils;
import com.smart119.common.utils.Query;
import com.smart119.common.utils.R;
import org.springframework.web.multipart.MultipartFile;

/**
 * 应急联动单位
 * 
 * @author liangsl
 * @email 1992lcg@163.com
 * @date 2021-01-14 16:04:33
 */
 
@Controller
@RequestMapping("/jczy/yjlddw")
public class YjlddwController extends BaseController{
	@Autowired
	private YjlddwService yjlddwService;

	@Autowired
	private AttachmentService attachmentService;

	@Autowired
	private DictService dictService;

	@Autowired
	private DeptService deptService;
	
	@GetMapping()
	@RequiresPermissions("jczy:yjlddw:yjlddw")
	String Yjlddw(){
	    return "jczy/yjlddw/yjlddw";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("jczy:yjlddw:yjlddw")
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
		List<YjlddwDO> yjlddwList = yjlddwService.list(query);
		int total = yjlddwService.count(query);
		PageUtils pageUtils = new PageUtils(yjlddwList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("jczy:yjlddw:add")
	String add(){
	    return "jczy/yjlddw/add";
	}

	@GetMapping("/edit/{yjlddwTywysbm}")
	@RequiresPermissions("jczy:yjlddw:edit")
	String edit(@PathVariable("yjlddwTywysbm") String yjlddwTywysbm,Model model){
		YjlddwDO yjlddw = yjlddwService.get(yjlddwTywysbm);
		model.addAttribute("yjlddw", yjlddw);
		String city = dictService.findParentValue(yjlddw.getXzqhdm());
		String province = dictService.findParentValue(city);
		model.addAttribute("province", province);
		model.addAttribute("city", city);

		Map m = new HashMap();
		m.put("fid",yjlddwTywysbm);
		m.put("fType","yjlddw");
		List<AttachmentDO> attachmentDOList = attachmentService.list(m);
		model.addAttribute("attachmentDOList", attachmentDOList);

	    return "jczy/yjlddw/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("jczy:yjlddw:add")
	public R save(@RequestPart(value = "file", required = false) MultipartFile[] files, YjlddwDO yjlddw){
		String id = UUID.randomUUID().toString().replace("-", "");
		yjlddw.setYjlddwTywysbm(id);
		yjlddw.setCdate(new Date());
		yjlddw.setStatus(0);
		yjlddw.setCperson(getUserId()+"");
		if(files!=null && files.length>0) {
			attachmentService.ftpUpload(files, id, "yjlddw");
		}
		if(yjlddwService.save(yjlddw)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("jczy:yjlddw:edit")
	public R update(@RequestPart(value = "file", required = false) MultipartFile[] files, YjlddwDO yjlddw){
		if(files!=null && files.length>0) {
			attachmentService.ftpUpload(files, yjlddw.getYjlddwTywysbm(), "yjlddw");
		}
		yjlddwService.update(yjlddw);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("jczy:yjlddw:remove")
	public R remove( String yjlddwTywysbm){
		if(yjlddwService.remove(yjlddwTywysbm)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("jczy:yjlddw:batchRemove")
	public R remove(@RequestParam("ids[]") String[] yjlddwTywysbms){
		yjlddwService.batchRemove(yjlddwTywysbms);
		return R.ok();
	}
	
}
