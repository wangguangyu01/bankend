package com.smart119.webapi.dao;

import com.smart119.webapi.domain.JqcjdpDO;

import java.util.List;
import java.util.Map;

import com.smart119.webapi.domain.XfjgcdxxDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 警情处警调派基本信息
 * @author yanyu
 * @email yanyu@sz000673.com
 * @date 2021-01-28 14:05:38
 */

public interface JqcjdpDao {

	JqcjdpDO get(String jqcjdpTywysbm);
	
	List<JqcjdpDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(JqcjdpDO jqcjdp);
	
	int update(JqcjdpDO jqcjdp);
	
	int remove(String JQCJDP_TYWYSBM);
	
	int batchRemove(String[] jqcjdpTywysbms);

	List<XfjgcdxxDO> findXfjgcdByJqBm(Map<String,Object> map);
}
