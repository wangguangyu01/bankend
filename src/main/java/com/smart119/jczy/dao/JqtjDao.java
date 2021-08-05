package com.smart119.jczy.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smart119.webapi.domain.JbxxDO;

import java.util.List;
import java.util.Map;

/**
 * 警情基本信息
 * @author scy
 * @email shichengyuan@sz000673.com
 * @date 2021-08-02 09:12:43
 */
public interface JqtjDao extends BaseMapper<JbxxDO> {

    List<JbxxDO> list(Map<String, Object> params);
    List<Map<String,Object>> getListType(Map<String, Object> params);
    Map<String,Object> getBJcout(Map<String, Object> params);
    Map<String,Object> getBJcout2(Map<String, Object> params);
    Map<String,Object> getCdcout(Map<String, Object> params);
    List<Map<String,Object>> hzpuList(Map<String, Object> params);
}
