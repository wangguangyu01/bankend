package com.smart119.jczy.service;

import com.smart119.jczy.domain.JqlbZjlyDO;

import java.util.List;
import java.util.Map;

/**
 * 警情类别-专家领域 对照表
 * 
 * @author shilei
 * @email shichengyuan@sz000673.com
 * @date 2021-09-15 11:35:18
 */
public interface JqlbZjlyService {
	
	JqlbZjlyDO get(String id);
	
	List<JqlbZjlyDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(JqlbZjlyDO jqlbZjly);
	
	int update(JqlbZjlyDO jqlbZjly);
	
	int remove(String id);
	
	int batchRemove(String[] ids);

	List<Map<String,Object>> getZjlyType();
}
