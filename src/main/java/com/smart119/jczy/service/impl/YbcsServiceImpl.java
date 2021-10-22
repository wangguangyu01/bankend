package com.smart119.jczy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.smart119.jczy.dao.YbcsDao;
import com.smart119.jczy.domain.YbcsDO;
import com.smart119.jczy.service.YbcsService;



@Service
public class YbcsServiceImpl implements YbcsService {
	@Autowired
	private YbcsDao ybcsDao;
	
	@Override
	public YbcsDO get(String ybcsTywysbm){
		return ybcsDao.get(ybcsTywysbm);
	}
	
	@Override
	public List<YbcsDO> list(Map<String, Object> map){

		return ybcsDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return ybcsDao.count(map);
	}
	
	@Override
	public int save(YbcsDO ybcs){
		return ybcsDao.save(ybcs);
	}
	
	@Override
	public int update(YbcsDO ybcs){
		return ybcsDao.update(ybcs);
	}
	
	@Override
	public int remove(String ybcsTywysbm){
		return ybcsDao.remove(ybcsTywysbm);
	}
	
	@Override
	public int batchRemove(String[] ybcsTywysbms){
		return ybcsDao.batchRemove(ybcsTywysbms);
	}
	
}
