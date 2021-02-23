package com.smart119.jczy.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.smart119.common.controller.BaseController;
import com.smart119.common.domain.AttachmentDO;
import com.smart119.common.service.AttachmentService;
import com.smart119.common.service.DictService;
import com.smart119.jczy.domain.LqbzdwDO;
import com.smart119.system.domain.DeptDO;
import com.smart119.system.service.DeptService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.smart119.jczy.domain.YjyaDO;
import com.smart119.jczy.service.YjyaService;
import com.smart119.common.utils.PageUtils;
import com.smart119.common.utils.Query;
import com.smart119.common.utils.R;
import org.springframework.web.multipart.MultipartFile;

/**
 * 应急预案基本信息
 * 
 * @author zhangxj
 * @email thrz@sz000673.com
 * @date 2021-01-18 16:02:22
 */
 
@Controller
@RequestMapping("/jczy/yjya")
public class YjyaController extends BaseController{
	@Autowired
	private YjyaService yjyaService;

	@Autowired
	private DictService dictService;

	@Autowired
	private AttachmentService attachmentService;

	@Autowired
	private DeptService deptService;
	
	@GetMapping()
	@RequiresPermissions("jczy:yjya:yjya")
	String Yjya(){
		String result = "";
	    return "jczy/yjya/yjya";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("jczy:yjya:yjya")
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
		List<YjyaDO> yjyaList = yjyaService.list(query);
		int total = yjyaService.count(query);
		PageUtils pageUtils = new PageUtils(yjyaList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("jczy:yjya:add")
	String add(){
	    return "jczy/yjya/add";
	}

	@GetMapping("/edit/{yjyaTywysbm}")
	@RequiresPermissions("jczy:yjya:edit")
	String edit(@PathVariable("yjyaTywysbm") String yjyaTywysbm,Model model){
		YjyaDO yjya = yjyaService.get(yjyaTywysbm);
		model.addAttribute("yjya", yjya);

		String city = dictService.findParentValue(yjya.getXzqhdm());
		String province = dictService.findParentValue(city);
		model.addAttribute("province", province);
		model.addAttribute("city", city);

		Map m = new HashMap();
		m.put("fid",yjyaTywysbm);
		m.put("fType","yawj");
		List<AttachmentDO> attachmentDOList = attachmentService.list(m);
		model.addAttribute("attachmentDOList", attachmentDOList);

	    return "jczy/yjya/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("jczy:yjya:add")
	public R save(@RequestPart(value = "file", required = false) MultipartFile[] files, YjyaDO yjya){

		if(yjyaService.save(yjya)>0){
			if(files!=null && files.length>0) {
				attachmentService.ftpUpload(files, yjya.getYjyaTywysbm(), "yawj");
			}
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("jczy:yjya:edit")
	public R update(@RequestPart(value = "file", required = false) MultipartFile[] files, YjyaDO yjya){
		if(files!=null && files.length>0) {
			attachmentService.ftpUpload(files, yjya.getYjyaTywysbm(), "yawj");
		}
		yjyaService.update(yjya);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("jczy:yjya:remove")
	public R remove( String yjyaTywysbm){
		if(yjyaService.remove(yjyaTywysbm)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("jczy:yjya:batchRemove")
	public R remove(@RequestParam("ids[]") String[] yjyaTywysbms){
		yjyaService.batchRemove(yjyaTywysbms);
		return R.ok();
	}
	
}
