package com.smart119.common.dao;

import com.smart119.common.domain.DictDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 字典表
 *
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-10-03 15:45:42
 */

public interface DictDao {

	DictDO get(Long id);

	List<DictDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(DictDO dict);

	int update(DictDO dict);

	int remove(Long id);

	int batchRemove(Long[] ids);

	List<DictDO> listType();

	List<DictDO> listParentId(Long parentId);

	List<DictDO> getChild(Map<String, Object> map);

	List<DictDO> listByParentId(Long id);

	List<DictDO> listByParentType(String type);

	List<DictDO> listByParentValue(String value);

	List<DictDO> getSelectByXfjbType(String type);

	String findParentValue(String value);

	List<DictDO> getDictListByType(String type);


	String findDictName(Map<String, Object> map);

	String findDictType(Map<String, Object> map);

	String getDictListClzByType(String type);

	String getDictListWclByType(String type);

	String getDictListYjaByType(String type);

	List<DictDO> findDictByTypeVal(Map<String, Object> map);

	/**
	 * 只根据数据字典的type查询数据字典，将其中value为空的去掉
	 * @return
	 */
	List<DictDO> queryByDictType(String type);

}
