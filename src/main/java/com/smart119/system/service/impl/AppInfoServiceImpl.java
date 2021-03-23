package com.smart119.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart119.common.utils.PageMybatisPlusUtils;
import com.smart119.system.dao.AppInfoDao;
import com.smart119.system.domain.AppInfoDO;
import com.smart119.system.service.AppInfoService;
import java.util.ArrayList;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.smart119.common.utils.PageUtils;

import java.util.List;
import java.util.Map;




@Service
public class AppInfoServiceImpl implements AppInfoService {

	@Autowired
	private AppInfoDao appInfoDao;

    @Override
    public IPage<AppInfoDO> queryPage(Map<String, Object> params) {
		Page<AppInfoDO> page = new Page<>();
		PageMybatisPlusUtils.pageHelperUtils(params,page);
		IPage<AppInfoDO> result = appInfoDao.selectPageVo(page, params);
    	return result;
    }

	@Override
	public AppInfoDO queryById(Integer id) {
		return appInfoDao.selectById(id);
	}

	@Override
	public int update(AppInfoDO appInfoDO) {
		int updateRow = appInfoDao.updateById(appInfoDO);
		return updateRow;
	}

	@Override
	public int remove(Integer id) {
		return appInfoDao.deleteById(id);
	}


	@Override
	public int batchRemove(Integer[] ids) {
		List<Integer> resultList = new ArrayList<>(ids.length);
		Collections.addAll(resultList, ids);
		int deleteBatchrow = appInfoDao.deleteBatchIds(resultList);
		return deleteBatchrow;
	}

	@Override
	public int save(AppInfoDO appInfo) {
		return appInfoDao.save(appInfo);
	}
}
