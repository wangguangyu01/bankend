package com.smart119.zb.service;

import com.smart119.zb.domain.ZbDO;

import java.util.List;
import java.util.Map;

/**
 * 值班
 * 
 * @author liangsl
 * @email liangshengli@sz000673.com
 * @date 2021-05-08 19:01:02
 */
public interface ZbService {
	
	ZbDO get(String zbId);
	
	List<ZbDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ZbDO zb);
	
	int update(ZbDO zb);
	
	int remove(String zbId);
	
	int batchRemove(String rq,String xfjyjgTywysbm);
}
