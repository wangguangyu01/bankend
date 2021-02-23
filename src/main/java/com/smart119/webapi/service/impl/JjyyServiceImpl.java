package com.smart119.webapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.smart119.webapi.dao.JjyyDao;
import com.smart119.webapi.domain.JjyyDO;
import com.smart119.webapi.service.JjyyService;



@Service
public class JjyyServiceImpl implements JjyyService {
	@Autowired
	private JjyyDao jjyyDao;
	
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
	@Override
	public List<JjyyDO> listlanguageType(){
		return jjyyDao.listlanguageType();
	}

	@Override
	public int size(){
		return jjyyDao.size();
	}

	@Override
	public List<JjyyDO> listlanguage(String type){
		return jjyyDao.listlanguage(type);
	}

	@Override
	public int languageSize(String type){
		return jjyyDao.languageSize(type);
	}

}
