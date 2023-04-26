package com.smart119.system.controller;

import com.smart119.common.annotation.Log;
import com.smart119.common.config.BootdoConfig;
import com.smart119.common.controller.BaseController;
import com.smart119.common.domain.FileDO;
import com.smart119.common.domain.Tree;
import com.smart119.common.enums.ResponseStatusEnum;
import com.smart119.common.service.FileService;
import com.smart119.common.utils.*;
import com.smart119.system.domain.MenuDO;
import com.smart119.system.domain.UserDO;
import com.smart119.system.service.MenuService;
import com.smart119.system.service.UserService;
import java.util.Objects;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Controller
public class LoginController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    MenuService menuService;
    @Autowired
    FileService fileService;
    @Autowired
    BootdoConfig bootdoConfig;



    @Autowired
    private UserService userService;

    private ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap(10);

    @GetMapping({"/", ""})
    String welcome(Model model) {

        return "redirect:/index";
    }

    @Log("请求访问主页")
    @GetMapping({"/index"})
    String index(Model model,HttpServletRequest request) {
        List<Tree<MenuDO>> menus = menuService.listMenuTree(getUserId());
        model.addAttribute("menus", menus);
         FileDO fileDO = fileService.get(getUser().getPicId());
        if (fileDO != null && fileDO.getUrl() != null) {
            if (fileService.isExist(fileDO.getUrl())) {
                model.addAttribute("picUrl", fileDO.getUrl());
            } else {
                model.addAttribute("picUrl", "/img/logo.png");
            }
        } else {
            model.addAttribute("picUrl", "/img/logo.png");
        }
        model.addAttribute("username", getUser().getUsername());
        model.addAttribute("Token", request.getSession().getId());
        return "index_v1";
    }

    @GetMapping("/login")
    String login(Model model) {
        //model.addAttribute("username", bootdoConfig.getUsername());
        //model.addAttribute("password", bootdoConfig.getPassword());
        return "login";
    }

    @Log("登录")
    @PostMapping("/login")
    @ResponseBody
    R ajaxLogin(String username, String password,String verify,HttpServletRequest request) {

//        上线是打开下方，跳过验证码注解
        try {
            //从session中获取随机数
            String random = (String)concurrentHashMap.get( "captcha_code_" + request.getRequestedSessionId());
            if (StringUtils.isBlank(verify)) {
                return R.error("请输入验证码");
            }
            if (!verify.equals(random)) {
                return R.error("请输入正确的验证码");
            }
        } catch (Exception e) {
            logger.error("验证码校验失败", e);
            return R.error("验证码校验失败");
        }
        //按照用户名查询用户
        UserDO userDO = userService.getUserByUsername(username);
        if (Objects.isNull(userDO)) {
            return R.error(ResponseStatusEnum.RESCODE_10010);
        }
        password = MD5Utils.encrypt(userDO.getSalt(),password);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            UserDO user = (UserDO) subject.getPrincipals().getPrimaryPrincipal();
            Map params = new HashMap();
            params.put("userid",user.getUserId());
            String serssionId = subject.getSession().getId().toString();
            Map map = new HashMap();
            map.put("user",user);
            map.put("token",serssionId);
            concurrentHashMap.remove( "captcha_code__" + request.getRequestedSessionId());
            return R.ok(map);
        } catch (AuthenticationException e) {
      // 判断是否超过登录次数了，如果超过登录次数，则锁定该用户
            logger.error("登录失败",e);
            return R.error(ResponseStatusEnum.RESCODE_10003.getCode(),"用户或密码错误");
        }
    }

    @GetMapping("/logout")
    String logout() {
        ShiroUtils.logout();
        return "redirect:/login";
    }

    @GetMapping("/weblogout")
    @ResponseBody
    R weblogout() {
        ShiroUtils.logout();
        return R.ok();
    }

    @GetMapping("/main")
    String main() {
        return "main";
    }

    /**
     * 生成验证码
     */
    @GetMapping(value = "/getVerify")
    public void getVerify(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
            response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expire", 0);
            RandomValidateCodeUtil randomValidateCode = new RandomValidateCodeUtil();
            randomValidateCode.getRandcode(concurrentHashMap, request, response);//输出验证码图片方法

        } catch (Exception e) {
            logger.error("获取验证码失败>>>> ", e);
        }
    }



}
