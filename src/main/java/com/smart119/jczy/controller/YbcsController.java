package com.smart119.jczy.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.smart119.common.controller.BaseController;
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

import com.smart119.jczy.domain.YbcsDO;
import com.smart119.jczy.service.YbcsService;
import com.smart119.common.utils.PageUtils;
import com.smart119.common.utils.Query;
import com.smart119.common.utils.R;

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
		model.addAttribute("ybcs", ybcs);
	    return "jczy/ybcs/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("jczy:ybcs:add")
	public R save( YbcsDO ybcs){
		String id = UUID.randomUUID().toString().replace("-", "");
		ybcs.setYbcsTywysbm(id);
		ybcs.setCdate(new Date());
		ybcs.setCperson(getUserId()+"");
		ybcs.setStatus("0");
		if(ybcs.getSfcfwxp()==null || "".equals(ybcs.getSfcfwxp())){
			ybcs.setSfcfwxp("无");
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
	public R update( YbcsDO ybcs){
		if(ybcs.getSfcfwxp()==null || "".equals(ybcs.getSfcfwxp())){
			ybcs.setSfcfwxp("无");
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
