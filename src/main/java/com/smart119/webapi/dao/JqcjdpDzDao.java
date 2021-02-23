package com.smart119.webapi.dao;

import com.smart119.webapi.domain.JqcjdpDzDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 警情处警调派队站表
 * @author yanyu
 * @email yanyu@sz000673.com
 * @date 2021-01-28 14:08:03
 */
@Mapper
public interface JqcjdpDzDao {

	JqcjdpDzDO get(String dpdzTywysbm);

	List<JqcjdpDzDO> list(Map<String,Object> map);

	int count(Map<String,Object> map);

	int save(JqcjdpDzDO jqcjdpDz);

	int update(JqcjdpDzDO jqcjdpDz);

	int remove(String DPDZ_TYWYSBM);

	int batchRemove(String[] dpdzTywysbms);

	String getDzNameGroup(@Param("dzid") String dzid);

	List<Map<String,Object>>  getDzCarListByJdId(@Param("value") String value);

	List<Map<String,Object>>  getDzListByDzId(@Param("value") String value);
}
