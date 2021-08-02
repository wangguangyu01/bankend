package com.smart119.jczy.controller;

import com.smart119.common.utils.PageUtils;
import com.smart119.common.utils.R;
import com.smart119.jczy.service.JqtjService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Map;

/**
 * 警情基本信息
 * 
 * @author scy
 * @email shichengyuan@sz000673.com
 * @date 2021-08-02 09:12:43
 */
@Api(tags = "警情统计管理")
@RestController
@RequestMapping("/jczy/jqtj")
@Slf4j
public class JqtjController {


	@Autowired
	private JqtjService jqtjService;


	@GetMapping()
	@RequiresPermissions("jczy:jqtj:jqtj")
	String Jqtj(){
		return "jczy/jqtj/jqtj";
	}

	@ApiOperation(value = "警情统计查询")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "startDate", value = "开始时间", required = true   ,  dataType = "String",paramType = "body"),
			@ApiImplicitParam(name = "endDate", value = "结束时间", required = true    ,  dataType = "String",paramType = "body"),
			@ApiImplicitParam(name = "xfjyjgTywysbm", value = "消防救援机构通用唯一识别码", required = true    ,  dataType = "String",paramType = "body")
	})
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("jczy:jqtj:jqtj")
	public R list(@ApiIgnore @RequestParam Map<String, Object> params){
		//查询列表数据
		PageUtils page = jqtjService.queryPage(params);
		return R.ok(page);
	}

}
