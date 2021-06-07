package com.smart119.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart119.common.redis.shiro.RedisManager;
import com.smart119.common.utils.PageMybatisPlusUtils;
import com.smart119.common.utils.PageUtils;
import com.smart119.system.dao.SysThresholdConfigDao;
import com.smart119.system.domain.SysThresholdConfigDo;
import com.smart119.system.domain.UserDO;
import com.smart119.system.service.SysThresholdConfigService;
import com.smart119.system.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SysThresholdConfigServiceImpl implements SysThresholdConfigService {


    @Resource
    private SysThresholdConfigDao sysThresholdConfigDao;

    @Resource
    private UserService userService;

    @Autowired
    private RedisManager redisManager;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<SysThresholdConfigDo> page = new Page();
        PageMybatisPlusUtils.pageHelperUtils(params, page);
        IPage<SysThresholdConfigDo> pageVo = sysThresholdConfigDao.selectPageVo(page, params);
        if (pageVo.getTotal() > 0) {
            for (SysThresholdConfigDo sysRepeatConfigDo: pageVo.getRecords()) {
                if (!ObjectUtils.isEmpty(sysRepeatConfigDo.getCreateUserId())) {
                   UserDO createUser = userService.get(sysRepeatConfigDo.getCreateUserId());
                   sysRepeatConfigDo.setCreateUserName(createUser.getUsername());
                }

                if (!ObjectUtils.isEmpty(sysRepeatConfigDo.getUpdateUserId())) {
                    UserDO updateUser = userService.get(sysRepeatConfigDo.getUpdateUserId());
                    sysRepeatConfigDo.setUpdateUserName(updateUser.getUsername());
                }
            }
        }
        return new PageUtils(pageVo.getRecords(),
                NumberUtils.toInt(String.valueOf(pageVo.getTotal()), 0));
    }


    @Override
    public SysThresholdConfigDo queryById(Long id) {
        return sysThresholdConfigDao.selectById(id);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveSysRepeatConfig(SysThresholdConfigDo sysRepeatConfigDo) {
         int count = sysThresholdConfigDao.insert(sysRepeatConfigDo);
         if (count > 0 ) {
             return true;
         }
        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean changeStatus(Long id, String cmd) {
        sysThresholdConfigDao.changeStatus(id, cmd);
        SysThresholdConfigDo sysRepeatConfigDo = sysThresholdConfigDao.selectById(id);
        if (StringUtils.equals(sysRepeatConfigDo.getStatus(), cmd)) {
            return  true;
        }
        return false;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateSysRepeatConfig(SysThresholdConfigDo sysRepeatConfigDo) {
        sysThresholdConfigDao.updateById(sysRepeatConfigDo);
        SysThresholdConfigDo sysRepeatConfigDoData = sysThresholdConfigDao.selectById(sysRepeatConfigDo.getId());
        if (sysRepeatConfigDoData.getUpdateUserId().equals(sysRepeatConfigDo.getUpdateUserId())) {
            return true;
        }
        return false;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeRepeatConfigBatch(String[] ids) {
        List<Long> longList = new ArrayList<>();
        for (String idStr: ids) {
            longList.add(NumberUtils.toLong(idStr, -4));
        }
        sysThresholdConfigDao.deleteBatchIds(longList);
        List<SysThresholdConfigDo> sysRepeatConfigDos = sysThresholdConfigDao.selectBatchIds(longList);
        if (sysRepeatConfigDos.size() == 0) {
            return true;
        }
        return false;

    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeRepeatConfigById(Long id) {
        sysThresholdConfigDao.deleteById(id);
        SysThresholdConfigDo sysRepeatConfigDo = sysThresholdConfigDao.selectById(id);
        if (ObjectUtils.isEmpty(sysRepeatConfigDo)) {
            return true;
        }
        return false;
    }
}
