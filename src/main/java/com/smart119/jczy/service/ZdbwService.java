package com.smart119.jczy.service;

import com.smart119.jczy.domain.ZdbwDO;

import java.util.List;
import java.util.Map;

/**
 * 重点部位基本信息
 * 
 * @author thrz
 * @email thrz@sz000673.com
 * @date 2021-01-20 15:50:30
 */
public interface ZdbwService {
	
	ZdbwDO get(String zdbwTywysbm);
	
	List<ZdbwDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ZdbwDO zdbw);
	
	int update(ZdbwDO zdbw);
	
	int remove(String zdbwTywysbm);
	
	int batchRemove(String[] zdbwTywysbms);
}
