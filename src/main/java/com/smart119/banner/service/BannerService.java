package com.smart119.banner.service;

import com.smart119.banner.domain.BannerDO;

import java.util.List;
import java.util.Map;

/**
 * 素材_banner
 * 
 * @author liangsl
 * @email liangshengli@sz000673.com
 * @date 2021-03-22 14:45:03
 */
public interface BannerService {
	
	BannerDO get(String scBannerId);
	
	List<BannerDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(BannerDO banner);
	
	int update(BannerDO banner);
	
	int remove(String scBannerId);
	
	int batchRemove(String[] scBannerIds);
}
