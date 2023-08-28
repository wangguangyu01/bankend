package com.smart119.blog.service;

import com.smart119.blog.domain.WxActivity;
import com.smart119.blog.vo.WxActivityVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface WxActivityService {




    List<WxActivity> queryWxActivityListPage(Map<String, Object> param);


    int count(Map<String, Object> param);


    List<WxActivityVo> queryWxActivityList(String activityUuid);
}
