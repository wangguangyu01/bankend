package com.smart119.jczy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.smart119.jczy.dao.ShlddwDao;
import com.smart119.jczy.domain.ShlddwDO;
import com.smart119.jczy.service.ShlddwService;



@Service
public class ShlddwServiceImpl implements ShlddwService {
	@Autowired
	private ShlddwDao shlddwDao;
	
	@Override
	public ShlddwDO get(String shlddwTywysbm){
		return shlddwDao.get(shlddwTywysbm);
	}
	
	@Override
	public List<ShlddwDO> list(Map<String, Object> map){
		return shlddwDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return shlddwDao.count(map);
	}
	
	@Override
	public int save(ShlddwDO shlddw){
		return shlddwDao.save(shlddw);
	}
	
	@Override
	public int update(ShlddwDO shlddw){
		return shlddwDao.update(shlddw);
	}
	
	@Override
	public int remove(String shlddwTywysbm){
		return shlddwDao.remove(shlddwTywysbm);
	}
	
	@Override
	public int batchRemove(String[] shlddwTywysbms){
		return shlddwDao.batchRemove(shlddwTywysbms);
	}
	
}
