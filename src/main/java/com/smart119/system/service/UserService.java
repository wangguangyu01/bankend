package com.smart119.system.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.smart119.system.domain.UserRoleDO;
import com.smart119.system.vo.UserVO;
import org.springframework.stereotype.Service;

import com.smart119.common.domain.Tree;
import com.smart119.system.domain.DeptDO;
import com.smart119.system.domain.UserDO;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface UserService {
	UserDO get(Long id);

	List<UserDO> list(Map<String, Object> map,Long userDeptId);

	int count(Map<String, Object> map);

	int save(UserDO user);

	int update(UserDO user);

	/**
	 * 删除用户，并更新掉【消防救援人员】表的关联关系
	 * @param userId
	 * @return
	 */
	int remove(Long userId);

	int batchremove(Long[] userIds);

	boolean exit(Map<String, Object> params);

	Set<Long> listRoles(Long userId);

	int resetPwd(UserVO userVO,UserDO userDO) throws Exception;
	int adminResetPwd(UserVO userVO) throws Exception;
	Tree<DeptDO> getTree();

	/**
	 * 更新个人信息
	 * @param userDO
	 * @return
	 */
	int updatePersonal(UserDO userDO);

	/**
	 * 更新个人图片
	 * @param file 图片
	 * @param avatar_data 裁剪信息
	 * @param userId 用户ID
	 * @throws Exception
	 */
    Map<String, Object> updatePersonalImg(MultipartFile file, String avatar_data, Long userId) throws Exception;

	/**
	 * 根据权限ID查找用户列表
	 * @param roleId
	 * @return
	 */
	List<UserDO> findByRoleId(Long roleId);

	/**
	 * 删除用户角色
	 * @param userId
	 * @param roleId
	 * @return
	 */
	int removeUserRole(Long userId, Long roleId);

	/**
	 * 批量添加同一角色的多个用户
	 * @param userIdArry
	 * @param roleId
	 * @return
	 */
    int addUserRole(String[] userIdArry, Long roleId);

	/**
	 * 批量删除用户角色
	 * @param userIdArry
	 * @param roleId
	 * @return
	 */
	int batchRemoveUserRole(String[] userIdArry, Long roleId);


	void addPassword();

	/**
	 * 校验用户名是否唯一
	 * @param user
	 * @return
	 */
	boolean checkUserName(UserDO user);



}
