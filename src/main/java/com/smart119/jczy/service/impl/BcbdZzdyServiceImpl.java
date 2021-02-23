package com.smart119.jczy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.smart119.jczy.dao.BcbdZzdyDao;
import com.smart119.jczy.domain.BcbdZzdyDO;
import com.smart119.jczy.service.BcbdZzdyService;



@Service
public class BcbdZzdyServiceImpl implements BcbdZzdyService {
	@Autowired
	private BcbdZzdyDao bcbdZzdyDao;
	
	@Override
	public BcbdZzdyDO get(String bcbdZzdyId){
		return bcbdZzdyDao.get(bcbdZzdyId);
	}
	
	@Override
	public List<BcbdZzdyDO> list(Map<String, Object> map){
		return bcbdZzdyDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return bcbdZzdyDao.count(map);
	}
	
	@Override
	public int save(BcbdZzdyDO bcbdZzdy){
		return bcbdZzdyDao.save(bcbdZzdy);
	}
	
	@Override
	public int update(BcbdZzdyDO bcbdZzdy){
		return bcbdZzdyDao.update(bcbdZzdy);
	}
	
	@Override
	public int remove(String bcbdZzdyId){
		return bcbdZzdyDao.remove(bcbdZzdyId);
	}

	@Override
	public int batchRemove(String[] bcbdZzdyIds){
		return bcbdZzdyDao.batchRemove(bcbdZzdyIds);
	}
	
}
