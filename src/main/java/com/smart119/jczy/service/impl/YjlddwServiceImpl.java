package com.smart119.jczy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.smart119.jczy.dao.YjlddwDao;
import com.smart119.jczy.domain.YjlddwDO;
import com.smart119.jczy.service.YjlddwService;



@Service
public class YjlddwServiceImpl implements YjlddwService {
	@Autowired
	private YjlddwDao yjlddwDao;
	
	@Override
	public YjlddwDO get(String yjlddwTywysbm){
		return yjlddwDao.get(yjlddwTywysbm);
	}
	
	@Override
	public List<YjlddwDO> list(Map<String, Object> map){
		return yjlddwDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return yjlddwDao.count(map);
	}
	
	@Override
	public int save(YjlddwDO yjlddw){
		return yjlddwDao.save(yjlddw);
	}
	
	@Override
	public int update(YjlddwDO yjlddw){
		return yjlddwDao.update(yjlddw);
	}
	
	@Override
	public int remove(String yjlddwTywysbm){
		return yjlddwDao.remove(yjlddwTywysbm);
	}
	
	@Override
	public int batchRemove(String[] yjlddwTywysbms){
		return yjlddwDao.batchRemove(yjlddwTywysbms);
	}
	
}
