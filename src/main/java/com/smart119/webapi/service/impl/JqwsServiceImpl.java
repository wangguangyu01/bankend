package com.smart119.webapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.smart119.webapi.dao.JqwsDao;
import com.smart119.webapi.domain.JqwsDO;
import com.smart119.webapi.service.JqwsService;



@Service
public class JqwsServiceImpl implements JqwsService {
	@Autowired
	private JqwsDao jqwsDao;
	
	@Override
	public JqwsDO get(String jqhcwsTywysbm){
		return jqwsDao.get(jqhcwsTywysbm);
	}
	
	@Override
	public List<JqwsDO> list(Map<String, Object> map){
		return jqwsDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return jqwsDao.count(map);
	}
	
	@Override
	public int save(JqwsDO jqws){
		return jqwsDao.save(jqws);
	}
	
	@Override
	public int update(JqwsDO jqws){
		return jqwsDao.update(jqws);
	}
	
	@Override
	public int remove(String jqhcwsTywysbm){
		return jqwsDao.remove(jqhcwsTywysbm);
	}
	
	@Override
	public int batchRemove(String[] jqhcwsTywysbms){
		return jqwsDao.batchRemove(jqhcwsTywysbms);
	}
	
}
