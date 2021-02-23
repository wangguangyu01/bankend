package com.smart119.jczy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.smart119.jczy.dao.XfscDao;
import com.smart119.jczy.domain.XfscDO;
import com.smart119.jczy.service.XfscService;



@Service
public class XfscServiceImpl implements XfscService {
	@Autowired
	private XfscDao xfscDao;
	
	@Override
	public XfscDO get(String xfscTywysbm){
		return xfscDao.get(xfscTywysbm);
	}
	
	@Override
	public List<XfscDO> list(Map<String, Object> map){
		return xfscDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return xfscDao.count(map);
	}
	
	@Override
	public int save(XfscDO xfsc){
		return xfscDao.save(xfsc);
	}
	
	@Override
	public int update(XfscDO xfsc){
		return xfscDao.update(xfsc);
	}
	
	@Override
	public int remove(String xfscTywysbm){
		return xfscDao.remove(xfscTywysbm);
	}
	
	@Override
	public int batchRemove(String[] xfscTywysbms){
		return xfscDao.batchRemove(xfscTywysbms);
	}

	@Override
	public List<XfscDO> getXfscByRange(Double jd, Double wd, Double distance) {
		return xfscDao.getXfscByRange(jd,wd,distance);
	}

}
