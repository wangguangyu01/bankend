package com.smart119.webapi.dao;

import com.smart119.webapi.domain.JqcjdpZzdyDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 警情处警调派-作战单元
 * @author yanyu
 * @email yanyu@sz000673.com
 * @date 2021-01-30 12:35:37
 */
@Mapper
public interface JqcjdpZzdyDao {

	JqcjdpZzdyDO get(String jqcjdpZzdyId);

	List<JqcjdpZzdyDO> list(Map<String,Object> map);

	int count(Map<String,Object> map);

	int save(JqcjdpZzdyDO jqcjdpZzdy);

	int update(JqcjdpZzdyDO jqcjdpZzdy);

	int remove(String JQCJDP_ZZDY_ID);

	int batchRemove(String[] jqcjdpZzdyIds);
}
