package com.smart119.jczy.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smart119.jczy.domain.KbmbDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 快报模板
 * @author shilei
 * @email shichengyuan@sz000673.com
 * @date 2021-06-03 14:39:20
 */
public interface KbmbDao extends BaseMapper<KbmbDO> {

	KbmbDO get(String kbmbId);
	
	List<KbmbDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(KbmbDO kbmb);
	
	int update(KbmbDO kbmb);
	
	int remove(String kbmb_id);
	
	int batchRemove(String[] kbmbIds);
}
