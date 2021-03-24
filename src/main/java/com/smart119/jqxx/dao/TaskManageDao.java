package com.smart119.jqxx.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart119.jqxx.vo.JqcjdpDzVo;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface TaskManageDao {

    IPage<JqcjdpDzVo> selectPageVo(@Param("page") Page<JqcjdpDzVo> page, @Param("params") Map<String, Object> params);

    JqcjdpDzVo getByDpdzTywysbm(@Param("dpdzTywysbm") String dpdzTywysbm);

}
