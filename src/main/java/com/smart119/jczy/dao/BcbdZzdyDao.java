package com.smart119.jczy.dao;

import com.smart119.jczy.dao.BcbdZzdyDao;

import java.util.List;
import java.util.Map;

import com.smart119.jczy.domain.BcbdDO;
import com.smart119.jczy.domain.BcbdZzdyDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 编程编队-作战单元
 * @author Jiyu Yang
 * @email yangjiyu@sz000673.com
 * @date 2021-01-30 10:12:02
 */

public interface BcbdZzdyDao {

	BcbdZzdyDO get(String bcbdZzdyId);
	
	List<BcbdZzdyDO> list(Map<String, Object> map);

	List<BcbdZzdyDO> getzzdyid(String bcbdId);
	
	int count(Map<String, Object> map);
	
	int save(BcbdZzdyDO bcbdZzdy);
	
	int update(BcbdZzdyDO bcbdZzdy);
	
	int remove(String BCBD_ZZDY_ID);

	int removeByBcbdId(String bcbdId);

	int batchRemove(String[] bcbdZzdyIds);
}
