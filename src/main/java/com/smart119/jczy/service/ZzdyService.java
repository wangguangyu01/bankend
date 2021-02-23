package com.smart119.jczy.service;

import com.alibaba.fastjson.JSONArray;
import com.smart119.jczy.domain.ZzdyDO;
import com.smart119.system.domain.DeptDO;


import java.util.List;
import java.util.Map;

/**
 * 作战单元
 * 
 * @author shilei
 * @email thrz@sz000673.com
 * @date 2021-01-29 20:42:51
 */
public interface ZzdyService {
	
	ZzdyDO get(String zzdyTywybs);
	
	List<ZzdyDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ZzdyDO zzdy);
	
	int update(ZzdyDO zzdy);
	
	int remove(String zzdyTywybs);
	
	int batchRemove(String[] zzdyTywybss);

	List<DeptDO> zzdyList(Map<String,Object> map);

	DeptDO deptByXfjyjgTywysbm(Map<String, Object> params);

}
