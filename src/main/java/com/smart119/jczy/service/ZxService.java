package com.smart119.jczy.service;

import com.smart119.jczy.domain.ZxDO;

import java.util.List;
import java.util.Map;

/**
 * 坐席
 * 
 * @author shilei
 * @email yangjiyu@sz000673.com
 * @date 2021-02-25 10:15:41
 */
public interface ZxService {
	
	ZxDO get(String id);
	
	List<ZxDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ZxDO zx);
	
	int update(ZxDO zx);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
