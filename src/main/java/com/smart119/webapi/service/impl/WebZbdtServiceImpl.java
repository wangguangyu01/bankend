package com.smart119.webapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.smart119.webapi.dao.WebZbdtDao;
import com.smart119.webapi.domain.ZbdtDO;
import com.smart119.webapi.service.WebZbdtService;



@Service
public class WebZbdtServiceImpl implements WebZbdtService {
	@Autowired
	private WebZbdtDao webZbdtDao;
	
	@Override
	public ZbdtDO get(String zhbTywysbm){
		return webZbdtDao.get(zhbTywysbm);
	}
	
	@Override
	public List<ZbdtDO> list(Map<String, Object> map){
		return webZbdtDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return webZbdtDao.count(map);
	}
	
	@Override
	public int save(ZbdtDO zbdt){
		return webZbdtDao.save(zbdt);
	}
	
	@Override
	public int update(ZbdtDO zbdt){
		return webZbdtDao.update(zbdt);
	}
	
	@Override
	public int remove(String zhbTywysbm){
		return webZbdtDao.remove(zhbTywysbm);
	}
	
	@Override
	public int batchRemove(String[] zhbTywysbms){
		return webZbdtDao.batchRemove(zhbTywysbms);
	}

	@Override
	public List<ZbdtDO> zbList(String xfjyjgTywysbm){
		return webZbdtDao.zbList(xfjyjgTywysbm);
	}
	@Override
	public int zbSize(String xfjyjgTywysbm){
		return webZbdtDao.zbSize(xfjyjgTywysbm);
	}
}
