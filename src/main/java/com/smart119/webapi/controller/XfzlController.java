package com.smart119.webapi.controller;


import com.smart119.common.controller.BaseController;
import com.smart119.common.service.DictService;
import com.smart119.common.utils.PageUtils;
import com.smart119.common.utils.Result;
import com.smart119.webapi.domain.XfzlDO;
import com.smart119.webapi.service.XfzlService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 消防战例
 * 
 * @author liangsl
 * @email liangsl@sz000673.com
 * @date 2021-03-11 14:56:44
 */
@Controller
@RequestMapping("/back/xfzl")
@Slf4j
public class XfzlController extends BaseController {


	@Autowired
	private XfzlService xfzlService;



	@Resource
	private DictService dictService;



	@GetMapping()
	@RequiresPermissions("back:xfzl:xfzl")
	String Xfzl(){
		return "xfzl/xfzl";
	}





	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("back:xfzl:xfzl")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
		PageUtils page = xfzlService.queryPage(params);
		return page;
	}


	@GetMapping("/add")
	@RequiresPermissions("back:xfzl:add")
	String add(Model model){
		List dictList = dictService.queryByDictType("JQFLYDM");
		model.addAttribute("dictList", dictList);
		return "xfzl/add";
	}



	@GetMapping("/info/{xfzlId}")
	@RequiresPermissions("appapi:xfzl:info")
	public Result<XfzlDO> info(@PathVariable("xfzlId") String xfzlId){
		XfzlDO xfzl = xfzlService.queryById(xfzlId);
		if(xfzl.getDzUserIds()!=null && !xfzl.getDzUserIds().equals("")){
			List<String> dzUserlist = Arrays.asList(xfzl.getDzUserIds().split(","));
			List<String> dzUserList1 =  dzUserlist.parallelStream().filter(o->o.equals(getUserId().toString())).collect(Collectors.toList());
			if(dzUserList1!=null && dzUserList1.size()>0){
				xfzl.setDzZt("1");
			}else{
				xfzl.setDzZt("0");
			}
		}else{
			xfzl.setDzZt("0");
		}
		return Result.suc(xfzl);
	}


	@ApiOperation(value = "消防战例点赞")
	@ApiParam(name = "xfzlId", value = "主键id", required = true)
	@GetMapping("/updDzCs/{xfzlId}")
	@RequiresPermissions("appapi:xfzl:updDzCs")
	public Result updDzCs(@PathVariable("xfzlId") String xfzlId){
		xfzlService.updDzCs(xfzlId);
		XfzlDO xfzlDO = xfzlService.queryById(xfzlId);
		if(xfzlDO.getDzUserIds()!=null && !xfzlDO.getDzUserIds().equals("")){
			xfzlService.updDzUserIds(xfzlDO.getDzUserIds()+","+getUserId(),xfzlId);
		}else{
			xfzlService.updDzUserIds(getUserId().toString(),xfzlId);
		}
		return Result.suc(xfzlDO.getDzcs());
	}


	
}
