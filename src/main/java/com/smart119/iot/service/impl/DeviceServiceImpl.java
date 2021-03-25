package com.smart119.iot.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart119.common.utils.PageMybatisPlusUtils;
import com.smart119.common.utils.PageUtils;
import com.smart119.iot.dao.DeviceDao;
import com.smart119.iot.domain.DeviceDO;
import com.smart119.iot.service.DeviceService;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;



@Service
public class DeviceServiceImpl  implements DeviceService {

	@Autowired
	private DeviceDao deviceDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
		Page<DeviceDO> page = new Page();
		PageMybatisPlusUtils.pageHelperUtils(params, page);
		IPage<DeviceDO> pageVo = deviceDao.selectPageVo(page, params);
		return new PageUtils(pageVo.getRecords(),
				NumberUtils.toInt(String.valueOf(pageVo.getTotal()), 0));
    }

	@Override
	public DeviceDO queryById(String id) {
		return deviceDao.selectById(id);
	}


	@Override
	public int save(DeviceDO deviceDO) {
		int inserRow = deviceDao.insert(deviceDO);
		return inserRow;
	}

	@Override
	public int update(DeviceDO deviceDO) {
		int updateRow = deviceDao.updateById(deviceDO);
		return updateRow;
	}

	@Override
	public int remove(String id) {
		return deviceDao.deleteById(id);
	}


	@Override
	public int batchRemove(String[] ids) {
		List<String> resultList = new ArrayList<>(ids.length);
		Collections.addAll(resultList, ids);
		int deleteBatchrow = deviceDao.deleteBatchIds(resultList);
		return deleteBatchrow;
	}
	
}
