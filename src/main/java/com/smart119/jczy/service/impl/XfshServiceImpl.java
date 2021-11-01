package com.smart119.jczy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.smart119.jczy.dao.XfshDao;
import com.smart119.jczy.domain.XfshDO;
import com.smart119.jczy.service.XfshService;



@Service
public class XfshServiceImpl implements XfshService {
	@Autowired
	private XfshDao xfshDao;

	@Override
	public XfshDO get(String xfshTywysbm){
		return xfshDao.get(xfshTywysbm);
	}

	@Override
	public List<XfshDO> list(Map<String, Object> map){
		return xfshDao.list(map);
	}

	@Override
	public int count(Map<String, Object> map){
		return xfshDao.count(map);
	}

	@Override
	public int save(XfshDO xfsh){
		return xfshDao.save(xfsh);
	}

	@Override
	public int update(XfshDO xfsh){
		return xfshDao.update(xfsh);
	}

	@Override
	public int remove(String xfshTywysbm){
		return this.updateStatus(xfshTywysbm);
	}

	@Override
	public int batchRemove(String[] xfshTywysbms){
		int count = 0;
		for (String xfshTywysbm: xfshTywysbms) {
			this.updateStatus(xfshTywysbm);
			count ++;
		}
		return count;
	}

	@Override
	public List<XfshDO> getXfshByRange(Double jd, Double wd, Double distance) {
		return xfshDao.getXfshByRange(jd,wd,distance);
	}


	@Override
	public int updateStatus(String xfshTywysbm) {
		return xfshDao.updateStatus(xfshTywysbm);
	}
}
