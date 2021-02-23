package com.smart119.webapi.service;

import com.smart119.webapi.domain.JqcjdpYaDO;

import java.util.List;
import java.util.Map;

/**
 * 警情处警调派-应急预案
 * 
 * @author yanyu
 * @email yanyu@sz000673.com
 * @date 2021-01-30 12:42:04
 */
public interface JqcjdpYaService {
	
	JqcjdpYaDO get(String jqcjdpYaId);
	
	List<JqcjdpYaDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(JqcjdpYaDO jqcjdpYa);
	
	int update(JqcjdpYaDO jqcjdpYa);
	
	int remove(String jqcjdpYaId);
	
	int batchRemove(String[] jqcjdpYaIds);
}
