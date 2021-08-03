package com.smart119.jczy.controller;

import com.smart119.common.controller.BaseController;
import com.smart119.common.utils.PageUtils;
import com.smart119.jczy.service.QyjqtjService;
import com.smart119.webapi.domain.JbxxDO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;

/**
 * 行政区域警情基本信息
 * 
 * @author scy
 * @email shichengyuan@sz000673.com
 * @date 2021-08-02 09:12:43
 */
@Api(tags = "行政区域警情统计管理")
@Controller
@RequestMapping("/jczy/qyjqtj")
@Slf4j
public class QyjqtjController extends BaseController {

	@Autowired
	private QyjqtjService qyjqtjService;

	@GetMapping()
	@RequiresPermissions("jczy:qyjqtj:qyjqtj")
	String Qyjqtj(){
		return "jczy/qyjqtj/qyjqtj";
	}

	@ApiOperation(value = "行政区域警情统计查询")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "startDate", value = "开始时间", required = true   ,  dataType = "String",paramType = "body"),
			@ApiImplicitParam(name = "endDate", value = "结束时间", required = true    ,  dataType = "String",paramType = "body")
	})
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("jczy:qyjqtj:qyjqtj")
	public PageUtils qyjqtjList(@ApiIgnore @RequestParam Map<String, Object> params){
		List<JbxxDO> qyjqtjList = qyjqtjService.qyjqtjList(params);
		PageUtils pageUtils = new PageUtils(qyjqtjList, qyjqtjList.size());
		return pageUtils;
	}

}
