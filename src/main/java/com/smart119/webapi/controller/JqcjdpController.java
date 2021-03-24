package com.smart119.webapi.controller;

import com.alibaba.fastjson.JSONObject;
import com.smart119.common.controller.BaseController;
import com.smart119.common.utils.PageUtils;
import com.smart119.common.utils.Query;
import com.smart119.common.utils.R;
import com.smart119.jczy.domain.XfclDO;
import com.smart119.jczy.domain.XfclSxDO;
import com.smart119.jczy.domain.XfclSxzDO;
import com.smart119.jczy.service.XfclService;
import com.smart119.jczy.service.XfclSxService;
import com.smart119.jczy.service.XfclSxzService;
import com.smart119.system.mq.RabbitMQClient;
import com.smart119.system.service.DeptService;
import com.smart119.webapi.dao.XfjgcdxxDao;
import com.smart119.webapi.domain.JqcjdpCarDO;
import com.smart119.webapi.domain.JqcjdpDO;
import com.smart119.webapi.domain.JqcjdpDzDO;
import com.smart119.webapi.domain.XfjgcdxxDO;
import com.smart119.webapi.service.JqcjdpCarService;
import com.smart119.webapi.service.JqcjdpDzService;
import com.smart119.webapi.service.JqcjdpService;
import com.smart119.webapi.service.XfjgcdxxService;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.*;

/**
 * 警情处警调派基本信息
 *
 * @author yanyu
 * @email yanyu@sz000673.com
 * @date 2021-01-28 14:05:38
 */
@Api(value = "力量调派", description = "力量调派 API")
@Controller
@RequestMapping("/webapi/jqcjdp")
public class JqcjdpController extends BaseController {
	@Autowired
	private JqcjdpService jqcjdpService;
	//调派队站业务
	@Autowired
	private JqcjdpDzService jqcjdpDzService;
	//调派车辆业务
	@Autowired
	private JqcjdpCarService jqcjdpCarService;
	//消防车辆属性
	@Autowired
	private XfclSxService xfclSxService;
    //消防车辆属性值
	@Autowired
	private XfclSxzService xfclSxzService;
    //消防车辆
	@Autowired
	private XfclService xfclService;
	//消防机构
	@Autowired
	private DeptService deptService;

	@Autowired
	RabbitMQClient rabbitMQClient;

	@Autowired
	private XfjgcdxxService xfjgcdxxService;

	@GetMapping()
	@RequiresPermissions("webapi:jqcjdp:jqcjdp")
	String Jqcjdp(){
	    return "webapi/jqcjdp/jqcjdp";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("webapi:jqcjdp:jqcjdp")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<JqcjdpDO> jqcjdpList = jqcjdpService.list(query);
		int total = jqcjdpService.count(query);
		PageUtils pageUtils = new PageUtils(jqcjdpList, total);
		return pageUtils;
	}

	@GetMapping("/add")
	@RequiresPermissions("webapi:jqcjdp:add")
	String add(){
	    return "webapi/jqcjdp/add";
	}

	/**
	 * @Description: 实力调派保存接口
	 * @Param: [jbxxDO, dtsxDO, dtsxzDO, jqcjdpDO, jqcjdpCarDO]
	 * @return: com.smart119.common.utils.R
	 * @Author: yanyu
	 * @Date: 2021/1/28
	 */
	@ApiOperation("力量调派保存传Map（接警端）")
	@ResponseBody
	@PostMapping("/dispatchSave")
	public Map dispatchSave(@RequestBody JSONObject params) throws ParseException {
		Map returnMap=jqcjdpService.strenghTransfer(params);
        if(returnMap.get("code").equals("200")){
			return returnMap;
		}else{
			return returnMap;
		}

	}


