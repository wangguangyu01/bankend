package com.smart119.jczy.dao;

import com.smart119.jczy.domain.YjyaClDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 应急预案-车辆类型表
 * @author liangsl
 * @email 123564081@qq.com
 * @date 2021-01-29 16:04:24
 */

public interface YjyaClDao {

	YjyaClDO get(String yjyaClId);
	
	List<YjyaClDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(YjyaClDO yjyaCl);
	
	int update(YjyaClDO yjyaCl);
	
	int remove(String YJYA_CL_ID);
	
	int batchRemove(String[] yjyaClIds);
}
