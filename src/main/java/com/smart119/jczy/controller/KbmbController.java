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

import com.smart119.jczy.domain.KbmbDO;
import com.smart119.jczy.service.KbmbService;
import com.smart119.common.utils.PageUtils;
import com.smart119.common.utils.Query;
import com.smart119.common.utils.R;

/**
 * 快报模板
 * 
 * @author shilei
 * @email shichengyuan@sz000673.com
 * @date 2021-06-03 14:39:20
 */
 
@Controller
@RequestMapping("/jczy/kbmb")
public class KbmbController extends BaseController {
	@Autowired
	private KbmbService kbmbService;
	
	@GetMapping()
	//@RequiresPermissions("jczy:kbmb:kbmb")
	String Kbmb(){
	    return "jczy/kbmb/kbmb";
	}
	
	@ResponseBody
	@GetMapping("/list")
	//@RequiresPermissions("jczy:kbmb:kbmb")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<KbmbDO> kbmbList = kbmbService.list(query);
		int total = kbmbService.count(query);
		PageUtils pageUtils = new PageUtils(kbmbList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	//@RequiresPermissions("jczy:kbmb:add")
	String add(){
	    return "jczy/kbmb/add";
	}

	@GetMapping("/edit/{kbmbId}")
	//@RequiresPermissions("jczy:kbmb:edit")
	String edit(@PathVariable("kbmbId") String kbmbId,Model model){
		KbmbDO kbmb = kbmbService.get(kbmbId);
		model.addAttribute("kbmb", kbmb);
	    return "jczy/kbmb/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	//@RequiresPermissions("jczy:kbmb:add")
	public R save( KbmbDO kbmb){
		String id = UUID.randomUUID().toString().replace("-", "");
		kbmb.setKbmbId(id);
		kbmb.setCdate(new Date());
		kbmb.setCperson(getUser().getUserId().toString());
		kbmb.setStatus("0");
		if(kbmbService.save(kbmb)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	//@RequiresPermissions("jczy:kbmb:edit")
	public R update( KbmbDO kbmb){
		KbmbDO kbmbNew = kbmbService.get(kbmb.getKbmbId());
		kbmbNew.setContent(kbmb.getContent());
		kbmbNew.setRemarks(kbmb.getRemarks());
		kbmbNew.setName(kbmb.getName());
		kbmbService.update(kbmbNew);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	//@RequiresPermissions("jczy:kbmb:remove")
	public R remove( String kbmbId){
		if(kbmbService.remove(kbmbId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	//@RequiresPermissions("jczy:kbmb:batchRemove")
	public R remove(@RequestParam("ids[]") String[] kbmbIds){
		kbmbService.batchRemove(kbmbIds);
		return R.ok();
	}
	
}
