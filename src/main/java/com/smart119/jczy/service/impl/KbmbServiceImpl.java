package com.smart119.jczy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.smart119.jczy.dao.KbmbDao;
import com.smart119.jczy.domain.KbmbDO;
import com.smart119.jczy.service.KbmbService;



@Service
public class KbmbServiceImpl implements KbmbService {
	@Autowired
	private KbmbDao kbmbDao;
	
	@Override
	public KbmbDO get(String kbmbId){
		return kbmbDao.get(kbmbId);
	}
	
	@Override
	public List<KbmbDO> list(Map<String, Object> map){
		return kbmbDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return kbmbDao.count(map);
	}
	
	@Override
	public int save(KbmbDO kbmb){
		return kbmbDao.save(kbmb);
	}
	
	@Override
	public int update(KbmbDO kbmb){
		return kbmbDao.update(kbmb);
	}
	
	@Override
	public int remove(String kbmbId){
		return kbmbDao.remove(kbmbId);
	}
	
	@Override
	public int batchRemove(String[] kbmbIds){
		return kbmbDao.batchRemove(kbmbIds);
	}
	
}
