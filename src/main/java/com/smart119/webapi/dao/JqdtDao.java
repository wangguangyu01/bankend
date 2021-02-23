package com.smart119.webapi.dao;

import com.smart119.webapi.domain.JqdtDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 警情动态
 * @author liangsl
 * @email 123564081@qq.com
 * @date 2021-01-30 10:02:02
 */
@Mapper
public interface JqdtDao {

	JqdtDO get(String jqdtId);
	
	List<JqdtDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(JqdtDO jqdt);
	
	int update(JqdtDO jqdt);
	
	int remove(String JQDT_ID);
	
	int batchRemove(String[] jqdtIds);

	List<JqdtDO> getJqdtByJqId(@Param("jqTywysbm") String jqTywysbm);

}
