package com.smart119.webapi.dao;

import com.smart119.webapi.domain.XfjgcdxxDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 消防机构出动信息
 * @author thrz
 * @email thrz@sz000673.com
 * @date 2021-01-28 17:21:21
 */
@Mapper
public interface XfjgcdxxDao {

	XfjgcdxxDO get(String xfjgCddm);
	
	List<XfjgcdxxDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(XfjgcdxxDO xfjgcdxx);
	
	int update(XfjgcdxxDO xfjgcdxx);
	
	int remove(String XFJG_CDDM);
	
	int batchRemove(String[] xfjgCddms);


}
