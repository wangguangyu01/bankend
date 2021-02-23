package com.smart119.jczy.service;

import com.smart119.jczy.domain.JjbDO;
import com.smart119.system.domain.UserDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author Jiyu Yang
 * @email yangjiyu@sz000673.com
 * @date 2021-02-09 09:36:49
 */
public interface JjbService {
	
	JjbDO get(String jjbTybsbm);
	
	List<JjbDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(JjbDO jjb);
	
	int update(JjbDO jjb);
	
	int remove(String jjbTybsbm);
	
	int batchRemove(String[] jjbTybsbms);

	Map<String,Object>selCurrentInfo();

	JjbDO getByUserId();

	List<UserDO>selMembersByUserId();
}
