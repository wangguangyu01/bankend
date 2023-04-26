package com.smart119.system.config;

import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.Writer;

/**
 * @ClassName : CustomFormAuthenticationFilter
 * @Description : CustomFormAuthen
 * @Author : Liangsl
 * @Date: 2021-01-26 15:27
 */

public class CustomFormAuthenticationFilter extends FormAuthenticationFilter {
    Logger logger  = LoggerFactory.getLogger(FormAuthenticationFilter.class);

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {

        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        if (isLoginRequest(request, response)) {
            if (isLoginSubmission(request, response)) {
                if (logger.isTraceEnabled()) {
                    logger.trace("Login submission detected.  Attempting to execute login.");
                }
                return executeLogin(request, response);
            } else {
                if (logger.isTraceEnabled()) {
                    logger.trace("Login page view.");
                }

                HttpServletRequest req = (HttpServletRequest)request;
                HttpServletResponse resp = (HttpServletResponse) response;

                //System.out.println(req.getHeader("x-requested-with"));
                if (req.getHeader("x-requested-with") != null
                        && req.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {

                    //前端Ajax请求，则不会重定向
                    resp.setHeader("Access-Control-Allow-Origin",  req.getHeader("Origin"));
                    resp.setHeader("Access-Control-Allow-Credentials", "true");
                    resp.setContentType("application/json; charset=utf-8");
                    resp.setCharacterEncoding("UTF-8");
                    PrintWriter out = resp.getWriter();
                    JSONObject result = new JSONObject();
                    result.put("code", 401);
                    out.println(result);
                    out.flush();
                    out.close();
                    return false;
                }else{
                    //allow them to see the login page ;)
                    return true;
                }

            }
        }
        return false;
    }

}

