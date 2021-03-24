package com.smart119.jczy.dao;

import com.smart119.jczy.domain.BlackListDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author thrz
 * @email thrz@sz000673.com
 * @date 2021-01-26 17:14:46
 */

public interface BlackListDao {

	BlackListDO get(String hmdTywysbm);
	
	List<BlackListDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(BlackListDO blackList);
	
	int update(BlackListDO blackList);

	int updateByPhoneNumber(String phoneNumber,String xsjhsc);
	
	int remove(String HMD_TYWYSBM);

	int removeByPhoneNumber(String phoneNumber);
	
	int batchRemove(String[] hmdTywysbms);
}
