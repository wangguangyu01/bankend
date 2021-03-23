package com.smart119.jczy.service;

import com.smart119.jczy.domain.XfjyryDO;


import java.util.List;
import java.util.Map;

/**
 * 消防救援人员
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2021-01-15 10:53:18
 */
public interface XfjyryService {
	
	XfjyryDO get(String xfjyryTywysbm);
	
	List<XfjyryDO> list(Map<String, Object> map);

	List<XfjyryDO> listOther(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(XfjyryDO xfjyry);
	
	int update(XfjyryDO xfjyry);
	
	int remove(String xfjyryTywysbm);
	
	int batchRemove(String[] xfjyryTywysbms);
}
