package com.smart119.banner.dao;

import com.smart119.banner.domain.BannerLxDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 素材_banner分类
 * @author liangsl
 * @email liangshengli@sz000673.com
 * @date 2021-03-22 14:45:03
 */

public interface BannerLxDao {

	BannerLxDO get(String bannerLxId);
	
	List<BannerLxDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(BannerLxDO bannerLx);
	
	int update(BannerLxDO bannerLx);
	
	int remove(String banner_lx_id);
	
	int batchRemove(String[] bannerLxIds);
}
