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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.smart119.jczy.domain.XhsDO;
import com.smart119.jczy.service.XhsService;
import com.smart119.common.utils.PageUtils;
import com.smart119.common.utils.Query;
import com.smart119.common.utils.R;
import org.springframework.web.multipart.MultipartFile;

/**
 * 消火栓基本信息
 * 
 * @author thrz
 * @email thrz@sz000673.com
 * @date 2021-01-19 14:57:59
 */
 
@Controller
@RequestMapping("/jczy/xhs")
public class XhsController extends BaseController{
	@Autowired
	private XhsService xhsService;

	@Autowired
	private AttachmentService attachmentService;

	@Autowired
	private DictService dictService;

	@Autowired
	private DeptService deptService;
	
	@GetMapping()
	@RequiresPermissions("jczy:xhs:xhs")
	String Xhs(){
	    return "jczy/xhs/xhs";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("jczy:xhs:xhs")
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
		List<XhsDO> xhsList = xhsService.list(query);
		int total = xhsService.count(query);
		PageUtils pageUtils = new PageUtils(xhsList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("jczy:xhs:add")
	String add(){
	    return "jczy/xhs/add";
	}

	@GetMapping("/edit/{xhsTywysbm}")
	@RequiresPermissions("jczy:xhs:edit")
	String edit(@PathVariable("xhsTywysbm") String xhsTywysbm,Model model){
		XhsDO xhs = xhsService.get(xhsTywysbm);
		model.addAttribute("xhs", xhs);

		String city = dictService.findParentValue(xhs.getXzqhdm());
		String province = dictService.findParentValue(city);
		model.addAttribute("province", province);
		model.addAttribute("city", city);

		Map m = new HashMap();
		m.put("fid",xhsTywysbm);
		m.put("fType","xhssjt");
		List<AttachmentDO> xhssjtList = attachmentService.list(m);
		model.addAttribute("xhssjtList", xhssjtList);

		m.put("fType","xhsfwt");
		List<AttachmentDO> xhsfwtList = attachmentService.list(m);
		model.addAttribute("xhsfwtList", xhsfwtList);


	    return "jczy/xhs/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("jczy:xhs:add")
	public R save(@RequestPart(value = "sjFile", required = false) MultipartFile[] sjFiles, @RequestPart(value = "fwFile", required = false) MultipartFile[] fwFiles,@Validated XhsDO xhs){
		String id = UUID.randomUUID().toString().replace("-", "");
		xhs.setXhsTywysbm(id);
		xhs.setCdate(new Date());
		xhs.setCperson(getUserId()+"");
		xhs.setStatus(0);
		if(sjFiles!=null && sjFiles.length>0) {
			attachmentService.ftpUpload(sjFiles, id, "xhssjt");
		}
		if(fwFiles!=null && fwFiles.length>0) {
			attachmentService.ftpUpload(fwFiles, id, "xhsfwt");
		}
		if(xhsService.save(xhs)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("jczy:xhs:edit")
	public R update(@RequestPart(value = "sjFile", required = false) MultipartFile[] sjFiles, @RequestPart(value = "fwFile", required = false) MultipartFile[] fwFiles,@Validated XhsDO xhs){
		if(sjFiles!=null && sjFiles.length>0) {
			attachmentService.ftpUpload(sjFiles, xhs.getXhsTywysbm(), "xhssjt");
		}
		if(fwFiles!=null && fwFiles.length>0) {
			attachmentService.ftpUpload(fwFiles, xhs.getXhsTywysbm(), "xhsfwt");
		}
		xhsService.update(xhs);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("jczy:xhs:remove")
	public R remove( String xhsTywysbm){
		if(xhsService.remove(xhsTywysbm)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("jczy:xhs:batchRemove")
	public R remove(@RequestParam("ids[]") String[] xhsTywysbms){
		xhsService.batchRemove(xhsTywysbms);
		return R.ok();
	}
	
}
