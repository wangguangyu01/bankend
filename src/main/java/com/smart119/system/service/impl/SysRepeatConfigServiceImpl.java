package com.smart119.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart119.common.utils.PageMybatisPlusUtils;
import com.smart119.common.utils.PageUtils;
import com.smart119.system.dao.SysRepeatConfigDao;
import com.smart119.system.domain.SysRepeatConfigDo;
import com.smart119.system.domain.UserDO;
import com.smart119.system.service.SysRepeatConfigService;
import com.smart119.system.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SysRepeatConfigServiceImpl implements SysRepeatConfigService {


    @Resource
    private SysRepeatConfigDao sysRepeatConfigDao;

    @Resource
    private UserService userService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<SysRepeatConfigDo> page = new Page();
        PageMybatisPlusUtils.pageHelperUtils(params, page);
        IPage<SysRepeatConfigDo> pageVo = sysRepeatConfigDao.selectPageVo(page, params);
        if (pageVo.getTotal() > 0) {
            for (SysRepeatConfigDo sysRepeatConfigDo: pageVo.getRecords()) {
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
    public SysRepeatConfigDo queryById(Long id) {
        return sysRepeatConfigDao.selectById(id);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveSysRepeatConfig(SysRepeatConfigDo sysRepeatConfigDo) {
         int count = sysRepeatConfigDao.insert(sysRepeatConfigDo);
         if (count > 0 ) {
             return true;
         }
        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean changeStatus(Long id, String cmd) {
        sysRepeatConfigDao.changeStatus(id, cmd);
        SysRepeatConfigDo sysRepeatConfigDo = sysRepeatConfigDao.selectById(id);
        if (StringUtils.equals(sysRepeatConfigDo.getStatus(), cmd)) {
            return  true;
        }
        return false;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateSysRepeatConfig(SysRepeatConfigDo sysRepeatConfigDo) {
        sysRepeatConfigDao.updateById(sysRepeatConfigDo);
        SysRepeatConfigDo sysRepeatConfigDoData = sysRepeatConfigDao.selectById(sysRepeatConfigDo.getId());
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
        sysRepeatConfigDao.deleteBatchIds(longList);
        List<SysRepeatConfigDo> sysRepeatConfigDos = sysRepeatConfigDao.selectBatchIds(longList);
        if (sysRepeatConfigDos.size() == 0) {
            return true;
        }
        return false;

    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeRepeatConfigById(Long id) {
        sysRepeatConfigDao.deleteById(id);
        SysRepeatConfigDo sysRepeatConfigDo = sysRepeatConfigDao.selectById(id);
        if (ObjectUtils.isEmpty(sysRepeatConfigDo)) {
            return true;
        }
        return false;
    }
}
