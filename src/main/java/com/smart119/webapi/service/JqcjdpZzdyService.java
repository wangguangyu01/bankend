package com.smart119.webapi.service;

import com.smart119.webapi.domain.JqcjdpZzdyDO;

import java.util.List;
import java.util.Map;

/**
 * 警情处警调派-作战单元
 *
 * @author yanyu
 * @email yanyu@sz000673.com
 * @date 2021-01-30 12:35:37
 */
public interface JqcjdpZzdyService {

	JqcjdpZzdyDO get(String jqcjdpZzdyId);

	List<JqcjdpZzdyDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(JqcjdpZzdyDO jqcjdpZzdy);

	int update(JqcjdpZzdyDO jqcjdpZzdy);

	int remove(String jqcjdpZzdyId);

	int batchRemove(String[] jqcjdpZzdyIds);
}
