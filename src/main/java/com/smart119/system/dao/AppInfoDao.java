package com.smart119.system.dao;

import com.smart119.system.domain.AppInfoDO;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.Map;



/**
 * 
 * @author zhangshunhua
 * @email zhangshunhua@sz000673.com
 * @date 2021-03-22 16:30:18
 */
public interface AppInfoDao extends BaseMapper<AppInfoDO> {

    IPage<AppInfoDO> selectPageVo(@Param("page") Page<AppInfoDO> page,
        @Param("params") Map<String, Object> params);
}
