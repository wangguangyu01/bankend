package com.smart119.jczy.dao;

import com.smart119.jczy.domain.XhsDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 消火栓基本信息
 * @author thrz
 * @email thrz@sz000673.com
 * @date 2021-01-19 14:57:59
 */
@Mapper
public interface XhsDao {

	XhsDO get(String xhsTywysbm);
	
	List<XhsDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(XhsDO xhs);
	
	int update(XhsDO xhs);
	
	int remove(String XHS_TYWYSBM);
	
	int batchRemove(String[] xhsTywysbms);

	List<XhsDO> getXhsListByRange(@Param("jd")Double jd, @Param("wd")Double wd, @Param("distance")Double distance);
}
