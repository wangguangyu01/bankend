package com.smart119.jczy.dao;

import com.smart119.jczy.domain.TrsyDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * @author thrz
 * @email thrz@sz000673.com
 * @date 2021-01-20 15:32:48
 */
@Mapper
public interface TrsyDao {

	TrsyDO get(String trsyTywysbm);
	
	List<TrsyDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(TrsyDO trsy);
	
	int update(TrsyDO trsy);
	
	int remove(String TRSY_TYWYSBM);
	
	int batchRemove(String[] trsyTywysbms);

	List<TrsyDO> getTrsyByRange(@Param("jd")Double jd, @Param("wd")Double wd, @Param("distance")Double distance);

}
