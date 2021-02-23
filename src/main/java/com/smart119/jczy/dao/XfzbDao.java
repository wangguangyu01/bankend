package com.smart119.jczy.dao;

import com.smart119.jczy.domain.XfzbDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 消防装备器材基本信息

 * @author liangsl
 * @email 1992lcg@163.com
 * @date 2021-01-18 15:16:57
 */
@Mapper
public interface XfzbDao {

	XfzbDO get(String xfzbTywysbm);
	
	List<XfzbDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(XfzbDO xfzb);
	
	int update(XfzbDO xfzb);
	
	int remove(String XFZB_TYWYSBM);
	
	int batchRemove(String[] xfzbTywysbms);
}
