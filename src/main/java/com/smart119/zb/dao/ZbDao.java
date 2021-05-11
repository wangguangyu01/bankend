package com.smart119.zb.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smart119.zb.domain.ZbDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 值班
 * @author liangsl
 * @email liangshengli@sz000673.com
 * @date 2021-05-08 19:01:02
 */
public interface ZbDao extends BaseMapper<ZbDO> {

	ZbDO get(String zbId);
	
	List<ZbDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ZbDO zb);
	
	int update(ZbDO zb);
	
	int remove(String ZB_ID);
	
	int batchRemove(@Param("rq") String rq, @Param("xfjyjgTywysbm") String xfjyjgTywysbm);
}
