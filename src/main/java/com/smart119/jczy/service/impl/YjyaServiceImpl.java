package com.smart119.jczy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.smart119.jczy.dao.YjyaDao;
import com.smart119.jczy.domain.YjyaDO;
import com.smart119.jczy.service.YjyaService;

import static com.smart119.common.utils.ShiroUtils.getUserId;


@Service
public class YjyaServiceImpl implements YjyaService {
	@Autowired
	private YjyaDao yjyaDao;
	
	@Override
	public YjyaDO get(String yjyaTywysbm){
		return yjyaDao.get(yjyaTywysbm);
	}
	
	@Override
	public List<YjyaDO> list(Map<String, Object> map){
		return yjyaDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return yjyaDao.count(map);
	}
	
	@Override
	public int save(YjyaDO yjya){
		yjya.setYjyaTywysbm(UUID.randomUUID().toString().replace("-", ""));
		yjya.setCdate(new Date());
		yjya.setCperson(getUserId()+"");
		yjya.setStatus(0);
		return yjyaDao.save(yjya);
	}
	
	@Override
	public int update(YjyaDO yjya){
		return yjyaDao.update(yjya);
	}
	
	@Override
	public int remove(String yjyaTywysbm){
		return yjyaDao.remove(yjyaTywysbm);
	}
	
	@Override
	public int batchRemove(String[] yjyaTywysbms){
		return yjyaDao.batchRemove(yjyaTywysbms);
	}

	@Override
	public List<YjyaDO> getYjya4Dp(Map<String, Object> map) {
		return yjyaDao.getYjya4Dp(map);
	}


}
