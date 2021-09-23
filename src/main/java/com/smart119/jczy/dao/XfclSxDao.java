package com.smart119.jczy.dao;

import com.smart119.jczy.domain.XfclSxDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 消防车辆属性
 * @author thrz
 * @email thrz@sz000673.com
 * @date 2021-01-25 10:51:31
 */

public interface XfclSxDao {

	XfclSxDO get(String id);

	
	List<XfclSxDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(XfclSxDO xfclSx);
	
	int update(XfclSxDO xfclSx);
	
	int remove(String id);
	
	int batchRemove(String[] ids);

	List<XfclSxDO> findAttrByCllx(String cllx);

	List<Map<String, Object>> findAttrByCllxVal(String clid);

	List<Map<String, Object>> findAttrByZbVal(String clid);

	int removeByXfclId(String xfclId);

	int removeBySxId(String sxId);

	List<Map<String,Object>> findSxAllByCltywysbm(String xfclTywysbm);

	List<String> findCltywysbmBylx(String xfclTywysbm);
}
