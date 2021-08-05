package com.smart119.jczy.service;

import com.smart119.jczy.domain.QyjqtjDO;
import com.smart119.webapi.domain.JbxxDO;

import java.util.List;
import java.util.Map;

/**
 * 行政区域警情基本信息
 * 
 * @author scy
 * @email shichengyuan@sz000673.com
 * @date 2021-08-02 09:12:43
 */
public interface QyjqtjService {

	List<JbxxDO> qyjqtjList(Map<String, Object> params);

	List<QyjqtjDO> qyjqtjExcel(Map<String, Object> params);
}
