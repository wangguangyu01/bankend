package com.smart119.blog.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smart119.blog.domain.WxActivity;
import com.smart119.blog.vo.WxActivityVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface WxActivityDao extends BaseMapper<WxActivity> {


    List<WxActivity> queryWxActivityListPage(@Param("param") Map<String, Object> param);


    int count(@Param("param") Map<String, Object> param);


    List<WxActivityVo> queryWxActivityList(@Param("activityUuid") String activityUuid);
}
