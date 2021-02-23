package com.smart119.jczy.dao;

import com.smart119.jczy.domain.ZzdyDO;

import java.util.List;
import java.util.Map;

import com.smart119.system.domain.DeptDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 作战单元
 * @author shilei
 * @email thrz@sz000673.com
 * @date 2021-01-29 20:42:51
 */
@Mapper
public interface ZzdyDao {

	ZzdyDO get(String zzdyTywybs);
	
	List<ZzdyDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ZzdyDO zzdy);
	
	int update(ZzdyDO zzdy);
	
	int remove(String ZZDY_TYWYBS);
	
	int batchRemove(String[] zzdyTywybss);

	List<DeptDO> deptList(Map<String,Object> map);

	List<Map<String,Object>> zzdyListByJg(Map<String,Object> map);

	List<Map<String,Object>> xfclListByZzdy (String zzdyTywybs);

	DeptDO deptByXfjyjgTywysbm(String xfjyjgTywysbm);
}
