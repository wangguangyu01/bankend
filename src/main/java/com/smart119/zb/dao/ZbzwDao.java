package com.smart119.zb.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smart119.zb.domain.ZbzwDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 值班职务
 * @author liangsl
 * @email liangshengli@sz000673.com
 * @date 2021-05-08 19:01:02
 */
public interface ZbzwDao extends BaseMapper<ZbzwDO> {

	ZbzwDO get(String zbzwId);
	
	List<ZbzwDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ZbzwDO zbzw);
	
	int update(ZbzwDO zbzw);
	
	int remove(String ZBZW_ID);
	
	int batchRemove(String[] zbzwIds);
}
