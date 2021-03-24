package com.smart119.jczy.dao;

import com.smart119.jczy.domain.ZhdjDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 灾害等级
 * @author Jiyu Yang
 * @email yangjiyu@sz000673.com
 * @date 2021-02-08 16:13:14
 */

public interface ZhdjDao {

	ZhdjDO get(String id);
	
	List<ZhdjDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ZhdjDO zhdj);
	
	int update(ZhdjDO zhdj);
	
	int remove(String id);
	
	int batchRemove(String[] ids);

	ZhdjDO findZhdj(Map<String,Object> map);
}
