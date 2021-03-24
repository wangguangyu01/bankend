package com.smart119.webapi.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.smart119.common.controller.BaseController;
import com.smart119.jczy.domain.MhjyzjDO;
import com.smart119.jczy.domain.YjlddwDO;
import com.smart119.system.domain.UserDO;
import com.smart119.webapi.domain.JjyyDO;
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

import com.smart119.webapi.domain.XfclcdxxDO;
import com.smart119.webapi.service.XfclcdxxService;
import com.smart119.common.utils.PageUtils;
import com.smart119.common.utils.Query;
import com.smart119.common.utils.R;

/**
 * 消防车辆出动信息
 * 
 * @author thrz
 * @email thrz@sz000673.com
 * @date 2021-01-28 17:21:21
 */
@Api(value = "出动力量", description = "出动力量API")
@Controller
@RequestMapping("/webapi/xfclcdxx")
public class XfclcdxxController extends BaseController{
	@Autowired
	private XfclcdxxService xfclcdxxService;
	
	@GetMapping()
	@RequiresPermissions("webapi:xfclcdxx:xfclcdxx")
	String Xfclcdxx(){
	    return "webapi/xfclcdxx/xfclcdxx";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("webapi:xfclcdxx:xfclcdxx")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<XfclcdxxDO> xfclcdxxList = xfclcdxxService.list(query);
		int total = xfclcdxxService.count(query);
		PageUtils pageUtils = new PageUtils(xfclcdxxList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("webapi:xfclcdxx:add")
	String add(){
	    return "webapi/xfclcdxx/add";
	}

	@GetMapping("/edit/{xfclCddm}")
	@RequiresPermissions("webapi:xfclcdxx:edit")
	String edit(@PathVariable("xfclCddm") String xfclCddm,Model model){
		XfclcdxxDO xfclcdxx = xfclcdxxService.get(xfclCddm);
		model.addAttribute("xfclcdxx", xfclcdxx);
	    return "webapi/xfclcdxx/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("webapi:xfclcdxx:add")
	public R save( XfclcdxxDO xfclcdxx){
		if(xfclcdxxService.save(xfclcdxx)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("webapi:xfclcdxx:edit")
	public R update( XfclcdxxDO xfclcdxx){
		xfclcdxxService.update(xfclcdxx);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("webapi:xfclcdxx:remove")
	public R remove( String xfclCddm){
		if(xfclcdxxService.remove(xfclCddm)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("webapi:xfclcdxx:batchRemove")
	public R remove(@RequestParam("ids[]") String[] xfclCddms){
		xfclcdxxService.batchRemove(xfclCddms);
		return R.ok();
	}



	/**
	 * 出动力量查询接口
	 * @param params 联动单位查询条件（bjTywysbm 报警_通用唯一识别码  xfjyjgTywysbm 消防救援机构通用唯一识别码）
	 * @return
	 */
	@ApiOperation("出动力量查询接口")
	@GetMapping( "/cdllcx")
	@ResponseBody
	@ApiImplicitParams({
			@ApiImplicitParam(name = "jqTywysbm", value = "警情_通用唯一识别码", required = true, dataType = "String",dataTypeClass = String.class,paramType = "query")
	})
	@ApiResponses(value = {@ApiResponse(code = 200, message = "访问成功",response= PageUtils.class)})
	public PageUtils qyldList(@RequestParam Map<String, Object> params){
		UserDO user =  getUser();
		String xfjyjgTywysbm = user.getXfjyjgTywysbm();
		params.put("xfjyjgTywysbm",xfjyjgTywysbm);
		List<Map<String,Object>> XfclcdxxList = xfclcdxxService.cdllhx(params);
		int total = XfclcdxxList.size();
		PageUtils pageUtils = new PageUtils(XfclcdxxList, total);
		return pageUtils;
	}


	/**
	 * 出动车辆查询接口
	 * @param params 联动单位查询条件（bjTywysbm 报警_通用唯一识别码  xfjyjgTywysbm 消防救援机构通用唯一识别码）
	 * @return
	 */
	@ApiOperation("出动车辆查询接口")
	@GetMapping( "/cdclHxcx")
	@ResponseBody
	@ApiImplicitParams({
			@ApiImplicitParam(name = "xfclTywysbms", value = "消防车辆_通用唯一识别码(多个)", required = true, dataType = "String",dataTypeClass = String.class,paramType = "query"),
			@ApiImplicitParam(name = "jqTywysbm", value = "警情_通用唯一识别码", required = true, dataType = "String",dataTypeClass = String.class,paramType = "query")
	})
	@ApiResponses(value = {@ApiResponse(code = 200, message = "访问成功",response= PageUtils.class)})
	public PageUtils cdclcx(@RequestParam Map<String, Object> params){

		List<Map<String,Object>> xfclList = xfclcdxxService.cdclhx(params);
		int total = xfclList.size();
		PageUtils pageUtils = new PageUtils(xfclList, total);
		return pageUtils;
	}



	/**
	 * 查询参站人员
	 * @param params    bjTywysbm 报警_通用唯一识别码     xfjyjgTywysbm 消防救援机构通用唯一识别码
	 * @return
	 */
	@ApiOperation("查询参站人员")
	@GetMapping( "/czrycx")
	@ResponseBody
	@ApiImplicitParams({
			@ApiImplicitParam(name = "jqTywysbm", value = "警情_通用唯一识别码", required = true, dataType = "String",dataTypeClass = String.class,paramType = "query")
	})
	@ApiResponses(value = {@ApiResponse(code = 200, message = "访问成功",response= PageUtils.class)})
	public PageUtils zjldList(@RequestParam Map<String, Object> params){
		UserDO user =  getUser();
		String xfjyjgTywysbm = user.getXfjyjgTywysbm();
		params.put("xfjyjgTywysbm",xfjyjgTywysbm);
		List<Map<String,Object>> xfjyryList = xfclcdxxService.cdryAll(params); //消防救援人员
		int total = xfjyryList.size();
		PageUtils pageUtils = new PageUtils(xfjyryList, total);
		return pageUtils;
	}



	/**
	 * 出动力量确认接口
	 * @param params 确认车辆、确认人员拼装Json +
	 * @return
	 */
	@ApiOperation("出动力量确认接口")
	@PostMapping( "/cdllqr")
	@ResponseBody
	@ApiResponses(value = {@ApiResponse(code = 200, message = "访问成功",response= R.class)})
	@ApiImplicitParams({
			@ApiImplicitParam(name = "paramsJson", value = "确认车辆、确认人员拼装Json", required = true, dataType = "String",dataTypeClass = String.class,paramType = "query"),
			@ApiImplicitParam(name = "jqTywysbm", value = "警情_通用唯一识别码 ", required = true, dataType = "String",dataTypeClass = String.class,paramType = "query")
	})
	public R cdllqr(@RequestBody Map<String, Object> params){
		UserDO user =  getUser();
		String xfjyjgTywysbm = user.getXfjyjgTywysbm();
		boolean boo = xfclcdxxService.saveCdll(params.get("paramsJson").toString(),params.get("jqTywysbm").toString(),xfjyjgTywysbm,user.getUserId().toString());
		if(boo){
			return R.ok();
		}else{
			return R.error();
		}

	}





	
}
