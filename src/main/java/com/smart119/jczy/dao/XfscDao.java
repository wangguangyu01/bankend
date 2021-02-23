package com.smart119.jczy.dao;

import com.smart119.jczy.domain.XfscDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 消防水池基本信息
 * @author thrz
 * @email thrz@sz000673.com
 * @date 2021-01-21 15:07:01
 */
@Mapper
public interface XfscDao {

	XfscDO get(String xfscTywysbm);
	
	List<XfscDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(XfscDO xfsc);
	
	int update(XfscDO xfsc);
	
	int remove(String XFSC_TYWYSBM);
	
	int batchRemove(String[] xfscTywysbms);

	List<XfscDO> getXfscByRange(@Param("jd")Double jd, @Param("wd")Double wd, @Param("distance")Double distance);


}
