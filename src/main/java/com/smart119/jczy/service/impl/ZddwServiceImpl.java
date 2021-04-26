package com.smart119.jczy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.smart119.jczy.dao.ZddwDao;
import com.smart119.jczy.domain.ZddwDO;
import com.smart119.jczy.service.ZddwService;



@Service
public class ZddwServiceImpl implements ZddwService {
	@Autowired
	private ZddwDao zddwDao;
	
	@Override
	public ZddwDO get(String zddwTywysbm){
		return zddwDao.get(zddwTywysbm);
	}
	
	@Override
	public List<ZddwDO> list(Map<String, Object> map){
		return zddwDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return zddwDao.count(map);
	}
	
	@Override
	public int save(ZddwDO zddw){
		return zddwDao.save(zddw);
	}
	
	@Override
	public int update(ZddwDO zddw){
		return zddwDao.update(zddw);
	}
	
	@Override
	public int remove(String zddwTywysbm){
		return zddwDao.remove(zddwTywysbm);
	}
	
	@Override
	public int batchRemove(String[] zddwTywysbms){
		return zddwDao.batchRemove(zddwTywysbms);
	}

	@Override
	public ZddwDO zddwSb(Double jd, Double wd) {
		return zddwDao.zddwSb(jd,wd);
	}

}
