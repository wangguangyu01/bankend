package com.smart119.jczy.dao;

import com.smart119.jczy.domain.ZzdyXfclDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 作战单元_车辆信息 关联表
 * @author shilei
 * @email thrz@sz000673.com
 * @date 2021-01-29 20:42:51
 */
@Mapper
public interface ZzdyXfclDao {

	ZzdyXfclDO get(String id);
	
	List<ZzdyXfclDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ZzdyXfclDO zzdyXfcl);
	
	int update(ZzdyXfclDO zzdyXfcl);
	
	int remove(String id);
	
	int batchRemove(String[] ids);

	int removeZzdy(String id);

    List<ZzdyXfclDO> getZzdyxfcl(String zzdyTywybs);
}
