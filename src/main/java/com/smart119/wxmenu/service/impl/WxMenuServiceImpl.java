package com.smart119.wxmenu.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart119.wxmenu.dao.WxMenuMapper;
import com.smart119.wxmenu.domain.WxMenu;
import com.smart119.wxmenu.service.WxMenuService;
@Service
public class WxMenuServiceImpl extends ServiceImpl<WxMenuMapper, WxMenu> implements WxMenuService{

}
