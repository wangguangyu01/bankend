package com.smart119.jczy.service.impl;

import com.smart119.jczy.dao.QyjqtjDao;
import com.smart119.jczy.domain.QyjqtjDO;
import com.smart119.jczy.service.QyjqtjService;
import com.smart119.webapi.domain.JbxxDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class QyjqtjServiceImpl implements QyjqtjService {

	@Autowired
	private QyjqtjDao qyjqtjDao;

    @Override
    public List<JbxxDO> qyjqtjList(Map<String, Object> params){
        return qyjqtjDao.qyjqtjList(params);
    }

    @Override
    public List<QyjqtjDO> qyjqtjExcel(Map<String, Object> params){
        return qyjqtjDao.qyjqtjExcel(params);
    }

}
