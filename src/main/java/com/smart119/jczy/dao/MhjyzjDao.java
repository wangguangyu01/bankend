package com.smart119.jczy.dao;



import java.util.List;
import java.util.Map;

import com.smart119.jczy.domain.MhjyzjDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 灭火救援专家基本信息
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2021-01-18 17:41:47
 */
@Mapper
public interface MhjyzjDao {

	MhjyzjDO get(String mhjyzjTywysbm);
	
	List<MhjyzjDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(MhjyzjDO mhjyzj);
	
	int update(MhjyzjDO mhjyzj);
	
	int remove(String MHJYZJ_TYWYSBM);
	
	int batchRemove(String[] mhjyzjTywysbms);

	List<Map<String,Object>> yjxfzjlb();

	List<Map<String,Object>> mhjyzjBylyfl(String id);
}
