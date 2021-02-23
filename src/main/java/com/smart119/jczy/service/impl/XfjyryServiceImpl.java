package com.smart119.jczy.service.impl;

import com.smart119.common.utils.MD5Utils;
import com.smart119.jczy.dao.XfjyryDao;
import com.smart119.jczy.domain.XfjyryDO;
import com.smart119.jczy.service.XfjyryService;
import com.smart119.system.domain.UserDO;
import com.smart119.system.service.DeptService;
import com.smart119.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;





@Service
public class XfjyryServiceImpl implements XfjyryService {
	@Autowired
	private XfjyryDao xfjyryDao;

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
	public int count(Map<String, Object> map){
		return xfjyryDao.count(map);
	}

	@Transactional
	@Override
	public int save(XfjyryDO xfjyry){
		//新增人员
		UserDO userDO = new UserDO();
		userDO.setUsername(xfjyry.getYdLxdh());    //用户名
		userDO.setName(xfjyry.getXm());        //姓名
		userDO.setPassword(MD5Utils.encrypt(xfjyry.getYdLxdh(),xfjyry.getYdLxdh())); //密码
		userDO.setStatus(1);                                   //状态
		userDO.setXfjyjgTywysbm(xfjyry.getSjszjgTywysbm());  //实际救援机构唯一识别码
		userDO.setEmail(xfjyry.getHlwDzxx());                //互联网
		userDO.setDeptId(deptService.getDeptId(xfjyry.getSjszjgTywysbm()).getDeptId()==null?null:deptService.getDeptId(xfjyry.getSjszjgTywysbm()).getDeptId()); //dept主键ID
		//角色
		List<Long> roleIds = new ArrayList<>();
		roleIds.add(61L);
		userDO.setRoleIds(roleIds);
		userService.save(userDO);
		xfjyry.setUserid(userDO.getUserId().toString());
		return xfjyryDao.save(xfjyry);
	}

	@Override
	public int update(XfjyryDO xfjyry){
		return xfjyryDao.update(xfjyry);
	}

	@Override
	public int remove(String xfjyryTywysbm){
		return xfjyryDao.remove(xfjyryTywysbm);
	}

	@Override
	public int batchRemove(String[] xfjyryTywysbms){
		return xfjyryDao.batchRemove(xfjyryTywysbms);
	}

}
