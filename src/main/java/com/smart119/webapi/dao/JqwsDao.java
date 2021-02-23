package com.smart119.webapi.dao;

import com.smart119.webapi.domain.JqwsDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author xuantianlong
 * @email yanyu@sz000673.com
 * @date 2021-01-28 14:00:49
 */
@Mapper
public interface JqwsDao {

	JqwsDO get(String jqhcwsTywysbm);
	
	List<JqwsDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(JqwsDO jqws);
	
	int update(JqwsDO jqws);
	
	int remove(String JQHCWS_TYWYSBM);
	
	int batchRemove(String[] jqhcwsTywysbms);
}
