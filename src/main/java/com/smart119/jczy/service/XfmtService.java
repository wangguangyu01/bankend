package com.smart119.jczy.service;

import com.smart119.jczy.domain.XfmtDO;

import java.util.List;
import java.util.Map;

/**
 *
 *
 * @author thrz
 * @email thrz@sz000673.com
 * @date 2021-01-20 15:29:22
 */
public interface XfmtService {

	XfmtDO get(String qsmtTywysbm);

	List<XfmtDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(XfmtDO xfmt);

	int update(XfmtDO xfmt);

	int remove(String qsmtTywysbm);

	boolean batchRemove(String[] qsmtTywysbms);

	List<XfmtDO> getXfmtByRange(Double jd, Double wd, Double distance);
}
