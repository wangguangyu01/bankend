package com.smart119.iot.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart119.iot.domain.ControllerDO;
import com.smart119.iot.domain.ControllerPortDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;



/**
 * 中控器端口
 * @author zhangshunhua
 * @email zhangshunhua@sz000673.com
 * @date 2021-03-23 16:52:25
 */
public interface ControllerPortDao extends BaseMapper<ControllerPortDO> {

    IPage<ControllerPortDO> selectPageVo(@Param("page") Page<ControllerPortDO> page, @Param("params") Map<String, Object> params);
    List<ControllerPortDO> list(@Param("params") Map<String, Object> params);
}
