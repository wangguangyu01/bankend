package com.smart119.webapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.smart119.webapi.dao.ThjlTqysDao;
import com.smart119.webapi.domain.ThjlTqysDO;
import com.smart119.webapi.service.ThjlTqysService;



@Service
public class ThjlTqysServiceImpl implements ThjlTqysService {
	@Autowired
	private ThjlTqysDao thjlTqysDao;
	
	@Override
	public ThjlTqysDO get(String id){
		return thjlTqysDao.get(id);
	}
	
	@Override
	public List<ThjlTqysDO> list(Map<String, Object> map){
		return thjlTqysDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return thjlTqysDao.count(map);
	}
	
	@Override
	public int save(ThjlTqysDO thjlTqys){
		return thjlTqysDao.save(thjlTqys);
	}
	
	@Override
	public int update(ThjlTqysDO thjlTqys){
		return thjlTqysDao.update(thjlTqys);
	}
	
	@Override
	public int remove(String id){
		return thjlTqysDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return thjlTqysDao.batchRemove(ids);
	}
	
}
