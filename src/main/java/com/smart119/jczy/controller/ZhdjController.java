package com.smart119.jczy.controller;

import java.util.*;

import com.smart119.common.controller.BaseController;
import com.smart119.system.domain.DeptDO;
import com.smart119.system.domain.UserDO;
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

import com.smart119.jczy.domain.ZhdjDO;
import com.smart119.jczy.service.ZhdjService;
import com.smart119.common.utils.PageUtils;
import com.smart119.common.utils.Query;
import com.smart119.common.utils.R;

/**
 * 灾害等级
 * 
 * @author Jiyu Yang
 * @email yangjiyu@sz000673.com
 * @date 2021-02-08 16:13:14
 */
@Api(value = "灾害等级", description = "灾害等级API")
@Controller
@RequestMapping("/jczy/zhdj")
public class ZhdjController extends BaseController {
	@Autowired
	private ZhdjService zhdjService;
	
	@GetMapping()
	@RequiresPermissions("jczy:zhdj:zhdj")
	String Zhdj(){
	    return "jczy/zhdj/zhdj";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("jczy:zhdj:zhdj")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ZhdjDO> zhdjList = zhdjService.list(query);
		int total = zhdjService.count(query);
		PageUtils pageUtils = new PageUtils(zhdjList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("jczy:zhdj:add")
	String add(){
	    return "jczy/zhdj/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("jczy:zhdj:edit")
	String edit(@PathVariable("id") String id,Model model){
		ZhdjDO zhdj = zhdjService.get(id);
		model.addAttribute("zhdj", zhdj);
	    return "jczy/zhdj/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("jczy:zhdj:add")
	public R save( ZhdjDO zhdj){
		UserDO user = getUser();
		zhdj.setId(UUID.randomUUID().toString().replace("-", ""));
		zhdj.setCperson(user.getUserId().toString());
		zhdj.setCdate(new Date());
		zhdj.setStatus("0");
		if(zhdjService.save(zhdj)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("jczy:zhdj:edit")
	public R update( ZhdjDO zhdj){
		zhdjService.update(zhdj);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("jczy:zhdj:remove")
	public R remove( String id){
		if(zhdjService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("jczy:zhdj:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		zhdjService.batchRemove(ids);
		return R.ok();
	}


	/**
	 * 警情升级判断
	 * @param params 警情升级判断参数
	 * @return
	 */
	@ApiOperation("警情升级判断")
	@GetMapping( "/jqsjpd")
	@ResponseBody
	@ApiImplicitParams({
			@ApiImplicitParam(name = "zhlx", value = "灾害类型", required = true, dataType = "String",paramType = "query"),
			@ApiImplicitParam(name = "zhcs", value = "灾害场所", required = true, dataType = "String",paramType = "query"),
			@ApiImplicitParam(name = "zhdj", value = "灾害等级", required = true, dataType = "String",paramType = "query"),
			@ApiImplicitParam(name = "bkrs", value = "被困人数", required = true, dataType = "String",paramType = "query")
	})
	@ApiResponses(value = {@ApiResponse(code = 200, message = "访问成功",response= DeptDO.class)})
	public R jqsjpd(@RequestParam Map<String, Object> params){
		int zhdj = Integer.parseInt(params.get("zhdj").toString());
		Map<String,Object> retMap = new HashMap<>();
		String mqZhdjStr = zhdjService.findZhdj(params);
		if(!"".equals(mqZhdjStr)){
			int mqZhdj = Integer.parseInt(mqZhdjStr);
			if(mqZhdj>zhdj){
				retMap.put("sfsj",1);
				retMap.put("mqZhdj",mqZhdj);
				return R.ok(retMap);
			}
		}
		retMap.put("sfsj",0);
		retMap.put("mqZhdj",zhdj);
		return R.ok(retMap);

	}
	
}
