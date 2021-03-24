package com.smart119.webapi.dao;

import com.smart119.webapi.domain.ThjlTqdzDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 通话记录_提取地址信息
 * @author shilei
 * @email thrz@sz000673.com
 * @date 2021-02-03 11:58:35
 */

public interface ThjlTqdzDao {

	ThjlTqdzDO get(String id);
	
	List<ThjlTqdzDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ThjlTqdzDO thjlTqdz);
	
	int update(ThjlTqdzDO thjlTqdz);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
