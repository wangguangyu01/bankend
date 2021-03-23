package com.smart119.xwzx.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.smart119.xwzx.dao.XwZxDao;
import com.smart119.xwzx.domain.XwZxDO;
import com.smart119.xwzx.service.XwZxService;



@Service
public class XwZxServiceImpl implements XwZxService {
	@Autowired
	private XwZxDao xwZxDao;
	
	@Override
	public XwZxDO get(String xwZxId){
		return xwZxDao.get(xwZxId);
	}
	
	@Override
	public List<XwZxDO> list(Map<String, Object> map){
		return xwZxDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return xwZxDao.count(map);
	}
	
	@Override
	public int save(XwZxDO xwZx){
		return xwZxDao.save(xwZx);
	}
	
	@Override
	public int update(XwZxDO xwZx){
		return xwZxDao.update(xwZx);
	}
	
	@Override
	public int remove(String xwZxId){
		return xwZxDao.remove(xwZxId);
	}
	
	@Override
	public int batchRemove(String[] xwZxIds){
		return xwZxDao.batchRemove(xwZxIds);
	}
	
}
