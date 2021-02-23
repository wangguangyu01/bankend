package com.smart119.webapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.smart119.webapi.dao.JqcjdpBcbdDao;
import com.smart119.webapi.domain.JqcjdpBcbdDO;
import com.smart119.webapi.service.JqcjdpBcbdService;



@Service
public class JqcjdpBcbdServiceImpl implements JqcjdpBcbdService {
	@Autowired
	private JqcjdpBcbdDao jqcjdpBcbdDao;
	
	@Override
	public JqcjdpBcbdDO get(String jqcjdpBcbdId){
		return jqcjdpBcbdDao.get(jqcjdpBcbdId);
	}
	
	@Override
	public List<JqcjdpBcbdDO> list(Map<String, Object> map){
		return jqcjdpBcbdDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return jqcjdpBcbdDao.count(map);
	}
	
	@Override
	public int save(JqcjdpBcbdDO jqcjdpBcbd){
		return jqcjdpBcbdDao.save(jqcjdpBcbd);
	}
	
	@Override
	public int update(JqcjdpBcbdDO jqcjdpBcbd){
		return jqcjdpBcbdDao.update(jqcjdpBcbd);
	}
	
	@Override
	public int remove(String jqcjdpBcbdId){
		return jqcjdpBcbdDao.remove(jqcjdpBcbdId);
	}
	
	@Override
	public int batchRemove(String[] jqcjdpBcbdIds){
		return jqcjdpBcbdDao.batchRemove(jqcjdpBcbdIds);
	}
	
}
