package com.smart119.jczy.dao;

import com.smart119.jczy.domain.YjlddwDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 应急联动单位
 * @author liangsl
 * @email 1992lcg@163.com
 * @date 2021-01-14 16:04:33
 */
@Mapper
public interface YjlddwDao {

	YjlddwDO get(String yjlddwTywysbm);
	
	List<YjlddwDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(YjlddwDO yjlddw);
	
	int update(YjlddwDO yjlddw);
	
	int remove(String YJLDDW_TYWYSBM);
	
	int batchRemove(String[] yjlddwTywysbms);
}
