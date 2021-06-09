package com.smart119.jczy.service;

import com.smart119.jczy.domain.KbmbDO;

import java.util.List;
import java.util.Map;

/**
 * 快报模板
 * 
 * @author shilei
 * @email shichengyuan@sz000673.com
 * @date 2021-06-03 14:39:20
 */
public interface KbmbService {
	
	KbmbDO get(String kbmbId);
	
	List<KbmbDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(KbmbDO kbmb);
	
	int update(KbmbDO kbmb);
	
	int remove(String kbmbId);
	
	int batchRemove(String[] kbmbIds);
}
