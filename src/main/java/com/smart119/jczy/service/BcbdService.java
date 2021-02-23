package com.smart119.jczy.service;

import com.smart119.common.domain.DictDO;
import com.smart119.jczy.domain.BcbdDO;
import com.smart119.jczy.domain.BcbdZzdyDO;
import com.smart119.jczy.domain.XfclDO;

import java.util.List;
import java.util.Map;

/**
 * 编程编队
 * 
 * @author Jiyu Yang
 * @email yangjiyu@sz000673.com
 * @date 2021-01-30 10:12:02
 */
public interface BcbdService {
	
	BcbdDO get(String bcbdId);

	List<BcbdZzdyDO> getzzdyid(String bcbdId);
	
	List<BcbdDO> list(Map<String, Object> map);

	List<BcbdDO> listWithQuery(Map<String, Object> map);

	boolean checkExist(String jqTywysbm,String bcbdId);

	List<XfclDO>selXfclByBcbdId(String bcbdId);

	int count(Map<String, Object> map);

	int save(BcbdDO bcbd);
	
	int update(BcbdDO bcbd);
	
	int remove(String bcbdId);
	
	int batchRemove(String[] bcbdIds);

	List<Map<String,Object>> getZZDY();
}
