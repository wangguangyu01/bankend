package com.smart119.system.service;

import com.smart119.common.utils.PageUtils;
import com.smart119.system.domain.LevelPlanDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author zhangshunhua
 * @email zhangshunhua@sz000673.com
 * @date 2021-07-21 10:59:21
 */
public interface LevelPlanService {


	PageUtils queryPage(Map<String, Object> params);

	LevelPlanDO queryById(String levelPlanId);

	int save(LevelPlanDO levelPlanDO);

	int update(LevelPlanDO levelPlanDO);


	int remove(String levelPlanId);

	int batchRemove(String[] levelPlanIds);
}
