package com.smart119.webapi.service;

import com.smart119.webapi.domain.JjyyDO;
import com.smart119.webapi.domain.ZhjqDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author scy
 * @email mrtidus@163.com
 * @date 2021-01-29 11:02:36
 */
public interface ZhjqService {
	
	ZhjqDO get(String id);
	
	List<ZhjqDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ZhjqDO zhjq);
	
	int update(ZhjqDO zhjq);
	
	int remove(String id);
	
	int batchRemove(String[] ids);

	List<ZhjqDO> getZhjqList(String lylb,String jqzt,String ms);

	int getZhjqSize(String lylb,String jqzt,String ms);

	List<ZhjqDO> getZhjqxqList(String id);

}
