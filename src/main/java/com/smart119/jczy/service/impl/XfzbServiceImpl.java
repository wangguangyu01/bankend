package com.smart119.jczy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.smart119.jczy.dao.XfzbDao;
import com.smart119.jczy.domain.XfzbDO;
import com.smart119.jczy.service.XfzbService;



@Service
public class XfzbServiceImpl implements XfzbService {
	@Autowired
	private XfzbDao xfzbDao;
	
	@Override
	public XfzbDO get(String xfzbTywysbm){
		return xfzbDao.get(xfzbTywysbm);
	}
	
	@Override
	public List<XfzbDO> list(Map<String, Object> map){
		return xfzbDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return xfzbDao.count(map);
	}
	
	@Override
	public int save(XfzbDO xfzb){
		return xfzbDao.save(xfzb);
	}
	
	@Override
	public int update(XfzbDO xfzb){
		return xfzbDao.update(xfzb);
	}
	
	@Override
	public int remove(String xfzbTywysbm){
		return xfzbDao.remove(xfzbTywysbm);
	}
	
	@Override
	public int batchRemove(String[] xfzbTywysbms){
		return xfzbDao.batchRemove(xfzbTywysbms);
	}
	
}
