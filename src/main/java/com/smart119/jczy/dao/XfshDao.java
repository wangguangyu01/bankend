package com.smart119.jczy.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smart119.jczy.domain.XfshDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 消防水鹤基本信息
 * @author thrz
 * @email thrz@sz000673.com
 * @date 2021-01-20 13:41:00
 */

public interface XfshDao extends BaseMapper<XfshDO> {

	XfshDO get(String xfshTywysbm);

	List<XfshDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(XfshDO xfsh);

	int update(XfshDO xfsh);

	int remove(String XFSH_TYWYSBM);

	int batchRemove(String[] xfshTywysbms);

	List<XfshDO> getXfshByRange(@Param("jd")Double jd, @Param("wd")Double wd, @Param("distance")Double distance);

	/**
	 * 消防水鹤
	 * @param xfshTywysbm
	 * @return
	 */
	int updateStatus(@Param("xfshTywysbm")String xfshTywysbm);

}
