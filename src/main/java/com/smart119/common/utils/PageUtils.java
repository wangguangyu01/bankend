package com.smart119.common.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart119.system.vo.QueryDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @Author smart119 1992lcg@163.com
 */
@Data
public class  PageUtils implements Serializable {
	private static final long serialVersionUID = 1L;
	private int total;
	private List<?> rows;
	/**
	 * 每一页显示条数
	 */
	@ApiModelProperty(notes = "每一页显示的条数", example = " 10")
	private long size = 10L;

	/**
	 * 当前页
	 */
	@ApiModelProperty(notes = "当前页数", example = " 1")
	private long current = 1;

	public PageUtils(List<?> list, int total) {
		this.rows = list;
		this.total = total;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}


	public PageUtils(Map<String, Object> params) {
		long limit = 10L;
		if (params != null && (params.containsKey("limit") || params.containsKey("size"))) {
			if (params.containsKey("limit") && !params.containsKey("size")) {
				this.size = NumberUtils.toInt(String.valueOf(params.get("limit")), 10);
				limit = NumberUtils.toInt(String.valueOf(params.get("limit")), 10);
			} else if (!params.containsKey("limit") && params.containsKey("size")) {
				this.size = NumberUtils.toInt(String.valueOf(params.get("size")), 10);
				limit = NumberUtils.toInt(String.valueOf(params.get("size")), 10);
			} else if (params.containsKey("limit") && params.containsKey("size")) {
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
					this.size = limitPageSize;
					limit = limitPageSize;
				} else if (limitPageSize <= 0 && pageSize > 0) {
					this.size = pageSize;
					limit = pageSize;
				} else {
					// 根据前端的架构以及传递的习惯，这里默认使用limit做每一页显示的条数
					this.size = limit;
				}
			}
		}  else {
			this.size = limit;
		}
		int offset = 0;
		if (params != null && params.containsKey("offset")) {
			offset = NumberUtils.toInt(String.valueOf(params.get("offset")), 0);
		}

		this.current = offset / limit + 1;
	}



	public PageUtils(QueryDto queryDto) {
		this.size = queryDto.getLimit();
		this.current = queryDto.getCurrent();
	}


	public PageUtils(IPage page) {
		this.rows = page.getRecords();
		this.total = new Long(page.getTotal()).intValue();
		this.current=page.getCurrent();
	}


}
