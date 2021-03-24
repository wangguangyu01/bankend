package com.smart119.jczy.dao;

import com.smart119.jczy.domain.SpjkDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 视频监控
 * @author thrz
 * @email thrz@sz000673.com
 * @date 2021-01-20 10:17:15
 */

public interface SpjkDao {

	SpjkDO get(String spjkTywysbm);
	
	List<SpjkDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(SpjkDO spjk);
	
	int update(SpjkDO spjk);
	
	int remove(String SPJK_TYWYSBM);
	
	int batchRemove(String[] spjkTywysbms);
}
