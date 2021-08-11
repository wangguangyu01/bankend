package com.smart119.jczy.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smart119.jczy.domain.ZzdyXfzbDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 作战单元-消防装备-关联表
 * @author liangsl
 * @email liangshengli@sz000673.com
 * @date 2021-08-06 10:00:07
 */
public interface ZzdyXfzbDao extends BaseMapper<ZzdyXfzbDO> {

	ZzdyXfzbDO get(String id);
	
	List<ZzdyXfzbDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ZzdyXfzbDO zzdyXfzb);
	
	int update(ZzdyXfzbDO zzdyXfzb);
	
	int remove(String id);
	
	int batchRemove(String[] ids);

	List<ZzdyXfzbDO> getZzdyXfzb(@Param("zzdyTywybs") String zzdyTywybs);

	int removeZzdyXfzb(String zzdyTywybs);
}
