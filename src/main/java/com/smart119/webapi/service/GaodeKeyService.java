package com.smart119.webapi.service;

import com.smart119.common.utils.PageUtils;
import com.smart119.webapi.domain.GaodeKeyDO;

import java.util.List;
import java.util.Map;


/**
 *高德key的Service
 *
 * @author wangguangyu
 * @email wangguangyu@sz000673.com
 * @date 2021-04-30 11:15:12
 */
public interface GaodeKeyService {


    /**
     * 根据id查询高德key
     * @param id
     * @return
     */
    GaodeKeyDO get(Integer id);

    /**
     * 根据条件查询高德的key的列表
     * @param map
     * @return
     */
    List<GaodeKeyDO> list(Map<String, Object> map);

    /**
     * 根据条件查询高德key
     * @param gaodeKeyParam 查询条件
     * @return
     */
    List<String> list(GaodeKeyDO gaodeKeyParam);


    /**
     * 根据条件查询高德key的总数
     * @param map
     * @return
     */
    int count(Map<String, Object> map);

    /**
     * 保存高德key
     * @param gaodeKeyDO
     * @return
     */
    int save(GaodeKeyDO gaodeKeyDO);

    /**
     * 修改高德key
     * @param gaodeKeyDO
     * @return
     */
    int update(GaodeKeyDO gaodeKeyDO);

    /**
     * 删除一个高德key
     * @param id
     * @return
     */
    int remove(Integer id);

    /**
     * 批量删除一个高德key
     * @param ids
     * @return
     */
    int batchRemove(Integer[] ids);


    PageUtils queryPage(Map<String, Object> params);

}
