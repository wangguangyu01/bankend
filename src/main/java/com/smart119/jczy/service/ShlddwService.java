package com.smart119.jczy.service;

import com.smart119.jczy.domain.ShlddwDO;

import java.util.List;
import java.util.Map;

/**
 * 社会联动单位管理
 * 
 * @author shilei
 * @email yangjiyu@sz000673.com
 * @date 2021-03-20 14:24:12
 */
public interface ShlddwService {
	
	ShlddwDO get(String shlddwTywysbm);
	
	List<ShlddwDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ShlddwDO shlddw);
	
	int update(ShlddwDO shlddw);
	
	int remove(String shlddwTywysbm);
	
	int batchRemove(String[] shlddwTywysbms);
}
