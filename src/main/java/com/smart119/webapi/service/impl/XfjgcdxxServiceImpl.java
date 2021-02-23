package com.smart119.webapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.smart119.webapi.dao.XfjgcdxxDao;
import com.smart119.webapi.domain.XfjgcdxxDO;
import com.smart119.webapi.service.XfjgcdxxService;



@Service
public class XfjgcdxxServiceImpl implements XfjgcdxxService {
	@Autowired
	private XfjgcdxxDao xfjgcdxxDao;
	
	@Override
	public XfjgcdxxDO get(String xfjgCddm){
		return xfjgcdxxDao.get(xfjgCddm);
	}
	
	@Override
	public List<XfjgcdxxDO> list(Map<String, Object> map){
		return xfjgcdxxDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return xfjgcdxxDao.count(map);
	}
	
	@Override
	public int save(XfjgcdxxDO xfjgcdxx){
		return xfjgcdxxDao.save(xfjgcdxx);
	}
	
	@Override
	public int update(XfjgcdxxDO xfjgcdxx){
		return xfjgcdxxDao.update(xfjgcdxx);
	}
	
	@Override
	public int remove(String xfjgCddm){
		return xfjgcdxxDao.remove(xfjgCddm);
	}
	
	@Override
	public int batchRemove(String[] xfjgCddms){
		return xfjgcdxxDao.batchRemove(xfjgCddms);
	}


	
}
