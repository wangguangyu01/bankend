package com.smart119.system.service;

import com.smart119.blog.domain.WxActivity;
import com.smart119.system.domain.OderPayReturn;

import java.util.List;
import java.util.Map;

public interface OderPayReturnService {




    public List<OderPayReturn> queryReturnPayListPage(Map<String, Object> param);


    public int count(Map<String, Object> param);



}
