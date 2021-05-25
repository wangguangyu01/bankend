package com.smart119.jczy.controller;

import java.util.*;

import com.smart119.common.controller.BaseController;
import com.smart119.common.redis.shiro.RedisManager;
import com.smart119.common.redis.shiro.SerializeUtils;
import com.smart119.common.service.DictService;
import com.smart119.jczy.domain.XfclDO;
import com.smart119.jczy.domain.XfclSxzDO;
import com.smart119.jczy.service.XfclService;
import com.smart119.jczy.service.XfclSxzService;
import com.smart119.system.service.DeptService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smart119.jczy.domain.XfclSxDO;
import com.smart119.jczy.service.XfclSxService;
import com.smart119.common.utils.PageUtils;
import com.smart119.common.utils.Query;
import com.smart119.common.utils.R;

import javax.annotation.Resource;

/**
 * 消防车辆属性
 * 
 * @author thrz
 * @email thrz@sz000673.com
 * @date 2021-01-25 10:51:31
 */
 
@Controller
@RequestMapping("/jczy/xfclSx")
public class XfclSxController extends BaseController {
	@Autowired
	private XfclSxService xfclSxService;
	@Autowired
	private DictService dictService;

	@Autowired
	private XfclSxzService xfclSxzService;

	@Autowired
	private XfclService xfclService;

	@Resource
	private RedisManager redisManager;

	
	@GetMapping()
	//@RequiresPermissions("jczy:xfclSx:xfclSx")
	String XfclSx(){
	    return "jczy/xfclSx/xfclSx";
	}
	
