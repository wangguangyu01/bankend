package com.smart119.webapi.service;

import com.smart119.system.domain.UserDO;
import com.smart119.webapi.domain.XfclcdxxDO;

import java.util.List;
import java.util.Map;

/**
 * 消防车辆出动信息
 * 
 * @author thrz
 * @email thrz@sz000673.com
 * @date 2021-01-28 17:21:21
 */
public interface XfclcdxxService {
	
	XfclcdxxDO get(String xfclCddm);
	
	List<XfclcdxxDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(XfclcdxxDO xfclcdxx);
	
	int update(XfclcdxxDO xfclcdxx);
	
	int remove(String xfclCddm);
	
	int batchRemove(String[] xfclCddms);

	//	根据警情id、机构id 查询出动力量（调派的车辆信息+车辆属性+人员数量）
	List<Map<String,Object>> cdllhx(Map<String, Object> map);

	//  根据警情id 和 车辆id 查询出动车辆信息（车辆信息+车辆属性+人员信息）
	List<Map<String,Object>> cdclhx(Map<String, Object> map);

	//根据警情id 查询出此机构下所有人员信息（是否已经出动，是否此警情出动）
	List<Map<String,Object>> cdryAll(Map<String, Object> map);

	//保存出动力量信息
	boolean saveCdll(String paramsJson,String jqTywysbm,String xfjyjgTywysbm,String userId);


}
