package com.smart119.webapi.service;

import com.smart119.webapi.domain.DtsxzDO;

import java.util.List;
import java.util.Map;

/**
 * 警情信息_动态属性值信息
 * 
 * @author yanyu
 * @email yanyu@sz000673.com
 * @date 2021-01-28 12:06:29
 */
public interface DtsxzService {
	
	DtsxzDO get(String dtsxzTywysbm);
	
	List<DtsxzDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(DtsxzDO dtsxz);
	
	int update(DtsxzDO dtsxz);
	
	int remove(String dtsxzTywysbm);
	
	int batchRemove(String[] dtsxzTywysbms);

	List<DtsxzDO> getJqDtsxByJqTywysbmAndZhcsId(String zhcsId,String jqTywysbm);

}
