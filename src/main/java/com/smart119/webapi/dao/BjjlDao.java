package com.smart119.webapi.dao;

import com.smart119.webapi.domain.BjjlDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 报警记录基本信息
 * @author yanyu
 * @email yanyu@sz000673.com
 * @date 2021-01-28 12:55:45
 */

public interface BjjlDao {

	BjjlDO get(String bjTywysbm);

	List<BjjlDO> list(Map<String,Object> map);

	int count(Map<String,Object> map);

	int save(BjjlDO bjjl);

	int update(BjjlDO bjjl);

	int remove(String BJ_TYWYSBM);

	int batchRemove(String[] bjTywysbms);

	List<BjjlDO> bjQuery(Map<String,Object> map);

	int bjcount(Map<String,Object> map);

	int countByDate();
}
