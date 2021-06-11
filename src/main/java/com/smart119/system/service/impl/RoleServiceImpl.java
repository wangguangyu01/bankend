package com.smart119.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.smart119.system.dao.RoleAppDao;
import com.smart119.system.domain.RoleAppDO;
import com.smart119.system.service.RoleAppService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smart119.system.dao.RoleDao;
import com.smart119.system.dao.RoleMenuDao;
import com.smart119.system.dao.UserDao;
import com.smart119.system.dao.UserRoleDao;
import com.smart119.system.domain.RoleDO;
import com.smart119.system.domain.RoleMenuDO;
import com.smart119.system.service.RoleService;


@Service
public class RoleServiceImpl implements RoleService {

    public static final String ROLE_ALL_KEY = "\"role_all\"";

    public static final String DEMO_CACHE_NAME = "role";

    @Autowired
    RoleDao roleMapper;
    @Autowired
    RoleMenuDao roleMenuMapper;
    @Autowired
    UserDao userMapper;
    @Autowired
    UserRoleDao userRoleMapper;
    @Autowired
    private RoleAppService roleAppService;

    @Override
    public List<RoleDO> list() {
        List<RoleDO> roles = roleMapper.list(new HashMap<>(16));
        return roles;
    }


    @Override
    public List<RoleDO> list(Long userId) {
        List<Long> rolesIds = userRoleMapper.listRoleId(userId);
        List<RoleDO> roles = roleMapper.list(new HashMap<>(16));
        for (RoleDO roleDO : roles) {
            roleDO.setRoleSign("false");
            for (Long roleId : rolesIds) {
                if (Objects.equals(roleDO.getRoleId(), roleId)) {
                    roleDO.setRoleSign("true");
                    break;
                }
            }
        }
        return roles;
    }
    @Transactional
    @Override
    public int save(RoleDO role) {
        int count = roleMapper.save(role);
        List<Long> menuIds = role.getMenuIds();
        List<Integer> appIds = role.getAppIds();
        Long roleId = role.getRoleId();
        List<RoleMenuDO> rms = new ArrayList<>();
        for (Long menuId : menuIds) {
            RoleMenuDO rmDo = new RoleMenuDO();
            rmDo.setRoleId(roleId);
            rmDo.setMenuId(menuId);
            rms.add(rmDo);
        }
        roleMenuMapper.removeByRoleId(roleId);
        if (rms.size() > 0) {
            roleMenuMapper.batchSave(rms);
        }
        List<RoleAppDO> apps = new ArrayList<>();
        for (Integer appId : appIds) {
            RoleAppDO appDO = new RoleAppDO();
            appDO.setRoleId(roleId);
            appDO.setAppId(appId);
            apps.add(appDO);
        }
        if (CollectionUtils.isNotEmpty(apps)) {
            roleAppService.saveBatch(apps);
        }
        return count;
    }

    @Transactional
    @Override
    public int remove(Long id) {
        int count = roleMapper.remove(id);
        userRoleMapper.removeByRoleId(id);
        roleMenuMapper.removeByRoleId(id);
        return count;
    }

    @Override
    public RoleDO get(Long id) {
        RoleDO roleDO = roleMapper.get(id);
        return roleDO;
    }

    @Override
    public int update(RoleDO role) {
        int r = roleMapper.update(role);
        List<Long> menuIds = role.getMenuIds();
        Long roleId = role.getRoleId();
        List<Integer> appIds = role.getAppIds();
        roleMenuMapper.removeByRoleId(roleId);
        List<RoleMenuDO> rms = new ArrayList<>();
        for (Long menuId : menuIds) {
            RoleMenuDO rmDo = new RoleMenuDO();
            rmDo.setRoleId(roleId);
            rmDo.setMenuId(menuId);
            rms.add(rmDo);
        }
        if (rms.size() > 0) {
            roleMenuMapper.batchSave(rms);
        }

        List<RoleAppDO> apps = new ArrayList<>();
        QueryWrapper delQuery=new QueryWrapper();
        delQuery.eq("role_id",role.getRoleId());
        roleAppService.remove(delQuery);
        for (Integer appId : appIds) {
            RoleAppDO appDO = new RoleAppDO();
            appDO.setRoleId(roleId);
            appDO.setAppId(appId);
            apps.add(appDO);
        }
        if (CollectionUtils.isNotEmpty(apps)) {
            roleAppService.saveBatch(apps);
        }

        return r;
    }

    @Override
    public int batchremove(Long[] ids) {
        int r = roleMapper.batchRemove(ids);
        return r;
    }

}
