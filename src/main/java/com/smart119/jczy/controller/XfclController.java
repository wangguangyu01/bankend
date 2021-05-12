package com.smart119.jczy.controller;

import com.smart119.common.annotation.validator.BindingResultError;
import com.smart119.common.controller.BaseController;
import com.smart119.common.domain.AttachmentDO;
import com.smart119.common.service.AttachmentService;
import com.smart119.common.utils.PageUtils;
import com.smart119.common.utils.Query;
import com.smart119.common.utils.R;
import com.smart119.jczy.domain.XfclDO;
import com.smart119.jczy.service.XfclService;
import com.smart119.jczy.service.XfclSxService;
import com.smart119.system.domain.DeptDO;
import com.smart119.system.service.DeptService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

/**
 * 消防车辆基本信息
 *
 * @author liangsl
 * @email 1992lcg@163.com
 * @date 2021-01-15 11:28:42
 */

@Controller
@RequestMapping("/jczy/xfcl")
public class XfclController extends BaseController{
	@Autowired
	private XfclService xfclService;

	@Autowired
	private XfclSxService xfclSxService;

	@Autowired
	private DeptService deptService;

	@Autowired
	private AttachmentService attachmentService;

	@GetMapping()
	@RequiresPermissions("jczy:xfcl:xfcl")
	String Xfcl(){
	    return "jczy/xfcl/xfcl";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("jczy:xfcl:xfcl")
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
		List<XfclDO> xfclList = xfclService.list(query);
		int total = xfclService.count(query);
		PageUtils pageUtils = new PageUtils(xfclList, total);
		return pageUtils;
	}
	@ResponseBody
	@GetMapping("/carlist")
	public PageUtils carlist(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<DeptDO> deptList = new ArrayList<>();
	/*	if(params.get("deptId")!=null && !params.get("deptId").equals("")){
			deptList = deptService.listChildren(Long.valueOf(params.get("deptId").toString()));
		}else{
			deptList = deptService.listChildren(getUser().getDeptId());
		}*/
		query.put("deptList",deptList);
		List<XfclDO> xfclList = xfclService.carlist(query);
		int total = xfclService.carlistcount(query);
		PageUtils pageUtils = new PageUtils(xfclList, total);
		return pageUtils;
	}
	@GetMapping("/add")
	@RequiresPermissions("jczy:xfcl:add")
	String add(@RequestParam("deptId") Long deptId,Model model){
		DeptDO  name=deptService.get(deptId);
		Map map=new HashMap();
		map.put("deptName", name.getDwmc());
		map.put("xfjyjgTywysbm", name.getXfjyjgTywysbm());
		map.put("deptId", deptId);
		model.addAttribute("map",map);
		return "jczy/xfcl/add";
	}

	@GetMapping("/edit/{xfclTywysbm}")
	@RequiresPermissions("jczy:xfcl:edit")
	String edit(@PathVariable("xfclTywysbm") String xfclTywysbm,Model model){
		XfclDO xfcl = xfclService.get(xfclTywysbm);
		Map m = new HashMap();
		m.put("fid",xfcl.getXfclTywysbm());
		m.put("fType","xfcl");
		List<AttachmentDO> attachmentDOList = attachmentService.list(m);
		model.addAttribute("attachmentDOList", attachmentDOList);
		model.addAttribute("xfcl", xfcl);
	    return "jczy/xfcl/edit";
	}

	@GetMapping("/getClInfoById/{xfclTywysbm}")
	@ResponseBody
	R getClInfoById(@PathVariable("xfclTywysbm") String xfclTywysbm){
		return R.ok(xfclService.get(xfclTywysbm));
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("jczy:xfcl:add")
	public R save(@RequestPart(value = "file", required = false) MultipartFile[] files, XfclDO xfcl,BindingResult bindingResult) throws Exception {
		if (bindingResult.hasErrors()) {
			String bindingResultError = BindingResultError.getBindingResultError(xfcl.getClass(), bindingResult);
			if (StringUtils.isNotBlank(bindingResultError)) {
				return R.error(bindingResultError);
			}
			return R.error(bindingResult.getFieldError().getDefaultMessage());
		}

		String id = UUID.randomUUID().toString().replace("-", "");
		xfcl.setXfclTywysbm(id);
		xfcl.setCdate(new Date());
		xfcl.setCperson(getUserId()+"");
		xfcl.setStatus(0);
		if(files!=null && files.length>0) {
			attachmentService.ftpUpload(files, id, "xfcl");
		}
		if(xfclService.save(xfcl)>0){
			return R.ok(xfcl);
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("jczy:xfcl:edit")
	public R update( @RequestPart(value = "file", required = false) MultipartFile[] files,@Validated XfclDO xfcl,BindingResult bindingResult) throws Exception {
		if (bindingResult.hasErrors()) {
			String bindingResultError = BindingResultError.getBindingResultError(xfcl.getClass(), bindingResult);
			if (StringUtils.isNotBlank(bindingResultError)) {
				return R.error(bindingResultError);
			}
			return R.error(bindingResult.getFieldError().getDefaultMessage());
		}

		if(files!=null && files.length>0) {
			attachmentService.ftpUpload(files, xfcl.getXfclTywysbm(), "xfcl");
		}
		xfclService.update(xfcl);
		return R.ok(xfcl);
	}

	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/websave")
	public String websave( @RequestBody XfclDO xfcl){
		xfcl.setXfclTywysbm(UUID.randomUUID().toString().replace("-",""));
		xfcl.setStatus(0);
		xfcl.setCdate(new Date());
		xfcl.setCperson(getUserId()+"");
		xfclService.save(xfcl);
		return xfcl.getXfclTywysbm();
	}

	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/webupdate")
	public String webupdate( @RequestBody XfclDO xfcl){
		xfclService.update(xfcl);
		return xfcl.getXfclTywysbm();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("jczy:xfcl:remove")
	public R remove( String xfclTywysbm){
		xfclSxService.removeByXfclId(xfclTywysbm);  //在删除车辆信息的同时 删除车辆的属性信息
		if(xfclService.remove(xfclTywysbm)>0){
		return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("jczy:xfcl:batchRemove")
	public R remove(@RequestParam("ids[]") String[] xfclTywysbms){
		for(String xfclTywysbm:xfclTywysbms){
			xfclSxService.removeByXfclId(xfclTywysbm);   //在删除车辆信息的同时 删除车辆的属性信息
		}
		xfclService.batchRemove(xfclTywysbms);
		return R.ok();
	}

	@GetMapping("/sxList")
	@RequiresPermissions("jczy:xfcl:add")
	String sxList(){
		return "jczy/xfclSx/xfclSx";
	}


	/**
	 * 删除
	 */
	@GetMapping( "/removeCar")
	@ResponseBody
	public R removeCar(@RequestParam Map<String,String> params){
		String xfclTywysbm = params.get("xfclTywysbm");
		xfclSxService.removeByXfclId(xfclTywysbm);  //在删除车辆信息的同时 删除车辆的属性信息
		if(xfclService.remove(xfclTywysbm)>0){
			return R.ok();
		}
		return R.error();
	}


}
