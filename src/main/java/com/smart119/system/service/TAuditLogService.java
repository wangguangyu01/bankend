package com.smart119.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.smart119.common.utils.PageUtils;
import com.smart119.common.utils.R;
import com.smart119.system.domain.TAuditLogEntity;

import java.util.Date;
import java.util.Map;

public interface TAuditLogService extends IService<TAuditLogEntity> {

    PageUtils queryPage(Map<String, Object> params);

    R logbackup(Date dateParam);

}

