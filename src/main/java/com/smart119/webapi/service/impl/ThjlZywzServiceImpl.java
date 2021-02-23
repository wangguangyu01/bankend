package com.smart119.webapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.smart119.webapi.dao.ThjlZywzDao;
import com.smart119.webapi.domain.ThjlZywzDO;
import com.smart119.webapi.service.ThjlZywzService;



@Service
public class ThjlZywzServiceImpl implements ThjlZywzService {
	@Autowired
	private ThjlZywzDao thjlZywzDao;
	
	@Override
	public ThjlZywzDO get(String id){
		return thjlZywzDao.get(id);
	}
	
	@Override
	public List<ThjlZywzDO> list(Map<String, Object> map){
		return thjlZywzDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return thjlZywzDao.count(map);
	}
	
	@Override
	public int save(ThjlZywzDO thjlZywz){
		return thjlZywzDao.save(thjlZywz);
	}
	
	@Override
	public int update(ThjlZywzDO thjlZywz){
		return thjlZywzDao.update(thjlZywz);
	}
	
	@Override
	public int remove(String id){
		return thjlZywzDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return thjlZywzDao.batchRemove(ids);
	}
	
}
