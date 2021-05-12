package com.smart119.system.dao;

import com.smart119.system.domain.UserDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-10-03 09:45:11
 */

public interface UserDao {

	UserDO get(Long userId);
	
	List<UserDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(UserDO user);
	
	int update(UserDO user);
	
	int remove(Long userId);
	
	int batchRemove(Long[] userIds);
	
	Long[] listAllDept();

    List<UserDO> findByRoleId(@Param("roleId") Long roleId);

	List<UserDO> findUser0506();
	List<UserDO> findByRoleId(@Param("roleId") Long roleId);

	/**
	 * 查询用户名是否唯一
	 * @param map
	 * @return
	 */
	int checkUserOne(Map<String,Object> map);
}
