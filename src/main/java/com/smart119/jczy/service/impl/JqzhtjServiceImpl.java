package com.smart119.jczy.service.impl;

import com.smart119.jczy.dao.JqtjDao;
import com.smart119.jczy.dao.JqzhtjDao;
import com.smart119.jczy.domain.JqzhtjDO;
import com.smart119.jczy.service.JqzhtjService;
import com.smart119.webapi.domain.JbxxDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class JqzhtjServiceImpl implements JqzhtjService {

	@Autowired
	private JqzhtjDao jqzhtjDao;

    @Override
    public List<JbxxDO> list(Map<String, Object> params){
        return jqzhtjDao.list(params);
    }

    @Override
    public JqzhtjDO getExcel(Map<String, Object> params){
        return jqzhtjDao.getExcel(params);
    }
}
