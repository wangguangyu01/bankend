package com.smart119.jczy.controller;

import java.util.*;

import com.alibaba.fastjson.JSON;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.smart119.jczy.domain.XfscDO;
import com.smart119.jczy.service.XfscService;
import com.smart119.common.utils.PageUtils;
import com.smart119.common.utils.Query;
import com.smart119.common.utils.R;
import org.springframework.web.multipart.MultipartFile;

/**
 * 消防水池基本信息
 * 
 * @author thrz
 * @email thrz@sz000673.com
 * @date 2021-01-21 15:07:01
 */
 
@Controller
@RequestMapping("/jczy/xfsc")
public class XfscController extends BaseController{
	@Autowired
	private XfscService xfscService;

	@Autowired
	private AttachmentService attachmentService;

	@Autowired
	private DictService dictService;

	@Autowired
	private DeptService deptService;
	
	@GetMapping()
	@RequiresPermissions("jczy:xfsc:xfsc")
	String Xfsc(){
	    return "jczy/xfsc/xfsc";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("jczy:xfsc:xfsc")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<XfscDO> xfscList = xfscService.list(query);
		int total = xfscService.count(query);
		PageUtils pageUtils = new PageUtils(xfscList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("jczy:xfsc:add")
	String add(){
	    return "jczy/xfsc/add";
	}

	@GetMapping("/edit/{xfscTywysbm}")
	@RequiresPermissions("jczy:xfsc:edit")
	String edit(@PathVariable("xfscTywysbm") String xfscTywysbm,Model model){
		XfscDO xfsc = xfscService.get(xfscTywysbm);
		model.addAttribute("xfsc", xfsc);
		Map m = new HashMap();
		m.put("fid",xfscTywysbm);
		m.put("fType","xfscsjt");
		List<AttachmentDO> xfscsjtList = attachmentService.list(m);
		model.addAttribute("xfscsjtList", xfscsjtList);

		m.put("fType","xfscfwt");
		List<AttachmentDO> xfscfwtList = attachmentService.list(m);
		model.addAttribute("xfscfwtList", xfscfwtList);
	    return "jczy/xfsc/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("jczy:xfsc:add")
	public R save(@RequestPart(value = "sjFile", required = false) MultipartFile[] sjFiles, @RequestPart(value = "fwFile", required = false) MultipartFile[] fwFiles,@Validated XfscDO xfsc){
		String id = UUID.randomUUID().toString().replace("-", "");
		xfsc.setXfscTywysbm(id);
		xfsc.setCdate(new Date());
		xfsc.setCperson(getUserId()+"");
		xfsc.setStatus(0);
		if(sjFiles!=null && sjFiles.length>0) {
			attachmentService.ftpUpload(sjFiles, id, "xfscsjt");
		}
		if(fwFiles!=null && fwFiles.length>0) {
			attachmentService.ftpUpload(fwFiles, id, "xfscfwt");
		}
		if(xfscService.save(xfsc)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("jczy:xfsc:edit")
	public R update(@RequestPart(value = "sjFile", required = false) MultipartFile[] sjFiles, @RequestPart(value = "fwFile", required = false) MultipartFile[] fwFiles,@Validated XfscDO xfsc){
		if(sjFiles!=null && sjFiles.length>0) {
			attachmentService.ftpUpload(sjFiles, xfsc.getXfscTywysbm(), "xfscsjt");
		}
		if(fwFiles!=null && fwFiles.length>0) {
			attachmentService.ftpUpload(fwFiles, xfsc.getXfscTywysbm(), "xfscfwt");
		}
		xfscService.update(xfsc);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("jczy:xfsc:remove")
	public R remove( String xfscTywysbm){
		if(xfscService.remove(xfscTywysbm)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("jczy:xfsc:batchRemove")
	public R remove(@RequestParam("ids[]") String[] xfscTywysbms){
		xfscService.batchRemove(xfscTywysbms);
		return R.ok();
	}
	
}
