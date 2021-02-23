package com.smart119.jczy.service.impl;

import com.smart119.jczy.dao.WxxfzDao;
import com.smart119.jczy.domain.WxxfzDO;
import com.smart119.jczy.service.WxxfzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;



@Service
public class WxxfzServiceImpl implements WxxfzService {
	@Autowired
	private WxxfzDao wxxfzDao;

	@Override
	public WxxfzDO get(String wxxfzTywysbm){
		return wxxfzDao.get(wxxfzTywysbm);
	}
	
	@Override
	public List<WxxfzDO> list(Map<String, Object> map){
		return wxxfzDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return wxxfzDao.count(map);
	}
	
	@Override
	public int save(WxxfzDO wxxfz){
		return wxxfzDao.save(wxxfz);
	}
	
	@Override
	public int update(WxxfzDO wxxfz){
		return wxxfzDao.update(wxxfz);
	}
	
	@Override
	public int remove(String wxxfzTywysbm){
		return wxxfzDao.remove(wxxfzTywysbm);
	}
	
	@Override
	public int batchRemove(String[] wxxfzTywysbms){
		return wxxfzDao.batchRemove(wxxfzTywysbms);
	}

	@Override
	public List<WxxfzDO> getWxxfzByRange(Double jd, Double wd, Double distance) {
		return wxxfzDao.getWxxfzByRange(jd,wd,distance);
	}

	@Override
	public List<Map<String, Object>> qyldList(Map<String, Object> map) {
		List<Map<String,Object>> retList = new ArrayList<>();
		List<Map<String, Object>> xzqhList = wxxfzDao.wxxfzGroupXzqh(map);
		for(Map<String, Object> xzqhMap:xzqhList){
			map.put("xzqhdm",xzqhMap.get("xzqhdm"));
			List<Map<String,Object>> wxxfzList = wxxfzDao.wxxfzByXzqh(map);
			xzqhMap.put("wxxfz",wxxfzList);
			retList.add(xzqhMap);
		}
		return retList;
	}

}
