package com.smart119.jczy.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart119.webapi.domain.JbxxDO;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * 警情基本信息
 * @author scy
 * @email shichengyuan@sz000673.com
 * @date 2021-08-02 09:12:43
 */
public interface JqtjDao extends BaseMapper<JbxxDO> {

    IPage<JbxxDO> selectPageVo(@Param("page") Page<JbxxDO> page, @Param("params") Map<String, Object> params);
}
