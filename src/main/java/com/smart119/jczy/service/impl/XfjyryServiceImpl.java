package com.smart119.jczy.service.impl;

import com.smart119.common.utils.MD5Utils;
import com.smart119.jczy.dao.XfjyryDao;
import com.smart119.jczy.domain.XfjyryDO;
import com.smart119.jczy.service.XfjyryService;
import com.smart119.system.dao.UserDao;
import com.smart119.system.dao.UserRoleDao;
import com.smart119.system.domain.UserDO;
import com.smart119.system.service.DeptService;
import com.smart119.system.service.UserService;
import lombok.val;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;





@Service
public class XfjyryServiceImpl implements XfjyryService {
	@Autowired
	private XfjyryDao xfjyryDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private UserRoleDao userRoleDao;

	@Autowired
	private DeptService deptService;

	@Autowired
	private UserService userService;
	@Override
	public XfjyryDO get(String xfjyryTywysbm){
		return xfjyryDao.get(xfjyryTywysbm);
	}

	@Override
	public List<XfjyryDO> list(Map<String, Object> map){
		return xfjyryDao.list(map);
	}

	@Override
	public List<XfjyryDO> listOther(Map<String, Object> map){
		return xfjyryDao.listOther(map);
	}

	@Override
	public int count(Map<String, Object> map){
		return xfjyryDao.count(map);
	}

	@Transactional
	@Override
	public int save(XfjyryDO xfjyry){

		if(StringUtils.equals(xfjyry.getIsCreateUser(),"1")){
			int result = saveUser(xfjyry);
			if(result == -1){
				return result;
			}
		}

		return xfjyryDao.save(xfjyry);
	}

	@Override
	@Transactional
	public int update(XfjyryDO xfjyry){

		//新增用户
		if(StringUtils.equals(xfjyry.getIsCreateUser(),"1")){
			int result = saveUser(xfjyry);
			if(result == -1){
				return result;
			}
		}

		//判断是否需要修改用户名
		if(StringUtils.isNotBlank(xfjyry.getUserid())){
			UserDO user = userDao.get(Long.parseLong(xfjyry.getUserid()));
			if(!StringUtils.equals(user.getUsername(),xfjyry.getYdLxdh())){
				//验证是否重复
				String username = xfjyry.getYdLxdh();
				Map<String, Object> params = new HashMap<>();
				params.put("username",username);
				boolean isHave = userService.exit(params);
				if(isHave){
					return -1;
				}
				user.setUsername(username);
				userDao.update(user);
			}
		}

		return xfjyryDao.update(xfjyry);
	}

	private int saveUser(XfjyryDO xfjyry){
		//验证用户名是否重复
		String username = xfjyry.getYdLxdh();
		Map<String, Object> params = new HashMap<>();
		params.put("username",username);
		boolean isHave = userService.exit(params);
		if(isHave){
			return -1;
		}

		//新增人员
		UserDO userDO = new UserDO();
		userDO.setUsername(username);    //用户名
		userDO.setName(xfjyry.getXm());        //姓名
		userDO.setPassword(MD5Utils.encrypt(xfjyry.getYdLxdh(),xfjyry.getYdLxdh())); //密码
		userDO.setStatus(1);                                   //状态
		userDO.setXfjyjgTywysbm(xfjyry.getSjszjgTywysbm());  //实际救援机构唯一识别码
		userDO.setEmail(xfjyry.getHlwDzxx());                //互联网
		userDO.setDeptId(deptService.getDeptId(xfjyry.getSjszjgTywysbm()).getDeptId()==null?null:deptService.getDeptId(xfjyry.getSjszjgTywysbm()).getDeptId()); //dept主键ID

		//角色
		List<Long> roleIds = new ArrayList<>();
		for(Long roleId : xfjyry.getRole()){
			roleIds.add(roleId);
		}
		userDO.setRoleIds(roleIds);
		userService.save(userDO);
		xfjyry.setUserid(userDO.getUserId().toString());
		return 0;
	}



	@Override
	@Transactional
	public int remove(String xfjyryTywysbm){
		XfjyryDO xfjyryDO = xfjyryDao.get(xfjyryTywysbm);
		if(StringUtils.isNotBlank(xfjyryDO.getUserid())){
			userService.remove(Long.parseLong(xfjyryDO.getUserid()));
		}
		return xfjyryDao.remove(xfjyryTywysbm);
	}

	@Override
	@Transactional
	public int batchRemove(String[] xfjyryTywysbms){
		//根据xfjyryTywysbms查找userId的集合
		List<String> userIdList = xfjyryDao.findUserIdByXfjyryTywysbms(xfjyryTywysbms);
		Long [] userIdArry = new Long[userIdList.size()];
		for(int i = 0; i < userIdList.size(); i++){
			userIdArry[i] = Long.parseLong(userIdList.get(i));
		}
		//批量删除用户权限
		userRoleDao.batchRemoveByUserId(userIdArry);
		//批量删除用户
		userDao.batchRemove(userIdArry);
		//批量删除消防救援人员
		return xfjyryDao.batchRemove(xfjyryTywysbms);
	}

}
