package com.smart119.webapi.dao;

import com.smart119.webapi.domain.JjyyDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author scy
 * @email mrtidus@163.com
 * @date 2021-01-28 13:59:02
 */

public interface JjyyDao {

	JjyyDO get(String id);
	
	List<JjyyDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(JjyyDO jjyy);
	
	int update(JjyyDO jjyy);
	
	int remove(String id);
	
	int batchRemove(String[] ids);

	List<JjyyDO> listlanguageType();

	int size();

	List<JjyyDO> listlanguage(String type);

	int languageSize(String type);
}
