package com.smart119.jczy.dao;

import com.smart119.jczy.domain.ZdbwDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 重点部位基本信息
 * @author thrz
 * @email thrz@sz000673.com
 * @date 2021-01-20 15:50:30
 */

public interface ZdbwDao {

	ZdbwDO get(String zdbwTywysbm);
	
	List<ZdbwDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ZdbwDO zdbw);
	
	int update(ZdbwDO zdbw);
	
	int remove(String String);
	
	int batchRemove(String[] Strings);
}
