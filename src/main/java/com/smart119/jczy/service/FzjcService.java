package com.smart119.jczy.service;

import com.smart119.common.utils.PageUtils;
import com.smart119.jczy.domain.FzjcDO;

import java.util.List;
import java.util.Map;

/**
 * 辅助决策
 * 
 * @author wangguangyu
 * @email wangguangyu@sz000673.com
 * @date 2021-02-02 10:22:35
 */
public interface FzjcService {
	
	FzjcDO get(String fzjcId);
	
	List<FzjcDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(FzjcDO fzjc);
	
	int update(FzjcDO fzjc);
	
	int remove(String fzjcId);
	
	int batchRemove(String[] fzjcIds);


	public PageUtils queryPage(Map<String, Object> params);
}
