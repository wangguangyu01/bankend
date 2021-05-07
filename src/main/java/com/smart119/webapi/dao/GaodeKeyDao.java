package com.smart119.webapi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart119.jczy.domain.FzjcDO;
import com.smart119.webapi.domain.GaodeKeyDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface GaodeKeyDao extends BaseMapper<GaodeKeyDO> {


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
     * 根据条件查询高德key的总数
     * @param map
     * @return
     */
    int count(Map<String, Object> map);

    /**
     * 保存高德key
     * @param key
     * @return
     */
    int save(GaodeKeyDO key);

    /**
     * 修改高德key
     * @param key
     * @return
     */
    int update(GaodeKeyDO key);

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



    List<String> queryListUseable(@Param("gaodeKeyParm") GaodeKeyDO gaodeKeyParm);



    IPage<GaodeKeyDO> selectPageVo(@Param("page") Page<GaodeKeyDO> page, @Param("map") Map<String, Object> map);
}
