package com.smart119.jczy.service;

import com.smart119.jczy.domain.ZddwDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author thrz
 * @email thrz@sz000673.com
 * @date 2021-01-20 15:32:48
 */
public interface ZddwService {
	
	ZddwDO get(String zddwTywysbm);
	
	List<ZddwDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ZddwDO zddw);
	
	int update(ZddwDO zddw);
	
	int remove(String zddwTywysbm);
	
	int batchRemove(String[] zddwTywysbms);

	ZddwDO zddwSb(Double jd,Double wd);
}
