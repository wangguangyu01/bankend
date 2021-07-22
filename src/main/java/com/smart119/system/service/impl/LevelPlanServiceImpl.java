package com.smart119.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart119.common.utils.PageMybatisPlusUtils;
import com.smart119.jczy.domain.FzjcDO;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.smart119.common.utils.PageUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.smart119.system.dao.LevelPlanDao;
import com.smart119.system.domain.LevelPlanDO;
import com.smart119.system.service.LevelPlanService;



@Service
public class LevelPlanServiceImpl  implements LevelPlanService {

	@Autowired
	private LevelPlanDao levelPlanDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
		Page<LevelPlanDO> page = new Page();
		PageMybatisPlusUtils.pageHelperUtils(params, page);
		IPage<LevelPlanDO> pageVo = levelPlanDao.selectPageVo(page, params);
		return new PageUtils(pageVo.getRecords(),
				NumberUtils.toInt(String.valueOf(pageVo.getTotal()), 0));
    }

	@Override
	public LevelPlanDO queryById(String levelPlanId) {
		return levelPlanDao.selectById(levelPlanId);
	}


	@Override
	public int save(LevelPlanDO levelPlanDO) {
		int inserRow = levelPlanDao.insert(levelPlanDO);
		return inserRow;
	}

	@Override
	public int update(LevelPlanDO levelPlanDO) {
		int updateRow = levelPlanDao.updateById(levelPlanDO);
		return updateRow;
	}

	@Override
	public int remove(String levelPlanId) {
		return levelPlanDao.deleteById(levelPlanId);
	}


	@Override
	public int batchRemove(String[] levelPlanIds) {
		List<String> resultList = new ArrayList<>(levelPlanIds.length);
		Collections.addAll(resultList, levelPlanIds);
		int deleteBatchrow = levelPlanDao.deleteBatchIds(resultList);
		return deleteBatchrow;
	}
	
}
