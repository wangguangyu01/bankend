package com.smart119.webapi.service;

import com.alibaba.fastjson.JSONObject;
import com.smart119.webapi.domain.*;

import java.util.List;
import java.util.Map;

/**
 * 警情基本信息
 *
 * @author yanyu
 * @email yanyu@sz000673.com
 * @date 2021-01-28 11:48:41
 */
public interface JbxxService {

	JbxxDO get(String jqTywysbm);

	List<JbxxDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(JSONObject params);

	int update(JbxxDO jbxx);

	int remove(String jqTywysbm);

	int batchRemove(String[] jqTywysbms);

	Map jqcase(JbxxDO jbxxDO);

	List<WebjbxxDO> jqlxTypeList(String xfjyjgTywysbm, String state);

	List<JbxxDO> dzjqlist(Map<String, Object> map);

	int dzjqcount(Map<String, Object> map);

    Map<String,Object> getJqAll(String jqTywysbm);

	JbxxDO getJqxxByJqTywysbm(String jqTywysbm);

	Map getDzJqInfo(String jqTywysbm);

	String getBt(String xfjyjgTywysbm);

	List<Map<String,Object>> getBjjlByJqid(String xfjyjgTywysbm);

	int updateja(String jqTywysbm);
}
