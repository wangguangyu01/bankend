package com.smart119.webapi.service;

import com.smart119.webapi.domain.DtsxDO;

import java.util.List;
import java.util.Map;

/**
 * 警情信息_动态属性信息
 * 
 * @author yanyu
 * @email yanyu@sz000673.com
 * @date 2021-01-28 12:05:01
 */
public interface DtsxService {
	
	DtsxDO get(String dtsxTywysbm);
	
	List<DtsxDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(DtsxDO dtsx);
	
	int update(DtsxDO dtsx);
	
	int remove(String dtsxTywysbm);
	
	int batchRemove(String[] dtsxTywysbms);
}
