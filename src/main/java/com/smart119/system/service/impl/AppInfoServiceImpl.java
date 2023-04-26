package com.smart119.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart119.common.utils.PageMybatisPlusUtils;
import com.smart119.system.dao.AppInfoDao;
import com.smart119.system.domain.AppInfoDO;
import com.smart119.system.domain.RoleAppDO;
import com.smart119.system.service.AppInfoService;
import com.smart119.system.service.RoleAppService;
import java.util.ArrayList;
import java.util.Collections;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;




@Service
public class AppInfoServiceImpl extends ServiceImpl<AppInfoDao, AppInfoDO> implements AppInfoService {

	@Autowired
	private AppInfoDao appInfoDao;
	@Autowired
	private RoleAppService roleAppService;

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

	/**
	 * 根据角色ID查询App应用列表
	 */
	@Override
	public List<AppInfoDO> queryByRoleId(Long roleId) {
		QueryWrapper query=new QueryWrapper();
		query.eq("role_id",roleId);
    List<RoleAppDO> roleAppDOS=roleAppService.list(query);
    List<Integer> appIds=new ArrayList<>(0);
    if(CollectionUtils.isNotEmpty(roleAppDOS)){
    	roleAppDOS.forEach(roleAppDO -> {
				appIds.add(roleAppDO.getAppId());
			});
		}
		QueryWrapper queryAppInfo=new QueryWrapper();
    queryAppInfo.eq("status","enable");
    List<AppInfoDO> appInfoDOS=this.list(queryAppInfo);
		if(CollectionUtils.isNotEmpty(appInfoDOS)){

			appInfoDOS.forEach(appInfoDO -> {
				if(appIds.contains(appInfoDO.getId())){
					appInfoDO.setAppSign("true");
				}else {
					appInfoDO.setAppSign("false");
				}
			});
		}

		return appInfoDOS;
	}

}
