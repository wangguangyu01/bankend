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

import com.smart119.jczy.domain.XfshDO;
import com.smart119.jczy.service.XfshService;
import com.smart119.common.utils.PageUtils;
import com.smart119.common.utils.Query;
import com.smart119.common.utils.R;
import org.springframework.web.multipart.MultipartFile;

/**
 * 消防水鹤基本信息
 * 
 * @author thrz
 * @email thrz@sz000673.com
 * @date 2021-01-20 13:41:00
 */
 
@Controller
@RequestMapping("/jczy/xfsh")
public class XfshController extends BaseController{
	@Autowired
	private XfshService xfshService;

	@Autowired
	private AttachmentService attachmentService;

	@Autowired
	private DictService dictService;

	@Autowired
	private DeptService deptService;
	
	@GetMapping()
	@RequiresPermissions("jczy:xfsh:xfsh")
	String Xfsh(){
	    return "jczy/xfsh/xfsh";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("jczy:xfsh:xfsh")
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
		List<XfshDO> xfshList = xfshService.list(query);
		int total = xfshService.count(query);
		PageUtils pageUtils = new PageUtils(xfshList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("jczy:xfsh:add")
	String add(){
	    return "jczy/xfsh/add";
	}

	@GetMapping("/edit/{xfshTywysbm}")
	@RequiresPermissions("jczy:xfsh:edit")
	String edit(@PathVariable("xfshTywysbm") String xfshTywysbm,Model model){
		XfshDO xfsh = xfshService.get(xfshTywysbm);
		model.addAttribute("xfsh", xfsh);

		String city = dictService.findParentValue(xfsh.getXzqhdm());
		String province = dictService.findParentValue(city);
		model.addAttribute("province", province);
		model.addAttribute("city", city);

		Map m = new HashMap();
		m.put("fid",xfshTywysbm);
		m.put("fType","xfshsjt");
		List<AttachmentDO> xfshsjtList = attachmentService.list(m);
		model.addAttribute("xfshsjtList", xfshsjtList);

		m.put("fType","xfshfwt");
		List<AttachmentDO> xfshfwtList = attachmentService.list(m);
		model.addAttribute("xfshfwtList", xfshfwtList);

	    return "jczy/xfsh/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("jczy:xfsh:add")
	public R save(@RequestPart(value = "sjFile", required = false) MultipartFile[] sjFiles, @RequestPart(value = "fwFile", required = false) MultipartFile[] fwFiles, XfshDO xfsh){
		String id = UUID.randomUUID().toString().replace("-", "");
		xfsh.setXfshTywysbm(id);
		xfsh.setCdate(new Date());
		xfsh.setCperson(getUserId()+"");
		xfsh.setStatus(0);
		if(sjFiles!=null && sjFiles.length>0) {
			attachmentService.ftpUpload(sjFiles, id, "xfshsjt");
		}
		if(fwFiles!=null && fwFiles.length>0) {
			attachmentService.ftpUpload(fwFiles, id, "xfshfwt");
		}
		if(xfshService.save(xfsh)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("jczy:xfsh:edit")
	public R update(@RequestPart(value = "sjFile", required = false) MultipartFile[] sjFiles, @RequestPart(value = "fwFile", required = false) MultipartFile[] fwFiles, XfshDO xfsh){
		if(sjFiles!=null && sjFiles.length>0) {
			attachmentService.ftpUpload(sjFiles, xfsh.getXfshTywysbm(), "xfshsjt");
		}
		if(fwFiles!=null && fwFiles.length>0) {
			attachmentService.ftpUpload(fwFiles, xfsh.getXfshTywysbm(), "xfshfwt");
		}
		xfshService.update(xfsh);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("jczy:xfsh:remove")
	public R remove( String xfshTywysbm){
		if(xfshService.remove(xfshTywysbm)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("jczy:xfsh:batchRemove")
	public R remove(@RequestParam("ids[]") String[] xfshTywysbms){
		xfshService.batchRemove(xfshTywysbms);
		return R.ok();
	}
	
}
