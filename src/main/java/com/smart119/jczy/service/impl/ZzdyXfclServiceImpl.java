package com.smart119.jczy.service.impl;

import com.smart119.jczy.dao.ZzdyXfclDao;
import com.smart119.jczy.domain.ZzdyXfclDO;
import com.smart119.jczy.service.ZzdyXfclService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;



@Service
public class ZzdyXfclServiceImpl implements ZzdyXfclService {
	@Autowired
	private ZzdyXfclDao zzdyXfclDao;
	
	@Override
	public ZzdyXfclDO get(String id){
		return zzdyXfclDao.get(id);
	}
	
	@Override
	public List<ZzdyXfclDO> list(Map<String, Object> map){
		return zzdyXfclDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return zzdyXfclDao.count(map);
	}
	
	@Override
	public int save(ZzdyXfclDO zzdyXfcl){
		return zzdyXfclDao.save(zzdyXfcl);
	}
	
	@Override
	public int update(ZzdyXfclDO zzdyXfcl){
		return zzdyXfclDao.update(zzdyXfcl);
	}
	
	@Override
	public int remove(String id){
		return zzdyXfclDao.remove(id);
	}
	@Override
	public int removeZzdy(String ZZDY_ID){
		return zzdyXfclDao.removeZzdy(ZZDY_ID);
	}

	@Override
	public List<ZzdyXfclDO> getZzdyxfcl(String zzdyTywybs) {
		return zzdyXfclDao.getZzdyxfcl(zzdyTywybs);
	}

	@Override
	public int batchRemove(String[] ids){
		return zzdyXfclDao.batchRemove(ids);
	}
	
}
