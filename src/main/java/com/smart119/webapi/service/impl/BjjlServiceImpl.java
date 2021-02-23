package com.smart119.webapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.smart119.webapi.dao.BjjlDao;
import com.smart119.webapi.domain.BjjlDO;
import com.smart119.webapi.service.BjjlService;



@Service
public class BjjlServiceImpl implements BjjlService {
	@Autowired
	private BjjlDao bjjlDao;

	@Override
	public BjjlDO get(String bjTywysbm){
		return bjjlDao.get(bjTywysbm);
	}

	@Override
	public List<BjjlDO> list(Map<String, Object> map){
		return bjjlDao.list(map);
	}

	@Override
	public int count(Map<String, Object> map){
		return bjjlDao.count(map);
	}

	@Override
	public int save(BjjlDO bjjl){
		return bjjlDao.save(bjjl);
	}

	@Override
	public int update(BjjlDO bjjl){
		return bjjlDao.update(bjjl);
	}

	@Override
	public int remove(String bjTywysbm){
		return bjjlDao.remove(bjTywysbm);
	}

	@Override
	public int batchRemove(String[] bjTywysbms){
		return bjjlDao.batchRemove(bjTywysbms);
	}

	@Override
	public List<BjjlDO> bjQuery(Map<String, Object> map) {
		return bjjlDao.bjQuery(map);

	}

	@Override
	public int bjcount(Map<String, Object> map) {
		return bjjlDao.bjcount(map);
	}
}
