package com.smart119.jczy.service;

import com.smart119.jczy.domain.SpjkDO;

import java.util.List;
import java.util.Map;

/**
 * 视频监控
 * 
 * @author thrz
 * @email thrz@sz000673.com
 * @date 2021-01-20 10:17:15
 */
public interface SpjkService {
	
	SpjkDO get(String spjkTywysbm);
	
	List<SpjkDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SpjkDO spjk);
	
	int update(SpjkDO spjk);
	
	int remove(String spjkTywysbm);
	
	int batchRemove(String[] spjkTywysbms);
}
