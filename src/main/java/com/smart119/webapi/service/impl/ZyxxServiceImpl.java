package com.smart119.webapi.service.impl;

import com.smart119.webapi.dao.ZyxxDao;
import com.smart119.webapi.domain.ZyxxDO;
import com.smart119.webapi.service.ZyxxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;



@Service
public class ZyxxServiceImpl implements ZyxxService {
	@Autowired
	private ZyxxDao zyxxDao;
	
	@Override
	public ZyxxDO get(String jxxxzybm){
		return zyxxDao.get(jxxxzybm);
	}
	
	@Override
	public List<ZyxxDO> list(Map<String, Object> map){
		return zyxxDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return zyxxDao.count(map);
	}
	
	@Override
	public int save(ZyxxDO zyxx){
		return zyxxDao.save(zyxx);
	}
	
	@Override
	public int update(ZyxxDO zyxx){
		return zyxxDao.update(zyxx);
	}
	
	@Override
	public int remove(String jxxxzybm){
		return zyxxDao.remove(jxxxzybm);
	}
	
	@Override
	public int batchRemove(String[] jxxxzybms){
		return zyxxDao.batchRemove(jxxxzybms);
	}

	@Override
	public Map<String, Object> getlist(Map<String, Object> params) {
		return zyxxDao.getlist(params);
	}

}
