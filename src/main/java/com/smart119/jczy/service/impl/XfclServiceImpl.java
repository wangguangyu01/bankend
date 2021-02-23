package com.smart119.jczy.service.impl;

import com.smart119.jczy.dao.XfclDao;
import com.smart119.jczy.domain.XfclDO;
import com.smart119.jczy.service.XfclService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;



@Service
public class XfclServiceImpl implements XfclService {
	@Autowired
	private XfclDao xfclDao;
	
	@Override
	public XfclDO get(String xfclTywysbm){
		return xfclDao.get(xfclTywysbm);
	}
	
	@Override
	public List<XfclDO> list(Map<String, Object> map){
		return xfclDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return xfclDao.count(map);
	}
	
	@Override
	public int save(XfclDO xfcl){
		return xfclDao.save(xfcl);
	}
	
	@Override
	public int update(XfclDO xfcl){
		return xfclDao.update(xfcl);
	}
	
	@Override
	public int remove(String xfclTywysbm){
		return xfclDao.remove(xfclTywysbm);
	}
	
	@Override
	public int batchRemove(String[] xfclTywysbms){
		return xfclDao.batchRemove(xfclTywysbms);
	}

	@Override
	public List<XfclDO> carlist(Map<String, Object> map) {
		return xfclDao.carlist(map);
	}

	@Override
	public int carlistcount(Map<String, Object> map) {
			return xfclDao.carlistcount(map);
	}

}
