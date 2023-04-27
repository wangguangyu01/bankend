package com.smart119.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.smart119.system.domain.TSerialNumber;


public interface TSerialNumberService extends IService<TSerialNumber>{


    /**
     * 创建编号
     * @return
     */
    public String createSerialNumber();
}
