package com.smart119.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart119.system.dao.TAuditLogConfigDao;
import com.smart119.system.domain.TAuditLogConfigEntity;
import com.smart119.system.service.TAuditLogConfigService;
import org.springframework.stereotype.Service;

@Service("tAuditLogConfigService")
public class TAuditLogConfigServiceImpl extends ServiceImpl<TAuditLogConfigDao, TAuditLogConfigEntity> implements TAuditLogConfigService {
}