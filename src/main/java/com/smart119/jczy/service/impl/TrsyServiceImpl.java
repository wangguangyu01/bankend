package com.smart119.jczy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.smart119.jczy.dao.TrsyDao;
import com.smart119.jczy.domain.TrsyDO;
import com.smart119.jczy.service.TrsyService;



@Service
public class TrsyServiceImpl implements TrsyService {
	@Autowired
	private TrsyDao trsyDao;

	@Override
	public TrsyDO get(String trsyTywysbm){
		return trsyDao.get(trsyTywysbm);
	}

	@Override
	public List<TrsyDO> list(Map<String, Object> map){
		return trsyDao.list(map);
	}

	@Override
	public int count(Map<String, Object> map){
		return trsyDao.count(map);
	}

	@Override
	public int save(TrsyDO trsy){
		return trsyDao.save(trsy);
	}

	@Override
	public int update(TrsyDO trsy){
		return trsyDao.update(trsy);
	}

	@Override
	public int remove(String trsyTywysbm){
		return trsyDao.remove(trsyTywysbm);
	}

	@Override
	public int batchRemove(String[] trsyTywysbms){
		int count = 0;
		for (String trsyTywysbm: trsyTywysbms) {
			this.updateStatus(trsyTywysbm);
			count ++;
		}
		return count;
	}

	@Override
	public List<TrsyDO> getTrsyByRange(Double jd, Double wd, Double distance) {
		return trsyDao.getTrsyByRange(jd,wd,distance);
	}


	@Override
	public int updateStatus(String trsyTywysbm) {
		return trsyDao.updateStatus(trsyTywysbm);
	}
}
