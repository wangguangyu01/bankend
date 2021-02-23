package com.smart119.jczy.service;

import com.smart119.jczy.domain.JjyyDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author thrz
 * @email thrz@sz000673.com
 * @date 2021-02-03 15:35:11
 */
public interface JczyjjyyService {
	
	JjyyDO get(String id);
	
	List<JjyyDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(JjyyDO jjyy);
	
	int update(JjyyDO jjyy);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
