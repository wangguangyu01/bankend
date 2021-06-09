package com.smart119.jczy.service.impl;

import com.alibaba.fastjson.JSON;
import com.smart119.common.redis.shiro.RedisManager;
import com.smart119.common.utils.R;
import com.smart119.jczy.dao.XfclDao;
import com.smart119.jczy.domain.XfclDO;
import com.smart119.jczy.service.XfclService;
import com.smart119.jczy.service.XfclSxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;



@Service
public class XfclServiceImpl implements XfclService {
	@Autowired
	private XfclDao xfclDao;

	@Resource
	private RedisManager redisManager;

	@Autowired
	private XfclSxService xfclSxService;
	
	@Override
	public XfclDO get(String xfclTywysbm){
		return xfclDao.get(xfclTywysbm);
	}

	@Override
	public Map<String, Object> getMap(String id) {
		return xfclDao.getMap(id);
	}

	@Override
	public List<XfclDO> list(Map<String, Object> map){
		return xfclDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return xfclDao.count(map);
	}
	
	@Override
	public int save(XfclDO xfcl){
		return xfclDao.save(xfcl);
	}
	
	@Override
	public int update(XfclDO xfcl){
		return xfclDao.update(xfcl);
	}
	
	@Override
	public int remove(String xfclTywysbm){
		return xfclDao.remove(xfclTywysbm);
	}
	
	@Override
	public int batchRemove(String[] xfclTywysbms){
		return xfclDao.batchRemove(xfclTywysbms);
	}

	@Override
	public List<XfclDO> carlist(Map<String, Object> map) {
		return xfclDao.carlist(map);
	}

	@Override
	public int carlistcount(Map<String, Object> map) {
			return xfclDao.carlistcount(map);
	}

	@Override
	@Async
	public void saveRedis(String id) {
		XfclDO xfcl = get(id);
		xfcl.setXfclSxxx(xfclSxService.findSxAllByCltywysbm(id));
		this.redisManager.set("sys:xfcl:"+id, JSON.toJSONString(xfcl));
	}

	@Override
	public List<String> findAllXfclTywysbm() {
		return xfclDao.findAllXfclTywysbm();
	}

}
