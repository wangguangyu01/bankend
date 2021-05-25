package com.smart119.jczy.service;

import com.smart119.jczy.domain.XfclDO;

import java.util.List;
import java.util.Map;

/**
 * 消防车辆基本信息
 * 
 * @author liangsl
 * @email 1992lcg@163.com
 * @date 2021-01-15 11:28:42
 */
public interface XfclService {
	
	XfclDO get(String xfclTywysbm);

	Map<String,Object> getMap(String id);
	
	List<XfclDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(XfclDO xfcl);
	
	int update(XfclDO xfcl);
	
	int remove(String xfclTywysbm);
	
	int batchRemove(String[] xfclTywysbms);

    List<XfclDO> carlist(Map<String, Object> map);

	int carlistcount(Map<String, Object> map);
}
