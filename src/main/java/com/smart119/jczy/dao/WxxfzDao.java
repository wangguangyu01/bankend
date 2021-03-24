package com.smart119.jczy.dao;

import com.smart119.jczy.domain.WxxfzDO;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 微型消防站
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2021-01-19 13:52:11
 */

public interface WxxfzDao {

	WxxfzDO get(String wxxfzTywysbm);
	
	List<WxxfzDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(WxxfzDO wxxfz);
	
	int update(WxxfzDO wxxfz);
	
	int remove(String WXXFZ_TYWYSBM);
	
	int batchRemove(String[] wxxfzTywysbms);

	List<WxxfzDO> getWxxfzByRange(@Param("jd")Double jd, @Param("wd")Double wd, @Param("distance")Double distance);

	List<Map<String,Object>> wxxfzGroupXzqh(Map<String,Object> map);

	List<Map<String,Object>> wxxfzByXzqh(Map<String,Object> map);


}
