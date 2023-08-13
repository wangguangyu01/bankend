package com.smart119.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart119.common.utils.PageMybatisPlusUtils;
import com.smart119.system.dao.SysConfigDao;
import com.smart119.system.domain.SysConfig;
import com.smart119.system.service.SysConfigService;
import com.smart119.wxuser.domain.WxUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SysConfigServiceImpl implements SysConfigService {

    @Autowired
    private SysConfigDao sysConfigDao;

    @Override
    public IPage<SysConfig> queryListPage(Map<String, Object> params) {
        Page<SysConfig> page = new Page();
        PageMybatisPlusUtils.pageHelperUtils(params, page);
        LambdaQueryWrapper<SysConfig> wxUserLambdaQueryWrapper = new LambdaQueryWrapper<>();

        IPage<SysConfig> sysConfigIPage = sysConfigDao.selectPage(page, wxUserLambdaQueryWrapper);

        return sysConfigIPage;
    }

    @Override
    public boolean addSysConfig(SysConfig sysConfig) {
       int count = sysConfigDao.insert(sysConfig);
       if (count > 0) {
           return true;
       } else {
           return false;
       }
    }

    @Override
    public SysConfig queryById(long toLong) {
        return sysConfigDao.selectById(toLong);
    }

    @Override
    public boolean updateSysConfig(SysConfig sysConfig) {
        int count = sysConfigDao.updateById(sysConfig);
        if (count > 0 || count == 0) {
            return true;
        }
        return false;
    }
}
