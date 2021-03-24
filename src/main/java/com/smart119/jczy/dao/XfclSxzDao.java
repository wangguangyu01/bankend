package com.smart119.jczy.dao;

import com.smart119.jczy.domain.XfclSxzDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 消防车辆属性值
 * @author thrz
 * @email thrz@sz000673.com
 * @date 2021-01-25 17:39:18
 */

public interface XfclSxzDao {

	XfclSxzDO get(String id);
	
	List<XfclSxzDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(XfclSxzDO xfclSxz);
	
	int update(XfclSxzDO xfclSxz);
	
	int remove(String id);
	
	int batchRemove(String[] ids);

	List<XfclSxzDO> getSxzlist();
}
