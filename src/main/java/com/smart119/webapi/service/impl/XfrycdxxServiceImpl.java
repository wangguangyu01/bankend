package com.smart119.webapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.smart119.webapi.dao.XfrycdxxDao;
import com.smart119.webapi.domain.XfrycdxxDO;
import com.smart119.webapi.service.XfrycdxxService;



@Service
public class XfrycdxxServiceImpl implements XfrycdxxService {
	@Autowired
	private XfrycdxxDao xfrycdxxDao;
	
	@Override
	public XfrycdxxDO get(String xfryCddm){
		return xfrycdxxDao.get(xfryCddm);
	}
	
	@Override
	public List<XfrycdxxDO> list(Map<String, Object> map){
		return xfrycdxxDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return xfrycdxxDao.count(map);
	}
	
	@Override
	public int save(XfrycdxxDO xfrycdxx){
		return xfrycdxxDao.save(xfrycdxx);
	}
	
	@Override
	public int update(XfrycdxxDO xfrycdxx){
		return xfrycdxxDao.update(xfrycdxx);
	}
	
	@Override
	public int remove(String xfryCddm){
		return xfrycdxxDao.remove(xfryCddm);
	}
	
	@Override
	public int batchRemove(String[] xfryCddms){
		return xfrycdxxDao.batchRemove(xfryCddms);
	}
	
}
