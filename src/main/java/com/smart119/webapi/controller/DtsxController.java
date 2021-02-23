package com.smart119.webapi.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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

import com.smart119.webapi.domain.DtsxDO;
import com.smart119.webapi.service.DtsxService;
import com.smart119.common.utils.PageUtils;
import com.smart119.common.utils.Query;
import com.smart119.common.utils.R;

/**
 * 警情信息_动态属性信息
 *
 * @author yanyu
 * @email yanyu@sz000673.com
 * @date 2021-01-28 12:05:01
 */
@Api(value = "警情动态属性", description = "警情动态表单 API")
@Controller
@RequestMapping("/webapi/dtsx")
public class DtsxController {
	@Autowired
	private DtsxService dtsxService;

	@GetMapping()
	@RequiresPermissions("webapi:dtsx:dtsx")
	String Dtsx(){
	    return "webapi/dtsx/dtsx";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("webapi:dtsx:dtsx")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<DtsxDO> dtsxList = dtsxService.list(query);
		int total = dtsxService.count(query);
		PageUtils pageUtils = new PageUtils(dtsxList, total);
		return pageUtils;
	}

	@GetMapping("/add")
	@RequiresPermissions("webapi:dtsx:add")
	String add(){
	    return "webapi/dtsx/add";
	}

	@GetMapping("/edit/{dtsxTywysbm}")
	@RequiresPermissions("webapi:dtsx:edit")
	String edit(@PathVariable("dtsxTywysbm") String dtsxTywysbm,Model model){
		DtsxDO dtsx = dtsxService.get(dtsxTywysbm);
		model.addAttribute("dtsx", dtsx);
	    return "webapi/dtsx/edit";
	}

	/**
	 * @Description: 获取警情动态属性信息
	 * @Param: [ZHCSID] 灾害场所ID
	 * @return: java.util.Map
	 * @Author: yanyu
	 * @Date: 2021/1/28
	 */
	@ApiOperation("历史警情查询传{'zhcsId':'灾害场所ID'}（接警端）")
	@GetMapping( "/getDtjqByZHCSID")
	@ResponseBody
	public R getDtjqByZHCSID(@RequestParam Map<String, Object> params){
		return R.ok(dtsxService.list(params));
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("webapi:dtsx:add")
	public R save( DtsxDO dtsx){
		if(dtsxService.save(dtsx)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("webapi:dtsx:edit")
	public R update( DtsxDO dtsx){
		dtsxService.update(dtsx);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("webapi:dtsx:remove")
	public R remove( String dtsxTywysbm){
		if(dtsxService.remove(dtsxTywysbm)>0){
		return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("webapi:dtsx:batchRemove")
	public R remove(@RequestParam("ids[]") String[] dtsxTywysbms){
		dtsxService.batchRemove(dtsxTywysbms);
		return R.ok();
	}

}
