package com.smart119.jqxx.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart119.jqxx.vo.JqcjdpDzVo;

import java.util.Map;

public interface TaskManageService {

    /**
     * 分页查询
     * @param params
     * @return
     */
    IPage<JqcjdpDzVo> queryPage(Map<String, Object> params);

    JqcjdpDzVo getByDpdzTywysbm(String dpdzTywysbm);
}
