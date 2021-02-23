package com.smart119.jczy.service;

import com.smart119.jczy.domain.XhsDO;

import java.util.List;
import java.util.Map;

/**
 * 消火栓基本信息
 * 
 * @author thrz
 * @email thrz@sz000673.com
 * @date 2021-01-19 14:57:59
 */
public interface XhsService {
	
	XhsDO get(String xhsTywysbm);
	
	List<XhsDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(XhsDO xhs);
	
	int update(XhsDO xhs);
	
	int remove(String xhsTywysbm);
	
	int batchRemove(String[] xhsTywysbms);

	List<XhsDO> getXhsListByRange(Double jd,Double wd,Double distance);
}
