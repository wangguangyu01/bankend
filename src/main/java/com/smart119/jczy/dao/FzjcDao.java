package com.smart119.jczy.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart119.jczy.domain.FzjcDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 辅助决策
 * @author wangguangyu
 * @email wangguangyu@sz000673.com
 * @date 2021-02-02 10:22:35
 */
public interface FzjcDao extends BaseMapper<FzjcDO> {

	FzjcDO get(String fzjcId);
	
	List<FzjcDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(FzjcDO fzjc);
	
//	int update(FzjcDO fzjc);
	
	int remove(String FZJC_ID);
	
	int batchRemove(String[] fzjcIds);

	IPage<FzjcDO> selectPageVo(@Param("page") Page<FzjcDO> page, @Param("params") Map<String, Object> params);
}
