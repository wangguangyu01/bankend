package com.smart119.system.dao;

import com.smart119.system.domain.DeptDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 部门管理
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-10-03 15:35:39
 */
@Mapper
public interface DeptDao {

	DeptDO get(long deptId);

	DeptDO getDeptId(String xfjyjgtysbm);

	List<DeptDO> list(Map<String,Object> map);

	int count(Map<String,Object> map);

	int save(DeptDO dept);

	int update(DeptDO dept);

	int remove(String xfjyjgTywysbm);

	int batchRemove(Long[] deptIds);

	Long[] listParentDept();

	int getDeptUserNumber(String deptId);

	String findXfjyjgxzdmName(String xfjyjgxzdm);

	String findNameByTYWYSBM(String xfjyjgTywysbm);

	List<DeptDO> getDeptByXFJYJGXZDM(@Param("XFJYJGXZDM")String xfjyjgxzdm);

	List<DeptDO> getDeptByRange(@Param("jd")Double jd, @Param("wd")Double wd, @Param("distance")Double distance);

	List<DeptDO> getXfjyjgZdAndDd();

	Map<String,Object> findJyjgxzdmById(String deptId);

}
