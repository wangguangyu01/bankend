package com.smart119.webapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.smart119.webapi.dao.JqcjdpDzDao;
import com.smart119.webapi.domain.JqcjdpDzDO;
import com.smart119.webapi.service.JqcjdpDzService;


@Service
public class JqcjdpDzServiceImpl implements JqcjdpDzService {
    @Autowired
    private JqcjdpDzDao jqcjdpDzDao;

    @Override
    public JqcjdpDzDO get(String dpdzTywysbm) {
        return jqcjdpDzDao.get(dpdzTywysbm);
    }

    @Override
    public List<JqcjdpDzDO> list(Map<String, Object> map) {
        return jqcjdpDzDao.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return jqcjdpDzDao.count(map);
    }

    @Override
    public int save(JqcjdpDzDO jqcjdpDz) {
        return jqcjdpDzDao.save(jqcjdpDz);
    }

    @Override
    public int update(JqcjdpDzDO jqcjdpDz) {
        return jqcjdpDzDao.update(jqcjdpDz);
    }

    @Override
    public int remove(String dpdzTywysbm) {
        return jqcjdpDzDao.remove(dpdzTywysbm);
    }

    @Override
    public int batchRemove(String[] dpdzTywysbms) {
        return jqcjdpDzDao.batchRemove(dpdzTywysbms);
    }

    @Override
    public List<Map<String, Object>> getDzCarListByJdId(String JQ_TYWYSBM) {
        return jqcjdpDzDao.getDzCarListByJdId(JQ_TYWYSBM);
    }
}
