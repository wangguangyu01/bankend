package com.smart119.jczy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.smart119.jczy.dao.XfclSxDao;
import com.smart119.jczy.domain.XfclSxDO;
import com.smart119.jczy.service.XfclSxService;
import org.springframework.web.bind.annotation.RequestParam;


@Service
public class XfclSxServiceImpl implements XfclSxService {
	@Autowired
	private XfclSxDao xfclSxDao;
	
	@Override
	public XfclSxDO get(String id){
		return xfclSxDao.get(id);
	}
	
	@Override
	public List<XfclSxDO> list(Map<String, Object> map){
		return xfclSxDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return xfclSxDao.count(map);
	}
	
	@Override
	public int save(XfclSxDO xfclSx){
		return xfclSxDao.save(xfclSx);
	}
	
	@Override
	public int update(XfclSxDO xfclSx){
		return xfclSxDao.update(xfclSx);
	}
	
	@Override
	public int remove(String id){
		return xfclSxDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return xfclSxDao.batchRemove(ids);
	}

	@Override
	public List<XfclSxDO> findAttrByCllx(String cllx) {
		return xfclSxDao.findAttrByCllx(cllx);
	}

	@Override
	public List<Map<String, Object>> findAttrByCllxVal(String clid) {
		return xfclSxDao.findAttrByCllxVal(clid);
	}

	@Override
	public int removeByXfclId(String xfclId) {
		return xfclSxDao.removeByXfclId(xfclId);
	}

	@Override
	public int removeBySxId(String sxId) {
		return xfclSxDao.removeBySxId(sxId);
	}

}
