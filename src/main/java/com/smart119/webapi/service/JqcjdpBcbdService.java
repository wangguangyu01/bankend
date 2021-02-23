package com.smart119.webapi.service;

import com.smart119.webapi.domain.JqcjdpBcbdDO;

import java.util.List;
import java.util.Map;

/**
 * 警情处警调派-编程编队
 * 
 * @author yanyu
 * @email yanyu@sz000673.com
 * @date 2021-01-30 12:40:38
 */
public interface JqcjdpBcbdService {
	
	JqcjdpBcbdDO get(String jqcjdpBcbdId);
	
	List<JqcjdpBcbdDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(JqcjdpBcbdDO jqcjdpBcbd);
	
	int update(JqcjdpBcbdDO jqcjdpBcbd);
	
	int remove(String jqcjdpBcbdId);
	
	int batchRemove(String[] jqcjdpBcbdIds);
}
