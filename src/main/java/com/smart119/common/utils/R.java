package com.smart119.common.utils;

import com.smart119.common.enums.ResponseStatusEnum;
import org.springframework.util.ObjectUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class R extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;

	public R() {
		put("code", 0);
		put("msg", "操作成功");
	}

	public static R error() {
		return error(1, "操作失败");
	}

	public static R error(String msg) {
		return error(500, msg);
	}

	public static R error(int code, String msg) {
		R r = new R();
		r.put("code", code);
		r.put("msg", msg);
		return r;
	}

	public static R ok(String msg) {
		R r = new R();
		r.put("msg", msg);
		return r;
	}

	public static R ok(Object data) {
		R r = new R();
		r.put("data", data);
		return r;
	}

	public static R ok(Map<String, Object> map) {
		R r = new R();
		r.putAll(map);
		return r;
	}

	public static R ok() {
		return new R();
	}

	@Override
	public R put(String key, Object value) {
		super.put(key, value);
		return this;
	}
    /**
     * 传入responseStatusEnum枚举
     * @param responseStatusEnum
     * @return
     */
    public static R error(ResponseStatusEnum responseStatusEnum) {
        R r = new R();
        r.put("code", responseStatusEnum.getCode());
        r.put("msg", responseStatusEnum.getDesc());
        return r;
    }
	public R setExtraNote(String extraNote) {
		super.put("extraNote", extraNote);
		return this;
	}
	public R setOnlyExtraNote(Object id) {
		if(!ObjectUtils.isEmpty(id)) {
			super.put("extraNote", new StringBuilder("数据唯一标识为 ").append(id).append(",").toString());
		}
		return this;
	}
	public R setOnlyExtraNote(Object[] id) {
		if(!ObjectUtils.isEmpty(id)) {
			super.put("extraNote", new StringBuilder("数据唯一标识为 ").append(Arrays.toString(id)).append(",").toString());
		}
		return this;
	}
}
