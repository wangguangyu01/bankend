package com.smart119.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smart119.blog.domain.WxActivity;
import com.smart119.system.domain.OderPayReturn;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OderPayReturnDao extends BaseMapper<OderPayReturn> {


    List<OderPayReturn> queryReturnPayListPage(@Param("param") Map<String, Object> param);


    int count(@Param("param") Map<String, Object> param);
}
