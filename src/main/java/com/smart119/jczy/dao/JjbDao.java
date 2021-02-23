package com.smart119.jczy.dao;

import com.smart119.jczy.domain.JjbDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author Jiyu Yang
 * @email yangjiyu@sz000673.com
 * @date 2021-02-09 09:36:49
 */
@Mapper
public interface JjbDao {

	JjbDO get(String jjbTybsbm);
	
	List<JjbDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(JjbDO jjb);
	
	int update(JjbDO jjb);
	
	int remove(String jjb_tybsbm);
	
	int batchRemove(String[] jjbTybsbms);

	JjbDO getByUserId(String jiebryid);
}
