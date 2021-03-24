package com.smart119.jczy.dao;

import com.smart119.jczy.domain.BrqyDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 避让区域
 * @author shilei
 * @email yangjiyu@sz000673.com
 * @date 2021-02-16 09:54:46
 */

public interface BrqyDao {

	BrqyDO get(String id);
	
	List<BrqyDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(BrqyDO brqy);
	
	int update(BrqyDO brqy);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
