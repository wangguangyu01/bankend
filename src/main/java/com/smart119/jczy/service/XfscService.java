package com.smart119.jczy.service;

import com.smart119.jczy.domain.XfscDO;

import java.util.List;
import java.util.Map;

/**
 * 消防水池基本信息
 *
 * @author thrz
 * @email thrz@sz000673.com
 * @date 2021-01-21 15:07:01
 */
public interface XfscService {

	XfscDO get(String xfscTywysbm);

	List<XfscDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(XfscDO xfsc);

	int update(XfscDO xfsc);

	int remove(String xfscTywysbm);

	boolean batchRemove(String[] xfscTywysbms);

	List<XfscDO> getXfscByRange(Double jd,Double wd,Double distance);
}
