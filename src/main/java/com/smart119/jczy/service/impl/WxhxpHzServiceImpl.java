package com.smart119.jczy.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart119.common.utils.PageMybatisPlusUtils;
import com.smart119.xwzx.domain.XwZxDO;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.smart119.common.utils.PageUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.smart119.jczy.dao.WxhxpHzDao;
import com.smart119.jczy.domain.WxhxpHzDO;
import com.smart119.jczy.service.WxhxpHzService;



@Service
public class WxhxpHzServiceImpl  implements WxhxpHzService {

	@Autowired
	private WxhxpHzDao wxhxpHzDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
		Page<WxhxpHzDO> page = new Page();
		PageMybatisPlusUtils.pageHelperUtils(params, page);
		IPage<WxhxpHzDO> pageVo = wxhxpHzDao.selectPageVo(page, params);
		return new PageUtils(pageVo.getRecords(),
							 NumberUtils.toInt(String.valueOf(pageVo.getTotal()), 0));
    }

	@Override
	public WxhxpHzDO queryById(Long id) {
		return wxhxpHzDao.selectById(id);
	}

	@Override
	public int save(WxhxpHzDO wxhxpHzDO) {
		return wxhxpHzDao.save(wxhxpHzDO);
	}


	@Override
	public int update(WxhxpHzDO wxhxpHzDO) {

		return wxhxpHzDao.update(wxhxpHzDO);
	}

	@Override
	public int remove(String id) {
		return wxhxpHzDao.remove(id);
	}


	@Override
	public int batchRemove(String[] ids) {
		return wxhxpHzDao.batchRemove(ids);
	}
	@Override
	public List<WxhxpHzDO> list(Map<String, Object> map){
		return wxhxpHzDao.list(map);
	}

	@Override
	public int count(Map<String, Object> map){
		return wxhxpHzDao.count(map);
	}

	@Override
	public WxhxpHzDO get(String id){
		return wxhxpHzDao.get(id);
	}

}
