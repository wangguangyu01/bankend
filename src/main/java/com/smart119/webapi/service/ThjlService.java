package com.smart119.webapi.service;

import com.smart119.common.utils.R;
import com.smart119.webapi.domain.ThjlDO;

import java.util.List;
import java.util.Map;

/**
 * 通话记录基本信息
 * 
 * @author yanyu
 * @email yanyu@sz000673.com
 * @date 2021-01-28 12:51:27
 */
public interface ThjlService {
	
	ThjlDO get(String thjlTywysbm);
	
	List<ThjlDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ThjlDO thjl);
	
	int update(ThjlDO thjl);
	
	int remove(String thjlTywysbm);
	
	int batchRemove(String[] thjlTywysbms);

	List<Map<String,Object>> thjlcx(String thjlTywysbm);

	R dzwjwz(String jqTywysbm);

	public boolean saveThjlOtherInfor(String jqTywysbm,String userId);
}
