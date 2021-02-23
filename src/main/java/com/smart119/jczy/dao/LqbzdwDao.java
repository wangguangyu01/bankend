package com.smart119.jczy.dao;

import com.smart119.jczy.domain.LqbzdwDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 联勤保障单位基本信息
 * @author liangsl
 * @email 1992lcg@163.com
 * @date 2021-01-13 17:06:48
 */
@Mapper
public interface LqbzdwDao {

	LqbzdwDO get(String lqbzdwTywysbm);
	
	List<LqbzdwDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(LqbzdwDO lqbzdw);
	
	int update(LqbzdwDO lqbzdw);
	
	int remove(String LQBZDW_TYWYSBM);
	
	int batchRemove(String[] lqbzdwTywysbms);
}
