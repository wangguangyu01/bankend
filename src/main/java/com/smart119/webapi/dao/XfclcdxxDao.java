package com.smart119.webapi.dao;

import com.smart119.webapi.domain.XfclcdxxDO;

import java.util.List;
import java.util.Map;

import com.smart119.webapi.domain.XfjgcdxxDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 消防车辆出动信息
 * @author thrz
 * @email thrz@sz000673.com
 * @date 2021-01-28 17:21:21
 */
@Mapper
public interface XfclcdxxDao {

	XfclcdxxDO get(String xfclCddm);
	
	List<XfclcdxxDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(XfclcdxxDO xfclcdxx);
	
	int update(XfclcdxxDO xfclcdxx);
	
	int remove(String XFCL_CDDM);
	
	int batchRemove(String[] xfclCddms);

	Map<String,Object> xfclById(String xfclTywysbm);

	List<Map<String,Object>> xfclSxxx(String xfclTywysbm);

	List<Map<String,Object>> cdryByJqCl(Map<String,Object> map);

	List<Map<String,Object>> xfjyryByJgAll(Map<String,Object> map);

	List<Map<String,Object>> cldphx(Map<String,Object> map);

    int removeCdry(String map);

    int removeCdcl(String map);

    XfjgcdxxDO xfjgcdxxByJqJg(Map<String,Object> map);

	int rycthf(String ryid);

	int clcdhf(String clid);

	int clcd(String xfjyryTywysbm);

	int rycd(String xfclTywysbm);

	List<Map<String,Object>> findCdry(Map<String,Object> map);

	List<Map<String,Object>> findCdcl(Map<String,Object> map);

	List<Map<String,Object>> findRycdxx(Map<String,Object> map);

	List<Map<String,Object>> findClcdxx(Map<String,Object> map);

}
