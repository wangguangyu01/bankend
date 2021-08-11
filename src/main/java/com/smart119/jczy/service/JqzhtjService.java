package com.smart119.jczy.service;

import com.smart119.jczy.domain.JqzhtjDO;
import com.smart119.webapi.domain.JbxxDO;

import java.util.List;
import java.util.Map;

/**
 * 警情基本信息
 * 
 * @author scy
 * @email shichengyuan@sz000673.com
 * @date 2021-08-02 09:12:43
 */
public interface JqzhtjService {


	List<JbxxDO> list(Map<String, Object> params);


	JqzhtjDO getExcel(Map<String, Object> params);


}
