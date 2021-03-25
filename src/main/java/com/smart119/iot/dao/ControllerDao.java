package com.smart119.iot.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart119.iot.domain.ControllerDO;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.Map;



/**
 * 中控器
 * @author zhangshunhua
 * @email zhangshunhua@sz000673.com
 * @date 2021-03-23 16:52:25
 */
public interface ControllerDao extends BaseMapper<ControllerDO> {

    IPage<ControllerDO> selectPageVo(@Param("page") Page<ControllerDO> page, @Param("params") Map<String, Object> params);

    @Override
    ControllerDO selectById(Serializable id);
}
