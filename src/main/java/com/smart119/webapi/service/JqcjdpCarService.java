package com.smart119.webapi.service;

import com.smart119.webapi.domain.JqcjdpCarDO;

import java.util.List;
import java.util.Map;

/**
 * 警情处警调派车辆表
 * 
 * @author yanyu
 * @email yanyu@sz000673.com
 * @date 2021-01-28 14:06:56
 */
public interface JqcjdpCarService {
	
	JqcjdpCarDO get(String dpclTywysbm);
	
	List<JqcjdpCarDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(JqcjdpCarDO jqcjdpCar);
	
	int update(JqcjdpCarDO jqcjdpCar);
	
	int remove(String dpclTywysbm);
	
	int batchRemove(String[] dpclTywysbms);

	List<Map<String,Object>> dzclCount(String jqTywysbm);
}
