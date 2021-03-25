package com.smart119.iot.service;

import com.smart119.common.utils.PageUtils;
import com.smart119.iot.domain.ControllerPortDO;

import java.util.Map;

/**
 * 中控器端口
 * 
 * @author zhangshunhua
 * @email zhangshunhua@sz000673.com
 * @date 2021-03-23 16:52:25
 */
public interface ControllerPortService {


	PageUtils queryPage(Map<String, Object> params);

	ControllerPortDO queryById(String id);

	int save(ControllerPortDO controllerPortDO);

	int update(ControllerPortDO controllerPortDO);


	int remove(String id);

	int batchRemove(String[] ids);
}
