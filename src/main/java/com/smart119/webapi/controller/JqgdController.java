package com.smart119.webapi.controller;

import java.util.*;

import com.smart119.common.controller.BaseController;
import com.smart119.common.domain.AttachmentDO;
import com.smart119.common.service.AttachmentService;
import com.smart119.webapi.domain.JbxxDO;
import com.smart119.webapi.domain.SeatsDO;
import com.smart119.webapi.service.JbxxService;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smart119.webapi.domain.JqgdDO;
import com.smart119.webapi.service.JqgdService;
import com.smart119.common.utils.PageUtils;
import com.smart119.common.utils.Query;
import com.smart119.common.utils.R;
import org.springframework.web.multipart.MultipartFile;

/**
 * 警情归档信息表
 * 
 * @author xuantianlong
 * @date 2021-01-28 14:23:42
 */
 
@Controller
@RequestMapping("/webapi/jqgd")
public class JqgdController extends BaseController {
	@Autowired
	private JqgdService jqgdService;

	@Autowired
	private JbxxService jbxxService;

	//附件业务
	@Autowired
	private AttachmentService attachmentService;
	
	@GetMapping()
	@RequiresPermissions("webapi:jqgd:jqgd")
	String Jqgd(){
	    return "webapi/jqgd/jqgd";
	}

	/**
	 * 根据警情ID查询归档信息
	 * @param jqTywysbm
	 * @return
	 */
	@ApiOperation("根据警情ID查询归档信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "jqTywysbm", value = "警情ID", required = true, dataType = "String",dataTypeClass = String.class,paramType = "query"),
	})
	//返回描述
	@ApiResponses(value = {@ApiResponse(code = 200, message = "访问成功",response=JqgdDO.class)})
	@ResponseBody
	@GetMapping("/queryJqgdByJqgdjlTywysbm")
	public R queryJqgdByJqgdjlTywysbm(String jqTywysbm){
		//获取警情信息ID
		Map<String,Object> params = new HashMap<>();
		params.put("jqTywysbm",jqTywysbm);
		params.put("status","0");
		//根据警情信息ID查询归档信息
		List<JqgdDO> jqgdDOList = this.jqgdService.list(params);
		for(JqgdDO jqgdDO:jqgdDOList){
			JbxxDO jbxxDO = this.jbxxService.get(jqTywysbm);
			jqgdDO.setJbxxDO(jbxxDO);
			params = new HashMap<>();
			params.put("jqgdjlTywysbm",jqgdDO.getJqgdjlTywysbm());
			params.put("fType","jqgd");
			params.put("status","0");
			//根据警情归档信息ID查询附件信息
			List<AttachmentDO> attachmentDOList = this.attachmentService.list(params);
			if(attachmentDOList!=null && attachmentDOList.size() > 0){
				jqgdDO.setAttachmentDO(attachmentDOList);
			}
		}
		return R.ok(jqgdDOList);
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("webapi:jqgd:jqgd")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<JqgdDO> jqgdList = jqgdService.list(query);
		int total = jqgdService.count(query);
		PageUtils pageUtils = new PageUtils(jqgdList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("webapi:jqgd:add")
	String add(){
	    return "webapi/jqgd/add";
	}

	@GetMapping("/edit/{jqgdjlTywysbm}")
	@RequiresPermissions("webapi:jqgd:edit")
	String edit(@PathVariable("jqgdjlTywysbm") String jqgdjlTywysbm,Model model){
		JqgdDO jqgd = jqgdService.get(jqgdjlTywysbm);
		model.addAttribute("jqgd", jqgd);
	    return "webapi/jqgd/edit";
	}
	
	/**
	 * 保存归档信息和附件信息
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("webapi:jqgd:add")
	public R save(@RequestParam("file") MultipartFile[] files,JqgdDO jqgd){
		jqgd.setCdate(new Date());
		jqgd.setCperson(getUser().getUserId().toString());
		jqgd.setStatus("0");
		if(files!=null && files.length > 0){
			this.attachmentService.ftpUpload(files,jqgd.getJqgdjlTywysbm(),"jqgd");
		}
		if(jqgdService.save(jqgd)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("webapi:jqgd:edit")
	public R update( JqgdDO jqgd){
		jqgdService.update(jqgd);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("webapi:jqgd:remove")
	public R remove( String jqgdjlTywysbm){
		if(jqgdService.remove(jqgdjlTywysbm)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("webapi:jqgd:batchRemove")
	public R remove(@RequestParam("ids[]") String[] jqgdjlTywysbms){
		jqgdService.batchRemove(jqgdjlTywysbms);
		return R.ok();
	}
	@ResponseBody
	@RequestMapping("/updateJqgdDO")
	public R updateJqgdDO( @RequestBody JqgdDO jqgd){
		if(jqgd.getJqgdjlTywysbm()!=null &&!jqgd.getJqgdjlTywysbm().equals("")){
			jqgdService.update(jqgd);
		}else{
			jqgd.setJqgdjlTywysbm(UUID.randomUUID().toString().replace("-",""));
			jqgd.setCdate(new Date());
			jqgd.setCperson(this.getUser().getUserId()+"");
			jqgd.setStatus("0");
			jqgdService.save(jqgd);
		}
		return R.ok();
	}
}
