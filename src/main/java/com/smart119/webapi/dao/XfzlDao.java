package com.smart119.webapi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart119.webapi.domain.XfzlDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface XfzlDao extends BaseMapper<XfzlDO> {

    IPage<XfzlDO> queryXflzList(@Param("page") Page<XfzlDO> page, @Param("params") Map<String, Object> params);

    List<XfzlDO> queryXflzList(@Param("params") Map<String, Object> params);

    XfzlDO queryXflzById(String xfzlId);

    int updLlCs(String xfzlId);

    int updDzCs(String xfzlId);

    int updDzUserIds(@Param("dzUserIds") String dzUserIds,@Param("xfzlId") String xfzlId);

    /**
     * 批量删除
     * @param xfzlIds
     * @return
     */
    int batchRemove(String[] xfzlIds);
}
