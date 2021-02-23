package com.smart119.jczy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.smart119.jczy.dao.BlackListDao;
import com.smart119.jczy.domain.BlackListDO;
import com.smart119.jczy.service.BlackListService;



@Service
public class BlackListServiceImpl implements BlackListService {
	@Autowired
	private BlackListDao blackListDao;
	
	@Override
	public BlackListDO get(String hmdTywysbm){
		return blackListDao.get(hmdTywysbm);
	}
	
	@Override
	public List<BlackListDO> list(Map<String, Object> map){
		return blackListDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return blackListDao.count(map);
	}
	
	@Override
	public int save(BlackListDO blackList){
		return blackListDao.save(blackList);
	}
	
	@Override
	public int update(BlackListDO blackList){
		return blackListDao.update(blackList);
	}

	@Override
	public int updateByPhoneNumber(String phoneNumber,String xsjhsc){
		return blackListDao.updateByPhoneNumber(phoneNumber,xsjhsc);
	}
	
	@Override
	public int remove(String hmdTywysbm){
		return blackListDao.remove(hmdTywysbm);
	}

	@Override
	public int removeByPhoneNumber(String phoneNumber){
		return blackListDao.removeByPhoneNumber(phoneNumber);
	}
	
	@Override
	public int batchRemove(String[] hmdTywysbms){
		return blackListDao.batchRemove(hmdTywysbms);
	}
	
}
