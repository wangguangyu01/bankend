package com.smart119.jczy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.smart119.jczy.dao.BrqyDao;
import com.smart119.jczy.domain.BrqyDO;
import com.smart119.jczy.service.BrqyService;



@Service
public class BrqyServiceImpl implements BrqyService {
	@Autowired
	private BrqyDao brqyDao;
	
	@Override
	public BrqyDO get(String id){
		return brqyDao.get(id);
	}
	
	@Override
	public List<BrqyDO> list(Map<String, Object> map){
		return brqyDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return brqyDao.count(map);
	}
	
	@Override
	public int save(BrqyDO brqy){
		return brqyDao.save(brqy);
	}
	
	@Override
	public int update(BrqyDO brqy){
		return brqyDao.update(brqy);
	}
	
	@Override
	public int remove(String id){
		return brqyDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return brqyDao.batchRemove(ids);
	}

	@Override
	public int openStatus(String id) {
		BrqyDO brqy = new BrqyDO();
		brqy.setId(id);
		brqy.setStatus("0");
		return brqyDao.update(brqy);
	}

	@Override
	public int closeStatus(String id) {
		BrqyDO brqy = new BrqyDO();
		brqy.setId(id);
		brqy.setStatus("1");
		return brqyDao.update(brqy);
	}

}
