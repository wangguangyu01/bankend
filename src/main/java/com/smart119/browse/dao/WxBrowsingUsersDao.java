package com.smart119.browse.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.smart119.browse.domain.WxBrowsingUsers;
import com.smart119.browse.dto.WxPersonalBrowsePageDTO;
import com.smart119.browse.vo.WxUserBrowsingUsersVo;
import org.apache.ibatis.annotations.Param;

public interface WxBrowsingUsersDao extends BaseMapper<WxBrowsingUsers> {


    /**
     * 查询
     * @param page
     * @param wxPersonalBrowseDTO
     * @return
     */
    IPage<WxUserBrowsingUsersVo> queryBrowsingUsersPage(@Param("page") Page page,
                                                        @Param("wxPersonalBrowseDTO") WxPersonalBrowsePageDTO wxPersonalBrowseDTO);
}
