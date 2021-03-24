package com.smart119.webapi.controller;

import com.alibaba.fastjson.JSONObject;
import com.smart119.common.controller.BaseController;
import com.smart119.common.utils.PageUtils;
import com.smart119.common.utils.Query;
import com.smart119.common.utils.R;
import com.smart119.common.utils.UUIDGenerator;
import com.smart119.system.mq.RabbitMQClient;
import com.smart119.webapi.domain.ZyxxClDO;
import com.smart119.webapi.domain.ZyxxDO;
import com.smart119.webapi.service.ZyxxClService;
import com.smart119.webapi.service.ZyxxService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @Description:
* @Param: 
* @return: 
* @Author: Miss.L
* @Date: 2021/2/8
*/
@Api(value = "增援力量", description = "增援力量 API")//Controller 描述
@Controller
@RequestMapping("/webapi/zyxx")
public class ZyxxController extends BaseController {
	@Autowired
	private ZyxxService zyxxService;
	@Autowired
	private ZyxxClService  zyxxClService;
	@Autowired
	private RabbitMQClient rabbitMQClient;
	@GetMapping()
	String Zyxx(){
	    return "webapi/zyxx/zyxx";
	}
	@ResponseBody
	@GetMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ZyxxDO> zyxxList = zyxxService.list(query);
		int total = zyxxService.count(query);
		PageUtils pageUtils = new PageUtils(zyxxList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	String add(){
	    return "webapi/zyxx/add";
	}

	@GetMapping("/edit/{jxxxzybm}")
	String edit(@PathVariable("jxxxzybm") String jxxxzybm,Model model){
		ZyxxDO zyxx = zyxxService.get(jxxxzybm);
		model.addAttribute("zyxx", zyxx);
	    return "webapi/zyxx/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@ApiOperation(value = "增援接口")
/*	@ApiImplicitParam(name = "map" , paramType = "body",examples = @Example({
			@ExampleProperty(value = "{'user':'id'}", mediaType = "application/json")
	}))*/
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public R save(@RequestBody Map<String, Object> map){
		 ZyxxDO zyxx =new ZyxxDO();
		    String uuid=UUIDGenerator.getUUID();
		    zyxx.setJxxxzybm(uuid);
		    zyxx.setJqTywysbm(map.get("JQ_TYWYSBM").toString());
			zyxx.setXfjyjgTywysbm(getUser().getXfjyjgTywysbm());
			zyxx.setZyuser(getUser().getUserId().toString());
			zyxx.setZycread(new Date());
	    	zyxx.setZyplace(map.get("ZYPLACE").toString());
	    	zyxx.setZysh(String.valueOf(map.get("ZYSH")));
	    	zyxx.setZytext(String.valueOf(map.get("ZYTEXT")));
	    	zyxx.setZyphone(String.valueOf(map.get("ZYPHONE")));
		    zyxx.setZylxuser(String.valueOf(map.get("ZYLXUSER")));
		if(zyxxService.save(zyxx)>0){
			List<Map<String,Object>> ZYXXCL=(List<Map<String,Object>>)map.get("ZYXXCL");
			for (int i = 0; i < ZYXXCL.size(); i++) {
				ZyxxClDO zzxxcl=new ZyxxClDO();
				zzxxcl.setId(UUIDGenerator.getUUID());
				zzxxcl.setJxxxzybm(uuid);
				zzxxcl.setXfzblxdm(ZYXXCL.get(i).get("value").toString());
				zzxxcl.setZycount(Integer.parseInt( ZYXXCL.get(i).get("zycount").toString()));
				zyxxClService.save(zzxxcl);
			}
			Map<String,Object>messMap=new HashMap<>();
			messMap.put("JQ_TYWYSBM",map.get("JQ_TYWYSBM").toString());
			messMap.put("jxxxzybm",uuid);
			messMap.put("XfjyjgTywysbm",getUser().getXfjyjgTywysbm());
			rabbitMQClient.sendMessageToExchange("zyqqdl", JSONObject.toJSONString(messMap));
			return R.ok();
		}
		return R.error();
	}

	@ResponseBody
	@GetMapping("/getlist")
	@ApiOperation(value = "增援力量查询", notes = "备注")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "jxxxzybm", value = "增援力量id", required = true, dataType = "String",dataTypeClass = String.class, paramType = "query"),
	})
	public Map<String,Object> getlist(@RequestParam Map<String, Object> params){
		Map<String,Object> map=new HashMap<>();
		try {
			map = zyxxService.getlist(params);
			if(!map.isEmpty()){
              List<Map<String,Object>>ZYXXCL=zyxxClService.getList(map.get("JXXXZYBM").toString());
				map.put("ZYXXCL",ZYXXCL);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	public R update( ZyxxDO zyxx){
		zyxxService.update(zyxx);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	public R remove( String jxxxzybm){
		if(zyxxService.remove(jxxxzybm)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	public R remove(@RequestParam("ids[]") String[] jxxxzybms){
		zyxxService.batchRemove(jxxxzybms);
		return R.ok();
	}
	
}
