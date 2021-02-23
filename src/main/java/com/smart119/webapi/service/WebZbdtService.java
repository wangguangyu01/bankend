package com.smart119.webapi.service;

import com.smart119.webapi.domain.ZbdtDO;

import java.util.List;
import java.util.Map;

/**
 * 值班动态基本信息
 *
 * @author scy
 * @email mrtidus@163.com
 * @date 2021-01-28 11:48:41
 */
public interface WebZbdtService {
	
	ZbdtDO get(String zhbTywysbm);
	
	List<ZbdtDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ZbdtDO zbdt);
	
	int update(ZbdtDO zbdt);
	
	int remove(String zhbTywysbm);
	
	int batchRemove(String[] zhbTywysbms);

	List<ZbdtDO> zbList(String xfjyjgTywysbm);

	int zbSize(String xfjyjgTywysbm);

}
