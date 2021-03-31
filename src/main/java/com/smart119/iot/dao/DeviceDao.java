package com.smart119.iot.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart119.iot.domain.ControllerDO;
import com.smart119.iot.domain.DeviceDO;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.Map;



/**
 * 物联设备
 * @author zhangshunhua
 * @email zhangshunhua@sz000673.com
 * @date 2021-03-23 16:52:25
 */
public interface DeviceDao extends BaseMapper<DeviceDO> {

    IPage<DeviceDO> selectPageVo(@Param("page") Page<DeviceDO> page, @Param("params") Map<String, Object> params);
    @Override
    DeviceDO selectById(Serializable id);
}
