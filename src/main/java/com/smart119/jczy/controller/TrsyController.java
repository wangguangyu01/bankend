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

import com.smart119.jczy.domain.TrsyDO;
import com.smart119.jczy.service.TrsyService;
import com.smart119.common.utils.PageUtils;
import com.smart119.common.utils.Query;
import com.smart119.common.utils.R;
import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * 
 * @author thrz
 * @email thrz@sz000673.com
 * @date 2021-01-20 15:32:48
 */
 
@Controller
@RequestMapping("/jczy/trsy")
public class TrsyController extends BaseController{
	@Autowired
	private TrsyService trsyService;

	@Autowired
	private DictService dictService;

	@Autowired
	private AttachmentService attachmentService;

	@Autowired
	private DeptService deptService;
	
	@GetMapping()
	@RequiresPermissions("jczy:trsy:trsy")
	String Trsy(){
	    return "jczy/trsy/trsy";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("jczy:trsy:trsy")
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
		List<TrsyDO> trsyList = trsyService.list(query);
		int total = trsyService.count(query);
		PageUtils pageUtils = new PageUtils(trsyList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("jczy:trsy:add")
	String add(){
	    return "jczy/trsy/add";
	}

	@GetMapping("/edit/{trsyTywysbm}")
	@RequiresPermissions("jczy:trsy:edit")
	String edit(@PathVariable("trsyTywysbm") String trsyTywysbm,Model model){
		TrsyDO trsy = trsyService.get(trsyTywysbm);
		model.addAttribute("trsy", trsy);
		String city = dictService.findParentValue(trsy.getXzqhdm());
		String province = dictService.findParentValue(city);
		model.addAttribute("province", province);
		model.addAttribute("city", city);

		Map m = new HashMap();
		m.put("fid",trsyTywysbm);
		m.put("fType","trsysjt");
		List<AttachmentDO> trsysjtList = attachmentService.list(m);
		model.addAttribute("trsysjtList", trsysjtList);

		m.put("fType","trsyfwt");
		List<AttachmentDO> trsyfwtList = attachmentService.list(m);
		model.addAttribute("trsyfwtList", trsyfwtList);
	    return "jczy/trsy/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("jczy:trsy:add")
	public R save(@RequestPart(value = "sjFile", required = false) MultipartFile[] sjFiles, @RequestPart(value = "fwFile", required = false) MultipartFile[] fwFiles, TrsyDO trsy){
		String id = UUID.randomUUID().toString().replace("-", "");
		trsy.setTrsyTywysbm(id);
		if(sjFiles!=null && sjFiles.length>0) {
			attachmentService.ftpUpload(sjFiles, id, "trsysjt");
		}
		if(fwFiles!=null && fwFiles.length>0) {
			attachmentService.ftpUpload(fwFiles, id, "trsyfwt");
		}
		if(trsyService.save(trsy)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("jczy:trsy:edit")
	public R update(@RequestPart(value = "sjFile", required = false) MultipartFile[] sjFiles, @RequestPart(value = "fwFile", required = false) MultipartFile[] fwFiles, TrsyDO trsy){
		if(sjFiles!=null && sjFiles.length>0) {
			attachmentService.ftpUpload(sjFiles, trsy.getTrsyTywysbm(), "trsysjt");
		}
		if(fwFiles!=null && fwFiles.length>0) {
			attachmentService.ftpUpload(fwFiles, trsy.getTrsyTywysbm(), "trsyfwt");
		}
		trsyService.update(trsy);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("jczy:trsy:remove")
	public R remove( String trsyTywysbm){
		if(trsyService.remove(trsyTywysbm)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("jczy:trsy:batchRemove")
	public R remove(@RequestParam("ids[]") String[] trsyTywysbms){
		trsyService.batchRemove(trsyTywysbms);
		return R.ok();
	}
	
}
