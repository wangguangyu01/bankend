package com.smart119.jczy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.smart119.common.utils.PageUtils;
import com.smart119.jczy.domain.FzjcDO;
import com.smart119.jqxx.utils.ExportExcel;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * 辅助决策
 *
 * @author wangguangyu
 * @email wangguangyu@sz000673.com
 * @date 2021-02-02 10:22:35
 */
public interface FzjcService extends IService<FzjcDO> {

	FzjcDO get(String fzjcId);

	List<FzjcDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);

//	int save(FzjcDO fzjc);

//	int update(FzjcDO fzjc);

	int remove(String fzjcId);

	int batchRemove(String[] fzjcIds);


	PageUtils queryPage(Map<String, Object> params);


	public ExportExcel exportData(Map<String, Object> params, String fileName) throws InvocationTargetException, IllegalAccessException;
}
