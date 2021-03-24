package com.smart119.jqxx.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart119.common.utils.PageMybatisPlusUtils;
import com.smart119.jqxx.dao.TaskManageDao;
import com.smart119.jqxx.service.TaskManageService;
import com.smart119.jqxx.vo.JqcjdpDzVo;
import com.smart119.system.domain.AppInfoDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TaskManageServiceImpl implements TaskManageService {

    @Autowired
    private TaskManageDao taskManageDao;

    @Override
    public IPage<JqcjdpDzVo> queryPage(Map<String, Object> params) {
        Page<JqcjdpDzVo> page = new Page<>();
        PageMybatisPlusUtils.pageHelperUtils(params,page);
        IPage<JqcjdpDzVo> result = taskManageDao.selectPageVo(page, params);
        return result;
    }

    @Override
    public JqcjdpDzVo getByDpdzTywysbm(String dpdzTywysbm) {
        return taskManageDao.getByDpdzTywysbm(dpdzTywysbm);
    }


}
