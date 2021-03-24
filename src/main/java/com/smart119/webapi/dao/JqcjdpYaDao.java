package com.smart119.webapi.dao;

import com.smart119.webapi.domain.JqcjdpYaDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 警情处警调派-应急预案
 * @author yanyu
 * @email yanyu@sz000673.com
 * @date 2021-01-30 12:42:04
 */

public interface JqcjdpYaDao {

	JqcjdpYaDO get(String jqcjdpYaId);
	
	List<JqcjdpYaDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(JqcjdpYaDO jqcjdpYa);
	
	int update(JqcjdpYaDO jqcjdpYa);
	
	int remove(String JQCJDP_YA_ID);
	
	int batchRemove(String[] jqcjdpYaIds);
}
