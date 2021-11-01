package com.smart119.jczy.service;

import com.smart119.jczy.domain.XfshDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 消防水鹤基本信息
 *
 * @author thrz
 * @email thrz@sz000673.com
 * @date 2021-01-20 13:41:00
 */
public interface XfshService {

	XfshDO get(String xfshTywysbm);

	List<XfshDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(XfshDO xfsh);

	int update(XfshDO xfsh);

	int remove(String xfshTywysbm);

	int batchRemove(String[] xfshTywysbms);

	List<XfshDO> getXfshByRange(Double jd, Double wd, Double distance);


	/**
	 * 消防水鹤
	 * @param xfshTywysbm
	 * @return
	 */
	int updateStatus(String xfshTywysbm);

}
