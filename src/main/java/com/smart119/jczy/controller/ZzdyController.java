package com.smart119.jczy.controller;

import com.alibaba.fastjson.JSONObject;
import com.smart119.common.controller.BaseController;
import com.smart119.common.domain.DictDO;
import com.smart119.common.service.BaiduMapService;
import com.smart119.common.service.DictService;
import com.smart119.common.utils.PageUtils;
import com.smart119.common.utils.Query;
import com.smart119.common.utils.R;
import com.smart119.jczy.domain.ZzdyDO;
import com.smart119.jczy.domain.ZzdyXfclDO;
import com.smart119.jczy.domain.ZzdyXfzbDO;
import com.smart119.jczy.service.ZzdyService;
import com.smart119.jczy.service.ZzdyXfclService;
import com.smart119.jczy.service.ZzdyXfzbService;
import com.smart119.system.domain.DeptDO;
import com.smart119.system.service.DeptService;
import io.swagger.annotations.*;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 作战单元
 *
 * @author shilei
 * @email thrz@sz000673.com
 * @date 2021-01-29 20:42:51
 */
@Api(value = "作战单元", description = "作战单元API")
@Controller
@RequestMapping("/webapi/zzdy")
public class ZzdyController extends BaseController {
	@Autowired
	private ZzdyService zzdyService;
	@Autowired
	private ZzdyXfclService  zzdyXfclService;
	@Autowired
	private BaiduMapService baiduMapService;
	@Autowired
	private DictService dictService;
	@Autowired
	private DeptService deptService;
	@Autowired
	private ZzdyXfzbService zzdyXfzbService;

