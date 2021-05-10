package com.smart119.jczy.controller;

import java.util.*;

import com.alibaba.fastjson.JSON;
import com.smart119.common.annotation.validator.BindingResultError;
import com.smart119.common.controller.BaseController;
import com.smart119.common.domain.AttachmentDO;
import com.smart119.common.service.AttachmentService;
import com.smart119.jczy.domain.XfclDO;
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

import com.smart119.jczy.domain.XfzbDO;
import com.smart119.jczy.service.XfzbService;
import com.smart119.common.utils.PageUtils;
import com.smart119.common.utils.Query;
import com.smart119.common.utils.R;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.executable.ValidateOnExecution;

/**
 * 消防装备器材基本信息

 *
 * @author liangsl
 * @email 1992lcg@163.com
 * @date 2021-01-18 15:16:57
 */

@Controller
@RequestMapping("/jczy/xfzb")
public class XfzbController extends BaseController{
	@Autowired
	private XfzbService xfzbService;
	@Autowired
	private DeptService deptService;
	@Autowired
	private AttachmentService attachmentService;

	@GetMapping()
	@RequiresPermissions("jczy:xfzb:xfzb")
	String Xfzb(){
	    return "jczy/xfzb/xfzb";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("jczy:xfzb:xfzb")
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
		List<XfzbDO> xfzbList = xfzbService.list(query);
		int total = xfzbService.count(query);
		PageUtils pageUtils = new PageUtils(xfzbList, total);
		return pageUtils;
	}

	@GetMapping("/add")
	@RequiresPermissions("jczy:xfzb:add")
	String add(){
	    return "jczy/xfzb/add";
	}

	@GetMapping("/edit/{xfzbTywysbm}")
	@RequiresPermissions("jczy:xfzb:edit")
	String edit(@PathVariable("xfzbTywysbm") String xfzbTywysbm,Model model){
		XfzbDO xfzb = xfzbService.get(xfzbTywysbm);
		Map m = new HashMap();
		m.put("fid",xfzbTywysbm);
		m.put("fType","xfzb");
		List<AttachmentDO> attachmentDOList = attachmentService.list(m);
		model.addAttribute("attachmentDOList", JSON.toJSONString(attachmentDOList));
		model.addAttribute("xfzb", xfzb);
	    return "jczy/xfzb/edit";
	}

	/**
	 * @Description: APP回显
	 * @Param: [xfzbTywysbm, model]
	 * @return: java.lang.String
	 * @Author: yanyu
	 * @Date: 2021/2/8
	 */
	@GetMapping("/appZbedit/{xfzbTywysbm}")
	@RequiresPermissions("jczy:xfzb:edit")
	@ResponseBody
	R appZbedit(@PathVariable("xfzbTywysbm") String xfzbTywysbm){
		XfzbDO xfzb = xfzbService.get(xfzbTywysbm);
		return R.ok(xfzb);
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("jczy:xfzb:add")
	public R save(@RequestPart(value = "file", required = false) MultipartFile[] files,@Validated XfzbDO xfzb){
		String id = UUID.randomUUID().toString().replace("-", "");
		xfzb.setXfzbTywysbm(id);
		xfzb.setCdate(new Date());
		xfzb.setCperson(getUserId()+"");
		xfzb.setStatus(0);
		if(files!=null && files.length>0) {
			attachmentService.ftpUpload(files, id, "xfzb");
		}
		if(xfzbService.save(xfzb)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/zdsave")
	@RequiresPermissions("jczy:xfzb:add")
	public R zdsave(@RequestBody XfzbDO xfzb){
		String id = UUID.randomUUID().toString().replace("-", "");
		xfzb.setXfzbTywysbm(id);
		xfzb.setCdate(new Date());
		xfzb.setCperson(getUserId()+"");
		xfzb.setStatus(0);
		if(xfzbService.save(xfzb)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("jczy:xfzb:edit")
	public R update( @RequestPart(value = "file", required = false) MultipartFile[] files,
					 @Validated XfzbDO xfzb,
					 BindingResult bindingResult) throws Exception {
		if (bindingResult.hasErrors()) {
			String bindingResultError = BindingResultError.getBindingResultError(xfzb.getClass(), bindingResult);
			if (StringUtils.isNotBlank(bindingResultError)) {
				return R.error(bindingResultError);
			}
			return R.error(bindingResult.getFieldError().getDefaultMessage());
		}
		if(files!=null && files.length>0) {
			attachmentService.ftpUpload(files, xfzb.getXfzbTywysbm(), "xfzb");
		}
		xfzbService.update(xfzb);
		return R.ok();
	}
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("jczy:xfzb:remove")
	public R remove( String xfzbTywysbm){
		if(xfzbService.remove(xfzbTywysbm)>0){
		return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("jczy:xfzb:batchRemove")
	public R remove(@RequestParam("ids[]") String[] xfzbTywysbms){
		xfzbService.batchRemove(xfzbTywysbms);
		return R.ok();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update2")
	public R update2(@RequestBody XfzbDO xfzb){
		xfzbService.update(xfzb);
		return R.ok();
	}
}
