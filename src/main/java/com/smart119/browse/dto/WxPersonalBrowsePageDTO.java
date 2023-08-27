package com.smart119.browse.dto;

import com.smart119.browse.domain.WxPersonalBrowse;

import lombok.Data;


@Data
public class WxPersonalBrowsePageDTO extends WxPersonalBrowse {


    /**
     * 0：表示浏览总数
     * 1：表示付费浏览数
     */
    private String pay;


    /**
     * 每页条数
     */
    private int limit;

    /**
     * 当前页数
     */
    private int currentPage;


}
