package com.smart119.jczy.service.impl;

import com.smart119.jczy.domain.WblxrExcelDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.smart119.jczy.dao.WblxrDao;
import com.smart119.jczy.domain.WblxrDO;
import com.smart119.jczy.service.WblxrService;



@Service
public class WblxrServiceImpl implements WblxrService {
	@Autowired
	private WblxrDao wblxrDao;

	@Override
	public WblxrDO get(String wblxrId){
		return wblxrDao.get(wblxrId);
	}

	@Override
	public List<WblxrDO> list(Map<String, Object> map){
		return wblxrDao.list(map);
	}

	@Override
	public List<WblxrExcelDO> listOther(Map<String, Object> map){
		return wblxrDao.listOther(map);
	}

	@Override
	public int count(Map<String, Object> map){
		return wblxrDao.count(map);
	}

	@Override
	public int save(WblxrDO wblxr){
		return wblxrDao.save(wblxr);
	}

	@Override
	public int update(WblxrDO wblxr){
		return wblxrDao.update(wblxr);
	}

	@Override
	public int updateExcel(WblxrDO wblxr){
		return wblxrDao.updateExcel(wblxr);
	}

	@Override
	public int getWblxr(WblxrDO wblxr){
		return wblxrDao.getWblxr(wblxr);
	}

	@Override
	public int remove(String wblxrId){
		return wblxrDao.remove(wblxrId);
	}

	@Override
	public int batchRemove(String[] wblxrIds){
		return wblxrDao.batchRemove(wblxrIds);
	}

}
