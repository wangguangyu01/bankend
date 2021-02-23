package com.smart119.jczy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.smart119.jczy.dao.SpjkDao;
import com.smart119.jczy.domain.SpjkDO;
import com.smart119.jczy.service.SpjkService;



@Service
public class SpjkServiceImpl implements SpjkService {
	@Autowired
	private SpjkDao spjkDao;
	
	@Override
	public SpjkDO get(String spjkTywysbm){
		return spjkDao.get(spjkTywysbm);
	}
	
	@Override
	public List<SpjkDO> list(Map<String, Object> map){
		return spjkDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return spjkDao.count(map);
	}
	
	@Override
	public int save(SpjkDO spjk){
		return spjkDao.save(spjk);
	}
	
	@Override
	public int update(SpjkDO spjk){
		return spjkDao.update(spjk);
	}
	
	@Override
	public int remove(String spjkTywysbm){
		return spjkDao.remove(spjkTywysbm);
	}
	
	@Override
	public int batchRemove(String[] spjkTywysbms){
		return spjkDao.batchRemove(spjkTywysbms);
	}
	
}
