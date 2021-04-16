package com.smart119.xwzx.controller;

import java.util.*;

import com.smart119.common.controller.BaseController;
import com.smart119.common.domain.AttachmentDO;
import com.smart119.common.service.AttachmentService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.smart119.xwzx.domain.XwZxDO;
import com.smart119.xwzx.service.XwZxService;
import com.smart119.common.utils.PageUtils;
import com.smart119.common.utils.Query;
import com.smart119.common.utils.R;
import org.springframework.web.multipart.MultipartFile;

/**
 * 新闻资讯
 * 
 * @author liangsl
 * @email liangshengli@sz000673.com
 * @date 2021-03-23 16:15:12
 */
 
@Controller
@RequestMapping("/xwzx/xwZx")
public class XwZxController extends BaseController{
	@Autowired
	private XwZxService xwZxService;

	@Autowired
	private AttachmentService attachmentService;
	
	@GetMapping()
	@RequiresPermissions("xwzx:xwZx:xwZx")
	String XwZx(){
	    return "xwzx/xwZx/xwZx";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("xwzx:xwZx:xwZx")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<XwZxDO> xwZxList = xwZxService.list(query);
		int total = xwZxService.count(query);
		PageUtils pageUtils = new PageUtils(xwZxList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("xwzx:xwZx:add")
	String add(){
	    return "xwzx/xwZx/add";
	}

	@GetMapping("/edit/{xwZxId}")
	@RequiresPermissions("xwzx:xwZx:edit")
	String edit(@PathVariable("xwZxId") String xwZxId,Model model){
		XwZxDO xwZx = xwZxService.get(xwZxId);
		model.addAttribute("xwZx", xwZx);
		Map m = new HashMap();
		m.put("fid",xwZxId);
		m.put("fType","xwzx");
		List<AttachmentDO> attachmentDOList = attachmentService.list(m);
		model.addAttribute("attachmentDOList", attachmentDOList);
	    return "xwzx/xwZx/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("xwzx:xwZx:add")
	public R save(@RequestPart(value = "file", required = false) MultipartFile[] file, @Validated XwZxDO xwZx){
		String id = UUID.randomUUID().toString().replace("-", "");
		Date now = new Date();
		xwZx.setXwZxId(id);
		xwZx.setStatus("0");
		xwZx.setCdate(now);
		xwZx.setCpserson(getUserId()+"");
		xwZx.setDzCs(0);
		xwZx.setLlCs(0);
		xwZx.setFbsj(now);
		if(file!=null && file.length>0) {
			attachmentService.ftpUpload(file, id, "xwzx");
		}
		if(xwZxService.save(xwZx)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("xwzx:xwZx:edit")
	public R update(@RequestPart(value = "file", required = false) MultipartFile[] file, @Validated XwZxDO xwZx){
		if(file!=null && file.length>0) {
			attachmentService.ftpUpload(file, xwZx.getXwZxId(), "xwzx");
		}
		xwZxService.update(xwZx);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("xwzx:xwZx:remove")
	public R remove( String xwZxId){
		if(xwZxService.remove(xwZxId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("xwzx:xwZx:batchRemove")
	public R remove(@RequestParam("ids[]") String[] xwZxIds){
		xwZxService.batchRemove(xwZxIds);
		return R.ok();
	}
	
}
