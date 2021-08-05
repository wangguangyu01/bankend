package com.smart119.jczy.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.smart119.common.domain.Distance;
import com.smart119.common.service.BaiduMapService;
import com.smart119.jczy.dao.ZzdyDao;
import com.smart119.jczy.domain.ZzdyDO;
import com.smart119.jczy.domain.ZzdyXfclDO;
import com.smart119.jczy.service.ZzdyService;
import com.smart119.jczy.service.ZzdyXfclService;
import com.smart119.system.domain.DeptDO;
import com.smart119.webapi.dao.JbxxDao;
import com.smart119.webapi.domain.JbxxDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.smart119.common.utils.ShiroUtils.getUserId;


@Service
public class ZzdyServiceImpl implements ZzdyService {
	@Autowired
	private ZzdyDao zzdyDao;

	@Autowired
	private JbxxDao jbxxDao;

	@Autowired
	private BaiduMapService baiduMapService;
	@Autowired
	private ZzdyXfclService zzdyXfclService;
	@Override
	public ZzdyDO get(String zzdyTywybs){
		return zzdyDao.get(zzdyTywybs);
	}
	
	@Override
	public List<ZzdyDO> list(Map<String, Object> map){
		return zzdyDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return zzdyDao.count(map);
	}
	
	@Override
	public int save(ZzdyDO zzdy){
		int res=0;
		String id=UUID.randomUUID().toString().replace("-", "");
		zzdy.setZzdyTywybs(id);
		zzdy.setCdate(new Date());
		zzdy.setStatus("0200");
		zzdy.setCperson(getUserId()+"");
		if(zzdyDao.save(zzdy)>0){
			String [] xfccl=zzdy.getXfclTywysbm().split(",");
			if(xfccl !=null && xfccl.length>0 ){
				for (int i = 0; i < xfccl.length; i++) {
					res=0;
					ZzdyXfclDO dao=new ZzdyXfclDO();
					dao.setId(UUID.randomUUID().toString().replace("-", ""));
					dao.setXfclTywysbm(xfccl[i]);
					dao.setZzdyTywybs(id);
					if(zzdyXfclService.save(dao)>0){
						res=1;
					}
				}
			}
		}
		return res;
	}
	
	@Override
	public int update(ZzdyDO zzdy){
		return zzdyDao.update(zzdy);
	}
	
	@Override
	public int remove(String zzdyTywybs){
		return zzdyDao.remove(zzdyTywybs);
	}
	
	@Override
	public int batchRemove(String[] zzdyTywybss){
		return zzdyDao.batchRemove(zzdyTywybss);
	}

	@Override
	public List<DeptDO> zzdyList(Map<String, Object> map) {
		String zdzb = "";
		if(map.get("jqTywysbm")!=null && !"".equals(map.get("jqTywysbm"))){
			JbxxDO jbxx = jbxxDao.get(map.get("jqTywysbm").toString());
			if(jbxx.getDqwd()!=null && !"".equals(jbxx.getDqwd()) && jbxx.getDqjd()!=null && !"".equals(jbxx.getDqjd()) ){
				zdzb = jbxx.getDqwd()+","+jbxx.getDqjd();
			}

		}else{
			zdzb = map.get("wd").toString()+","+map.get("jd").toString();
		}
		List<DeptDO> retDeptList = new ArrayList<>();
		List<DeptDO> deptList = zzdyDao.deptList(map);
		for(DeptDO dept:deptList){
			String qszb = dept.getDqwd()+","+dept.getDqjd();
			if(!"".equals(zdzb)){
				JSONArray jsonArray = baiduMapService.getDistanceAndDuration(qszb,zdzb);
				if(jsonArray!=null){
					dept.setDistance(JSON.toJavaObject(jsonArray.getJSONObject(0).getJSONObject("distance"), Distance.class));
				}
			}
			Map<String,Object> map1 = new HashMap<>();
			map1.put("jqTywysbm",map.get("jqTywysbm")!=null?map.get("jqTywysbm"):"");
			map1.put("xfjyjgTywysbm",dept.getXfjyjgTywysbm());
			List<Map<String, Object>> retZzdyList = new ArrayList<>();
			List<Map<String, Object>> zzdyList = zzdyDao.zzdyListByJg(map1);
			for(Map<String, Object> zzdyMap:zzdyList){
				List<Map<String, Object>> xfclList = zzdyDao.xfclListByZzdy(zzdyMap.get("zzdyTywyds").toString());
				String xfclName = "";
				for(Map<String, Object> xfclMap:xfclList){
					if("".equals(xfclName)){
						xfclName += xfclMap.get("xfzblx");
					}else{
						xfclName += " + "+xfclMap.get("xfzblx");
					}
				}
				zzdyMap.put("xfcl",xfclList);
				zzdyMap.put("xfclName",xfclName);
				retZzdyList.add(zzdyMap);
			}
			dept.setZzdyList(retZzdyList);

			retDeptList.add(dept);

		}
		if(!"".equals(zdzb)){
			retDeptList = retDeptList.stream().sorted(Comparator.comparing(o->o.getDistance().getValue())).collect(Collectors.toList());
		}
		return retDeptList;
	}

	@Override
	public DeptDO deptByXfjyjgTywysbm(Map<String, Object> params) {
		DeptDO deptDO = zzdyDao.deptByXfjyjgTywysbm(params.get("xfjyjgTywysbm").toString());
		return deptDO;
	}




}
