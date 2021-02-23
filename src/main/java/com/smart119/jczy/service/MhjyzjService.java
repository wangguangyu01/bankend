package com.smart119.jczy.service;

import com.smart119.jczy.domain.MhjyzjDO;


import java.util.List;
import java.util.Map;

/**
 * 灭火救援专家基本信息
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2021-01-18 17:41:47
 */
public interface MhjyzjService {
	
	MhjyzjDO get(String mhjyzjTywysbm);
	
	List<MhjyzjDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(MhjyzjDO mhjyzj);
	
	int update(MhjyzjDO mhjyzj);
	
	int remove(String mhjyzjTywysbm);
	
	int batchRemove(String[] mhjyzjTywysbms);

	List<Map<String,Object>> zjldList();
}