	@ResponseBody
	@GetMapping("/list")
	//@RequiresPermissions("jczy:xfclSx:xfclSx")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<XfclSxDO> xfclSxList = xfclSxService.list(query);
		int total = xfclSxService.count(query);
		PageUtils pageUtils = new PageUtils(xfclSxList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	//@RequiresPermissions("jczy:xfclSx:add")
	String add(){
	    return "jczy/xfclSx/add";
	}

	@GetMapping("/edit/{id}")
	//@RequiresPermissions("jczy:xfclSx:edit")
	String edit(@PathVariable("id") String id,Model model){
		XfclSxDO xfclSx = xfclSxService.get(id);
		Map<String,Object> map = new HashMap<>();

		map.put("value",xfclSx.getCllx());
		map.put("type","XFZBLXDM");
		model.addAttribute("cllx_name",dictService.findDictType(map)); //消防装备类型

		map.put("value",xfclSx.getSfzs());
		map.put("type","s_f");
		model.addAttribute("sfzs_name",dictService.findDictType(map)); //是否展示

		map.put("value",xfclSx.getSfcyjs());
		map.put("type","s_f");
		model.addAttribute("sfcyjs_name",dictService.findDictType(map)); //是否参与计算

		model.addAttribute("xfclSx", xfclSx);
	    return "jczy/xfclSx/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	//@RequiresPermissions("jczy:xfclSx:add")
	public R save( XfclSxDO xfclSx){
		String id = UUID.randomUUID().toString().replace("-", "");
		xfclSx.setId(id);
		xfclSx.setCdate(new Date());
		xfclSx.setStatus(0);
		xfclSx.setCperson(getUserId()+"");
		if(xfclSxService.save(xfclSx)>0){
			List<String> xfclTywysbms = xfclSxService.findCltywysbmBylx(xfclSx.getCllx());
			for(String xfclTywysbm:xfclTywysbms){
				//xfcl.setXfclSxxx(xfclSxService.findSxAllByCltywysbm(id));
				Map<String,Object> xfclMap = xfclService.getMap(xfclTywysbm);
				xfclMap.put("CLSXXX",xfclSxService.findSxAllByCltywysbm(xfclTywysbm));
				Map<String,String> newMap = changeData(xfclMap);
				this.redisManager.hmset("sys:xfcl:"+xfclTywysbm, newMap);
			}
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	//@RequiresPermissions("jczy:xfclSx:edit")
	public R update( XfclSxDO xfclSx){
		if(xfclSxService.update(xfclSx)>0){
			List<String> xfclTywysbms = xfclSxService.findCltywysbmBylx(xfclSx.getCllx());
			for(String xfclTywysbm:xfclTywysbms){
				Map<String,Object> xfclMap = xfclService.getMap(xfclTywysbm);
				xfclMap.put("CLSXXX",xfclSxService.findSxAllByCltywysbm(xfclTywysbm));
				Map<String,String> newMap = changeData(xfclMap);
				this.redisManager.hmset("sys:xfcl:"+xfclTywysbm, newMap);
			}
			return R.ok();
		}else {
			return R.error();
		}

	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	//@RequiresPermissions("jczy:xfclSx:remove")
	public R remove( String id){
		xfclSxService.removeBySxId(id);  //在删除属性数据的同时 要删除所有此属性关联的属性值  之后所有车辆无此属性
		XfclSxDO xfclSx = xfclSxService.get(id);
		if(xfclSxService.remove(id)>0){
			List<String> xfclTywysbms = xfclSxService.findCltywysbmBylx(xfclSx.getCllx());
			for(String xfclTywysbm:xfclTywysbms){
				Map<String,Object> xfclMap = xfclService.getMap(xfclTywysbm);
				xfclMap.put("CLSXXX",xfclSxService.findSxAllByCltywysbm(xfclTywysbm));
				Map<String,String> newMap = changeData(xfclMap);
				this.redisManager.hmset("sys:xfcl:"+xfclTywysbm, newMap);
			}
			return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	//@RequiresPermissions("jczy:xfclSx:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		xfclSxService.batchRemove(ids);
		return R.ok();
	}


	@ResponseBody
	@GetMapping("/cllxAttr")
	//@RequiresPermissions("jczy:xfclSx:xfclSx")
	public List<XfclSxDO> cllxAttr(String cllx){
		List<XfclSxDO> xfclSxList = xfclSxService.findAttrByCllx(cllx);
		return xfclSxList;
	}

	@ResponseBody
	@GetMapping("/cllxAttrVal")
	//@RequiresPermissions("jczy:xfclSx:xfclSx")
	public List<Map<String,Object>> cllxAttrVal(@RequestParam Map<String, Object> params){

		List<Map<String,Object>> list = xfclSxService.findAttrByCllxVal(params.get("clId").toString());
		return list;
	}


	/**
	 * 保存车辆类型属性值
	 * @param clId 车辆id
	 * @param sxId 属性id（多个）
	 * @param valAll 值（多个）
	 * @return
	 */
	@ResponseBody
	@PostMapping("/saveCllxSXZ")
	//@RequiresPermissions("jczy:xfcl:add")
	public boolean save( String clId,String sxId,String valAll){
		xfclSxService.removeByXfclId(clId);
		boolean flag = true;
		String[] sxIds = sxId.split(",");
		String[] vals = valAll.split(",");
		for(int i=0;i<sxIds.length;i++){
			XfclSxzDO  XfclSxz = new XfclSxzDO();
			XfclSxz.setId(UUID.randomUUID().toString().replace("-", ""));
			XfclSxz.setXfclId(clId);
			XfclSxz.setXfclSxId(sxIds[i]);
			if(vals.length>i){
				XfclSxz.setSxz(vals[i]);
			}else{
				XfclSxz.setSxz("");
			}
			XfclSxz.setClsx(xfclSxService.get(sxIds[i]).getClsx());
			if(xfclSxzService.save(XfclSxz)<=0){
				flag = false;
			}
		}
		if(flag){
			Map<String,Object> xfclMap = xfclService.getMap(clId);
			xfclMap.put("CLSXXX",xfclSxService.findSxAllByCltywysbm(clId));
			Map<String,String> newMap = changeData(xfclMap);
			this.redisManager.hmset("sys:xfcl:"+clId, newMap);
		}
		return flag;
	}



	/**
	 * 保存车辆类型属性值
	 * @return
	 */
	@ResponseBody
	@PostMapping("/webSaveCllxSXZ")
	public boolean webSaveCllxSXZ( @RequestBody Map<String,String> params ){
		String clId = params.get("clId");
		String sxId =  params.get("sxId");
		String valAll =  params.get("valAll");
		xfclSxService.removeByXfclId(clId);
		boolean flag = true;
		String[] sxIds = sxId.split(",");
		String[] vals = valAll.split(",");
		for(int i=0;i<sxIds.length;i++){
			XfclSxzDO  XfclSxz = new XfclSxzDO();
			XfclSxz.setId(UUID.randomUUID().toString().replace("-", ""));
			XfclSxz.setXfclId(clId);
			XfclSxz.setXfclSxId(sxIds[i]);
			if(vals.length>i){
				XfclSxz.setSxz(vals[i]);
			}else{
				XfclSxz.setSxz("");
			}
			XfclSxz.setClsx(xfclSxService.get(sxIds[i]).getClsx());
			if(xfclSxzService.save(XfclSxz)<=0){
				flag = false;
			}
		}
		if(flag){
			Map<String,Object> xfclMap = xfclService.getMap(clId);
			xfclMap.put("CLSXXX",xfclSxService.findSxAllByCltywysbm(clId));
			Map<String,String> newMap = changeData(xfclMap);
			this.redisManager.hmset("sys:xfcl:"+clId, newMap);
		}
		return flag;
	}



	public Map<String,String> changeData(Map<String,Object> map){
		Map<String,String> newMap =new HashMap<String,String>();
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			if(null!=entry.getValue() && !"".equals(entry.getValue())){
				newMap.put(entry.getKey(), entry.getValue().toString());
			}else{
				newMap.put(entry.getKey(), "");
			}

		}
		return newMap;
	}


	
}
