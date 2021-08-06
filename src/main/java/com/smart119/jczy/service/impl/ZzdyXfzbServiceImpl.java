package com.smart119.jczy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.smart119.jczy.dao.ZzdyXfzbDao;
import com.smart119.jczy.domain.ZzdyXfzbDO;
import com.smart119.jczy.service.ZzdyXfzbService;



@Service
public class ZzdyXfzbServiceImpl implements ZzdyXfzbService {
	@Autowired
	private ZzdyXfzbDao zzdyXfzbDao;
	
	@Override
	public ZzdyXfzbDO get(String id){
		return zzdyXfzbDao.get(id);
	}
	
	@Override
	public List<ZzdyXfzbDO> list(Map<String, Object> map){
		return zzdyXfzbDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return zzdyXfzbDao.count(map);
	}
	
	@Override
	public int save(ZzdyXfzbDO zzdyXfzb){
		return zzdyXfzbDao.save(zzdyXfzb);
	}
	
	@Override
	public int update(ZzdyXfzbDO zzdyXfzb){
		return zzdyXfzbDao.update(zzdyXfzb);
	}
	
	@Override
	public int remove(String id){
		return zzdyXfzbDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return zzdyXfzbDao.batchRemove(ids);
	}

	@Override
	public List<ZzdyXfzbDO> getZzdyXfzb(String zzdyTywybs) {
		return zzdyXfzbDao.getZzdyXfzb(zzdyTywybs);
	}

	@Override
	public int removeZzdyXfzb(String zzdyTywybs) {
		return zzdyXfzbDao.removeZzdyXfzb(zzdyTywybs);
	}

}
