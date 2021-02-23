package com.smart119.webapi.service.impl;

import com.smart119.webapi.dao.ZyxxClDao;
import com.smart119.webapi.domain.ZyxxClDO;
import com.smart119.webapi.service.ZyxxClService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;



@Service
public class ZyxxClServiceImpl implements ZyxxClService {
	@Autowired
	private ZyxxClDao zyxxClDao;
	
	@Override
	public ZyxxClDO get(String id){
		return zyxxClDao.get(id);
	}
	
	@Override
	public List<ZyxxClDO> list(Map<String, Object> map){
		return zyxxClDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return zyxxClDao.count(map);
	}
	
	@Override
	public int save(ZyxxClDO zyxxCl){
		return zyxxClDao.save(zyxxCl);
	}
	
	@Override
	public int update(ZyxxClDO zyxxCl){
		return zyxxClDao.update(zyxxCl);
	}
	
	@Override
	public int remove(String id){
		return zyxxClDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return zyxxClDao.batchRemove(ids);
	}

	@Override
	public List<Map<String, Object>> getList(String jxxxzybm) {
		return zyxxClDao.getList(jxxxzybm);
	}

}
