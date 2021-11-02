package com.smart119.jczy.service;

import com.smart119.jczy.domain.WxxfzDO;
import org.apache.ibatis.annotations.Param;


import java.util.List;
import java.util.Map;

/**
 * 微型消防站
 *
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2021-01-19 13:52:11
 */
public interface WxxfzService {

	WxxfzDO get(String wxxfzTywysbm);

	List<WxxfzDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(WxxfzDO wxxfz);

	int update(WxxfzDO wxxfz);

	int remove(String wxxfzTywysbm);

	int batchRemove(String[] wxxfzTywysbms);

	List<WxxfzDO> getWxxfzByRange(Double jd, Double wd, Double distance);

	List<Map<String,Object>> qyldList(Map<String, Object> map);


	/**
	 * 删除
	 * @param wxxfzTywysbm
	 * @return
	 */
	int updateStatus(String wxxfzTywysbm);
}
