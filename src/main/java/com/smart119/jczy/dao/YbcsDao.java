package com.smart119.jczy.dao;

import com.smart119.jczy.domain.YbcsDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 一般场所
 * @author shilei
 * @email yangjiyu@sz000673.com
 * @date 2021-10-21 10:04:30
 */
@Mapper
public interface YbcsDao {

	YbcsDO get(String ybcsTywysbm);
	
	List<YbcsDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(YbcsDO ybcs);
	
	int update(YbcsDO ybcs);
	
	int remove(String YBCS_TYWYSBM);
	
	int batchRemove(String[] ybcsTywysbms);
}
