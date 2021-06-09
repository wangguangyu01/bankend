package com.smart119.jczy.dao;

import com.smart119.jczy.domain.XfclDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 消防车辆基本信息
 * @author liangsl
 * @email 1992lcg@163.com
 * @date 2021-01-15 11:28:42
 */

public interface XfclDao {

	XfclDO get(String xfclTywysbm);

	Map<String,Object> getMap(String id);
	
	List<XfclDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(XfclDO xfcl);
	
	int update(XfclDO xfcl);
	
	int remove(String XFCL_TYWYSBM);
	
	int batchRemove(String[] xfclTywysbms);

    int carlistcount(Map<String, Object> map);

	List<XfclDO> carlist(Map<String, Object> map);

	List<String> findAllXfclTywysbm();
}
