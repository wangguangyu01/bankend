package com.smart119.jczy.controller;

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

import com.smart119.jczy.domain.YbcsDO;
import com.smart119.jczy.service.YbcsService;
import com.smart119.common.utils.PageUtils;
import com.smart119.common.utils.Query;
import com.smart119.common.utils.R;
import org.springframework.web.multipart.MultipartFile;

/**
 * 一般场所
 * 
 * @author shilei
 * @email yangjiyu@sz000673.com
 * @date 2021-10-21 10:04:30
 */
 
@Controller
@RequestMapping("/jczy/ybcs")
public class YbcsController extends BaseController {
	@Autowired
	private YbcsService ybcsService;

	@Autowired
	private AttachmentService attachmentService;
	
	@GetMapping()
	@RequiresPermissions("jczy:ybcs:ybcs")
	String Ybcs(){
	    return "jczy/ybcs/ybcs";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("jczy:ybcs:ybcs")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<YbcsDO> ybcsList = ybcsService.list(query);
		int total = ybcsService.count(query);
		PageUtils pageUtils = new PageUtils(ybcsList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("jczy:ybcs:add")
	String add(){
	    return "jczy/ybcs/add";
	}

	@GetMapping("/edit/{ybcsTywysbm}")
	@RequiresPermissions("jczy:ybcs:edit")
	String edit(@PathVariable("ybcsTywysbm") String ybcsTywysbm,Model model){
		YbcsDO ybcs = ybcsService.get(ybcsTywysbm);

		Map m = new HashMap();
		m.put("fid",ybcsTywysbm);
		m.put("fType","wjt");
		List<AttachmentDO> wjtList = attachmentService.list(m);
		model.addAttribute("wjt", wjtList);

		m.put("fType","ylsn");
		List<AttachmentDO> ylsnList = attachmentService.list(m);
		model.addAttribute("ylsn", ylsnList);

		m.put("fType","elsn");
		List<AttachmentDO> elsnList = attachmentService.list(m);
		model.addAttribute("elsn", elsnList);

		m.put("fType","slsn");
		List<AttachmentDO> slsnList = attachmentService.list(m);
		model.addAttribute("slsn", slsnList);

		model.addAttribute("ybcs", ybcs);
	    return "jczy/ybcs/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("jczy:ybcs:add")
	public R save(
			@RequestPart(value = "wjt", required = false) MultipartFile[] wjt,   //外景图
			@RequestPart(value = "ylsn", required = false) MultipartFile[] ylsn,   //一楼室内
			@RequestPart(value = "elsn", required = false) MultipartFile[] elsn,   //二楼室内
			@RequestPart(value = "slsn", required = false) MultipartFile[] slsn,   //三楼室内
			@Validated YbcsDO ybcs){
		String id = UUID.randomUUID().toString().replace("-", "");
		ybcs.setYbcsTywysbm(id);
		ybcs.setCdate(new Date());
		ybcs.setCperson(getUserId()+"");
		ybcs.setStatus("0");
		if(ybcs.getSfcfwxp()==null || "".equals(ybcs.getSfcfwxp())){
			ybcs.setSfcfwxp("无");
		}
		if(wjt!=null && wjt.length>0) {
			attachmentService.ftpUpload(wjt, id, "wjt");
		}
		if(ylsn!=null && ylsn.length>0) {
			attachmentService.ftpUpload(ylsn, id, "ylsn");
		}
		if(elsn!=null && elsn.length>0) {
			attachmentService.ftpUpload(elsn, id, "elsn");
		}
		if(slsn!=null && slsn.length>0) {
			attachmentService.ftpUpload(slsn, id, "slsn");
		}
		if(ybcsService.save(ybcs)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("jczy:ybcs:edit")
	public R update(
			@RequestPart(value = "wjt", required = false) MultipartFile[] wjt,   //外景图
			@RequestPart(value = "ylsn", required = false) MultipartFile[] ylsn,   //一楼室内
			@RequestPart(value = "elsn", required = false) MultipartFile[] elsn,   //二楼室内
			@RequestPart(value = "slsn", required = false) MultipartFile[] slsn,   //三楼室内
			@Validated YbcsDO ybcs){
		if(ybcs.getSfcfwxp()==null || "".equals(ybcs.getSfcfwxp())){
			ybcs.setSfcfwxp("无");
		}
		if(ybcs.getSfcfwxp()==null || "".equals(ybcs.getSfcfwxp())){
			ybcs.setSfcfwxp("无");
		}
		if(wjt!=null && wjt.length>0) {
			attachmentService.ftpUpload(wjt, ybcs.getYbcsTywysbm(), "wjt");
		}
		if(ylsn!=null && ylsn.length>0) {
			attachmentService.ftpUpload(ylsn, ybcs.getYbcsTywysbm(), "ylsn");
		}
		if(elsn!=null && elsn.length>0) {
			attachmentService.ftpUpload(elsn, ybcs.getYbcsTywysbm(), "elsn");
		}
		if(slsn!=null && slsn.length>0) {
			attachmentService.ftpUpload(slsn, ybcs.getYbcsTywysbm(), "slsn");
		}
		ybcsService.update(ybcs);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("jczy:ybcs:remove")
	public R remove( String ybcsTywysbm){
		if(ybcsService.remove(ybcsTywysbm)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("jczy:ybcs:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ybcsTywysbms){
		ybcsService.batchRemove(ybcsTywysbms);
		return R.ok();
	}
	
}
