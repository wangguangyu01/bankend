package com.smart119.webapi.service;

import com.smart119.webapi.domain.JqwsDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author xuantianlong
 * @date 2021-01-28 14:00:49
 */
public interface JqwsService {
	
	JqwsDO get(String jqhcwsTywysbm);
	
	List<JqwsDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(JqwsDO jqws);
	
	int update(JqwsDO jqws);
	
	int remove(String jqhcwsTywysbm);
	
	int batchRemove(String[] jqhcwsTywysbms);
}
