package com.smart119.webapi.service;

import com.smart119.webapi.domain.XfrycdxxDO;

import java.util.List;
import java.util.Map;

/**
 * 消防人员出动信息
 * 
 * @author thrz
 * @email thrz@sz000673.com
 * @date 2021-01-28 17:21:22
 */
public interface XfrycdxxService {
	
	XfrycdxxDO get(String xfryCddm);
	
	List<XfrycdxxDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(XfrycdxxDO xfrycdxx);
	
	int update(XfrycdxxDO xfrycdxx);
	
	int remove(String xfryCddm);
	
	int batchRemove(String[] xfryCddms);
}
