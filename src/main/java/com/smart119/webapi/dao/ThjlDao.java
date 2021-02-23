package com.smart119.webapi.dao;

import com.smart119.webapi.domain.ThjlDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 通话记录基本信息
 * @author yanyu
 * @email yanyu@sz000673.com
 * @date 2021-01-28 12:51:27
 */
@Mapper
public interface ThjlDao {

	ThjlDO get(String thjlTywysbm);
	
	List<ThjlDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ThjlDO thjl);
	
	int update(ThjlDO thjl);
	
	int remove(String THJL_TYWYSBM);
	
	int batchRemove(String[] thjlTywysbms);

	List<Map<String,Object>> thjlcx(String thjlTywysbm);

	Map<String,Object> dzwjwz(String bjTywysbm);

	List<Map<String,Object>> zywzList(String jqTywysbm);

	List<Map<String,Object>> tqdzList(String jqTywysbm);

	List<Map<String,Object>> tqysList(String jqTywysbm);

	Map<String,Object> findThjlTywysbm(String jqTywysbm);


}
