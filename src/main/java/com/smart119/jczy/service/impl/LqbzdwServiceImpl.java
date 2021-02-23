package com.smart119.jczy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.smart119.jczy.dao.LqbzdwDao;
import com.smart119.jczy.domain.LqbzdwDO;
import com.smart119.jczy.service.LqbzdwService;



@Service
public class LqbzdwServiceImpl implements LqbzdwService {
	@Autowired
	private LqbzdwDao lqbzdwDao;
	
	@Override
	public LqbzdwDO get(String lqbzdwTywysbm){
		return lqbzdwDao.get(lqbzdwTywysbm);
	}
	
	@Override
	public List<LqbzdwDO> list(Map<String, Object> map){
		return lqbzdwDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return lqbzdwDao.count(map);
	}
	
	@Override
	public int save(LqbzdwDO lqbzdw){
		return lqbzdwDao.save(lqbzdw);
	}
	
	@Override
	public int update(LqbzdwDO lqbzdw){
		return lqbzdwDao.update(lqbzdw);
	}
	
	@Override
	public int remove(String lqbzdwTywysbm){
		return lqbzdwDao.remove(lqbzdwTywysbm);
	}
	
	@Override
	public int batchRemove(String[] lqbzdwTywysbms){
		return lqbzdwDao.batchRemove(lqbzdwTywysbms);
	}
	
}
