package com.smart119.jczy.dao;

import com.smart119.jczy.domain.ZddwDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * @author thrz
 * @email thrz@sz000673.com
 * @date 2021-01-20 15:32:48
 */
@Mapper
public interface ZddwDao {

	ZddwDO get(String zddwTywysbm);
	
	List<ZddwDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ZddwDO zddw);
	
	int update(ZddwDO zddw);
	
	int remove(String ZDDW_TYWYSBM);
	
	int batchRemove(String[] zddwTywysbms);

	ZddwDO zddwSb(@Param("jd")Double jd, @Param("wd")Double wd);
}
