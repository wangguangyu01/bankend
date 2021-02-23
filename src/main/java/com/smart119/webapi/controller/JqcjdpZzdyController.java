package com.smart119.webapi.controller;

import java.util.List;
import java.util.Map;

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

import com.smart119.webapi.domain.JqcjdpZzdyDO;
import com.smart119.webapi.service.JqcjdpZzdyService;
import com.smart119.common.utils.PageUtils;
import com.smart119.common.utils.Query;
import com.smart119.common.utils.R;

/**
 * 警情处警调派-作战单元
 *
 * @author yanyu
 * @email yanyu@sz000673.com
 * @date 2021-01-30 12:35:37
 */

@Controller
@RequestMapping("/webapi/jqcjdpZzdy")
public class JqcjdpZzdyController {
	@Autowired
	private JqcjdpZzdyService jqcjdpZzdyService;

	@GetMapping()
	@RequiresPermissions("webapi:jqcjdpZzdy:jqcjdpZzdy")
	String JqcjdpZzdy(){
	    return "webapi/jqcjdpZzdy/jqcjdpZzdy";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("webapi:jqcjdpZzdy:jqcjdpZzdy")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<JqcjdpZzdyDO> jqcjdpZzdyList = jqcjdpZzdyService.list(query);
		int total = jqcjdpZzdyService.count(query);
		PageUtils pageUtils = new PageUtils(jqcjdpZzdyList, total);
		return pageUtils;
	}

	@GetMapping("/add")
	@RequiresPermissions("webapi:jqcjdpZzdy:add")
	String add(){
	    return "webapi/jqcjdpZzdy/add";
	}

	@GetMapping("/edit/{jqcjdpZzdyId}")
	@RequiresPermissions("webapi:jqcjdpZzdy:edit")
	String edit(@PathVariable("jqcjdpZzdyId") String jqcjdpZzdyId,Model model){
		JqcjdpZzdyDO jqcjdpZzdy = jqcjdpZzdyService.get(jqcjdpZzdyId);
		model.addAttribute("jqcjdpZzdy", jqcjdpZzdy);
	    return "webapi/jqcjdpZzdy/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("webapi:jqcjdpZzdy:add")
	public R save( JqcjdpZzdyDO jqcjdpZzdy){
		if(jqcjdpZzdyService.save(jqcjdpZzdy)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("webapi:jqcjdpZzdy:edit")
	public R update( JqcjdpZzdyDO jqcjdpZzdy){
		jqcjdpZzdyService.update(jqcjdpZzdy);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("webapi:jqcjdpZzdy:remove")
	public R remove( String jqcjdpZzdyId){
		if(jqcjdpZzdyService.remove(jqcjdpZzdyId)>0){
		return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("webapi:jqcjdpZzdy:batchRemove")
	public R remove(@RequestParam("ids[]") String[] jqcjdpZzdyIds){
		jqcjdpZzdyService.batchRemove(jqcjdpZzdyIds);
		return R.ok();
	}


}
