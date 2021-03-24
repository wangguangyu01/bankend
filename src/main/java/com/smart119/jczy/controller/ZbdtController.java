package com.smart119.jczy.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.smart119.common.controller.BaseController;
import com.smart119.common.service.DictService;
import com.smart119.system.domain.DeptDO;
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

import com.smart119.jczy.domain.ZbdtDO;
import com.smart119.jczy.service.ZbdtService;
import com.smart119.common.utils.PageUtils;
import com.smart119.common.utils.Query;
import com.smart119.common.utils.R;

/**
 * 值班动态基本信息
 * 
 * @author zhangxj
 * @email thrz@sz000673.com
 * @date 2021-01-18 16:02:23
 */
@Api(value = "值班动态", description = "值班动态API")
@Controller
@RequestMapping("/jczy/zbdt")
public class ZbdtController extends BaseController{
	@Autowired
	private ZbdtService zbdtService;
	@Autowired
	private DictService dictService;
	@Autowired
	private DeptService deptService;
	
	@GetMapping()
	@RequiresPermissions("jczy:zbdt:zbdt")
	String Zbdt(){
	    return "jczy/zbdt/zbdt";
	}


	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("jczy:zbdt:zbdt")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<DeptDO> deptList = new ArrayList<>();
		if(params.get("deptId")!=null && !params.get("deptId").equals("")){
			deptList = deptService.listChildren(Long.valueOf(params.get("deptId").toString()));
		}else{
			deptList = deptService.listChildren(getUser().getDeptId());
		}
		query.put("deptList",deptList);
		List<ZbdtDO> zbdtList = zbdtService.list(query);
		int total = zbdtService.count(query);
		PageUtils pageUtils = new PageUtils(zbdtList, total);
		return pageUtils;
	}


	@ApiOperation("值班动态列表查询")
	@ResponseBody
	@GetMapping("/appList")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "limit", value = "分页-每页信息数量", required = true, dataType = "String",dataTypeClass = String.class,paramType = "query"),
			@ApiImplicitParam(name = "offset", value = "分页-页码(从0开始)", required = true, dataType = "String",dataTypeClass = String.class,paramType = "query"),
			@ApiImplicitParam(name = "zbryXm", value = "值班人姓名（查询用）", required = true, dataType = "String",dataTypeClass = String.class,paramType = "query")
	})
	@ApiResponses(value = {@ApiResponse(code = 200, message = "访问成功",response= DeptDO.class)})
	public PageUtils appList(@RequestParam Map<String, Object> params){
		String userId = getUser().getUserId().toString();
		Map<String,Object> map = zbdtService.xfjyjgTywysbmByUserId(userId);
		params.put("xfjyjgTywysbm",map.get("XFJYJG_TYWYSBM").toString());
		//查询列表数据
		Query query = new Query(params);
		List<DeptDO> deptList = new ArrayList<>();
		if(params.get("deptId")!=null && !params.get("deptId").equals("")){
			deptList = deptService.listChildren(Long.valueOf(params.get("deptId").toString()));
		}else{
			deptList = deptService.listChildren(getUser().getDeptId());
		}
		query.put("deptList",deptList);
		List<ZbdtDO> zbdtList = zbdtService.list(query);
		int total = zbdtService.count(query);
		PageUtils pageUtils = new PageUtils(zbdtList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("jczy:zbdt:add")
	String add(){
	    return "jczy/zbdt/add";
	}

	@GetMapping("/edit/{zhbTywysbm}")
	@RequiresPermissions("jczy:zbdt:edit")
	String edit(@PathVariable("zhbTywysbm") String zhbTywysbm,Model model){
		ZbdtDO zbdt = zbdtService.get(zhbTywysbm);
		model.addAttribute("zbdt", zbdt);

		String city = dictService.findParentValue(zbdt.getXzqhdm());
		String province = dictService.findParentValue(city);
		model.addAttribute("province", province);
		model.addAttribute("city", city);

	    return "jczy/zbdt/edit";
	}


	@ApiOperation("值班动态动态信息查询")
	@ResponseBody
	@GetMapping("/appEdit")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "访问成功",response= DeptDO.class)})
	public R appEdit(@ApiParam(value = "值班_通用唯一识别码",name = "zhbTywysbm") @RequestParam(value = "", required = true) String zhbTywysbm){
		Map<String,Object> map = new HashMap<>();
		ZbdtDO zbdt = zbdtService.get(zhbTywysbm);
		map.put("zbdt",zbdt);
		String city = dictService.findParentValue(zbdt.getXzqhdm());
		String province = dictService.findParentValue(city);
		map.put("province", province);
		map.put("city", city);
		return R.ok(map);
	}

	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("jczy:zbdt:add")
	public R save( ZbdtDO zbdt){
		if(zbdtService.save(zbdt)>0){
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 手机端保存
	 */
	@ApiOperation("值班动态保存")
	@ResponseBody
	@PostMapping("/app_save")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "zhbRq", value = "值班日期", required = true, dataType = "String",dataTypeClass = String.class,paramType = "query"),
			@ApiImplicitParam(name = "zbryXm", value = "值班人姓名", required = true, dataType = "String",dataTypeClass = String.class,paramType = "query"),
			@ApiImplicitParam(name = "zbryLxdh", value = "值班人联系电话", required = true, dataType = "String",dataTypeClass = String.class,paramType = "query"),
			@ApiImplicitParam(name = "zbryXfzbjslbdm", value = "值班人员_消防值班角色类别代码", required = true, dataType = "String",dataTypeClass = String.class,paramType = "query"),
			@ApiImplicitParam(name = "zbryXfzbjslb", value = "值班人员_消防值班角色类别", required = true, dataType = "String",dataTypeClass = String.class,paramType = "query"),
			@ApiImplicitParam(name = "zbryXfgwflydm", value = "值班人员_消防岗位分类与代码", required = true, dataType = "String",dataTypeClass = String.class,paramType = "query"),
			@ApiImplicitParam(name = "zbryXfgwfly", value = "值班人员_消防岗位分类与", required = true, dataType = "String",dataTypeClass = String.class,paramType = "query"),
			@ApiImplicitParam(name = "zbryDwmc", value = "值班人员_单位名称", required = true, dataType = "String",dataTypeClass = String.class,paramType = "query"),
			@ApiImplicitParam(name = "zfbPdbs", value = "值班人员_正副班_判断标识", required = true, dataType = "String",dataTypeClass = String.class,paramType = "query"),
			@ApiImplicitParam(name = "bz", value = "备注", required = true, dataType = "String",dataTypeClass = String.class,paramType = "query"),
			@ApiImplicitParam(name = "xzqhdm", value = "行政区划代码", required = true, dataType = "String",dataTypeClass = String.class,paramType = "query"),
			@ApiImplicitParam(name = "zbrzJyqk", value = "值班日志_简要情况", required = true, dataType = "String",dataTypeClass = String.class,paramType = "query")
	})
	@ApiResponses(value = {@ApiResponse(code = 200, message = "访问成功",response= DeptDO.class)})
	public R app_save(@RequestBody Map<String, Object> params){
//		JSONObject params = JSON.parseObject(paramsStr);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		ZbdtDO zbdt = new ZbdtDO();
		try {
			zbdt.setZhbRq(params.get("zhbRq")!=null?sdf.parse(params.get("zhbRq").toString()):new Date());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		zbdt.setZbryXm(params.get("zbryXm")!=null?params.get("zbryXm").toString():"");
		zbdt.setZbryLxdh(params.get("zbryLxdh")!=null?params.get("zbryLxdh").toString():"");
		zbdt.setZbryXfzbjslbdm(params.get("zbryXfzbjslbdm")!=null?params.get("zbryXfzbjslbdm").toString():"");
		zbdt.setZbryXfzbjslb(params.get("zbryXfzbjslb")!=null?params.get("zbryXfzbjslb").toString():"");
		zbdt.setZbryXfgwflydm(params.get("zbryXfgwflydm").toString());
		zbdt.setZbryXfgwfly(params.get("zbryXfgwfly")!=null?params.get("zbryXfgwfly").toString():"");
		zbdt.setZbryDwmc(params.get("zbryDwmc")!=null?params.get("zbryDwmc").toString():"");
		zbdt.setZfbPdbs(params.get("zfbPdbs")!=null?params.get("zfbPdbs").toString():"");
		zbdt.setBz(params.get("bz")!=null?params.get("bz").toString():"");
		zbdt.setXzqhdm(params.get("xzqhdm")!=null?params.get("xzqhdm").toString():"");
		zbdt.setZbrzJyqk(params.get("zbrzJyqk")!=null?params.get("zbrzJyqk").toString():"");

		String userId = getUser().getUserId().toString();
		Map<String,Object> map = zbdtService.xfjyjgTywysbmByUserId(userId);
		zbdt.setXfjyjgTywysbm(map.get("XFJYJG_TYWYSBM").toString());
		zbdt.setXzqhdm(map.get("XZQHDM").toString());
		if(zbdtService.save(zbdt)>0){
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 修改
	 */
	@ApiOperation("值班动态更改")
	@ResponseBody
	@PostMapping("/update")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "zhbTywysbm", value = "值班_通用唯一识别码", required = true, dataType = "String",dataTypeClass = String.class,paramType = "query"),
			@ApiImplicitParam(name = "zhbRq", value = "值班日期", required = true, dataType = "String",dataTypeClass = String.class,paramType = "query"),
			@ApiImplicitParam(name = "zbryXm", value = "值班人姓名", required = true, dataType = "String",dataTypeClass = String.class,paramType = "query"),
			@ApiImplicitParam(name = "zbryLxdh", value = "值班人联系电话", required = true, dataType = "String",dataTypeClass = String.class,paramType = "query"),
			@ApiImplicitParam(name = "zbryXfzbjslbdm", value = "值班人员_消防值班角色类别代码", required = true, dataType = "String",dataTypeClass = String.class,paramType = "query"),
			@ApiImplicitParam(name = "zbryXfzbjslb", value = "值班人员_消防值班角色类别", required = true, dataType = "String",dataTypeClass = String.class,paramType = "query"),
			@ApiImplicitParam(name = "zbryXfgwflydm", value = "值班人员_消防岗位分类与代码", required = true, dataType = "String",dataTypeClass = String.class,paramType = "query"),
			@ApiImplicitParam(name = "zbryXfgwfly", value = "值班人员_消防岗位分类与", required = true, dataType = "String",dataTypeClass = String.class,paramType = "query"),
			@ApiImplicitParam(name = "zbryDwmc", value = "值班人员_单位名称", required = true, dataType = "String",dataTypeClass = String.class,paramType = "query"),
			@ApiImplicitParam(name = "zfbPdbs", value = "值班人员_正副班_判断标识", required = true, dataType = "String",dataTypeClass = String.class,paramType = "query"),
			@ApiImplicitParam(name = "bz", value = "备注", required = true, dataType = "String",dataTypeClass = String.class,paramType = "query"),
			@ApiImplicitParam(name = "xzqhdm", value = "行政区划代码", required = true, dataType = "String",dataTypeClass = String.class,paramType = "query"),
			@ApiImplicitParam(name = "zbrzJyqk", value = "值班日志_简要情况", required = true, dataType = "String",dataTypeClass = String.class,paramType = "query")
	})
	@ApiResponses(value = {@ApiResponse(code = 200, message = "访问成功",response= DeptDO.class)})
	public R update(@RequestBody Map<String, Object> params){

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String zhbTywysbm = params.get("zhbTywysbm").toString();
		ZbdtDO zbdt = zbdtService.get(zhbTywysbm);
		try {
			zbdt.setZhbRq(params.get("zhbRq")!=null?sdf.parse(params.get("zhbRq").toString()):new Date());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		zbdt.setZbryXm(params.get("zbryXm")!=null?params.get("zbryXm").toString():"");
		zbdt.setZbryLxdh(params.get("zbryLxdh")!=null?params.get("zbryLxdh").toString():"");
		zbdt.setZbryXfzbjslbdm(params.get("zbryXfzbjslbdm")!=null?params.get("zbryXfzbjslbdm").toString():"");
		zbdt.setZbryXfzbjslb(params.get("zbryXfzbjslb")!=null?params.get("zbryXfzbjslb").toString():"");
		zbdt.setZbryXfgwflydm(params.get("zbryXfgwflydm").toString());
		zbdt.setZbryXfgwfly(params.get("zbryXfgwfly")!=null?params.get("zbryXfgwfly").toString():"");
		zbdt.setZbryDwmc(params.get("zbryDwmc")!=null?params.get("zbryDwmc").toString():"");
		zbdt.setZfbPdbs(params.get("zfbPdbs")!=null?params.get("zfbPdbs").toString():"");
		zbdt.setBz(params.get("bz")!=null?params.get("bz").toString():"");
		zbdt.setXzqhdm(params.get("xzqhdm")!=null?params.get("xzqhdm").toString():"");
		zbdt.setZbrzJyqk(params.get("zbrzJyqk")!=null?params.get("zbrzJyqk").toString():"");


		String userId = getUser().getUserId().toString();
		Map<String,Object> map = zbdtService.xfjyjgTywysbmByUserId(userId);
		zbdt.setXfjyjgTywysbm(map.get("XFJYJG_TYWYSBM")!=null?map.get("XFJYJG_TYWYSBM").toString():"");
		zbdt.setXzqhdm(map.get("XZQHDM")!=null?map.get("XZQHDM").toString():"");
		if(zbdtService.update(zbdt)>0){
			return R.ok();
		}
		return R.error();

	}


	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/appUpdate")
	@RequiresPermissions("jczy:zbdt:edit")
	public R appUpdate( ZbdtDO zbdt){
		zbdtService.update(zbdt);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("jczy:zbdt:remove")
	public R remove( String zhbTywysbm){
		if(zbdtService.remove(zhbTywysbm)>0){
		return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@ApiOperation("值班动态删除")
	@PostMapping( "/appRemove")
	@ResponseBody
	@ApiResponses(value = {@ApiResponse(code = 200, message = "访问成功",response= DeptDO.class)})
	public R appRemove(@RequestBody Map<String, Object> params){
		String zhbTywysbm = params.get("zhbTywysbm").toString();
		if(zbdtService.remove(zhbTywysbm)>0){
			return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("jczy:zbdt:batchRemove")
	public R remove(@RequestParam("ids[]") String[] zhbTywysbms){
		zbdtService.batchRemove(zhbTywysbms);
		return R.ok();
	}
	
}
