package com.smart119.webapi.dao;

import com.smart119.webapi.domain.ZbdtDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author scy
 * @email mrtidus@163.com
 * @date 2021-01-28 13:59:02
 */

public interface WebZbdtDao {

	ZbdtDO get(String zhbTywysbm);
	
	List<ZbdtDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ZbdtDO zbdt);
	
	int update(ZbdtDO zbdt);
	
	int remove(String ZHB_TYWYSBM);
	
	int batchRemove(String[] zhbTywysbms);

	List<ZbdtDO> zbList(@Param("xfjyjgTywysbm") String xfjyjgTywysbm);

	int zbSize(	@Param("xfjyjgTywysbm") String xfjyjgTywysbm);
}
