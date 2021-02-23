package com.smart119.webapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.smart119.webapi.dao.JqcjdpZzdyDao;
import com.smart119.webapi.domain.JqcjdpZzdyDO;
import com.smart119.webapi.service.JqcjdpZzdyService;



@Service
public class JqcjdpZzdyServiceImpl implements JqcjdpZzdyService {
	@Autowired
	private JqcjdpZzdyDao jqcjdpZzdyDao;
	
	@Override
	public JqcjdpZzdyDO get(String jqcjdpZzdyId){
		return jqcjdpZzdyDao.get(jqcjdpZzdyId);
	}
	
	@Override
	public List<JqcjdpZzdyDO> list(Map<String, Object> map){
		return jqcjdpZzdyDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return jqcjdpZzdyDao.count(map);
	}
	
	@Override
	public int save(JqcjdpZzdyDO jqcjdpZzdy){
		return jqcjdpZzdyDao.save(jqcjdpZzdy);
	}
	
	@Override
	public int update(JqcjdpZzdyDO jqcjdpZzdy){
		return jqcjdpZzdyDao.update(jqcjdpZzdy);
	}
	
	@Override
	public int remove(String jqcjdpZzdyId){
		return jqcjdpZzdyDao.remove(jqcjdpZzdyId);
	}
	
	@Override
	public int batchRemove(String[] jqcjdpZzdyIds){
		return jqcjdpZzdyDao.batchRemove(jqcjdpZzdyIds);
	}
	
}
