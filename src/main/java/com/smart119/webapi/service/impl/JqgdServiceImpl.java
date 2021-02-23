package com.smart119.webapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.smart119.webapi.dao.JqgdDao;
import com.smart119.webapi.domain.JqgdDO;
import com.smart119.webapi.service.JqgdService;



@Service
public class JqgdServiceImpl implements JqgdService {
	@Autowired
	private JqgdDao jqgdDao;
	
	@Override
	public JqgdDO get(String jqgdjlTywysbm){
		return jqgdDao.get(jqgdjlTywysbm);
	}
	
	@Override
	public List<JqgdDO> list(Map<String, Object> map){
		return jqgdDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return jqgdDao.count(map);
	}
	
	@Override
	public int save(JqgdDO jqgd){
		return jqgdDao.save(jqgd);
	}
	
	@Override
	public int update(JqgdDO jqgd){
		return jqgdDao.update(jqgd);
	}
	
	@Override
	public int remove(String jqgdjlTywysbm){
		return jqgdDao.remove(jqgdjlTywysbm);
	}
	
	@Override
	public int batchRemove(String[] jqgdjlTywysbms){
		return jqgdDao.batchRemove(jqgdjlTywysbms);
	}
	
}
