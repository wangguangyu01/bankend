package com.smart119.jczy.service;

import com.smart119.jczy.domain.BcbdZzdyDO;

import java.util.List;
import java.util.Map;

/**
 * 编程编队-作战单元
 * 
 * @author Jiyu Yang
 * @email yangjiyu@sz000673.com
 * @date 2021-01-30 10:12:02
 */
public interface BcbdZzdyService {
	
	BcbdZzdyDO get(String bcbdZzdyId);

	List<BcbdZzdyDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(BcbdZzdyDO bcbdZzdy);
	
	int update(BcbdZzdyDO bcbdZzdy);
	
	int remove(String bcbdZzdyId);

	int batchRemove(String[] bcbdZzdyIds);
}
