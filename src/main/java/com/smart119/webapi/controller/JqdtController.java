package com.smart119.webapi.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.smart119.webapi.service.JqdtService;
import com.smart119.common.utils.R;

/**
 * 警情动态
 * 
 * @author liangsl
 * @email 123564081@qq.com
 * @date 2021-01-30 10:02:02
 */
@RestController
@Api(value = "（接警端）警情动态API", description = "（接警端）警情动态API")
@RequestMapping("/webapi/jqdt")
public class JqdtController {
	@Autowired
	private JqdtService jqdtService;

	@ApiOperation("查询警情动态")
	@GetMapping("/getJqdtByJqId")
	public R getJqdtByJqId(@ApiParam(value = "警情id") @RequestParam(value="jqTywysbm",required = true)String jqTywysbm){
		return R.ok(jqdtService.getJqdtByJqId(jqTywysbm));
	}
	
}
