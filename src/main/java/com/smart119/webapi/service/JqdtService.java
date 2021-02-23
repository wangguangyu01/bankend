package com.smart119.webapi.service;

import com.smart119.webapi.domain.JqdtDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 警情动态
 * 
 * @author liangsl
 * @email 123564081@qq.com
 * @date 2021-01-30 10:02:02
 */
public interface JqdtService {
	
	JqdtDO get(String jqdtId);
	
	List<JqdtDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(JqdtDO jqdt);
	
	int update(JqdtDO jqdt);
	
	int remove(String jqdtId);
	
	int batchRemove(String[] jqdtIds);

	List<JqdtDO> getJqdtByJqId(String jqTywysbm);

}
