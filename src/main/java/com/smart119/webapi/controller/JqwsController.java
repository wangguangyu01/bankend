package com.smart119.webapi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.smart119.common.domain.AttachmentDO;
import com.smart119.common.service.AttachmentService;
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

import com.smart119.webapi.domain.JqwsDO;
import com.smart119.webapi.service.JqwsService;
import com.smart119.common.utils.PageUtils;
import com.smart119.common.utils.Query;
import com.smart119.common.utils.R;
import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * 
 * @author xuantianlong
 * @date 2021-01-28 14:00:49
 */
 
@Controller
@RequestMapping("/webapi/jqws")
public class JqwsController {
	@Autowired
	private JqwsService jqwsService;

	@Autowired
	private AttachmentService attachmentService;


	@GetMapping()
	@RequiresPermissions("webapi:jqws:jqws")
	String Jqws(){
	    return "webapi/jqws/jqws";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("webapi:jqws:jqws")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<JqwsDO> jqwsList = jqwsService.list(query);
		int total = jqwsService.count(query);
		PageUtils pageUtils = new PageUtils(jqwsList, total);
		return pageUtils;
	}

	@GetMapping("/add")
	@RequiresPermissions("webapi:jqws:add")
	String add(){
	    return "webapi/jqws/add";
	}

	@GetMapping("/edit/{jqhcwsTywysbm}")
	@RequiresPermissions("webapi:jqws:edit")
	String edit(@PathVariable("jqhcwsTywysbm") String jqhcwsTywysbm,Model model){
		JqwsDO jqws = jqwsService.get(jqhcwsTywysbm);
		model.addAttribute("jqws", jqws);
	    return "webapi/jqws/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("webapi:jqws:add")
	public R save(JqwsDO jqws){
		if(jqwsService.save(jqws)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("webapi:jqws:edit")
	public R update( JqwsDO jqws){
		jqwsService.update(jqws);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("webapi:jqws:remove")
	public R remove( String jqhcwsTywysbm){
		if(jqwsService.remove(jqhcwsTywysbm)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("webapi:jqws:batchRemove")
	public R remove(@RequestParam("ids[]") String[] jqhcwsTywysbms){
		jqwsService.batchRemove(jqhcwsTywysbms);
		return R.ok();
	}
	
}
