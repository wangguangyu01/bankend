package com.smart119.jczy.dao;

import com.smart119.jczy.domain.FzjcDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 辅助决策
 * @author wangguangyu
 * @email wangguangyu@sz000673.com
 * @date 2021-02-02 10:22:35
 */
@Mapper
public interface FzjcDao {

	FzjcDO get(String fzjcId);
	
	List<FzjcDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(FzjcDO fzjc);
	
	int update(FzjcDO fzjc);
	
	int remove(String FZJC_ID);
	
	int batchRemove(String[] fzjcIds);
}
