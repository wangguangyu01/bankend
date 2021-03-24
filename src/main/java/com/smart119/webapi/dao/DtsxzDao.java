package com.smart119.webapi.dao;

import com.smart119.webapi.domain.DtsxzDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 警情信息_动态属性值信息
 * @author yanyu
 * @email yanyu@sz000673.com
 * @date 2021-01-28 12:06:29
 */

public interface DtsxzDao {

	DtsxzDO get(String dtsxzTywysbm);
	
	List<DtsxzDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(DtsxzDO dtsxz);
	
	int update(DtsxzDO dtsxz);
	
	int remove(String DTSXZ_TYWYSBM);
	
	int batchRemove(String[] dtsxzTywysbms);

	List<DtsxzDO> getJqDtsxByJqTywysbmAndZhcsId(@Param("zhcsId")String zhcsId, @Param("jqTywysbm")String jqTywysbm);

}
