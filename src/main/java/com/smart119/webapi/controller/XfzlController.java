package com.smart119.webapi.controller;


import com.smart119.common.controller.BaseController;
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

import javax.annotation.Resource;
import java.util.Date;
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
	public R save(XfzlDO xfzl){
		UserDO user = (UserDO) SecurityUtils.getSubject().getPrincipal();
		if(xfzlService.save(xfzl, user.getUsername())>0){
			return R.ok();
		}
		return R.error();
	}



	@GetMapping("/edit/{xfzlId}")
	@RequiresPermissions("back:xfzl:edit")
	String edit(@PathVariable("xfzlId") String xfzlId,Model model){
		XfzlDO xfzl = xfzlService.queryById(xfzlId);
		List dictList = dictService.queryByDictType("JQFLYDM");
		model.addAttribute("dictList", dictList);
		model.addAttribute("xfzl", xfzl);
		return "xfzl/edit";
	}


	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("webapi:xfzl:edit")
	public R update( XfzlDO xfzl){
		UserDO user = (UserDO) SecurityUtils.getSubject().getPrincipal();
		xfzlService.update(xfzl, user.getUsername());
		return R.ok();
	}






}
