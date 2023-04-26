package com.smart119.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart119.system.dao.TElementDirectionDao;
import com.smart119.system.domain.TElementDirectionEntity;
import com.smart119.system.service.TElementDirectionService;
import org.springframework.stereotype.Service;

@Service("tElementDirectionService")
public class TElementDirectionServiceImpl extends ServiceImpl<TElementDirectionDao, TElementDirectionEntity> implements TElementDirectionService {
}