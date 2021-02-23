package com.smart119.jczy.service;

import com.smart119.jczy.domain.XfzbDO;

import java.util.List;
import java.util.Map;

/**
 * 消防装备器材基本信息

 * 
 * @author liangsl
 * @email 1992lcg@163.com
 * @date 2021-01-18 15:16:57
 */
public interface XfzbService {
	
	XfzbDO get(String xfzbTywysbm);
	
	List<XfzbDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(XfzbDO xfzb);
	
	int update(XfzbDO xfzb);
	
	int remove(String xfzbTywysbm);
	
	int batchRemove(String[] xfzbTywysbms);
}
