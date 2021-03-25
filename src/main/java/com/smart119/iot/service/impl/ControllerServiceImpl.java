package com.smart119.iot.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart119.common.utils.PageMybatisPlusUtils;
import com.smart119.common.utils.PageUtils;
import com.smart119.iot.dao.ControllerDao;
import com.smart119.iot.domain.ControllerDO;
import com.smart119.iot.service.ControllerService;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;



@Service
public class ControllerServiceImpl  implements ControllerService {

	@Autowired
	private ControllerDao controllerDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
		Page<ControllerDO> page = new Page();
		PageMybatisPlusUtils.pageHelperUtils(params, page);
		IPage<ControllerDO> pageVo = controllerDao.selectPageVo(page, params);
		return new PageUtils(pageVo.getRecords(),
				NumberUtils.toInt(String.valueOf(pageVo.getTotal()), 0));
    }

	@Override
	public ControllerDO queryById(String id) {
		return controllerDao.selectById(id);
	}


	@Override
	public int save(ControllerDO controllerDO) {
		int inserRow = controllerDao.insert(controllerDO);
		return inserRow;
	}

	@Override
	public int update(ControllerDO controllerDO) {
		int updateRow = controllerDao.updateById(controllerDO);
		return updateRow;
	}

	@Override
	public int remove(String id) {
		return controllerDao.deleteById(id);
	}


	@Override
	public int batchRemove(String[] ids) {
		List<String> resultList = new ArrayList<>(ids.length);
		Collections.addAll(resultList, ids);
		int deleteBatchrow = controllerDao.deleteBatchIds(resultList);
		return deleteBatchrow;
	}
	
}
