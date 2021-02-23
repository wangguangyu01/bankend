package com.smart119.jczy.service;

import com.smart119.jczy.domain.ZbdtDO;

import java.util.List;
import java.util.Map;

/**
 * 值班动态基本信息
 * 
 * @author zhangxj
 * @email thrz@sz000673.com
 * @date 2021-01-18 16:02:23
 */
public interface ZbdtService {
	
	ZbdtDO get(String zhbTywysbm);
	
	List<ZbdtDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ZbdtDO zbdt);
	
	int update(ZbdtDO zbdt);
	
	int remove(String zhbTywysbm);
	
	int batchRemove(String[] zhbTywysbms);

	//	根据用户id查出消防救援机构_通用唯一识别码
	Map<String,Object> xfjyjgTywysbmByUserId(String userId);
}
