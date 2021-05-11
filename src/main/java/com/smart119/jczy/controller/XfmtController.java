package com.smart119.jczy.controller;

import java.util.*;

import com.smart119.common.annotation.validator.BindingResultError;
import com.smart119.common.controller.BaseController;
import com.smart119.common.domain.AttachmentDO;
import com.smart119.common.service.AttachmentService;
import com.smart119.common.service.DictService;
import com.smart119.system.domain.DeptDO;
import com.smart119.system.service.DeptService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.smart119.jczy.domain.XfmtDO;
import com.smart119.jczy.service.XfmtService;
import com.smart119.common.utils.PageUtils;
import com.smart119.common.utils.Query;
import com.smart119.common.utils.R;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author thrz
 * @email thrz@sz000673.com
 * @date 2021-01-20 15:29:22
 */
 
@Controller
@RequestMapping("/jczy/xfmt")
public class XfmtController extends BaseController{
	@Autowired
	private XfmtService xfmtService;

	@Autowired
	private DictService dictService;

	@Autowired
	private AttachmentService attachmentService;

	@Autowired
	private DeptService deptService;
	
	@GetMapping()
	@RequiresPermissions("jczy:xfmt:xfmt")
	String Xfmt(){
	    return "jczy/xfmt/xfmt";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("jczy:xfmt:xfmt")
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
		List<XfmtDO> xfmtList = xfmtService.list(query);
		int total = xfmtService.count(query);
		PageUtils pageUtils = new PageUtils(xfmtList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("jczy:xfmt:add")
	String add(){
	    return "jczy/xfmt/add";
	}

	@GetMapping("/edit/{qsmtTywysbm}")
	@RequiresPermissions("jczy:xfmt:edit")
	String edit(@PathVariable("qsmtTywysbm") String qsmtTywysbm,Model model){
		XfmtDO xfmt = xfmtService.get(qsmtTywysbm);
		model.addAttribute("xfmt", xfmt);
		String city = dictService.findParentValue(xfmt.getXzqhdm());
		String province = dictService.findParentValue(city);
		model.addAttribute("province", province);
		model.addAttribute("city", city);

		Map m = new HashMap();
		m.put("fid",qsmtTywysbm);
		m.put("fType","xfmtsjt");
		List<AttachmentDO> xfmtsjtList = attachmentService.list(m);
		model.addAttribute("xfmtsjtList", xfmtsjtList);

		m.put("fType","xfmtfwt");
		List<AttachmentDO> xfmtfwtList = attachmentService.list(m);
		model.addAttribute("xfmtfwtList", xfmtfwtList);

	    return "jczy/xfmt/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("jczy:xfmt:add")
	public R save(@RequestPart(value = "sjFile", required = false) MultipartFile[] sjFiles, @RequestPart(value = "fwFile", required = false) MultipartFile[] fwFiles,@Validated XfmtDO xfmt,BindingResult bindingResult) throws Exception {
		if (bindingResult.hasErrors()) {
			String bindingResultError = BindingResultError.getBindingResultError(xfmt.getClass(), bindingResult);
			if (StringUtils.isNotBlank(bindingResultError)) {
				return R.error(bindingResultError);
			}
			return R.error(bindingResult.getFieldError().getDefaultMessage());
		}
		String id = UUID.randomUUID().toString().replace("-", "");
		xfmt.setQsmtTywysbm(id);
		if(sjFiles!=null && sjFiles.length>0) {
			attachmentService.ftpUpload(sjFiles, id, "xfmtsjt");
		}
		if(fwFiles!=null && fwFiles.length>0) {
			attachmentService.ftpUpload(fwFiles, id, "xfmtfwt");
		}
		if(xfmtService.save(xfmt)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("jczy:xfmt:edit")
	public R update(@RequestPart(value = "sjFile", required = false) MultipartFile[] sjFiles, @RequestPart(value = "fwFile", required = false) MultipartFile[] fwFiles,@Validated XfmtDO xfmt,BindingResult bindingResult) throws Exception {
		if (bindingResult.hasErrors()) {
			String bindingResultError = BindingResultError.getBindingResultError(xfmt.getClass(), bindingResult);
			if (StringUtils.isNotBlank(bindingResultError)) {
				return R.error(bindingResultError);
			}
			return R.error(bindingResult.getFieldError().getDefaultMessage());
		}
		if(sjFiles!=null && sjFiles.length>0) {
			attachmentService.ftpUpload(sjFiles, xfmt.getQsmtTywysbm(), "xfmtsjt");
		}
		if(fwFiles!=null && fwFiles.length>0) {
			attachmentService.ftpUpload(fwFiles, xfmt.getQsmtTywysbm(), "xfmtfwt");
		}
		xfmtService.update(xfmt);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("jczy:xfmt:remove")
	public R remove( String qsmtTywysbm){
		if(xfmtService.remove(qsmtTywysbm)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("jczy:xfmt:batchRemove")
	public R remove(@RequestParam("ids[]") String[] qsmtTywysbms){
		xfmtService.batchRemove(qsmtTywysbms);
		return R.ok();
	}
	
}
