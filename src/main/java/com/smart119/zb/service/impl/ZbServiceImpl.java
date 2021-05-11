package com.smart119.zb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.smart119.zb.dao.ZbDao;
import com.smart119.zb.domain.ZbDO;
import com.smart119.zb.service.ZbService;



@Service
public class ZbServiceImpl implements ZbService {
	@Autowired
	private ZbDao zbDao;
	
	@Override
	public ZbDO get(String zbId){
		return zbDao.get(zbId);
	}
	
	@Override
	public List<ZbDO> list(Map<String, Object> map){
		return zbDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return zbDao.count(map);
	}
	
	@Override
	public int save(ZbDO zb){
		return zbDao.save(zb);
	}
	
	@Override
	public int update(ZbDO zb){
		return zbDao.update(zb);
	}
	
	@Override
	public int remove(String zbId){
		return zbDao.remove(zbId);
	}
	
	@Override
	public int batchRemove(String rq,String xfjyjgTywysbm){
		return zbDao.batchRemove(rq,xfjyjgTywysbm);
	}
	
}