	@GetMapping("/edit/{jqcjdpTywysbm}")
	@RequiresPermissions("webapi:jqcjdp:edit")
	String edit(@PathVariable("jqcjdpTywysbm") String jqcjdpTywysbm,Model model){
		JqcjdpDO jqcjdp = jqcjdpService.get(jqcjdpTywysbm);
		model.addAttribute("jqcjdp", jqcjdp);
	    return "webapi/jqcjdp/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("webapi:jqcjdp:add")
	public R save( JqcjdpDO jqcjdp){
		if(jqcjdpService.save(jqcjdp)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("webapi:jqcjdp:edit")
	public R update( JqcjdpDO jqcjdp){
		jqcjdpService.update(jqcjdp);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("webapi:jqcjdp:remove")
	public R remove( String jqcjdpTywysbm){
		if(jqcjdpService.remove(jqcjdpTywysbm)>0){
		return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("webapi:jqcjdp:batchRemove")
	public R remove(@RequestParam("ids[]") String[] jqcjdpTywysbms){
		jqcjdpService.batchRemove(jqcjdpTywysbms);
		return R.ok();
	}


	/**
	 * 根据警情ID和组织机构ID查询被派信息
	 * @param params
	 * @return
	 */
	@ApiOperation("根据警情ID和机构识别编码查询被派信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "jqTywysbm", value = "警情ID", required = true, dataType = "String",dataTypeClass = String.class,paramType = "query"),
			@ApiImplicitParam(name = "xfjyjgTywysbm", value = "机构识别编码", required = true, dataType = "String",dataTypeClass = String.class,paramType = "query"),

	})
	//返回描述
	@ApiResponses(value = {@ApiResponse(code = 200, message = "访问成功",response=XfclDO.class)})
	@GetMapping("/queryBeSentByjqTywysbmAndXfjyjgTywysbm")
	@ResponseBody
	public R queryBeSentByjqTywysbmAndXfjyjgTywysbm(@RequestParam Map<String,String> params){
		List<XfclDO> xfclDOList = new ArrayList<>();
		//获取警情ID
		String jqTywysbm = params.get("jqTywysbm");
		//获取机构编码
		String xfjyjgTywysbm = params.get("xfjyjgTywysbm");
		//构建参数对象
		Map<String,Object> paramMap = new HashMap<>();
		//警情ID
		paramMap.put("jqTywysbm",jqTywysbm);
		paramMap.put("status","0");
		//获取该警情下的调派记录
		List<JqcjdpDO> jqcjdpDOList = this.jqcjdpService.list(paramMap);
		for(JqcjdpDO jqcjdpDO:jqcjdpDOList){
			paramMap = new HashMap<>();
			//调派ID
			paramMap.put("dpTywysbm",jqcjdpDO.getJqcjdpTywysbm());
			//机构识别编码
			paramMap.put("xfjyjgTywysbm",xfjyjgTywysbm);
			paramMap.put("status","0");
			List<JqcjdpDzDO> jqcjdpDzDOList = this.jqcjdpDzService.list(paramMap);
			if(jqcjdpDzDOList .size() > 0){
				//循环遍历每个队站
				for(JqcjdpDzDO jqcjdpDzDO:jqcjdpDzDOList){
					String nameByTYWYSBM = this.deptService.findNameByTYWYSBM(jqcjdpDzDO.getXfjyjgTywysbm());
					jqcjdpDzDO.setXfjyjgTywysbmName(nameByTYWYSBM);
					//获取队站ID
					String dpdzTywysbm = jqcjdpDzDO.getDpdzTywysbm();
					paramMap = new HashMap<>();
					//队站ID
					paramMap.put("dpdzTywysbm",dpdzTywysbm);
					//调派ID
					paramMap.put("jqcjdpTywysbm",jqcjdpDzDO.getDpTywysbm());
					paramMap.put("status","0");
					//根据队站ID和调派ID查询调派车辆信息
					List<JqcjdpCarDO> jqcjdpCarDOList = this.jqcjdpCarService.list(paramMap);
					for(JqcjdpCarDO jqcjdpCarDO:jqcjdpCarDOList){
						XfclDO xfclDO = this.xfclService.get(jqcjdpCarDO.getXfjyclTywysbm());
						jqcjdpCarDO.setXfclDO(xfclDO);
						paramMap = new HashMap<>();
						//车辆Id
						paramMap.put("xfclId",jqcjdpCarDO.getXfjyclTywysbm());
						paramMap.put("status","0");
						//根据车辆ID查询对应的车辆属性信息
						List<XfclSxzDO> xfclSxzDOList = xfclSxzService.list(paramMap);
						for(XfclSxzDO xfclSxzDO:xfclSxzDOList){
							//获取属性ID
							String xfclSxId = xfclSxzDO.getXfclSxId();
							XfclSxDO xfclSxDO = xfclSxService.get(xfclSxId);
							String clsx = xfclSxDO.getClsx();
							String dw = xfclSxDO.getDw();
							xfclSxzDO.setClsx(clsx);
							xfclSxzDO.setDw(dw);
						}
						xfclDO.setXfclSxzDOList(xfclSxzDOList);
						xfclDOList.add(xfclDO);
					}
				}
			}
		}
		return R.ok(xfclDOList);
	}



	/**
	 * 根据警情ID和组织机构ID和调派ID查询被派信息
	 * @param params
	 * @return
	 */
	@ApiOperation("根据警情ID和组织机构ID和调派ID查询被派信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "jqTywysbm", value = "警情ID", required = true, dataType = "String",dataTypeClass = String.class,paramType = "query"),
			@ApiImplicitParam(name = "jqcjdpTywysbm", value = "调派ID", required = true, dataType = "String",dataTypeClass = String.class,paramType = "query"),
			@ApiImplicitParam(name = "xfjyjgTywysbm", value = "机构识别编码", required = true, dataType = "String",dataTypeClass = String.class,paramType = "query"),

	})
	//返回描述
	@ApiResponses(value = {@ApiResponse(code = 200, message = "访问成功",response=JqcjdpDO.class)})
	@GetMapping("/queryBeSentByjqTywysbm")
	@ResponseBody
	public R queryBeSentByjqTywysbm(@RequestParam Map<String,String> params){
		//获取警情ID
		String jqTywysbm = params.get("jqTywysbm");
		//获取机构编码
		String xfjyjgTywysbm = params.get("xfjyjgTywysbm");

		//获取调派ID
		String jqcjdpTywysbm = params.get("jqcjdpTywysbm");
		//构建参数对象
		Map<String,Object> paramMap = new HashMap<>();
		//警情ID
		paramMap.put("jqTywysbm",jqTywysbm);
		//调派ID
		paramMap.put("jqcjdpTywysbm",jqcjdpTywysbm);
		paramMap.put("status","0");
		//获取该警情下的调派记录
		JqcjdpDO jqcjdpDO = this.jqcjdpService.list(paramMap).get(0);
		List<XfclDO> xfclDOList = new ArrayList<>();
		paramMap = new HashMap<>();
		//调派ID
		paramMap.put("dpTywysbm",jqcjdpTywysbm);
		//机构识别编码
		paramMap.put("xfjyjgTywysbm",xfjyjgTywysbm);
		paramMap.put("status","0");
		List<JqcjdpDzDO> jqcjdpDzDOList = this.jqcjdpDzService.list(paramMap);
		if(jqcjdpDzDOList .size() > 0){
			//循环遍历每个队站
			for(JqcjdpDzDO jqcjdpDzDO:jqcjdpDzDOList){
				String nameByTYWYSBM = this.deptService.findNameByTYWYSBM(jqcjdpDzDO.getXfjyjgTywysbm());
				jqcjdpDzDO.setXfjyjgTywysbmName(nameByTYWYSBM);
				//获取队站ID
				String dpdzTywysbm = jqcjdpDzDO.getDpdzTywysbm();
				paramMap = new HashMap<>();
				//队站ID
				paramMap.put("dpdzTywysbm",dpdzTywysbm);
				//调派ID
				paramMap.put("jqcjdpTywysbm",jqcjdpDzDO.getDpTywysbm());
				paramMap.put("status","0");
				//根据队站ID和调派ID查询调派车辆信息
				List<JqcjdpCarDO> jqcjdpCarDOList = this.jqcjdpCarService.list(paramMap);
				for(JqcjdpCarDO jqcjdpCarDO:jqcjdpCarDOList){
					XfclDO xfclDO = this.xfclService.get(jqcjdpCarDO.getXfjyclTywysbm());
					jqcjdpCarDO.setXfclDO(xfclDO);
					paramMap = new HashMap<>();
					//车辆Id
					paramMap.put("xfclId",jqcjdpCarDO.getXfjyclTywysbm());
					paramMap.put("status","0");
					//根据车辆ID查询对应的车辆属性信息
					List<XfclSxzDO> xfclSxzDOList = xfclSxzService.list(paramMap);
					for(XfclSxzDO xfclSxzDO:xfclSxzDOList){
						//获取属性ID
						String xfclSxId = xfclSxzDO.getXfclSxId();
						XfclSxDO xfclSxDO = xfclSxService.get(xfclSxId);
						String clsx = xfclSxDO.getClsx();
						String dw = xfclSxDO.getDw();
						xfclSxzDO.setClsx(clsx);
						xfclSxzDO.setDw(dw);
					}
					xfclDO.setXfclSxzDOList(xfclSxzDOList);
					xfclDOList.add(xfclDO);
				}
			}
		}
		jqcjdpDO.setXfclDOList(xfclDOList);
		return R.ok(Arrays.asList(jqcjdpDO));
	}

	/**
	 * 队站车辆数量
	 * @param  jqTywysbm 参数集合
	 * @return
	 */
	@ApiOperation("查询队站车辆基本信息")
	@GetMapping( "/dzcl")
	@ResponseBody
	@ApiResponses(value = {@ApiResponse(code = 200, message = "访问成功",response= HashMap.class)})
	public PageUtils dzcl(@ApiParam(value = "警情ID") @RequestParam(value = "jqTywysbm", required = true) String jqTywysbm){
		List<Map<String,Object>> retList = jqcjdpCarService.dzclCount(jqTywysbm);
		int total = retList.size();
		PageUtils pageUtils = new PageUtils(retList, total);
		return pageUtils;
	}








	//返回描述
	@ApiResponses(value = {@ApiResponse(code = 200, message = "访问成功",response=JqcjdpDO.class)})
	@GetMapping("/queryBeSentByjqTywysbmAndXfjyjgTywysbm2")
	@ResponseBody
	public R queryBeSentByjqTywysbmAndXfjyjgTywysbm2(@RequestParam Map<String,String> params){
		//获取警情ID
		String jqTywysbm = params.get("jqTywysbm");
		//获取机构编码
		String xfjyjgTywysbm = params.get("xfjyjgTywysbm");
		//构建参数对象
		Map<String,Object> paramMap = new HashMap<>();
		//警情ID
		paramMap.put("jqTywysbm",jqTywysbm);
		paramMap.put("status","0");
		//获取该警情下的调派记录
		List<JqcjdpDO> jqcjdpDOList = this.jqcjdpService.list(paramMap);
		for(JqcjdpDO jqcjdpDO:jqcjdpDOList){
			List<XfclDO> xfclDOList = new ArrayList<>();
			paramMap = new HashMap<>();
			//调派ID
			paramMap.put("dpTywysbm",jqcjdpDO.getJqcjdpTywysbm());
			//机构识别编码
			paramMap.put("xfjyjgTywysbm",xfjyjgTywysbm);
			paramMap.put("status","0");
			List<JqcjdpDzDO> jqcjdpDzDOList = this.jqcjdpDzService.list(paramMap);
			if(jqcjdpDzDOList .size() > 0){
				//循环遍历每个队站
				for(JqcjdpDzDO jqcjdpDzDO:jqcjdpDzDOList){
					String nameByTYWYSBM = this.deptService.findNameByTYWYSBM(jqcjdpDzDO.getXfjyjgTywysbm());
					jqcjdpDzDO.setXfjyjgTywysbmName(nameByTYWYSBM);
					//获取队站ID
					String dpdzTywysbm = jqcjdpDzDO.getDpdzTywysbm();
					paramMap = new HashMap<>();
					//队站ID
					paramMap.put("dpdzTywysbm",dpdzTywysbm);
					//调派ID
					paramMap.put("jqcjdpTywysbm",jqcjdpDzDO.getDpTywysbm());
					paramMap.put("status","0");
					//根据队站ID和调派ID查询调派车辆信息
					List<JqcjdpCarDO> jqcjdpCarDOList = this.jqcjdpCarService.list(paramMap);
					for(JqcjdpCarDO jqcjdpCarDO:jqcjdpCarDOList){
						XfclDO xfclDO = this.xfclService.get(jqcjdpCarDO.getXfjyclTywysbm());
						jqcjdpCarDO.setXfclDO(xfclDO);
						paramMap = new HashMap<>();
						//车辆Id
						paramMap.put("xfclId",jqcjdpCarDO.getXfjyclTywysbm());
						paramMap.put("status","0");
						//根据车辆ID查询对应的车辆属性信息
						List<XfclSxzDO> xfclSxzDOList = xfclSxzService.list(paramMap);
						for(XfclSxzDO xfclSxzDO:xfclSxzDOList){
							//获取属性ID
							String xfclSxId = xfclSxzDO.getXfclSxId();
							XfclSxDO xfclSxDO = xfclSxService.get(xfclSxId);
							String clsx = xfclSxDO.getClsx();
							String dw = xfclSxDO.getDw();
							xfclSxzDO.setClsx(clsx);
							xfclSxzDO.setDw(dw);
						}
						xfclDO.setXfclSxzDOList(xfclSxzDOList);
						xfclDOList.add(xfclDO);
					}
				}
			}
			jqcjdpDO.setXfclDOList(xfclDOList);
		}
		return R.ok(jqcjdpDOList);
	}

	@GetMapping( "/preDeployment")
	@ResponseBody
	public R preDeployment(@RequestParam Map<String,String> params){
		String XFJYJG_TYWYSBM = params.get("XFJYJG_TYWYSBM");
		JSONObject mqjsonObject=new JSONObject();
		mqjsonObject.put("dzid",XFJYJG_TYWYSBM);                                                          //警情ID
		rabbitMQClient.sendMessageToExchange("perDispatchSave",mqjsonObject.toJSONString());
		return R.ok();
	}


	/**
	 * 力量出动验证接口
	 */
	@ApiOperation("力量出动验证接口")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "jqTywysbm", value = "警情ID", required = true, dataType = "String",dataTypeClass = String.class,paramType = "query")

	})
	//返回描述
	@ApiResponses(value = {@ApiResponse(code = 200, message = "访问成功",response=XfclDO.class)})
	@ResponseBody
	@PostMapping("/verification")
	public R verification(@RequestBody Map<String, Object> params){
		params.put("xfjyjgTywysbm",getUser().getXfjyjgTywysbm());
		List<XfjgcdxxDO> list = jqcjdpService.findXfjgcdByJqBm(params);
		if(list!=null && list.size()>0){
			return R.ok("0");
		}
		return R.ok("1");
	}




}