	@GetMapping()
	@RequiresPermissions("webapi:zzdy:zzdy")
	String Zzdy(Model model){
		model.addAttribute("deptId", getUser().getDeptId());
		return "jczy/zzdy/zzdy";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("webapi:zzdy:zzdy")
	public PageUtils list(@RequestParam Map<String, Object> params){
		String xfjyjgTywysbm = getUser().getXfjyjgTywysbm();

		params.put("xfjyjgTywysbm",xfjyjgTywysbm);
		//params.put("status","0");
		//查询列表数据
        Query query = new Query(params);
		List<DeptDO> deptList = new ArrayList<>();
		if(params.get("deptId")!=null && !params.get("deptId").equals("")){
			deptList = deptService.listChildren(Long.valueOf(params.get("deptId").toString()));
		}else{
			deptList = deptService.listChildren(getUser().getDeptId());
		}
		query.put("deptList",deptList);
		List<ZzdyDO> zzdyList = zzdyService.list(query);
		int total = zzdyService.count(query);
		PageUtils pageUtils = new PageUtils(zzdyList, total);
		return pageUtils;
	}

	@GetMapping("/add")
	@RequiresPermissions("webapi:zzdy:add")
	String add(@RequestParam("deptId") Long deptId,Model model){
	    //Long deptId = Long.parseLong(deptIdStr);
		DeptDO  name=deptService.get(deptId);
		Map map=new HashMap();
		map.put("deptName", name.getDwmc());
		map.put("xfjyjgTywysbm", name.getXfjyjgTywysbm());
		map.put("deptId", deptId);
		model.addAttribute("map",map);
		return "jczy/zzdy/add";
	}

	@GetMapping("/addxfcl")
	String addxfcl(HttpServletRequest request, Model model){
		String zzdyTywybs=request.getParameter("zzdyTywybs");
		String xfjyjgTywysbm=request.getParameter("xfjyjgTywysbm");
		List<ZzdyXfclDO> xfclnameList=zzdyXfclService.getZzdyxfcl(zzdyTywybs);
		model.addAttribute("xfclnameList",xfclnameList);
		model.addAttribute("xfjyjgTywysbm",xfjyjgTywysbm);
		return "jczy/zzdy/addXfcl";
	}

    @GetMapping("/addzbqc")
    String addzbqc(HttpServletRequest request, Model model){
        String zzdyTywybs=request.getParameter("zzdyTywybs");
        String xfjyjgTywysbm=request.getParameter("xfjyjgTywysbm");
        List<ZzdyXfzbDO> xfzbnameList=zzdyXfzbService.getZzdyXfzb(zzdyTywybs);
        model.addAttribute("xfzbnameList",xfzbnameList);
        model.addAttribute("xfjyjgTywysbm",xfjyjgTywysbm);
        return "jczy/zzdy/addZbqc";
    }

	@GetMapping("/edit/{zzdyTywybs}")
	@RequiresPermissions("webapi:zzdy:edit")
	String edit(@PathVariable("zzdyTywybs") String zzdyTywybs,Model model){
		ZzdyDO zzdy = zzdyService.get(zzdyTywybs);
		String jqflydms = zzdy.getJqflydm();
		if (jqflydms != null && !jqflydms.equals("")) {
			String[] jqflydmsArr = jqflydms.split(",");
			List<String> jqflydmsList = Arrays.asList(jqflydmsArr);
			List<DictDO> dictDOList = dictService.getDictByTypeAndValues(jqflydmsList,"JQFLYDM");
			model.addAttribute("dictDOList",dictDOList);
		}else{
			List<DictDO> dictDOList = new ArrayList<>();
			model.addAttribute("dictDOList",dictDOList);
		}
		model.addAttribute("zzdy", zzdy);
		List<ZzdyXfclDO> xfclnameList=zzdyXfclService.getZzdyxfcl(zzdyTywybs);
		model.addAttribute("xfclnameList",xfclnameList);
		List<ZzdyXfzbDO> xfzbnameList=zzdyXfzbService.getZzdyXfzb(zzdyTywybs);
		model.addAttribute("xfzbnameList",xfzbnameList);
	    return "jczy/zzdy/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("webapi:zzdy:add")
	public R save(@Validated ZzdyDO zzdy){
		try {
			if(zzdyService.save(zzdy)>0){
				return R.ok();
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("webapi:zzdy:edit")
	public R update(@Validated ZzdyDO zzdy){
		//删除绑定车辆
		zzdyXfclService.removeZzdy(zzdy.getZzdyTywybs());
		String [] xfccl=zzdy.getXfclTywysbm().split(",");
		if(xfccl !=null && xfccl.length>0){
			for (int i = 0; i < xfccl.length; i++) {
				ZzdyXfclDO dao=new ZzdyXfclDO();
				dao.setId(UUID.randomUUID().toString().replace("-", ""));
				dao.setXfclTywysbm(xfccl[i]);
				dao.setZzdyTywybs(zzdy.getZzdyTywybs());
				zzdyXfclService.save(dao);
			}
		}
		//删除绑定装备
		zzdyXfzbService.removeZzdyXfzb(zzdy.getZzdyTywybs());
		if (zzdy.getXfzbTywysbm() != null && !zzdy.getXfzbTywysbm().equals("")) {
			String[] xfzbTywysbmArr = zzdy.getXfzbTywysbm().split(",");
			if (xfzbTywysbmArr != null && xfzbTywysbmArr.length > 0) {
				for (String xfzbTywysbm : xfzbTywysbmArr) {
					ZzdyXfzbDO zzdyXfzbDO = new ZzdyXfzbDO();
					zzdyXfzbDO.setId(UUID.randomUUID().toString().replace("-", ""));
					zzdyXfzbDO.setXfzbTywysbm(xfzbTywysbm);
					zzdyXfzbDO.setZzdyTywybs(zzdy.getZzdyTywybs());
					zzdyXfzbService.save(zzdyXfzbDO);
				}
			}
		}
		zzdyService.update(zzdy);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("webapi:zzdy:remove")
	public R remove( String zzdyTywybs){
		if(zzdyService.remove(zzdyTywybs)>0){
		return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("webapi:zzdy:batchRemove")
	public R remove(@RequestParam("ids[]") String[] zzdyTywybss){
		zzdyService.batchRemove(zzdyTywybss);
		return R.ok();
	}



	/**
	 * 作战单元接口
	 * @param params 作战单元查询条件（zzdymc 作战单元名称）
	 * @return
	 */
	@ApiOperation("作战单元接口")
	@GetMapping( "/zzdyList")
	@ResponseBody
	@ApiImplicitParams({
			@ApiImplicitParam(name = "zzdymc", value = "作战单元名称", required = true, dataType = "String",dataTypeClass = String.class,paramType = "query"),
			@ApiImplicitParam(name = "xfjyjgTywysbm", value = "消防救援机构编码", required = true, dataType = "String",dataTypeClass = String.class,paramType = "query"),
			@ApiImplicitParam(name = "jqTywysbm", value = "警情编码", required = true, dataType = "String",dataTypeClass = String.class,paramType = "query"),
			@ApiImplicitParam(name = "jd", value = "经度坐标", required = true, dataType = "String",dataTypeClass = String.class,paramType = "query"),
			@ApiImplicitParam(name = "wd", value = "纬度坐标", required = true, dataType = "String",dataTypeClass = String.class,paramType = "query")
	})
	@ApiResponses(value = {@ApiResponse(code = 200, message = "访问成功",response= DeptDO.class)})
	public R zzdyList(@RequestParam Map<String, Object> params){

		List<DeptDO> deptList = zzdyService.zzdyList(params);
		return R.ok(deptList);

	}










	/**
	 * 路线推荐接口
	 * @param
	 * @return bjTywysbm   报警_通用唯一识别码
	 * @return xfjyjgTywysbm   消防救援机构通用唯一识别码
	 */
	@ApiOperation("路线推荐接口_百度_货车")
	@GetMapping( "/lxtj_baidu")
	@ResponseBody
	@ApiImplicitParams({
			@ApiImplicitParam(name = "jqjd", value = "警情经度", required = true, dataType = "String",dataTypeClass = String.class,paramType = "query"),
			@ApiImplicitParam(name = "jqwd", value = "警情纬度", required = true, dataType = "String",dataTypeClass = String.class,paramType = "query"),
			@ApiImplicitParam(name = "xfjyjgTywysbm", value = "消防救援机构通用唯一识别码", required = true, dataType = "String",dataTypeClass = String.class,paramType = "query")
	})
	@ApiResponses(value = {@ApiResponse(code = 200, message = "访问成功",response= HashMap.class)})
	public Map<String,Object> lxtj_baidu(@RequestParam Map<String, Object> params){
		DeptDO deptDo = zzdyService.deptByXfjyjgTywysbm(params);
		String origins = deptDo.getDqwd()+","+deptDo.getDqjd();  //起点坐标
		String destinations = params.get("jqwd")+","+params.get("jqjd");  //终点坐标
		Object obj = baiduMapService.routeRecommendationBaidu(origins,destinations);
		return R.ok(obj);
	}



	/**
	 * 路线推荐接口
	 * @param
	 * @return bjTywysbm   报警_通用唯一识别码
	 * @return xfjyjgTywysbm   消防救援机构通用唯一识别码
	 */
	@ApiOperation("路线推荐接口_高德_货车")
	@GetMapping( "/lxtj_gaode")
	@ResponseBody
	@ApiImplicitParams({
			@ApiImplicitParam(name = "jqjd", value = "警情经度", required = true, dataType = "String",dataTypeClass = String.class,paramType = "query"),
			@ApiImplicitParam(name = "jqwd", value = "警情纬度", required = true, dataType = "String",dataTypeClass = String.class,paramType = "query"),
			@ApiImplicitParam(name = "xfjyjgTywysbm", value = "消防救援机构通用唯一识别码", required = true, dataType = "String",dataTypeClass = String.class,paramType = "query")
	})
	@ApiResponses(value = {@ApiResponse(code = 200, message = "访问成功",response= HashMap.class)})
	public Map<String,Object> lxtj_gaode (@RequestParam Map<String, Object> params){
		DeptDO deptDo = zzdyService.deptByXfjyjgTywysbm(params);

		String origins = deptDo.getDqjd()+","+deptDo.getDqwd();  //起点坐标
		String destinations = params.get("jqjd")+","+params.get("jqwd");  //终点坐标
		String baiduZb = origins+"|"+destinations;
		JSONObject gaodeObj = baiduMapService.baiduZbToGaodeZb(baiduZb);
		String[] gaodeZbs = gaodeObj.get("locations").toString().split(";");
		Object obj = baiduMapService.routeRecommendationGaode(gaodeZbs[0],gaodeZbs[1]);
		return R.ok(obj);
	}


	/**
	 * 路线推荐接口
	 * @param
	 * @return bjTywysbm   报警_通用唯一识别码
	 * @return xfjyjgTywysbm   消防救援机构通用唯一识别码
	 */
	@ApiOperation("路线推荐接口_高德_轿车")
	@GetMapping( "/lxtj_gaode_jc")
	@ResponseBody
	@ApiImplicitParams({
			@ApiImplicitParam(name = "jqjd", value = "警情经度", required = true, dataType = "String",dataTypeClass = String.class,paramType = "query"),
			@ApiImplicitParam(name = "jqwd", value = "警情纬度", required = true, dataType = "String",dataTypeClass = String.class,paramType = "query"),
			@ApiImplicitParam(name = "xfjyjgTywysbm", value = "消防救援机构通用唯一识别码", required = true, dataType = "String",dataTypeClass = String.class,paramType = "query")
	})
	@ApiResponses(value = {@ApiResponse(code = 200, message = "访问成功",response= HashMap.class)})
	public Map<String,Object> lxtj_gaode_jc (@RequestParam Map<String, Object> params){
		DeptDO deptDo = zzdyService.deptByXfjyjgTywysbm(params);

		String origins = deptDo.getDqjd()+","+deptDo.getDqwd();  //起点坐标
		String destinations = params.get("jqjd")+","+params.get("jqwd");  //终点坐标
		String baiduZb = origins+"|"+destinations;
		JSONObject gaodeObj = baiduMapService.baiduZbToGaodeZb(baiduZb);
		String[] gaodeZbs = gaodeObj.get("locations").toString().split(";");
		Object obj = baiduMapService.routeRecommendationGaodeJc(gaodeZbs[0],gaodeZbs[1]);
		return R.ok(obj);
	}

	@GetMapping("/jqfl")
	String jqfl(Model model,HttpServletRequest request){
		String jqflydms = request.getParameter("jqflydms");
		if(jqflydms!=null && !jqflydms.equals("")){
			String[] jqflydmsArr = jqflydms.split(",");
			List<String> jqflydmsList = Arrays.asList(jqflydmsArr);
			List<DictDO> dictDOList = dictService.getDictByTypeAndValues(jqflydmsList,"JQFLYDM");
			model.addAttribute("dictDOList",dictDOList);
		}else{
			List<DictDO> dictDOList = new ArrayList<>();
			model.addAttribute("dictDOList",dictDOList);
		}
		return "jczy/zzdy/jqfl";
	}

	@GetMapping("/getJqflList")
	@ResponseBody
	public List<DictDO> getJqflList(){
//		DictDO dictDO = new DictDO();
//		dictDO.setId(2286L);
//		dictDO.setName("警情分类与代码");
//		dictDO.setParentId(0L);
//		dictDO.setValue("");
		List<DictDO> result = new ArrayList<>();
//		result.add(dictDO);
		List<DictDO> list = dictService.queryByDictType("JQFLYDM");
		result.addAll(list);
		return result;
	}

}
