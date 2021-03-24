package com.smart119.jczy.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smart119.jczy.domain.WblxrDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 外部联系人
 * @author scy
 * @email shichengyuan@sz000673.com
 * @date 2021-03-24 14:31:16
 */
public interface WblxrDao extends BaseMapper<WblxrDO> {

	WblxrDO get(String wblxrId);
	
	List<WblxrDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(WblxrDO wblxr);
	
	int update(WblxrDO wblxr);
	
	int remove(String WBLXR_ID);
	
	int batchRemove(String[] wblxrIds);
}
