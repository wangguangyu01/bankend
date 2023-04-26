package com.smart119.common.utils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.util.ObjectUtils;

import java.util.Map;

/**
 * @Author smart119 1992lcg@163.com
 */
public class PageMybatisPlusUtils {

	public static Page<?> pageHelperUtils(Map<String, Object> params, Page<?> page) {
		// 旧的沿用的是从bootstrap的js的table控件中的分页传参数
		if (params.containsKey("offset") && !params.containsKey("current")) {
			PageUtils mybatisPageUtils = new PageUtils(params);
			page.setCurrent(mybatisPageUtils.getCurrent());
			page.setSize(mybatisPageUtils.getSize());
		}
		// 这里的分页参数是手机端的前端开发人员传递的参数使用limit：一页显示多少条；current：表示当前页
		if (!params.containsKey("offset") &&  params.containsKey("current")) {
			// 如果有limit，没有size的话，每一页显示的条数是limit
			if (params.containsKey("limit") && !params.containsKey("size")) {
				page.setSize(NumberUtils.toLong(String.valueOf(params.get("limit")), 10));
			} else if (!params.containsKey("limit") && params.containsKey("size")) {
				// 如果没有limit，有size的话，每一页显示的条数是size
				page.setSize(NumberUtils.toLong(String.valueOf(params.get("size")), 10));
			} else if (params.containsKey("limit") && params.containsKey("size") ) {
				// 获取map对象中如果有limit参数的时候，获取limit的数据
				long limitPageSize = 0;
				if (!ObjectUtils.isEmpty(params.get("limit"))) {
					limitPageSize = NumberUtils.toLong(String.valueOf(params.get("limit")), 0);
				}
				// 获取map对象中如果有limit参数的时候，获取size的数据
				long pageSize = 0;
				if (!ObjectUtils.isEmpty(params.get("size"))) {
					pageSize = NumberUtils.toLong(String.valueOf(params.get("size")), 0);
				}
				if (limitPageSize > 0 && pageSize <= 0) {
					page.setSize(limitPageSize);
				} else if (limitPageSize <= 0 && pageSize > 0) {
					page.setSize(pageSize);
				} else {
					// 根据前端的架构以及传递的习惯，这里默认使用limit做每一页显示的条数
					page.setSize(10);
				}
			} else {
				page.setSize(10);
			}
			page.setCurrent(NumberUtils.toLong(String.valueOf(params.get("current")), 1));
		}
		return page;
	}

}
