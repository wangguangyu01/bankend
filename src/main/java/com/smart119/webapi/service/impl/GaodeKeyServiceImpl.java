package com.smart119.webapi.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart119.common.utils.PageMybatisPlusUtils;
import com.smart119.common.utils.PageUtils;
import com.smart119.webapi.dao.GaodeKeyDao;
import com.smart119.webapi.domain.GaodeKeyDO;
import com.smart119.webapi.service.GaodeKeyService;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 *高德key的Service
 *
 * @author wangguangyu
 * @email wangguangyu@sz000673.com
 * @date 2021-04-30 11:15:12
 */
@Service
public class GaodeKeyServiceImpl implements GaodeKeyService {

    @Resource
    private GaodeKeyDao gaodeKeyDao;

    @Override
    public GaodeKeyDO get(Integer id) {
        return gaodeKeyDao.get(id);
    }

    @Override
    public List<GaodeKeyDO> list(Map<String, Object> map) {
        return gaodeKeyDao.list(map);
    }

    @Override
    public List<String> list(GaodeKeyDO gaodeKeyParam) {
        return  gaodeKeyDao.queryListUseable(gaodeKeyParam);
    }

    @Override
    public int count(Map<String, Object> map) {
        return gaodeKeyDao.count(map);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int save(GaodeKeyDO gaodeKeyDO) {
        return gaodeKeyDao.insert(gaodeKeyDO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(GaodeKeyDO gaodeKeyDO) {
        return gaodeKeyDao.update(gaodeKeyDO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int remove(Integer id) {
        return gaodeKeyDao.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchRemove(Integer[] ids) {
        return gaodeKeyDao.batchRemove(ids);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<GaodeKeyDO> page = new Page();
        PageMybatisPlusUtils.pageHelperUtils(params, page);
        IPage<GaodeKeyDO> pageVo = gaodeKeyDao.selectPageVo(page, params);
        return new PageUtils(pageVo.getRecords(),
                NumberUtils.toInt(String.valueOf(pageVo.getTotal()), 0));
    }
}
