package com.smart119.jczy.dao;

import com.smart119.jczy.domain.XfjyryDO;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 消防救援人员
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2021-01-15 10:53:18
 */
@Mapper
public interface XfjyryDao {

	XfjyryDO get(String xfjyryTywysbm);
	
	List<XfjyryDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(XfjyryDO xfjyry);
	
	int update(XfjyryDO xfjyry);
	
	int remove(String XFJYRY_TYWYSBM);
	
	int batchRemove(String[] xfjyryTywysbms);

	/**
	 * 删除掉消防救援人员与系统用户的关联关系
	 * @param userIdList
	 * @return
	 */
    int updateUserIdForNull(@Param("userIdList") List<String> userIdList);

	/**
	 * 根据主键，查找关联的全部UserId集合
	 * @param xfjyryTywysbms
	 * @return
	 */
	List<String> findUserIdByXfjyryTywysbms(String[] xfjyryTywysbms);
}
