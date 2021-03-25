package com.smart119.iot.service;

import com.smart119.common.utils.PageUtils;
import com.smart119.iot.domain.DeviceDO;

import java.util.Map;

/**
 * 物联设备
 * 
 * @author zhangshunhua
 * @email zhangshunhua@sz000673.com
 * @date 2021-03-23 16:52:25
 */
public interface DeviceService {


	PageUtils queryPage(Map<String, Object> params);

	DeviceDO queryById(String id);

	int save(DeviceDO deviceDO);

	int update(DeviceDO deviceDO);


	int remove(String id);

	int batchRemove(String[] ids);
}
