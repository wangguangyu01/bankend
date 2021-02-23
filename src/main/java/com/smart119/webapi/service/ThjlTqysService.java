package com.smart119.webapi.service;

import com.smart119.webapi.domain.ThjlTqysDO;

import java.util.List;
import java.util.Map;

/**
 * 通话记录_提取要素信息
 * 
 * @author shilei
 * @email thrz@sz000673.com
 * @date 2021-02-03 11:58:35
 */
public interface ThjlTqysService {
	
	ThjlTqysDO get(String id);
	
	List<ThjlTqysDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ThjlTqysDO thjlTqys);
	
	int update(ThjlTqysDO thjlTqys);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
