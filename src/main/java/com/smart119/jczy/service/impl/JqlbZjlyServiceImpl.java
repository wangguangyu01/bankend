package com.smart119.jczy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.smart119.jczy.dao.JqlbZjlyDao;
import com.smart119.jczy.domain.JqlbZjlyDO;
import com.smart119.jczy.service.JqlbZjlyService;



@Service
public class JqlbZjlyServiceImpl implements JqlbZjlyService {
	@Autowired
	private JqlbZjlyDao jqlbZjlyDao;
	
	@Override
	public JqlbZjlyDO get(String id){
		return jqlbZjlyDao.get(id);
	}
	
	@Override
	public List<JqlbZjlyDO> list(Map<String, Object> map){
		return jqlbZjlyDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return jqlbZjlyDao.count(map);
	}
	
	@Override
	public int save(JqlbZjlyDO jqlbZjly){
		return jqlbZjlyDao.save(jqlbZjly);
	}
	
	@Override
	public int update(JqlbZjlyDO jqlbZjly){
		return jqlbZjlyDao.update(jqlbZjly);
	}
	
	@Override
	public int remove(String id){
		return jqlbZjlyDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return jqlbZjlyDao.batchRemove(ids);
	}

	@Override
	public List<Map<String, Object>> getZjlyType() {
		return jqlbZjlyDao.getZjlyType();
	}

}
