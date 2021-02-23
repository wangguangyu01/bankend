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

import com.smart119.jczy.domain.LqbzdwDO;
import com.smart119.jczy.service.LqbzdwService;
import com.smart119.common.utils.PageUtils;
import com.smart119.common.utils.Query;
import com.smart119.common.utils.R;
import org.springframework.web.multipart.MultipartFile;

/**
 * 联勤保障单位基本信息
 * 
 * @author liangsl
 * @email 1992lcg@163.com
 * @date 2021-01-13 17:06:48
 */
 
@Controller
@RequestMapping("/jczy/lqbzdw")
public class LqbzdwController extends BaseController{
	@Autowired
	private LqbzdwService lqbzdwService;

	@Autowired
	private AttachmentService attachmentService;

	@Autowired
	private DictService dictService;

	@Autowired
	private DeptService deptService;

	@GetMapping()
	@RequiresPermissions("jczy:lqbzdw:lqbzdw")
	String Lqbzdw(){
	    return "jczy/lqbzdw/lqbzdw";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("jczy:lqbzdw:lqbzdw")
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
		List<LqbzdwDO> lqbzdwList = lqbzdwService.list(query);
		int total = lqbzdwService.count(query);
		PageUtils pageUtils = new PageUtils(lqbzdwList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("jczy:lqbzdw:add")
	String add(){
	    return "jczy/lqbzdw/add";
	}

	@GetMapping("/edit/{lqbzdwTywysbm}")
	@RequiresPermissions("jczy:lqbzdw:edit")
	String edit(@PathVariable("lqbzdwTywysbm") String lqbzdwTywysbm,Model model){
		LqbzdwDO lqbzdw = lqbzdwService.get(lqbzdwTywysbm);
		model.addAttribute("lqbzdw", lqbzdw);
		String city = dictService.findParentValue(lqbzdw.getXzqhdm());
		String province = dictService.findParentValue(city);
		model.addAttribute("province", province);
		model.addAttribute("city", city);

		Map m = new HashMap();
		m.put("fid",lqbzdwTywysbm);
		m.put("fType","lqbzdw");
		List<AttachmentDO> attachmentDOList = attachmentService.list(m);
		model.addAttribute("attachmentDOList", attachmentDOList);

	    return "jczy/lqbzdw/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("jczy:lqbzdw:add")
	public R save(@RequestPart(value = "file", required = false) MultipartFile[] files, LqbzdwDO lqbzdw){
		String id = UUID.randomUUID().toString().replace("-", "");
		lqbzdw.setLqbzdwTywysbm(id);
		lqbzdw.setCdate(new Date());
		lqbzdw.setStatus(0);
		lqbzdw.setCperson(getUserId()+"");
		if(files!=null && files.length>0) {
			attachmentService.ftpUpload(files, id, "lqbzdw");
		}
		if(lqbzdwService.save(lqbzdw)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("jczy:lqbzdw:edit")
	public R update(@RequestPart(value = "file", required = false) MultipartFile[] files, LqbzdwDO lqbzdw){
		if(files!=null && files.length>0) {
			attachmentService.ftpUpload(files, lqbzdw.getLqbzdwTywysbm(), "lqbzdw");
		}
		lqbzdwService.update(lqbzdw);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("jczy:lqbzdw:remove")
	public R remove( String lqbzdwTywysbm){
		if(lqbzdwService.remove(lqbzdwTywysbm)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("jczy:lqbzdw:batchRemove")
	public R remove(@RequestParam("ids[]") String[] lqbzdwTywysbms){
		lqbzdwService.batchRemove(lqbzdwTywysbms);
		return R.ok();
	}
	
}
