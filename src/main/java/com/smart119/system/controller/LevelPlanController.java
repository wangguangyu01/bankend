package com.smart119.system.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.smart119.common.controller.BaseController;
import com.smart119.common.domain.DictDO;
import com.smart119.common.service.DictService;
import com.smart119.common.utils.PageUtils;
import com.smart119.system.dao.PolicestaionTypeLevelDao;
import com.smart119.system.domain.LevelPlanDO;
import com.smart119.system.domain.PolicestaionTypeLevelDO;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.smart119.system.service.LevelPlanService;
import com.smart119.common.utils.Query;
import com.smart119.common.utils.R;

/**
 * 
 * 
 * @author zhangshunhua
 * @email zhangshunhua@sz000673.com
 * @date 2021-07-21 10:59:21
 */
@Api(tags = "管理")
@Controller
@RequestMapping("/system/levelPlan")
@Slf4j
public class LevelPlanController extends BaseController{


	@Autowired
	private LevelPlanService levelPlanService;

	@Autowired
	private PolicestaionTypeLevelDao policestaionTypeLevelDao;

	@Autowired
	private DictService dictService;

	@GetMapping()
	@RequiresPermissions("system:levelPlan:levelPlan")
	String LevelPlan(Model model){
		return "jczy/levelPlan/levelPlan";
	}




