package com.smart119.webapi.controller;

import com.alibaba.fastjson.JSONArray;
import com.smart119.common.service.BaiduMapService;
import com.smart119.common.utils.PageUtils;
import com.smart119.common.utils.Query;
import com.smart119.common.utils.R;
import com.smart119.webapi.domain.JbxxDO;
import com.smart119.webapi.service.JbxxService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 地址补全接口
 *
 * @author yanyu
 * @email yanyu@sz000673.com
 * @date 2021-01-28 11:48:41
 */
@Api(value = "地址补全", description = "（接警端）地址补全 API")
@Controller
@RequestMapping("/webapi/v1")
public class AddressController {
	@Autowired
	private JbxxService jbxxService;
	@Autowired
	private BaiduMapService baiduMapService;

	@GetMapping()
	@RequiresPermissions("webapi:jbxx:jbxx")
	String Jbxx(){
	    return "webapi/jbxx/jbxx";
	}

	/**
	 * @Description:
	 * @Param: [params]
	 * @return: com.smart119.common.utils.PageUtils
	 * @Author: yanyu
	 * @Date: 2021/1/28
	 */
	@ApiOperation("地址补全查询传{'addrName':'地址名称','city':'地市名称如临沂、哈尔滨'}")
	@ResponseBody
	@GetMapping("/dzbqQuery")
		public R list(@RequestParam Map<String, Object> params){
		JSONArray jsonArray = baiduMapService.getMapAddrByName(params.get("addrName").toString(),params.get("city").toString());
		return R.ok(jsonArray);
	}
}
