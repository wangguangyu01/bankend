package com.smart119.blog.service.impl;

import com.smart119.blog.dao.WxActivityDao;
import com.smart119.blog.domain.WxActivity;
import com.smart119.blog.service.WxActivityService;
import com.smart119.blog.vo.WxActivityVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class WxActivityServiceImpl implements WxActivityService {

    @Autowired
    private WxActivityDao activityDao;

    @Override
    public List<WxActivity> queryWxActivityListPage(Map<String, Object> param) {
        return activityDao.queryWxActivityListPage(param);
    }

    @Override
    public int count(Map<String, Object> param) {
        return activityDao.count(param);
    }


    @Override
    public List<WxActivityVo> queryWxActivityList(String activityUuid) {
        return activityDao.queryWxActivityList(activityUuid);
    }


    @Override
    public WxActivity queryWxActivityById(Long id) {
        return activityDao.selectById(id);
    }
}
