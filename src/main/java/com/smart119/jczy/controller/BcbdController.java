package com.smart119.jczy.controller;

import com.smart119.common.utils.PageUtils;
import com.smart119.common.utils.Query;
import com.smart119.common.utils.R;
import com.smart119.jczy.domain.BcbdDO;
import com.smart119.jczy.domain.BcbdZzdyDO;
import com.smart119.jczy.domain.BlackListDO;
import com.smart119.jczy.domain.ZzdyDO;
import com.smart119.jczy.service.BcbdService;
import com.smart119.jczy.service.BcbdZzdyService;
import com.smart119.jczy.service.ZzdyService;
import com.smart119.system.service.DeptService;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 编程编队
 *
 * @author Jiyu Yang
 * @email yangjiyu@sz000673.com
 * @date 2021-01-30 10:12:02
 */

@Controller
@RequestMapping("/jczy/bcbd")
public class BcbdController {
	@Autowired
	private BcbdService bcbdService;

	@Autowired
	private ZzdyService zzdyService;

	@Autowired
	private DeptService deptService;
	@Autowired
	private BcbdZzdyService bcbdZzdyService;

	@GetMapping()
	@RequiresPermissions("jczy:bcbd:bcbd")
	String Bcbd(){
	    return "jczy/bcbd/bcbd";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("jczy:bcbd:bcbd")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<BcbdDO> bcbdList = bcbdService.list(query);
		int total = bcbdService.count(query);
		PageUtils pageUtils = new PageUtils(bcbdList, total);
		return pageUtils;
	}


	@ResponseBody
	@GetMapping("/zzdyList")
	@RequiresPermissions("jczy:bcbd:bcbd")
	public PageUtils zzdyList(@RequestParam Map<String, Object> params){
		Query query = new Query(params);
		List<ZzdyDO> zzdyList = zzdyService.list(query);
		for(ZzdyDO zzdyDO : zzdyList){
			zzdyDO.setCperson(deptService.findNameByTYWYSBM(zzdyDO.getXfjyjgTywysbm()));
		}
		int total = zzdyService.count(query);
		PageUtils pageUtils = new PageUtils(zzdyList, total);
		return pageUtils;
	}

	@ApiOperation("查询编程编队信息")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "访问成功",response= BlackListDO.class)})
	@ResponseBody
	@GetMapping("/listWithQuery")
	public Map<String, List<BcbdDO>> listWithQuery(@RequestParam Map<String, Object> params){
		List<BcbdDO> bcbdList = bcbdService.listWithQuery(params);
		Map<String, List<BcbdDO>> groupBySex = bcbdList.stream().collect(Collectors.groupingBy(BcbdDO::getBcbdMc));
		return groupBySex;
	}


	/**
	 * 编成编队确认接口
	 */
	@ApiOperation("编程编队确认接口")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "jqTywysbm", value = "警情id", required = true, dataType = "String",dataTypeClass = String.class,paramType = "header"),
			@ApiImplicitParam(name = "bcbdId", value = "编成id", required = true, dataType = "String",dataTypeClass = String.class,paramType = "header")
	})
	@ApiResponses(value = {@ApiResponse(code = 200, message = "编程编队确认成功",response= R.class)})
	@ResponseBody
	@GetMapping("/bcbdqr")
	public R bcbdqr(@RequestParam Map<String, Object> params){
		String jqTywysbm = params.get("jqTywysbm").toString(); //警情id
		String bcbdId = params.get("bcbdId").toString(); //编程id
		if(!bcbdService.checkExist(jqTywysbm,bcbdId)){
			return R.error(200,"已存在编成编队！");
		}else{
			return R.ok(bcbdService.selXfclByBcbdId(bcbdId));
		}
	}

	@GetMapping("/add")
	@RequiresPermissions("jczy:bcbd:add")
	String add(){
	    return "jczy/bcbd/add";
	}

	@GetMapping("/edit/{bcbdId}")
	@RequiresPermissions("jczy:bcbd:edit")
	String edit(@PathVariable("bcbdId") String bcbdId,Model model){
		List<ZzdyDO> zzdyList = new ArrayList<>();
		BcbdDO bcbd = bcbdService.get(bcbdId);

		Map<String,Object> params = new HashMap<>();
		params.put("status","0");
		params.put("bcbdId",bcbdId);
		List<BcbdZzdyDO> list = bcbdZzdyService.list(params);
		Set<String> zzdyTywysbmSet = new HashSet<>();
		for(BcbdZzdyDO bcbdZzdyDO:list){
			//单元id
			String zzdytywybs = bcbdZzdyDO.getZzdytywybs();
			ZzdyDO zzdyDO = zzdyService.get(zzdytywybs);
			zzdyList.add(zzdyDO);
			zzdyTywysbmSet.add(zzdytywybs);
		}
//		List<BcbdZzdyDO> bcbdZzdyList = bcbdService.getzzdyid(bcbdId);
		String zzdyTywysbms = StringUtils.join(zzdyTywysbmSet, ",");
		bcbd.setZzdyid(zzdyTywysbms);
		model.addAttribute("bcbd", bcbd);
		model.addAttribute("bcbdZzdyList", zzdyList);
	    return "jczy/bcbd/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("jczy:bcbd:add")
	public R save( BcbdDO bcbd){
		if(bcbdService.save(bcbd)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("jczy:bcbd:edit")
	public R update( BcbdDO bcbd){
		bcbdService.update(bcbd);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("jczy:bcbd:remove")
	public R remove( String bcbdId){
		if(bcbdService.remove(bcbdId)>0){
		return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("jczy:bcbd:batchRemove")
	public R remove(@RequestParam("ids[]") String[] bcbdIds){
		bcbdService.batchRemove(bcbdIds);
		return R.ok();
	}

	@ResponseBody
	@GetMapping("/getZZDY")
	public List<Map<String,Object>> getZZDY() {
		List<Map<String,Object>> dictList = bcbdService.getZZDY();
		return dictList;
	}

	@GetMapping("/addzzdy")
	String addzzdy(){
		return "jczy/bcbd/addzzdy";
	}

}
