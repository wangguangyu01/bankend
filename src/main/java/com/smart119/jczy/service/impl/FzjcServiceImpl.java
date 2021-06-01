package com.smart119.jczy.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart119.common.utils.ExportExcelSeedBack;
import com.smart119.common.utils.PageMybatisPlusUtils;
import com.smart119.common.utils.PageUtils;
import com.smart119.common.utils.UUIDGenerator;
import com.smart119.jqxx.utils.ExportExcel;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.smart119.jczy.dao.FzjcDao;
import com.smart119.jczy.domain.FzjcDO;
import com.smart119.jczy.service.FzjcService;



@Service
public class FzjcServiceImpl extends ServiceImpl<FzjcDao, FzjcDO> implements FzjcService {
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


	@Override
	public ExportExcel exportData(Map<String, Object> params, String fileName) throws InvocationTargetException, IllegalAccessException {
		params.put("limit", 10000);
		params.put("offset", 0);
		//查询列表数据
		PageUtils page = this.queryPage(params);
		List<FzjcDO> list = (List<FzjcDO>) page.getRows();

		ExportExcel exportExcel = new ExportExcel(fileName, FzjcDO.class);
		for (FzjcDO fzjcDO : list) {
			List<Object> list1 = exportExcel.getExportData(fzjcDO);
			Row row = exportExcel.addRow();
			for (int i = 0; i < list1.size(); i++) {
				exportExcel.addCell(row,i,list1.get(i));
			}
		}
		return  exportExcel;
	}

}
