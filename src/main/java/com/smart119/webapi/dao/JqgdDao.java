package com.smart119.webapi.dao;

import com.smart119.webapi.domain.JqgdDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 警情归档信息表
 * @author xuantianlong
 * @email yanyu@sz000673.com
 * @date 2021-01-28 14:23:42
 */

public interface JqgdDao {

	JqgdDO get(String jqgdjlTywysbm);
	
	List<JqgdDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(JqgdDO jqgd);
	
	int update(JqgdDO jqgd);
	
	int remove(String JQGDJL_TYWYSBM);
	
	int batchRemove(String[] jqgdjlTywysbms);
}
