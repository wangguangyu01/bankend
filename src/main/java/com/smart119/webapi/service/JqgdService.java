package com.smart119.webapi.service;

import com.smart119.webapi.domain.JqgdDO;

import java.util.List;
import java.util.Map;

/**
 * 警情归档信息表
 * 
 * @author xuantianlong
 * @date 2021-01-28 14:23:42
 */
public interface JqgdService {
	
	JqgdDO get(String jqgdjlTywysbm);
	
	List<JqgdDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(JqgdDO jqgd);
	
	int update(JqgdDO jqgd);
	
	int remove(String jqgdjlTywysbm);
	
	int batchRemove(String[] jqgdjlTywysbms);
}
