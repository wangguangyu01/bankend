package com.smart119.zb.service;

import com.smart119.zb.domain.ZbzwDO;

import java.util.List;
import java.util.Map;

/**
 * 值班职务
 * 
 * @author liangsl
 * @email liangshengli@sz000673.com
 * @date 2021-05-08 19:01:02
 */
public interface ZbzwService {
	
	ZbzwDO get(String zbzwId);
	
	List<ZbzwDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ZbzwDO zbzw);
	
	int update(ZbzwDO zbzw);
	
	int remove(String zbzwId);
	
	int batchRemove(String[] zbzwIds);
}
