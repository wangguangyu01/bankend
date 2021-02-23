package com.smart119.webapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.smart119.webapi.dao.JqcjdpYaDao;
import com.smart119.webapi.domain.JqcjdpYaDO;
import com.smart119.webapi.service.JqcjdpYaService;



@Service
public class JqcjdpYaServiceImpl implements JqcjdpYaService {
	@Autowired
	private JqcjdpYaDao jqcjdpYaDao;
	
	@Override
	public JqcjdpYaDO get(String jqcjdpYaId){
		return jqcjdpYaDao.get(jqcjdpYaId);
	}
	
	@Override
	public List<JqcjdpYaDO> list(Map<String, Object> map){
		return jqcjdpYaDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return jqcjdpYaDao.count(map);
	}
	
	@Override
	public int save(JqcjdpYaDO jqcjdpYa){
		return jqcjdpYaDao.save(jqcjdpYa);
	}
	
	@Override
	public int update(JqcjdpYaDO jqcjdpYa){
		return jqcjdpYaDao.update(jqcjdpYa);
	}
	
	@Override
	public int remove(String jqcjdpYaId){
		return jqcjdpYaDao.remove(jqcjdpYaId);
	}
	
	@Override
	public int batchRemove(String[] jqcjdpYaIds){
		return jqcjdpYaDao.batchRemove(jqcjdpYaIds);
	}
	
}
