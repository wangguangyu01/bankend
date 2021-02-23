package com.smart119.webapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.smart119.webapi.dao.JqcjdpCarDao;
import com.smart119.webapi.domain.JqcjdpCarDO;
import com.smart119.webapi.service.JqcjdpCarService;



@Service
public class JqcjdpCarServiceImpl implements JqcjdpCarService {
	@Autowired
	private JqcjdpCarDao jqcjdpCarDao;
	
	@Override
	public JqcjdpCarDO get(String dpclTywysbm){
		return jqcjdpCarDao.get(dpclTywysbm);
	}
	
	@Override
	public List<JqcjdpCarDO> list(Map<String, Object> map){
		return jqcjdpCarDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return jqcjdpCarDao.count(map);
	}
	
	@Override
	public int save(JqcjdpCarDO jqcjdpCar){
		return jqcjdpCarDao.save(jqcjdpCar);
	}
	
	@Override
	public int update(JqcjdpCarDO jqcjdpCar){
		return jqcjdpCarDao.update(jqcjdpCar);
	}
	
	@Override
	public int remove(String dpclTywysbm){
		return jqcjdpCarDao.remove(dpclTywysbm);
	}
	
	@Override
	public int batchRemove(String[] dpclTywysbms){
		return jqcjdpCarDao.batchRemove(dpclTywysbms);
	}

	@Override
	public List<Map<String,Object>> dzclCount(String jqTywysbm) {
		return jqcjdpCarDao.dzclCount(jqTywysbm);
	}
}
