package com.smart119.jczy.dao;

import com.smart119.jczy.domain.WxhxpHzDO;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart119.xwzx.domain.XwZxDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;



/**
 * 
 * @author shichengyuan
 * @email shichengyuan@sz000673.com
 * @date 2021-09-15 08:15:15
 */
public interface WxhxpHzDao extends BaseMapper<WxhxpHzDO> {

    IPage<WxhxpHzDO> selectPageVo(@Param("page") Page<WxhxpHzDO> page, @Param("params") Map<String, Object> params);

    List<WxhxpHzDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(WxhxpHzDO wxhxpHzDO);

    int update(WxhxpHzDO wxhxpHzDO);

    int remove(String id);

    int batchRemove(String[] ids);

    WxhxpHzDO get(String id);
}
