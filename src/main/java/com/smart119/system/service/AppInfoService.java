package com.smart119.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.smart119.system.domain.AppInfoDO;
import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author zhangshunhua
 * @email zhangshunhua@sz000673.com
 * @date 2021-03-22 16:30:18
 */
public interface AppInfoService extends IService<AppInfoDO>{


	IPage<AppInfoDO> queryPage(Map<String, Object> params);

	AppInfoDO queryById(Integer id);

	int update(AppInfoDO appInfoDO);


	int remove(Integer id);

	int batchRemove(Integer[] ids);

	/**
	 * 根据角色ID查询App应用列表
	 * @param roleId
	 * @return
	 */
	List<AppInfoDO> queryByRoleId(Long roleId);
}
