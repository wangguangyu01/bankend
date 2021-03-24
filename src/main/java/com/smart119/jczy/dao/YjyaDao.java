package com.smart119.jczy.dao;

import com.smart119.jczy.domain.YjyaDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 应急预案基本信息
 * @author zhangxj
 * @email thrz@sz000673.com
 * @date 2021-01-18 16:02:22
 */

public interface YjyaDao {

	YjyaDO get(String yjyaTywysbm);
	
	List<YjyaDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(YjyaDO yjya);
	
	int update(YjyaDO yjya);
	
	int remove(String YJYA_TYWYSBM);
	
	int batchRemove(String[] yjyaTywysbms);

	List<YjyaDO> getYjya4Dp(Map<String,Object> map);

}
