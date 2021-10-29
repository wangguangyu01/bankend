package com.smart119.jczy.service.impl;

import com.smart119.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.smart119.jczy.dao.XfmtDao;
import com.smart119.jczy.domain.XfmtDO;
import com.smart119.jczy.service.XfmtService;



@Service
public class XfmtServiceImpl implements XfmtService {
	@Autowired
	private XfmtDao xfmtDao;

	@Override
	public XfmtDO get(String qsmtTywysbm){
		return xfmtDao.get(qsmtTywysbm);
	}

	@Override
	public List<XfmtDO> list(Map<String, Object> map){
		return xfmtDao.list(map);
	}

	@Override
	public int count(Map<String, Object> map){
		return xfmtDao.count(map);
	}

	@Override
	public int save(XfmtDO xfmt){
		return xfmtDao.save(xfmt);
	}

	@Override
	public int update(XfmtDO xfmt){
		return xfmtDao.update(xfmt);
	}

	@Override
	public int remove(String qsmtTywysbm){
		return xfmtDao.updateStatus(qsmtTywysbm);
	}

	@Override
	public boolean batchRemove(String[] qsmtTywysbms){
		int count = 0;
		for (String qsmtTywysbm: qsmtTywysbms) {
			xfmtDao.updateStatus(qsmtTywysbm);
			count ++;
		}
		if (count == qsmtTywysbms.length) {
			return true;
		} else {
			return  false;
		}

	}

	@Override
	public List<XfmtDO> getXfmtByRange(Double jd, Double wd, Double distance) {
		return xfmtDao.getXfmtByRange(jd,wd,distance);
	}

}
