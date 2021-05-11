package com.smart119.zb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.smart119.zb.dao.ZbzwDao;
import com.smart119.zb.domain.ZbzwDO;
import com.smart119.zb.service.ZbzwService;



@Service
public class ZbzwServiceImpl implements ZbzwService {
	@Autowired
	private ZbzwDao zbzwDao;
	
	@Override
	public ZbzwDO get(String zbzwId){
		return zbzwDao.get(zbzwId);
	}
	
	@Override
	public List<ZbzwDO> list(Map<String, Object> map){
		return zbzwDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return zbzwDao.count(map);
	}
	
	@Override
	public int save(ZbzwDO zbzw){
		return zbzwDao.save(zbzw);
	}
	
	@Override
	public int update(ZbzwDO zbzw){
		return zbzwDao.update(zbzw);
	}
	
	@Override
	public int remove(String zbzwId){
		return zbzwDao.remove(zbzwId);
	}
	
	@Override
	public int batchRemove(String[] zbzwIds){
		return zbzwDao.batchRemove(zbzwIds);
	}
	
}
