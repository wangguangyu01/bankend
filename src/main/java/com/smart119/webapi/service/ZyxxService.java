package com.smart119.webapi.service;

import com.smart119.webapi.domain.ZyxxDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author Jiyu Yang
 * @email yangjiyu@sz000673.com
 * @date 2021-02-08 19:46:49
 */
public interface ZyxxService {
	
	ZyxxDO get(String jxxxzybm);
	
	List<ZyxxDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ZyxxDO zyxx);
	
	int update(ZyxxDO zyxx);
	
	int remove(String jxxxzybm);
	
	int batchRemove(String[] jxxxzybms);

	Map<String, Object> getlist(Map<String, Object> params);
}
