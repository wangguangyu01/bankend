package com.smart119.jczy.dao;

import com.smart119.jczy.domain.WblxrDO;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;



/**
 * 外部联系人
 * @author shichengyuan
 * @email shichengyuan@sz000673.com
 * @date 2021-03-24 17:25:55
 */
public interface WblxrDao extends BaseMapper<WblxrDO> {

    WblxrDO get(String zdbwTywysbm);

    List<WblxrDO> list(Map<String, Object> map);

    List<WblxrDO> listOther(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(WblxrDO wblxrDO);

    int update(WblxrDO wblxrDO);

    int remove(String String);

    int batchRemove(String[] Strings);
}
