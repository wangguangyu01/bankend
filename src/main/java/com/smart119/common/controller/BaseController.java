package com.smart119.common.controller;

import org.springframework.stereotype.Controller;
import com.smart119.common.utils.ShiroUtils;
import com.smart119.system.domain.UserDO;

@Controller
public class BaseController {
	public UserDO getUser() {
		return ShiroUtils.getUser();
	}

	public Long getUserId() {
		return getUser().getUserId();
	}

	public String getUsername() {
		return getUser().getUsername();
	}
}