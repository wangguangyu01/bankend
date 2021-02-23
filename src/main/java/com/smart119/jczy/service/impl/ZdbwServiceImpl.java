package com.smart119.jczy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.smart119.jczy.dao.ZdbwDao;
import com.smart119.jczy.domain.ZdbwDO;
import com.smart119.jczy.service.ZdbwService;



@Service
public class ZdbwServiceImpl implements ZdbwService {
    @Autowired
    private ZdbwDao zdbwDao;

    @Override
    public ZdbwDO get(String zdbwTywysbm){
        return zdbwDao.get(zdbwTywysbm);
    }

    @Override
    public List<ZdbwDO> list(Map<String, Object> map){
        return zdbwDao.list(map);
    }

    @Override
    public int count(Map<String, Object> map){
        return zdbwDao.count(map);
    }

    @Override
    public int save(ZdbwDO zdby){
        return zdbwDao.save(zdby);
    }

    @Override
    public int update(ZdbwDO zdby){
        return zdbwDao.update(zdby);
    }

    @Override
    public int remove(String zdbwTywysbm){
        return zdbwDao.remove(zdbwTywysbm);
    }

    @Override
    public int batchRemove(String[] zdbwTywysbms){
        return zdbwDao.batchRemove(zdbwTywysbms);
    }

}
