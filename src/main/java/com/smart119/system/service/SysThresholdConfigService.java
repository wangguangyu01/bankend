package com.smart119.system.service;

import com.smart119.common.utils.PageUtils;
import com.smart119.system.domain.SysThresholdConfigDo;

import java.util.Map;

public interface SysThresholdConfigService {


    public final static String REDIS_CONFIG = "redis:config";


    PageUtils queryPage(Map<String, Object> params);


    /**
     * 根据id进行开启与关闭
     * @param id
     * @param cmd
     */
   boolean changeStatus(Long id, String cmd);


    /**
     * 根据主键id查询警情多报配置
     * @param id
     * @return
     */
   SysThresholdConfigDo queryById(Long id);


   boolean saveSysRepeatConfig(SysThresholdConfigDo sysRepeatConfigDo);


   boolean updateSysRepeatConfig(SysThresholdConfigDo sysRepeatConfigDo);




    boolean removeRepeatConfigBatch(String[] ids);


    boolean removeRepeatConfigById(Long id);

}
