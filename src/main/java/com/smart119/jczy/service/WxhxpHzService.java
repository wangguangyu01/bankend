package com.smart119.jczy.service;

import com.smart119.common.utils.PageUtils;
import com.smart119.jczy.domain.WxhxpHzDO;
import com.smart119.xwzx.domain.XwZxDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author shichengyuan
 * @email shichengyuan@sz000673.com
 * @date 2021-09-15 08:15:15
 */
public interface WxhxpHzService {


	WxhxpHzDO get(String id);
	List<WxhxpHzDO> list(Map<String, Object> map);
	PageUtils queryPage(Map<String, Object> params);

	WxhxpHzDO queryById(Long id);

	int save(WxhxpHzDO wxhxpHzDO);

	int update(WxhxpHzDO wxhxpHzDO);

	int count(Map<String, Object> map);
	int remove(String id);

	int batchRemove(String[] ids);
}
