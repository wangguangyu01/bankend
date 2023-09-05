package com.smart119.system.service.impl;

import com.smart119.system.dao.OderPayReturnDao;
import com.smart119.system.domain.OderPayReturn;
import com.smart119.system.service.OderPayReturnService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class OderPayReturnServiceImpl implements OderPayReturnService {

    @Resource
    private OderPayReturnDao oderPayReturnDao;


    @Override
    public List<OderPayReturn> queryReturnPayListPage(Map<String, Object> param) {
        return oderPayReturnDao.queryReturnPayListPage(param);
    }

    @Override
    public int count(Map<String, Object> param) {
        return oderPayReturnDao.count(param);
    }
}
