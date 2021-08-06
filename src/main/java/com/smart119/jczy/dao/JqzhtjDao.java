package com.smart119.jczy.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smart119.jczy.domain.JqtjDO;
import com.smart119.jczy.domain.JqzhtjDO;
import com.smart119.webapi.domain.JbxxDO;

import java.util.List;
import java.util.Map;

/**
 * 警情基本信息
 * @author scy
 * @email shichengyuan@sz000673.com
 * @date 2021-08-02 09:12:43
 */
public interface JqzhtjDao extends BaseMapper<JbxxDO> {

    List<JbxxDO> list(Map<String, Object> params);

    JqzhtjDO getExcel(Map<String, Object> params);

}
