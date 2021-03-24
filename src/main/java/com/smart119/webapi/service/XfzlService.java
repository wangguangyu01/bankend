package com.smart119.webapi.service;


import com.smart119.common.utils.PageUtils;
import com.smart119.webapi.domain.XfzlDO;

import java.util.List;
import java.util.Map;

/**
 * 消防战例
 * 
 * @author liangsl
 * @email liangsl@sz000673.com
 * @date 2021-03-11 14:56:44
 */
public interface XfzlService {


	PageUtils queryPage(Map<String, Object> params);

	XfzlDO queryById(String xfzlId);

	List<XfzlDO> queryXflzList(Map<String, Object> params);

	int updLlCs(String xwZxId);

	int updDzCs(String xwZxId);

	int updDzUserIds(String dzUserIds, String xfzlId);

}