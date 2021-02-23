package com.smart119.webapi.dao;

import com.smart119.webapi.domain.XfrycdxxDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 消防人员出动信息
 * @author thrz
 * @email thrz@sz000673.com
 * @date 2021-01-28 17:21:22
 */
@Mapper
public interface XfrycdxxDao {

	XfrycdxxDO get(String xfryCddm);
	
	List<XfrycdxxDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(XfrycdxxDO xfrycdxx);
	
	int update(XfrycdxxDO xfrycdxx);
	
	int remove(String XFRY_CDDM);
	
	int batchRemove(String[] xfryCddms);
}
