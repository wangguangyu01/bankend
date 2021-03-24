package com.smart119.common.service;

import com.smart119.common.domain.DictDO;
import com.smart119.system.domain.UserDO;

import java.util.List;
import java.util.Map;

/**
 * 字典表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-09-29 18:28:07
 */
public interface DictService {
	
	DictDO get(Long id);
	
	List<DictDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(DictDO dict);
	
	int update(DictDO dict);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	List<DictDO> listType();
	
	String getName(String type,String value);

	/**
	 * 获取爱好列表
	 * @return
     * @param userDO
	 */
	List<DictDO> getHobbyList(UserDO userDO);

	/**
	 * 获取性别列表
 	 * @return
	 */
	List<DictDO> getSexList();

	/**
	 * 根据type获取数据
	 * @param map
	 * @return
	 */
	List<DictDO> listByType(String type);

	int recursion_remove(Long id);

	List<DictDO> getChild(Map<String, Object> map);

	List<DictDO> listByParentId(Long id);

	List<DictDO> listByParentType(String type);

	List<DictDO> listByParentValue(String value);

	String findParentValue(String value);

	List<Map<String,Object>> getChildAll(String type);

	String findDictName(Map<String, Object> map);

	String findDictType(Map<String, Object> map);

	List<DictDO> findDictByTypeVal(Map<String, Object> map);

	List<DictDO> getSelectByXfjbType(String type);

	/**
	 * 根据type
	 * @param type
	 * @return
	 */
	List<DictDO> queryByDictType(String type);

}
