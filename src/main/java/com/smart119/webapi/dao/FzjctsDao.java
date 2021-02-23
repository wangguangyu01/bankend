package com.smart119.webapi.dao;

import com.smart119.webapi.domain.FzjctsDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 辅助决策推送
 * @author liangsl
 * @email 123564081@qq.com
 * @date 2021-02-07 10:30:11
 */
@Mapper
public interface FzjctsDao {

	FzjctsDO get(String fzjctsId);
	
	List<FzjctsDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(FzjctsDO fzjcts);
	
	int update(FzjctsDO fzjcts);
	
	int remove(String FZJCTS_ID);
	
	int batchRemove(String[] fzjctsIds);

	List<FzjctsDO> getFzjcTslistByJqTywysbm(@Param("jqTywysbm") String jqTywysbm);
}
