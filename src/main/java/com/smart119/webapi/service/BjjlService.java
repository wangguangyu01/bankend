package com.smart119.webapi.service;

import com.smart119.webapi.domain.BjjlDO;

import java.util.List;
import java.util.Map;

/**
 * 报警记录基本信息
 *
 * @author yanyu
 * @email yanyu@sz000673.com
 * @date 2021-01-28 12:55:45
 */
public interface BjjlService {

	BjjlDO get(String bjTywysbm);

	List<BjjlDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(BjjlDO bjjl);

	int update(BjjlDO bjjl);

	int remove(String bjTywysbm);

	int batchRemove(String[] bjTywysbms);

	List<BjjlDO> bjQuery(Map<String, Object> map);

	int bjcount(Map<String, Object> map);

}
