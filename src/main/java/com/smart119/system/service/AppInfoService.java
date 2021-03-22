package com.smart119.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.smart119.common.utils.PageUtils;
import com.smart119.system.domain.AppInfoDO;
import java.util.Map;

/**
 * 
 * 
 * @author zhangshunhua
 * @email zhangshunhua@sz000673.com
 * @date 2021-03-22 16:30:18
 */
public interface AppInfoService extends IService<AppInfoDO> {


	PageUtils queryPage(Map<String, Object> params);

	AppInfoDO queryById(Integer id);

	int update(AppInfoDO appInfoDO);


	int remove(Integer id);

	int batchRemove(Integer[] ids);
}
