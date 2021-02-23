package com.smart119.jczy.controller;

import java.util.*;

import com.smart119.common.controller.BaseController;
import com.smart119.common.service.DictService;
import com.smart119.jczy.domain.WxxfzDO;
import com.smart119.jczy.domain.YjlddwDO;
import com.smart119.jczy.service.WxxfzService;
import com.smart119.system.service.DeptService;
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

import com.smart119.common.utils.PageUtils;
import com.smart119.common.utils.Query;
import com.smart119.common.utils.R;

/**
 * 微型消防站
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2021-01-19 13:52:11
 */
@Api(value = "微型消防站", description = "微型消防站API")
@Controller
@RequestMapping("/jczy/wxxfz")
public class WxxfzController extends BaseController {
	@Autowired
	private WxxfzService wxxfzService;
	@Autowired
	private DictService dictService;

	@Autowired
	private DeptService deptService;
	
	@GetMapping()
	@RequiresPermissions("jczy:wxxfz:wxxfz")
	String Wxxfz(){
	    return "jczy/wxxfz/wxxfz";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("jczy:wxxfz:wxxfz")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<WxxfzDO> wxxfzList = wxxfzService.list(query);
		int total = wxxfzService.count(query);
		PageUtils pageUtils = new PageUtils(wxxfzList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("jczy:wxxfz:add")
	String add(){
	    return "jczy/wxxfz/add";
	}

	@GetMapping("/edit/{wxxfzTywysbm}")
	@RequiresPermissions("jczy:wxxfz:edit")
	String edit(@PathVariable("wxxfzTywysbm") String wxxfzTywysbm,Model model){
		WxxfzDO wxxfz = wxxfzService.get(wxxfzTywysbm);
		model.addAttribute("wxxfz", wxxfz);
		String city = dictService.findParentValue(wxxfz.getXzqhdm());
		String province = dictService.findParentValue(city);

		model.addAttribute("province", province);  //区划代码-省
		model.addAttribute("city", city);  //区划代码-市
	    return "jczy/wxxfz/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("jczy:wxxfz:add")
	public R save( WxxfzDO wxxfz){
		String id = UUID.randomUUID().toString().replace("-", "");
		wxxfz.setWxxfzTywysbm(id);
		wxxfz.setCdate(new Date());
		wxxfz.setCperson(getUser().getUserId().toString());
		wxxfz.setStatus("0");
		if(wxxfzService.save(wxxfz)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("jczy:wxxfz:edit")
	public R update( WxxfzDO wxxfz){
		wxxfzService.update(wxxfz);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("jczy:wxxfz:remove")
	public R remove( String wxxfzTywysbm){
		if(wxxfzService.remove(wxxfzTywysbm)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("jczy:wxxfz:batchRemove")
	public R remove(@RequestParam("ids[]") String[] wxxfzTywysbms){
		wxxfzService.batchRemove(wxxfzTywysbms);
		return R.ok();
	}


	/**
	 * 企业联动接口（用企业、区划代码查询联动企业信息）
	 * @param params 联动单位查询条件（wxxfzMc 微型消防站名称  xzqhdm 行政区划代码）
	 * @return
	 */
	@ApiOperation("企业联动接口")
	@GetMapping( "/qyldList")
	@ResponseBody
	@ApiImplicitParams({
			@ApiImplicitParam(name = "wxxfzMc", value = "微型消防站名称", required = true, dataType = "String",paramType = "header"),
			@ApiImplicitParam(name = "xzqhdm", value = "行政区划代码", required = true, dataType = "String",paramType = "header")
	})
	@ApiResponses(value = {@ApiResponse(code = 200, message = "访问成功",response= YjlddwDO.class)})
	public PageUtils qyldList(@RequestParam Map<String, Object> params){
		Map<String,Object> map = new HashMap<>();
		List<Map<String,Object>> qylddwList = wxxfzService.qyldList(params);  //这里要改成分组列表查询
		int total = qylddwList.size();
		PageUtils pageUtils = new PageUtils(qylddwList, total);
		return pageUtils;
	}
	
}
