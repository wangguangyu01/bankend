package com.smart119.webapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.smart119.webapi.dao.FzjctsDao;
import com.smart119.webapi.domain.FzjctsDO;
import com.smart119.webapi.service.FzjctsService;



@Service
public class FzjctsServiceImpl implements FzjctsService {
	@Autowired
	private FzjctsDao fzjctsDao;
	
	@Override
	public FzjctsDO get(String fzjctsId){
		return fzjctsDao.get(fzjctsId);
	}
	
	@Override
	public List<FzjctsDO> list(Map<String, Object> map){
		return fzjctsDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return fzjctsDao.count(map);
	}
	
	@Override
	public int save(FzjctsDO fzjcts){
		return fzjctsDao.save(fzjcts);
	}
	
	@Override
	public int update(FzjctsDO fzjcts){
		return fzjctsDao.update(fzjcts);
	}
	
	@Override
	public int remove(String fzjctsId){
		return fzjctsDao.remove(fzjctsId);
	}
	
	@Override
	public int batchRemove(String[] fzjctsIds){
		return fzjctsDao.batchRemove(fzjctsIds);
	}

	@Override
	public List<FzjctsDO> getFzjcTslistByJqTywysbm(String jqTywysbm) {
		return fzjctsDao.getFzjcTslistByJqTywysbm(jqTywysbm);
	}

}
