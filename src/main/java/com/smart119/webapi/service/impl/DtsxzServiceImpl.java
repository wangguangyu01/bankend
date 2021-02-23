package com.smart119.webapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.smart119.webapi.dao.DtsxzDao;
import com.smart119.webapi.domain.DtsxzDO;
import com.smart119.webapi.service.DtsxzService;



@Service
public class DtsxzServiceImpl implements DtsxzService {
	@Autowired
	private DtsxzDao dtsxzDao;
	
	@Override
	public DtsxzDO get(String dtsxzTywysbm){
		return dtsxzDao.get(dtsxzTywysbm);
	}
	
	@Override
	public List<DtsxzDO> list(Map<String, Object> map){
		return dtsxzDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return dtsxzDao.count(map);
	}
	
	@Override
	public int save(DtsxzDO dtsxz){
		return dtsxzDao.save(dtsxz);
	}
	
	@Override
	public int update(DtsxzDO dtsxz){
		return dtsxzDao.update(dtsxz);
	}
	
	@Override
	public int remove(String dtsxzTywysbm){
		return dtsxzDao.remove(dtsxzTywysbm);
	}
	
	@Override
	public int batchRemove(String[] dtsxzTywysbms){
		return dtsxzDao.batchRemove(dtsxzTywysbms);
	}

	@Override
	public List<DtsxzDO> getJqDtsxByJqTywysbmAndZhcsId(String zhcsId, String jqTywysbm) {
		return dtsxzDao.getJqDtsxByJqTywysbmAndZhcsId(zhcsId,jqTywysbm);
	}

}
