package com.smart119.jczy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.smart119.jczy.dao.ZbdtDao;
import com.smart119.jczy.domain.ZbdtDO;
import com.smart119.jczy.service.ZbdtService;

import static com.smart119.common.utils.ShiroUtils.getUserId;


@Service
public class ZbdtServiceImpl implements ZbdtService {
	@Autowired
	private ZbdtDao zbdtDao;
	
	@Override
	public ZbdtDO get(String zhbTywysbm){
		return zbdtDao.get(zhbTywysbm);
	}
	
	@Override
	public List<ZbdtDO> list(Map<String, Object> map){
		return zbdtDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return zbdtDao.count(map);
	}
	
	@Override
	public int save(ZbdtDO zbdt){
		zbdt.setZhbTywysbm(UUID.randomUUID().toString().replace("-", ""));
		zbdt.setCdate(new Date());
		zbdt.setCperson(getUserId()+"");
		zbdt.setStatus(0);
		return zbdtDao.save(zbdt);
	}
	
	@Override
	public int update(ZbdtDO zbdt){
		return zbdtDao.update(zbdt);
	}
	
	@Override
	public int remove(String zhbTywysbm){
		return zbdtDao.remove(zhbTywysbm);
	}
	
	@Override
	public int batchRemove(String[] zhbTywysbms){
		return zbdtDao.batchRemove(zhbTywysbms);
	}

	@Override
	public Map<String,Object> xfjyjgTywysbmByUserId(String userId) {
		Map<String,Object> map = zbdtDao.xfjyjgTywysbmByUserId(userId);

		return map;
	}

}
