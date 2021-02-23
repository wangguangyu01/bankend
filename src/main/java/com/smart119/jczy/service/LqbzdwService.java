package com.smart119.jczy.service;

import com.smart119.jczy.domain.LqbzdwDO;

import java.util.List;
import java.util.Map;

/**
 * 联勤保障单位基本信息
 * 
 * @author liangsl
 * @email 1992lcg@163.com
 * @date 2021-01-13 17:06:48
 */
public interface LqbzdwService {
	
	LqbzdwDO get(String lqbzdwTywysbm);
	
	List<LqbzdwDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(LqbzdwDO lqbzdw);
	
	int update(LqbzdwDO lqbzdw);
	
	int remove(String lqbzdwTywysbm);
	
	int batchRemove(String[] lqbzdwTywysbms);
}
