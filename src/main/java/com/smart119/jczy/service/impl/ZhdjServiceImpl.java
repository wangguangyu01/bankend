package com.smart119.jczy.service.impl;

import com.smart119.jczy.dao.ZhdjDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.smart119.jczy.domain.ZhdjDO;
import com.smart119.jczy.service.ZhdjService;



@Service
public class ZhdjServiceImpl implements ZhdjService {
	@Autowired
	private ZhdjDao zhdjDao;
	
	@Override
	public ZhdjDO get(String id){
		return zhdjDao.get(id);
	}
	
	@Override
	public List<ZhdjDO> list(Map<String, Object> map){
		return zhdjDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return zhdjDao.count(map);
	}
	
	@Override
	public int save(ZhdjDO zhdj){
		return zhdjDao.save(zhdj);
	}
	
	@Override
	public int update(ZhdjDO zhdj){
		return zhdjDao.update(zhdj);
	}
	
	@Override
	public int remove(String id){
		return zhdjDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return zhdjDao.batchRemove(ids);
	}

	@Override
	public String findZhdj(Map<String,Object> map) {
		ZhdjDO zhdj = zhdjDao.findZhdj(map);
		if(zhdj!=null){
			return zhdj.getZhdj();
		}else{
			return null;
		}
	}


}
