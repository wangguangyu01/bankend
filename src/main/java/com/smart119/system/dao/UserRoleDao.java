package com.smart119.system.dao;

import com.smart119.system.domain.RoleDO;
import com.smart119.system.domain.UserRoleDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 用户与角色对应关系
 *
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-10-03 11:08:59
 */

public interface UserRoleDao {

    UserRoleDO get(Long id);

    List<UserRoleDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(UserRoleDO userRole);

    int update(UserRoleDO userRole);

    int remove(Long id);

    int batchRemove(Long[] ids);

    List<Long> listRoleId(Long userId);

    int removeByUserId(Long userId);

    int removeByRoleId(Long roleId);

    int batchSave(List<UserRoleDO> list);

    int batchRemoveByUserId(Long[] ids);

    int removeUserRole(@Param("userId") Long userId, @Param("roleId") Long roleId);

    int deleteByroleIdAndUserIdArry(@Param("roleId") Long roleId, @Param("userIdArry") String[] userIdArry);

    int saveByroleIdAndUserIdArry(@Param("roleId") Long roleId, @Param("userIdArry") String[] userIdArry);

    /**
     * 根据用户id查询角色
     * @param userId
     * @return
     */
    Boolean queryRoleIsAdmin(@Param("userId") Long userId);
}
