package com.smart119.jczy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.smart119.jczy.dao.JczyjjyyDao;
import com.smart119.jczy.domain.JjyyDO;
import com.smart119.jczy.service.JczyjjyyService;



@Service
public class JczyjjyyServiceImpl implements JczyjjyyService {
	@Autowired
	private JczyjjyyDao jjyyDao;
	
	@Override
	public JjyyDO get(String id){
		return jjyyDao.get(id);
	}
	
	@Override
	public List<JjyyDO> list(Map<String, Object> map){
		return jjyyDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return jjyyDao.count(map);
	}
	
	@Override
	public int save(JjyyDO jjyy){
		return jjyyDao.save(jjyy);
	}
	
	@Override
	public int update(JjyyDO jjyy){
		return jjyyDao.update(jjyy);
	}
	
	@Override
	public int remove(String id){
		return jjyyDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return jjyyDao.batchRemove(ids);
	}
	
}
