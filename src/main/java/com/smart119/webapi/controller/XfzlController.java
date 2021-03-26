package com.smart119.webapi.controller;


import com.smart119.common.controller.BaseController;
import com.smart119.common.domain.AttachmentDO;
import com.smart119.common.service.AttachmentService;
import com.smart119.common.service.DictService;
import com.smart119.common.utils.PageUtils;
import com.smart119.common.utils.R;
import com.smart119.system.domain.UserDO;
import com.smart119.webapi.domain.XfzlDO;
import com.smart119.webapi.service.XfzlService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 消防战例
 *
 * @author liangsl
 * @email liangsl@sz000673.com
 * @date 2021-03-11 14:56:44
 */
@Controller
@RequestMapping("/back/xfzl")
@Slf4j
public class XfzlController extends BaseController {


	@Autowired
	private XfzlService xfzlService;



	@Resource
	private DictService dictService;

	@Resource
	private AttachmentService attachmentService;



	@GetMapping()
	@RequiresPermissions("back:xfzl:xfzl")
	String Xfzl(){
		return "xfzl/xfzl";
	}





	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("back:xfzl:xfzl")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
		PageUtils page = xfzlService.queryPage(params);
		return page;
	}


	@GetMapping("/add")
	@RequiresPermissions("back:xfzl:add")
	String add(Model model){
		List dictList = dictService.queryByDictType("JQFLYDM");
		model.addAttribute("dictList", dictList);
		return "xfzl/add";
	}


	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("back:xfzl:add")
	public R save(@RequestPart(value = "file", required = false) MultipartFile[] file, XfzlDO xfzl){
		UserDO user = (UserDO) SecurityUtils.getSubject().getPrincipal();
		if(xfzlService.save(xfzl, user.getUsername())>0){
			if(file!=null && file.length>0) {
				attachmentService.ftpUpload(file, xfzl.getXfzlId(), "xfzl_slt");
			}
			return R.ok();
		}
		return R.error();
	}



	@GetMapping("/edit/{xfzlId}")
	@RequiresPermissions("back:xfzl:edit")
	String edit(@PathVariable("xfzlId") String xfzlId,Model model){
		XfzlDO xfzl = xfzlService.queryById(xfzlId);
		Map param = new HashMap();
		param.put("fid",xfzlId);
		param.put("fType","xfzl_slt");
		List<AttachmentDO> attachmentDOList = attachmentService.list(param);
		List dictList = dictService.queryByDictType("JQFLYDM");
		model.addAttribute("dictList", dictList);
		model.addAttribute("xfzl", xfzl);
		model.addAttribute("attachmentDOList", attachmentDOList);
		return "xfzl/edit";
	}


	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("back:xfzl:edit")
	public R update(@RequestPart(value = "file", required = false) MultipartFile[] file, XfzlDO xfzl){
		UserDO user = (UserDO) SecurityUtils.getSubject().getPrincipal();
		 int count = xfzlService.update(xfzl, user.getUsername());
		if (count > 0) {
			if(file!=null && file.length>0) {
				attachmentService.ftpUpload(file, xfzl.getXfzlId(), "xfzl_slt");
			}
			return R.ok();
		}
		return R.error();
	}



	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("back:xfzl:remove")
	public R remove( String xfzlId){
		if(xfzlService.remove(xfzlId)>0){
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("back:xfzl:batchRemove")
	public R remove(@RequestParam("ids[]") String[] xfzlIds){
		xfzlService.batchRemove(xfzlIds);
		return R.ok();
	}



}
