package com.smart119.jczy.dao;

import com.smart119.common.domain.DictDO;
import com.smart119.jczy.domain.BcbdDO;

import java.util.List;
import java.util.Map;

import com.smart119.jczy.domain.XfclDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 编程编队
 * @author Jiyu Yang
 * @email yangjiyu@sz000673.com
 * @date 2021-01-30 10:12:02
 */

public interface BcbdDao {

	BcbdDO get(String bcbdId);
	
	List<BcbdDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(BcbdDO bcbd);
	
	int update(BcbdDO bcbd);
	
	int remove(String BCBD_ID);
	
	int batchRemove(String[] bcbdIds);

	List<BcbdDO> listWithQuery(Map<String, Object> map);

	int checkExist(String jqTywysbm,String bcbdId);

	List<XfclDO>selXfclByBcbdId(String bcbdId);

	List<Map<String,Object>> getZZDY();

}
