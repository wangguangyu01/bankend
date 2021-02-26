package com.smart119.jczy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.smart119.jczy.dao.ZxDao;
import com.smart119.jczy.domain.ZxDO;
import com.smart119.jczy.service.ZxService;



@Service
public class ZxServiceImpl implements ZxService {
	@Autowired
	private ZxDao zxDao;
	
	@Override
	public ZxDO get(String id){
		return zxDao.get(id);
	}
	
	@Override
	public List<ZxDO> list(Map<String, Object> map){
		return zxDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return zxDao.count(map);
	}
	
	@Override
	public int save(ZxDO zx){
		return zxDao.save(zx);
	}
	
	@Override
	public int update(ZxDO zx){
		return zxDao.update(zx);
	}
	
	@Override
	public int remove(String id){
		return zxDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return zxDao.batchRemove(ids);
	}
	
}
