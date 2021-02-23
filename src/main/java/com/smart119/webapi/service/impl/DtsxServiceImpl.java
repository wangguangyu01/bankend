package com.smart119.webapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.smart119.webapi.dao.DtsxDao;
import com.smart119.webapi.domain.DtsxDO;
import com.smart119.webapi.service.DtsxService;



@Service
public class DtsxServiceImpl implements DtsxService {
	@Autowired
	private DtsxDao dtsxDao;
	
	@Override
	public DtsxDO get(String dtsxTywysbm){
		return dtsxDao.get(dtsxTywysbm);
	}
	
	@Override
	public List<DtsxDO> list(Map<String, Object> map){
		return dtsxDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return dtsxDao.count(map);
	}
	
	@Override
	public int save(DtsxDO dtsx){
		return dtsxDao.save(dtsx);
	}
	
	@Override
	public int update(DtsxDO dtsx){
		return dtsxDao.update(dtsx);
	}
	
	@Override
	public int remove(String dtsxTywysbm){
		return dtsxDao.remove(dtsxTywysbm);
	}
	
	@Override
	public int batchRemove(String[] dtsxTywysbms){
		return dtsxDao.batchRemove(dtsxTywysbms);
	}
	
}
