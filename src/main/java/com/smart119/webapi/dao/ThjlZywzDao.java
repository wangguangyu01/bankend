package com.smart119.webapi.dao;

import com.smart119.webapi.domain.ThjlZywzDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 通话记录_转义文字
 * @author shilei
 * @email thrz@sz000673.com
 * @date 2021-02-03 11:58:35
 */
@Mapper
public interface ThjlZywzDao {

	ThjlZywzDO get(String id);
	
	List<ThjlZywzDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ThjlZywzDO thjlZywz);
	
	int update(ThjlZywzDO thjlZywz);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
