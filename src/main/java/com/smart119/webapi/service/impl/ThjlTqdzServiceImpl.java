package com.smart119.webapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.smart119.webapi.dao.ThjlTqdzDao;
import com.smart119.webapi.domain.ThjlTqdzDO;
import com.smart119.webapi.service.ThjlTqdzService;



@Service
public class ThjlTqdzServiceImpl implements ThjlTqdzService {
	@Autowired
	private ThjlTqdzDao thjlTqdzDao;
	
	@Override
	public ThjlTqdzDO get(String id){
		return thjlTqdzDao.get(id);
	}
	
	@Override
	public List<ThjlTqdzDO> list(Map<String, Object> map){
		return thjlTqdzDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return thjlTqdzDao.count(map);
	}
	
	@Override
	public int save(ThjlTqdzDO thjlTqdz){
		return thjlTqdzDao.save(thjlTqdz);
	}
	
	@Override
	public int update(ThjlTqdzDO thjlTqdz){
		return thjlTqdzDao.update(thjlTqdz);
	}
	
	@Override
	public int remove(String id){
		return thjlTqdzDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return thjlTqdzDao.batchRemove(ids);
	}
	
}
