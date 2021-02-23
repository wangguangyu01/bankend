package com.smart119.jczy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.smart119.jczy.dao.XhsDao;
import com.smart119.jczy.domain.XhsDO;
import com.smart119.jczy.service.XhsService;



@Service
public class XhsServiceImpl implements XhsService {
	@Autowired
	private XhsDao xhsDao;
	
	@Override
	public XhsDO get(String xhsTywysbm){
		return xhsDao.get(xhsTywysbm);
	}
	
	@Override
	public List<XhsDO> list(Map<String, Object> map){
		return xhsDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return xhsDao.count(map);
	}
	
	@Override
	public int save(XhsDO xhs){
		return xhsDao.save(xhs);
	}
	
	@Override
	public int update(XhsDO xhs){
		return xhsDao.update(xhs);
	}
	
	@Override
	public int remove(String xhsTywysbm){
		return xhsDao.remove(xhsTywysbm);
	}
	
	@Override
	public int batchRemove(String[] xhsTywysbms){
		return xhsDao.batchRemove(xhsTywysbms);
	}

	@Override
	public List<XhsDO> getXhsListByRange(Double jd, Double wd, Double distance) {
		return xhsDao.getXhsListByRange(jd,wd,distance);
	}

}
