package com.smart119.iot.service;

import com.smart119.common.utils.PageUtils;
import com.smart119.iot.domain.ControllerDO;

import java.util.Map;

/**
 * 中控器
 *
 * @author zhangshunhua
 * @email zhangshunhua@sz000673.com
 * @date 2021-03-23 16:52:25
 */
public interface ControllerService {


    PageUtils queryPage(Map<String, Object> params);

    ControllerDO queryById(String id);

    int save(ControllerDO controllerDO);

    int update(ControllerDO controllerDO);


    int remove(String id);

    int batchRemove(String[] ids);
}
