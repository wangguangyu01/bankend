package com.smart119.webapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.smart119.webapi.dao.JqdtDao;
import com.smart119.webapi.domain.JqdtDO;
import com.smart119.webapi.service.JqdtService;



@Service
public class JqdtServiceImpl implements JqdtService {
	@Autowired
	private JqdtDao jqdtDao;
	
	@Override
	public JqdtDO get(String jqdtId){
		return jqdtDao.get(jqdtId);
	}
	
	@Override
	public List<JqdtDO> list(Map<String, Object> map){
		return jqdtDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return jqdtDao.count(map);
	}
	
	@Override
	public int save(JqdtDO jqdt){
		return jqdtDao.save(jqdt);
	}
	
	@Override
	public int update(JqdtDO jqdt){
		return jqdtDao.update(jqdt);
	}
	
	@Override
	public int remove(String jqdtId){
		return jqdtDao.remove(jqdtId);
	}
	
	@Override
	public int batchRemove(String[] jqdtIds){
		return jqdtDao.batchRemove(jqdtIds);
	}

	@Override
	public List<JqdtDO> getJqdtByJqId(String jqTywysbm) {
		return jqdtDao.getJqdtByJqId(jqTywysbm);
	}

}
