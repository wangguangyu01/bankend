package com.smart119.jczy.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart119.jczy.domain.XfclDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 消防车辆基本信息
 * @author liangsl
 * @email 1992lcg@163.com
 * @date 2021-01-15 11:28:42
 */

public interface XfclDao extends BaseMapper<XfclDO> {

	XfclDO get(String xfclTywysbm);


	Map<String,Object> getMap(String id);

	List<XfclDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(XfclDO xfcl);

	int update(XfclDO xfcl);

	int remove(String XFCL_TYWYSBM);

	int batchRemove(String[] xfclTywysbms);

    int carlistcount(Map<String, Object> map);

	List<XfclDO> carlist(Map<String, Object> map);

	List<String> findAllXfclTywysbm();


	int updateCar(@Param("xfclTywysbm")String xfclTywysbm, @Param("clqwztlbdm") String clqwztlbdm);


	IPage<Map<String,Object>> selectPageVoApp(@Param("page") Page<XfclDO> page,
											  @Param("params") Map<String, Object> params);


	List<XfclDO> getDpCarList(Map<String, Object> map);



	/**
	 * 根据出动编号查询车辆
	 * @param params
	 * @return
	 */
	List<XfclDO> getDpCarListByCcbh(Map<String, Object> params);



	/**
	 * 根据调派队站的唯一标识查询车辆
	 * @param dpdzTywysbm
	 * @return
	 */
	List<String> queryByDpdzTyywbs(String dpdzTywysbm);


	/**
	 * 根据车辆唯一标识修改状态
	 * @param map  map.put("clqwztlbdm" , "200")车辆勤务状态
	 *              map.put("xfclTywysbms", list)需要修改的车辆状态的车辆唯一标识
	 * @return
	 */
	int updateClqwztlbdm(Map<String,Object> map);


	/**
	 * 根据参数查询gps中车辆的GPS_VID
	 * @return
	 */
	List<XfclDO> queryGPSDeviceIds();




}
