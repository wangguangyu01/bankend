package com.smart119.jczy.controller;

import java.util.List;
import java.util.Map;

import com.smart119.jczy.domain.ZddwDO;
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

import com.smart119.jczy.domain.BlackListDO;
import com.smart119.jczy.service.BlackListService;
import com.smart119.common.utils.PageUtils;
import com.smart119.common.utils.Query;
import com.smart119.common.utils.R;

/**
 * 
 * 
 * @author thrz
 * @email thrz@sz000673.com
 * @date 2021-01-26 17:14:46
 */
@Api(value = "黑名单管理", description = "接警端黑名单管理API")
@Controller
@RequestMapping("/jczy/blackList")
public class BlackListController {
	@Autowired
	private BlackListService blackListService;
	
	@GetMapping()
	@RequiresPermissions("jczy:blackList:blackList")
	String BlackList(){
	    return "jczy/blackList/blackList";
	}

	@ApiOperation("查询黑名单信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "limit", value = "条数", required = true, dataType = "String",dataTypeClass = String.class,paramType = "header"),
			@ApiImplicitParam(name = "offset", value = "页数", required = true, dataType = "String",dataTypeClass = String.class,paramType = "header")
	})
	@ResponseBody
	@GetMapping("/list")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "访问成功",response= BlackListDO.class)})
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<BlackListDO> blackListList = blackListService.list(query);
		int total = blackListService.count(query);
		PageUtils pageUtils = new PageUtils(blackListList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("jczy:blackList:add")
	String add(){
	    return "jczy/blackList/add";
	}

	@GetMapping("/edit/{hmdTywysbm}")
	@RequiresPermissions("jczy:blackList:edit")
	String edit(@PathVariable("hmdTywysbm") String hmdTywysbm,Model model){
		BlackListDO blackList = blackListService.get(hmdTywysbm);
		model.addAttribute("blackList", blackList);
	    return "jczy/blackList/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("jczy:blackList:add")
	public R save( BlackListDO blackList){
		if(blackListService.save(blackList)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("jczy:blackList:edit")
	public R update( BlackListDO blackList){
		blackListService.update(blackList);
		return R.ok();
	}

	@ApiOperation("限时禁呼")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "phoneNumber", value = "电话号", required = true, dataType = "String",dataTypeClass = String.class,paramType = "header"),
			@ApiImplicitParam(name = "xsjhsc", value = "禁呼时间", required = true, dataType = "String",dataTypeClass = String.class,paramType = "header")
	})
	@ResponseBody
	@PostMapping("/updateByPhoneNumber")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "限时禁呼成功",response= R.class)})
	public R updateByPhoneNumber(String phoneNumber,String xsjhsc){
		blackListService.updateByPhoneNumber(phoneNumber,xsjhsc);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("jczy:blackList:remove")
	public R remove( String hmdTywysbm){
		if(blackListService.remove(hmdTywysbm)>0){
		return R.ok();
		}
		return R.error();
	}


	@ApiOperation("黑名单解锁")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "phoneNumber", value = "电话号", required = true, dataType = "String",dataTypeClass = String.class,paramType = "header")

	})
	@PostMapping( "/removeByPhoneNumber")
	@ResponseBody
	@ApiResponses(value = {@ApiResponse(code = 200, message = "解锁成功",response= R.class)})
	public R removeByPhoneNumber(String phoneNumber){
		if(blackListService.removeByPhoneNumber(phoneNumber)>0){
			return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("jczy:blackList:batchRemove")
	public R remove(@RequestParam("ids[]") String[] hmdTywysbms){
		blackListService.batchRemove(hmdTywysbms);
		return R.ok();
	}
	
}
