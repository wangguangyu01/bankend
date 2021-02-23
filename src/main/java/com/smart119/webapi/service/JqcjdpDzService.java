package com.smart119.webapi.service;

import com.smart119.webapi.domain.JqcjdpDzDO;

import java.util.List;
import java.util.Map;

/**
 * 警情处警调派队站表
 *
 * @author yanyu
 * @email yanyu@sz000673.com
 * @date 2021-01-28 14:08:03
 */
public interface JqcjdpDzService {

	JqcjdpDzDO get(String dpdzTywysbm);

	List<JqcjdpDzDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(JqcjdpDzDO jqcjdpDz);

	int update(JqcjdpDzDO jqcjdpDz);

	int remove(String dpdzTywysbm);

	int batchRemove(String[] dpdzTywysbms);

	List<Map<String,Object>>  getDzCarListByJdId(String JQ_TYWYSBM);
}
