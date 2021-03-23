package com.smart119.xwzx.dao;

import com.smart119.xwzx.domain.XwZxDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 新闻资讯
 * @author liangsl
 * @email liangshengli@sz000673.com
 * @date 2021-03-23 16:15:12
 */
@Mapper
public interface XwZxDao {

	XwZxDO get(String xwZxId);
	
	List<XwZxDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(XwZxDO xwZx);
	
	int update(XwZxDO xwZx);
	
	int remove(String XW_ZX_ID);
	
	int batchRemove(String[] xwZxIds);
}
