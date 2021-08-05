package com.smart119.jczy.service.impl;

import com.smart119.jczy.dao.JqtjDao;
import com.smart119.jczy.domain.JqtjDO;
import com.smart119.jczy.service.JqtjService;
import com.smart119.webapi.domain.JbxxDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class JqtjServiceImpl implements JqtjService {

	@Autowired
	private JqtjDao jqtjDao;

    @Override
    public List<JbxxDO> list(Map<String, Object> params){
        return jqtjDao.list(params);
    }


    @Override
    public List<JqtjDO> listExcel(Map<String, Object> params){
        return jqtjDao.listExcel(params);
    }
}
