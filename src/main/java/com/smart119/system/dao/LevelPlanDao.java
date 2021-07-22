package com.smart119.system.dao;

import com.smart119.system.domain.LevelPlanDO;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;



/**
 * 
 * @author zhangshunhua
 * @email zhangshunhua@sz000673.com
 * @date 2021-07-21 10:59:21
 */
public interface LevelPlanDao extends BaseMapper<LevelPlanDO> {

    IPage<LevelPlanDO> selectPageVo(@Param("page") Page<LevelPlanDO> page, @Param("params") Map<String, Object> params);
}
