package com.smart119.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart119.system.domain.SysConfig;
import com.smart119.wxuser.domain.WxUser;

import java.util.Map;

public interface SysConfigService {


    public IPage<SysConfig> queryListPage(Map<String, Object> params);


    /**
     * 添加参数
     * @param sysConfig
     */
    boolean  addSysConfig(SysConfig sysConfig);


    /**
     * @param toLong
     * @return
     */
    SysConfig queryById(long toLong);


    /**
     * 修改日志参数
     * @param sysConfig
     * @return
     */
    boolean updateSysConfig(SysConfig sysConfig);
}
