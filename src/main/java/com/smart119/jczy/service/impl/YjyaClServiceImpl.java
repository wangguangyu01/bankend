package com.smart119.jczy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.smart119.jczy.dao.YjyaClDao;
import com.smart119.jczy.domain.YjyaClDO;
import com.smart119.jczy.service.YjyaClService;



@Service
public class YjyaClServiceImpl implements YjyaClService {
	@Autowired
	private YjyaClDao yjyaClDao;
	
	@Override
	public YjyaClDO get(String yjyaClId){
		return yjyaClDao.get(yjyaClId);
	}
	
	@Override
	public List<YjyaClDO> list(Map<String, Object> map){
		return yjyaClDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return yjyaClDao.count(map);
	}
	
	@Override
	public int save(YjyaClDO yjyaCl){
		return yjyaClDao.save(yjyaCl);
	}
	
	@Override
	public int update(YjyaClDO yjyaCl){
		return yjyaClDao.update(yjyaCl);
	}
	
	@Override
	public int remove(String yjyaClId){
		return yjyaClDao.remove(yjyaClId);
	}
	
	@Override
	public int batchRemove(String[] yjyaClIds){
		return yjyaClDao.batchRemove(yjyaClIds);
	}
	
}
