package com.smart119.jczy.controller;

import com.smart119.common.controller.BaseController;
import com.smart119.common.utils.PageUtils;
import com.smart119.jczy.service.JqtjService;
import com.smart119.system.domain.DeptDO;
import com.smart119.system.service.DeptService;
import com.smart119.webapi.domain.JbxxDO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 警情基本信息
 * 
 * @author scy
 * @email shichengyuan@sz000673.com
 * @date 2021-08-02 09:12:43
 */
@Api(tags = "警情统计管理")
@Controller
@RequestMapping("/jczy/jqtj")
@Slf4j
public class JqtjController extends BaseController {


	@Autowired
	private JqtjService jqtjService;

	@Autowired
	private DeptService deptService;

	@GetMapping()
	@RequiresPermissions("jczy:jqtj:jqtj")
	String Jqtj(){
		return "jczy/jqtj/jqtj";
	}

	@ApiOperation(value = "警情统计查询")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "startDate", value = "开始时间", required = true   ,  dataType = "String",paramType = "body"),
			@ApiImplicitParam(name = "endDate", value = "结束时间", required = true    ,  dataType = "String",paramType = "body"),
			@ApiImplicitParam(name = "deptId", value = "部门唯一标识", required = true    ,  dataType = "String",paramType = "body")
	})
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("jczy:jqtj:jqtj")
	public PageUtils list(@ApiIgnore @RequestParam Map<String, Object> params){
		//查询列表数据
		List<DeptDO> deptList = new ArrayList<>();
		if(params.get("deptId")!=null && !params.get("deptId").equals("")){
			deptList = deptService.listChildren(Long.valueOf(params.get("deptId").toString()));
		}else{
			deptList = deptService.listChildren(getUser().getDeptId());
		}
		params.put("deptList",deptList);

		List<JbxxDO> jqtjList = jqtjService.list(params);

		PageUtils pageUtils = new PageUtils(jqtjList, jqtjList.size());
		return pageUtils;
	}

}
