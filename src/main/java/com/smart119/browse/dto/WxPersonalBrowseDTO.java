package com.smart119.browse.dto;

import com.smart119.browse.domain.WxPersonalBrowse;

import lombok.Data;

@Data
public class WxPersonalBrowseDTO extends WxPersonalBrowse {

    private String requestIp;
}
