package com.smart119.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart119.system.dao.UserConfigDao;
import com.smart119.system.domain.UserConfigDO;
import com.smart119.system.service.UserConfigService;
import org.springframework.stereotype.Service;


@Service
public class UserConfigServiceImpl extends ServiceImpl<UserConfigDao, UserConfigDO> implements
    UserConfigService {


}
