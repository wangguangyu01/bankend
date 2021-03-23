package com.smart119.jczy.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart119.common.utils.PageMybatisPlusUtils;
import com.smart119.common.utils.PageUtils;
import com.smart119.common.utils.UUIDGenerator;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.smart119.jczy.dao.FzjcDao;
import com.smart119.jczy.domain.FzjcDO;
import com.smart119.jczy.service.FzjcService;



@Service
public class FzjcServiceImpl implements FzjcService {
	@Autowired
	private FzjcDao fzjcDao;
	
	@Override
	public FzjcDO get(String fzjcId){
		return fzjcDao.get(fzjcId);
	}
	
	@Override
	public List<FzjcDO> list(Map<String, Object> map){
		return fzjcDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){

		return fzjcDao.count(map);
	}
	
	@Override
	public int save(FzjcDO fzjc){
		fzjc.setFzjcId(UUIDGenerator.getUUID());
		return fzjcDao.save(fzjc);
	}
	
	@Override
	public int update(FzjcDO fzjc){

		return fzjcDao.update(fzjc);
	}
	
	@Override
	public int remove(String fzjcId){

		return fzjcDao.remove(fzjcId);
	}
	
	@Override
	public int batchRemove(String[] fzjcIds){
		return fzjcDao.batchRemove(fzjcIds);
	}


	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		Page<FzjcDO> page = new Page();
		PageMybatisPlusUtils.pageHelperUtils(params, page);
		IPage<FzjcDO> pageVo = fzjcDao.selectPageVo(page, params);
		return new PageUtils(pageVo.getRecords(),
				NumberUtils.toInt(String.valueOf(pageVo.getTotal()), 0));
	}
	
}
