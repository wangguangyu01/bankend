package com.smart119.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart119.system.domain.SysRepeatConfigDo;
import org.apache.ibatis.annotations.Param;

import java.util.Map;



/**
 * 警情多报配置Dao
 *
 * @author wangguangyu
 * @email wangguangyu@sz000673.com
 * @date 2021-06-01
 */
public interface SysRepeatConfigDao extends BaseMapper<SysRepeatConfigDo> {



    IPage<SysRepeatConfigDo> selectPageVo(@Param("page") Page<SysRepeatConfigDo> page,
                                                 @Param("params") Map<String, Object> params);


    int changeStatus(@Param("id") Long id,
                      @Param("status") String status);




}
