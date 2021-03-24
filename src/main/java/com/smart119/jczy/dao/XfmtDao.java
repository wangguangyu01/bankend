package com.smart119.jczy.dao;

import com.smart119.jczy.domain.XfmtDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * @author thrz
 * @email thrz@sz000673.com
 * @date 2021-01-20 15:29:22
 */

public interface XfmtDao {

	XfmtDO get(String qsmtTywysbm);
	
	List<XfmtDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(XfmtDO xfmt);
	
	int update(XfmtDO xfmt);
	
	int remove(String QSMT_TYWYSBM);
	
	int batchRemove(String[] qsmtTywysbms);

	List<XfmtDO> getXfmtByRange(@Param("jd")Double jd, @Param("wd")Double wd, @Param("distance")Double distance);

}