	@ApiOperation(value = "分页列表查询")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "limit", value = "条数", required = true, paramType = "body"),
			@ApiImplicitParam(name = "offset", value = "页数", required = true,paramType = "body"),
			@ApiImplicitParam(name = "params", value = "json格式的查询参数",  paramType = "body")
	})
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("system:levelPlan:levelPlan")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
		PageUtils page = levelPlanService.queryPage(params);
		return page;
	}


	@GetMapping("/add")
	@RequiresPermissions("system:levelPlan:add")
	public String add(){
		return "jczy/levelPlan/add";
	}



	@ApiOperation(value = "查询详情")
	@ApiParam(name = "levelPlanId", value = "主键id", required = true)
	@GetMapping("/edit/{levelPlanId}")
	@RequiresPermissions("system:levelPlan:edit")
	public String edit(@PathVariable("levelPlanId") String levelPlanId,Model model){
		LevelPlanDO levelPlan = levelPlanService.queryById(levelPlanId);
		QueryWrapper<PolicestaionTypeLevelDO> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("level_plan_id",levelPlan.getLevelPlanId());
		List<PolicestaionTypeLevelDO> policestaionTypeLevelDOS = policestaionTypeLevelDao.selectList(queryWrapper);
		if(policestaionTypeLevelDOS.size() > 0){
			Map ma= new HashMap();
			ma.put("type","JQFLYDM");
			ma.put("value",policestaionTypeLevelDOS.get(0).getPOLICESTAIONTYPETYWYSBM());
			List<DictDO> dictByTypeVal = dictService.findDictByTypeVal(ma);
			levelPlan.setPlanTypeName(dictByTypeVal.get(0).getName());
			levelPlan.setPlanType(policestaionTypeLevelDOS.get(0).getPOLICESTAIONTYPETYWYSBM());
			levelPlan.setPlanLevel(policestaionTypeLevelDOS.get(0).getPOLICESTAIONLEVELTYWYSBM());
		}
		for(PolicestaionTypeLevelDO aaa:policestaionTypeLevelDOS){
			Map ma= new HashMap();
			ma.put("type","XFZBLXDM");
			ma.put("value",aaa.getXFCLTYWYSBM());
			List<DictDO> dictByTypeVal = dictService.findDictByTypeVal(ma);
			aaa.setXFCLTYWYSBMNAME(dictByTypeVal.get(0).getName());
		}
		levelPlan.setPoliceTypeLevelList(policestaionTypeLevelDOS);
		model.addAttribute("levelPlan",levelPlan);
		return "jczy/levelPlan/edit";
	}
	
	/**
	 * 保存
	 */
	@ApiOperation(value = "保存信息")
	@ApiParam(name = "LevelPlan对象", value = "传入LevelPlan对象的json格式", required = true)
	@PostMapping("/save")
	@ResponseBody
	@RequiresPermissions("system:levelPlan:add")
	public R save(@RequestBody Map params){
		LevelPlanDO levelPlanDO = new LevelPlanDO();
		try {
			BeanUtils.populate(levelPlanDO,params);
			List<LinkedHashMap> policeTypeLevel = (List<LinkedHashMap>)params.get("policeTypeLevelList");
			List<PolicestaionTypeLevelDO> policestaionTypeLevelDOS = new ArrayList<>();
			for(LinkedHashMap linkedHashMap:policeTypeLevel){
				PolicestaionTypeLevelDO policestaionTypeLevelDO =new PolicestaionTypeLevelDO();
				policestaionTypeLevelDO.setPOLICESTAIONLEVELTYPETYWYSBM(UUID.randomUUID().toString().replace("-",""));
				policestaionTypeLevelDO.setPOLICESTAIONTYPETYWYSBM(linkedHashMap.get("pOLICESTAIONTYPETYWYSBM").toString());
				policestaionTypeLevelDO.setPOLICESTAIONLEVELTYWYSBM(linkedHashMap.get("pOLICESTAIONLEVELTYWYSBM").toString());
				policestaionTypeLevelDO.setXFCLTYWYSBM(linkedHashMap.get("xFCLTYWYSBM").toString());
				policestaionTypeLevelDO.setXFCLNUM(linkedHashMap.get("xFCLNUM").toString());
				policestaionTypeLevelDO.setCdate(new Date());
				policestaionTypeLevelDO.setCperson(this.getUser().getUserId()+"");
				policestaionTypeLevelDO.setLevelPlanId(levelPlanDO.getLevelPlanId());
				policestaionTypeLevelDOS.add(policestaionTypeLevelDO);
			}
			levelPlanDO.setPoliceTypeLevelList(policestaionTypeLevelDOS);
			int flag = -1;
			if(levelPlanDO.getLevelPlanId()==null){
				levelPlanDO.setLevelPlanId(UUID.randomUUID().toString().replace("-",""));
				levelPlanDO.setCdate(new Date());
				levelPlanDO.setStatus("1");
				levelPlanDO.setCperson(this.getUser().getUserId()+"");
				flag =levelPlanService.save(levelPlanDO);
			}else{
				flag =levelPlanService.update(levelPlanDO);
			}
			if(flag> 0){
				QueryWrapper<PolicestaionTypeLevelDO> queryWrapper = new QueryWrapper<>();
				queryWrapper.eq("level_plan_id",levelPlanDO.getLevelPlanId());
				 policestaionTypeLevelDao.delete(queryWrapper);
				List<PolicestaionTypeLevelDO> policeTypeLevelList = levelPlanDO.getPoliceTypeLevelList();
				for(PolicestaionTypeLevelDO policestaionTypeLevelDO:policeTypeLevelList){
					policestaionTypeLevelDO.setLevelPlanId(levelPlanDO.getLevelPlanId());
					policestaionTypeLevelDao.insert(policestaionTypeLevelDO);
				}
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return R.ok();
	}
	/**
	 * 修改
	 */
	@ApiOperation(value = "修改信息")
	@ApiParam(name = "LevelPlan对象", value = "传入LevelPlan对象的json格式", required = true)
	@PostMapping("/update")
	@ResponseBody
	@RequiresPermissions("system:levelPlan:edit")
	public R update(@RequestBody LevelPlanDO levelPlan){
		levelPlanService.update(levelPlan);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ApiOperation(value = "删除信息")
	@ApiParam(name = "levelPlanId", value = "传入主键", required = true)
	@GetMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("system:levelPlan:remove")
	public R remove(String levelPlanId){
		if(levelPlanService.remove(levelPlanId)>0){
			QueryWrapper<PolicestaionTypeLevelDO> queryWrapper = new QueryWrapper<>();
			queryWrapper.eq("level_plan_id",levelPlanId);
			policestaionTypeLevelDao.delete(queryWrapper);
			return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@ApiOperation(value = "批量删除信息")
	@ApiParam(name = "levelPlanIds", value = "传入主键数组", required = true)
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("system:levelPlan:batchRemove")
	public R remove(@RequestBody String[] levelPlanIds){
		levelPlanService.batchRemove(levelPlanIds);
		return R.ok();
	}
	
}
