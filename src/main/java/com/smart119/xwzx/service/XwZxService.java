package com.smart119.xwzx.service;

import com.smart119.xwzx.domain.XwZxDO;

import java.util.List;
import java.util.Map;

/**
 * 新闻资讯
 * 
 * @author liangsl
 * @email liangshengli@sz000673.com
 * @date 2021-03-23 16:15:12
 */
public interface XwZxService {
	
	XwZxDO get(String xwZxId);
	
	List<XwZxDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(XwZxDO xwZx);
	
	int update(XwZxDO xwZx);
	
	int remove(String xwZxId);
	
	int batchRemove(String[] xwZxIds);
}
