package com.smart119.webapi.service;

import com.alibaba.fastjson.JSONObject;
import com.smart119.webapi.dao.XfjgcdxxDao;
import com.smart119.webapi.domain.JqcjdpDO;
import com.smart119.webapi.domain.XfjgcdxxDO;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * 警情处警调派基本信息
 *
 * @author yanyu
 * @email yanyu@sz000673.com
 * @date 2021-01-28 14:05:38
 */
public interface JqcjdpService {

	JqcjdpDO get(String jqcjdpTywysbm);

	List<JqcjdpDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(JqcjdpDO jqcjdp);

	int update(JqcjdpDO jqcjdp);

	int remove(String jqcjdpTywysbm);

	int batchRemove(String[] jqcjdpTywysbms);

	Map strenghTransfer(JSONObject params) throws ParseException;

	List<XfjgcdxxDO> findXfjgcdByJqBm(Map<String,Object> map);


}
