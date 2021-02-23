package com.smart119.webapi.service.impl;

import com.smart119.webapi.domain.JjyyDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.smart119.webapi.dao.ZhjqDao;
import com.smart119.webapi.domain.ZhjqDO;
import com.smart119.webapi.service.ZhjqService;



@Service
public class ZhjqServiceImpl implements ZhjqService {
	@Autowired
	private ZhjqDao zhjqDao;

	@Override
	public ZhjqDO get(String id){
		return zhjqDao.get(id);
	}

	@Override
	public List<ZhjqDO> list(Map<String, Object> map){
		return zhjqDao.list(map);
	}

	@Override
	public int count(Map<String, Object> map){
		return zhjqDao.count(map);
	}

	@Override
	public int save(ZhjqDO zhjq){
		return zhjqDao.save(zhjq);
	}

	@Override
	public int update(ZhjqDO zhjq){
		return zhjqDao.update(zhjq);
	}

	@Override
	public int remove(String id){
		return zhjqDao.remove(id);
	}

	@Override
	public int batchRemove(String[] ids){
		return zhjqDao.batchRemove(ids);
	}

	@Override
	public List<ZhjqDO> getZhjqList(String lylb,String jqzt,String ms){
		return zhjqDao.getZhjqList(lylb,jqzt,ms);
	}

	@Override
	public int getZhjqSize(String lylb,String jqzt,String ms){
		return zhjqDao.getZhjqSize(lylb,jqzt,ms);
	}

	@Override
	public List<ZhjqDO> getZhjqxqList(String id){
		return zhjqDao.getZhjqxqList(id);
	}


}
