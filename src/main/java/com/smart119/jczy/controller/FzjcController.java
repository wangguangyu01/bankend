package com.smart119.jczy.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.smart119.common.config.Constant;
import com.smart119.common.service.DictService;
import com.smart119.system.domain.UserDO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smart119.jczy.domain.FzjcDO;
import com.smart119.jczy.service.FzjcService;
import com.smart119.common.utils.PageUtils;
import com.smart119.common.utils.Query;
import com.smart119.common.utils.R;

import javax.annotation.Resource;

/**
 * 辅助决策
 * 
 * @author wangguangyu
 * @email wangguangyu@sz000673.com
 * @date 2021-02-02 10:22:35
 */
 
@Controller
@RequestMapping("/jczy/fzjc")
public class FzjcController {
	@Autowired
	private FzjcService fzjcService;

	@Resource
	private DictService dictService;





	@GetMapping()
	@RequiresPermissions("jczy:fzjc:fzjc")
	String Fzjc(){
	    return "jczy/fzjc/fzjc";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("jczy:fzjc:fzjc")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<FzjcDO> fzjcList = fzjcService.list(query);
		int total = fzjcService.count(query);
		PageUtils pageUtils = new PageUtils(fzjcList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("jczy:fzjc:add")
	String add(Model model){
		List dictList = dictService.listByParentType("FZJCLXDM");
		model.addAttribute("dictList", dictList);
	    return "jczy/fzjc/add";
	}

	@GetMapping("/edit/{fzjcId}")
	@RequiresPermissions("jczy:fzjc:edit")
	String edit(@PathVariable("fzjcId") String fzjcId,Model model){
		FzjcDO fzjc = fzjcService.get(fzjcId);
		List dictList = dictService.listByParentType("FZJCLXDM");
		model.addAttribute("dictList", dictList);
		model.addAttribute("fzjc", fzjc);
	    return "jczy/fzjc/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("jczy:fzjc:add")
	public R save( FzjcDO fzjc){
		UserDO user = (UserDO) SecurityUtils.getSubject().getPrincipal();
		fzjc.setCperson(user.getUsername());
		fzjc.setCdate(new Date());
		fzjc.setStatus(0);
		if(fzjcService.save(fzjc)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("jczy:fzjc:edit")
	public R update( FzjcDO fzjc){
		UserDO user = (UserDO) SecurityUtils.getSubject().getPrincipal();
		fzjc.setCperson(user.getUsername());
		fzjc.setCdate(new Date());
		fzjc.setStatus(0);
		fzjcService.update(fzjc);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("jczy:fzjc:remove")
	public R remove( String fzjcId){
		if(fzjcService.remove(fzjcId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("jczy:fzjc:batchRemove")
	public R remove(@RequestParam("ids[]") String[] fzjcIds){
		fzjcService.batchRemove(fzjcIds);
		return R.ok();
	}
	
}
