package com.smart119.jczy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.smart119.jczy.dao.XfclSxzDao;
import com.smart119.jczy.domain.XfclSxzDO;
import com.smart119.jczy.service.XfclSxzService;



@Service
public class XfclSxzServiceImpl implements XfclSxzService {
	@Autowired
	private XfclSxzDao xfclSxzDao;
	
	@Override
	public XfclSxzDO get(String id){
		return xfclSxzDao.get(id);
	}
	
	@Override
	public List<XfclSxzDO> list(Map<String, Object> map){
		return xfclSxzDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return xfclSxzDao.count(map);
	}
	
	@Override
	public int save(XfclSxzDO xfclSxz){
		return xfclSxzDao.save(xfclSxz);
	}
	
	@Override
	public int update(XfclSxzDO xfclSxz){
		return xfclSxzDao.update(xfclSxz);
	}
	
	@Override
	public int remove(String id){
		return xfclSxzDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return xfclSxzDao.batchRemove(ids);
	}

	@Override
	public List<XfclSxzDO> getSxzlist() {
		return xfclSxzDao.getSxzlist();
	}

}
