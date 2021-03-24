package com.smart119.webapi.dao;

import com.smart119.webapi.domain.ZhjqDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author scy
 * @email mrtidus@163.com
 * @date 2021-01-29 11:02:36
 */

public interface ZhjqDao {

	ZhjqDO get(String id);
	
	List<ZhjqDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ZhjqDO zhjq);
	
	int update(ZhjqDO zhjq);
	
	int remove(String id);
	
	int batchRemove(String[] ids);

	List<ZhjqDO> getZhjqList(@Param("lylb") String lylb, @Param("jqzt") String jqzt,@Param("ms") String ms);

	int getZhjqSize(@Param("lylb") String lylb, @Param("jqzt") String jqzt,@Param("ms") String ms);

	List<ZhjqDO> getZhjqxqList(@Param("id") String id);
}
