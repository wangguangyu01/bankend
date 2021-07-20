package com.smart119.jczy.controller;

import java.util.*;

import com.smart119.common.annotation.validator.BindingResultError;
import com.smart119.common.controller.BaseController;
import com.smart119.common.domain.AttachmentDO;
import com.smart119.common.service.AttachmentService;
import com.smart119.common.service.DictService;
import com.smart119.jczy.domain.XfjyryDO;
import com.smart119.system.domain.DeptDO;
import com.smart119.system.service.DeptService;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.smart119.jczy.domain.ZddwDO;
import com.smart119.jczy.service.ZddwService;
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

@Api(value = "重点单位管理", description = "接警端重点单位管理API")
@Controller
@RequestMapping("/jczy/zddw")
public class ZddwController extends BaseController{

	@Autowired
	private ZddwService zddwService;

	@Autowired
	private DictService dictService;

	@Autowired
	private DeptService deptService;

	@Autowired
	private AttachmentService attachmentService;
	
	@GetMapping()
	@RequiresPermissions("jczy:zddw:zddw")
	String Zddw(){
	    return "jczy/zddw/zddw";
	}



	@ResponseBody
	@GetMapping("/listWithPage")
	public PageUtils listWithPage(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ZddwDO> zddwList = zddwService.list(query);
		int total = zddwService.count(query);
		PageUtils pageUtils = new PageUtils(zddwList, total);
		return pageUtils;
	}



	@ApiOperation("查询重点单位信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "dwmc", value = "单位名称", required = true, dataType = "String",dataTypeClass = String.class,paramType = "query")
	})
	@ResponseBody
	@GetMapping("/list")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "访问成功",response= ZddwDO.class)})
	public PageUtils list(@RequestParam Map<String, Object> params){
		if(null == params.get("offset")){
			params.put("offset",0);
		}
		if(null == params.get("limit")){
			params.put("limit",9999);
		}
		Query query = new Query(params);
		List<DeptDO> deptList = new ArrayList<>();
		if(params.get("deptId")!=null && !params.get("deptId").equals("")){
			deptList = deptService.listChildren(Long.valueOf(params.get("deptId").toString()));
		}else{
			deptList = deptService.listChildren(getUser().getDeptId());
		}
		query.put("deptList",deptList);
		List<ZddwDO> zddwList = zddwService.list(query);
		int total = zddwService.count(query);
		PageUtils pageUtils = new PageUtils(zddwList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("jczy:zddw:add")
	String add(){
	    return "jczy/zddw/add";
	}

	@GetMapping("/edit/{zddwTywysbm}")
	@RequiresPermissions("jczy:zddw:edit")
	String edit(@PathVariable("zddwTywysbm") String zddwTywysbm,Model model){
		ZddwDO zddw = zddwService.get(zddwTywysbm);
		model.addAttribute("zddw", zddw);
		String city = dictService.findParentValue(zddw.getXzqhdm());
		String province = dictService.findParentValue(city);
		model.addAttribute("province", province);
		model.addAttribute("city", city);
		//回显附件
		Map m = new HashMap();
		m.put("fid",zddwTywysbm);
		m.put("fType","zddwtp");
		List<AttachmentDO> zddwtpList = attachmentService.list(m);
		model.addAttribute("zddwtpList", zddwtpList);
	    return "jczy/zddw/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("jczy:zddw:add")
	public R save(
			@RequestPart(value = "zddwFile", required = false) MultipartFile[] zddwFile,
			@RequestPart(value = "yuanFile", required = false) MultipartFile[] yuanFile,
			@Validated ZddwDO zddw,BindingResult bindingResult) throws Exception {
		if (bindingResult.hasErrors()) {
			String bindingResultError = BindingResultError.getBindingResultError(zddw.getClass(), bindingResult);
			if (StringUtils.isNotBlank(bindingResultError)) {
				return R.error(bindingResultError);
			}
			return R.error(bindingResult.getFieldError().getDefaultMessage());
		}

		String id = UUID.randomUUID().toString().replace("-", "");
		zddw.setZddwTywysbm(id);
		//重点单位增加图片
		if(zddwFile!=null && zddwFile.length>0) {
			attachmentService.ftpUpload(zddwFile, id, "zddwtp");
		}
		//预案附件
		if(yuanFile!=null && yuanFile.length>0) {
			attachmentService.ftpUpload(yuanFile, id, "yuanFile");
		}
		if(zddwService.save(zddw)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("jczy:zddw:edit")
	public R update(
			@RequestPart(value = "zddwFile", required = false) MultipartFile[] zddwFile,
			@RequestPart(value = "yuanFile", required = false) MultipartFile[] yuanFile,
			@Validated ZddwDO zddw,BindingResult bindingResult) throws Exception {
		if (bindingResult.hasErrors()) {
			String bindingResultError = BindingResultError.getBindingResultError(zddw.getClass(), bindingResult);
			if (StringUtils.isNotBlank(bindingResultError)) {
				return R.error(bindingResultError);
			}
			return R.error(bindingResult.getFieldError().getDefaultMessage());
		}
		if(zddwFile!=null && zddwFile.length>0) {
			attachmentService.ftpUpload(zddwFile, zddw.getZddwTywysbm(), "zddwtp");
		}
		//预案附件
		if(yuanFile!=null && yuanFile.length>0) {
			attachmentService.ftpUpload(yuanFile, zddw.getZddwTywysbm(), "yuanFile");
		}
		zddwService.update(zddw);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("jczy:zddw:remove")
	public R remove( String zddwTywysbm){
		if(zddwService.remove(zddwTywysbm)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("jczy:zddw:batchRemove")
	public R remove(@RequestParam("ids[]") String[] zddwTywysbms){
		zddwService.batchRemove(zddwTywysbms);
		return R.ok();
	}

	@GetMapping("/selectZddw")
	String selectZddw(String xfjyjgTywysbm, Model model) {
		model.addAttribute("xfjyjgTywysbm",xfjyjgTywysbm);
		return "jczy/zddw/selectZddw";
	}

	@ResponseBody
	@GetMapping("/selectZddwList")
	public PageUtils selectZddwList(@RequestParam Map<String, Object> params) {
		Query query = new Query(params);
		List<ZddwDO> zddwList = zddwService.list(query);
		int total = zddwService.count(query);
		PageUtils pageUtils = new PageUtils(zddwList, total);
		return pageUtils;
	}
}
