package com.smart119.jczy.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart119.common.utils.PageMybatisPlusUtils;
import com.smart119.common.utils.PageUtils;
import com.smart119.jczy.dao.JqtjDao;
import com.smart119.jczy.service.JqtjService;
import com.smart119.webapi.domain.JbxxDO;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class JqtjServiceImpl implements JqtjService {

	@Autowired
	private JqtjDao jqtjDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
		Page<JbxxDO> page = new Page();
		PageMybatisPlusUtils.pageHelperUtils(params, page);
		IPage<JbxxDO> pageVo = jqtjDao.selectPageVo(page, params);
		return new PageUtils(pageVo.getRecords(),
							 NumberUtils.toInt(String.valueOf(pageVo.getTotal()), 0));
    }


	
}
