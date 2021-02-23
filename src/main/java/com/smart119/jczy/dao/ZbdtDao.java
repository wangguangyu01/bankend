package com.smart119.jczy.dao;

import com.smart119.jczy.domain.ZbdtDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 值班动态基本信息
 * @author zhangxj
 * @email thrz@sz000673.com
 * @date 2021-01-18 16:02:23
 */
@Mapper
public interface ZbdtDao {

	ZbdtDO get(String zhbTywysbm);
	
	List<ZbdtDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ZbdtDO zbdt);
	
	int update(ZbdtDO zbdt);
	
	int remove(String ZHB_TYWYSBM);
	
	int batchRemove(String[] zhbTywysbms);

	Map<String,Object> xfjyjgTywysbmByUserId(String userId);
}
