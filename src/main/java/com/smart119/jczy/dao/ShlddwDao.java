package com.smart119.jczy.dao;

import com.smart119.jczy.domain.ShlddwDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 社会联动单位管理
 * @author shilei
 * @email yangjiyu@sz000673.com
 * @date 2021-03-20 14:24:12
 */

public interface ShlddwDao {

	ShlddwDO get(String shlddwTywysbm);
	
	List<ShlddwDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ShlddwDO shlddw);
	
	int update(ShlddwDO shlddw);
	
	int remove(String SHLDDW_TYWYSBM);
	
	int batchRemove(String[] shlddwTywysbms);
}
