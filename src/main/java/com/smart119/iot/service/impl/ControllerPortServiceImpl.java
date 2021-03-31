package com.smart119.iot.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart119.common.utils.PageMybatisPlusUtils;
import com.smart119.common.utils.PageUtils;
import com.smart119.iot.dao.ControllerPortDao;
import com.smart119.iot.domain.ControllerPortDO;
import com.smart119.iot.service.ControllerPortService;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;



@Service
public class ControllerPortServiceImpl  implements ControllerPortService {

	@Autowired
	private ControllerPortDao controllerPortDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
		Page<ControllerPortDO> page = new Page();
		PageMybatisPlusUtils.pageHelperUtils(params, page);
		IPage<ControllerPortDO> pageVo = controllerPortDao.selectPageVo(page, params);
		return new PageUtils(pageVo.getRecords(),
				NumberUtils.toInt(String.valueOf(pageVo.getTotal()), 0));
    }

	@Override
	public List<ControllerPortDO> list(Map<String, Object> params) {
		return controllerPortDao.list(params);
	}

	@Override
	public ControllerPortDO queryById(String id) {
		return controllerPortDao.selectById(id);
	}


	@Override
	public int save(ControllerPortDO controllerPortDO) {
		int inserRow = controllerPortDao.insert(controllerPortDO);
		return inserRow;
	}

	@Override
	public int update(ControllerPortDO controllerPortDO) {
		int updateRow = controllerPortDao.updateById(controllerPortDO);
		return updateRow;
	}

	@Override
	public int remove(String id) {
		return controllerPortDao.deleteById(id);
	}


	@Override
	public int batchRemove(String[] ids) {
		List<String> resultList = new ArrayList<>(ids.length);
		Collections.addAll(resultList, ids);
		int deleteBatchrow = controllerPortDao.deleteBatchIds(resultList);
		return deleteBatchrow;
	}
	
}
