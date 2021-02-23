package com.smart119.webapi.service;

import com.smart119.webapi.domain.ZyxxClDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author Jiyu Yang
 * @email yangjiyu@sz000673.com
 * @date 2021-02-08 19:46:54
 */
public interface ZyxxClService {
	
	ZyxxClDO get(String id);
	
	List<ZyxxClDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ZyxxClDO zyxxCl);
	
	int update(ZyxxClDO zyxxCl);
	
	int remove(String id);
	
	int batchRemove(String[] ids);

    List<Map<String, Object>> getList(String jxxxzybm);
}
