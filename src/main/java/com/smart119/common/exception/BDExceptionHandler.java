package com.smart119.common.exception;

import com.smart119.common.config.Constant;
import com.smart119.common.domain.LogDO;
import com.smart119.common.service.LogService;
import com.smart119.common.utils.HttpServletUtils;
import com.smart119.common.utils.R;
import com.smart119.common.utils.ResultCode;
import com.smart119.common.utils.ShiroUtils;
import com.smart119.system.domain.UserDO;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Date;

/**
 * 异常处理器
 */
@RestControllerAdvice
public class BDExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    LogService logService;
//
//    /**
//     * 自定义异常
//     */
//    @ExceptionHandler(BDException.class)
//    public R handleBDException(BDException e) {
//        logger.error(e.getMessage(), e);
//        R r = new R();
//        r.put("code", e.getCode());
//        r.put("msg", e.getMessage());
//        return r;
//    }
//
//    @ExceptionHandler(DuplicateKeyException.class)
//    public R handleDuplicateKeyException(DuplicateKeyException e) {
//        logger.error(e.getMessage(), e);
//        return R.error("数据库中已存在该记录");
//    }
//
//    @ExceptionHandler(org.springframework.web.servlet.NoHandlerFoundException.class)
//    public R noHandlerFoundException(org.springframework.web.servlet.NoHandlerFoundException e) {
//        logger.error(e.getMessage(), e);
//        return R.error(404, "没找找到页面");
//    }

    @ExceptionHandler(AuthorizationException.class)
    public Object handleAuthorizationException(AuthorizationException e, HttpServletRequest request) {
        logger.error(e.getMessage(), e);
        if (HttpServletUtils.jsAjax(request)) {
            return R.error(403, "未授权");
        }
        return new ModelAndView("error/403");
    }


    @ExceptionHandler({Exception.class})
    public Object handleException(Exception e, HttpServletRequest request) {
        LogDO logDO = new LogDO();
        logDO.setGmtCreate(new Date());
        logDO.setOperation(Constant.LOG_ERROR);
        logDO.setMethod(request.getRequestURL().toString());
        logDO.setParams(e.toString());
        UserDO current = ShiroUtils.getUser();
        if(null!=current){
            logDO.setUserId(current.getUserId());
            logDO.setUsername(current.getUsername());
        }
        logService.save(logDO);
        logger.error(e.getMessage(), e);
        if (HttpServletUtils.jsAjax(request)) {
            return R.error(500, "服务器错误，请联系管理员");
        }
        return new ModelAndView("error/500");
    }
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public Object MethodArgumentNotValidHandler(MethodArgumentNotValidException exception){
        //按需重新封装需要返回的错误信息
        StringBuilder msg = new StringBuilder();
        //解析原错误信息，封装后返回，此处返回非法的字段名称，原始值，错误信息
        for (FieldError error : exception.getBindingResult().getFieldErrors()) {
            msg.append(error.getDefaultMessage()).append(",");
        }

        if (msg.length() > 0) {
            msg.deleteCharAt(msg.length() - 1);
        }
        return R.error(ResultCode.PARAM_IS_INVALID.code(), msg.toString());
    }
    //处理Get请求中 使用@Valid 验证路径中请求实体校验失败后抛出的异常，详情继续往下看代码
    @ExceptionHandler(BindException.class)
    public Object BindExceptionHandler(BindException exception) {
        //按需重新封装需要返回的错误信息
        StringBuilder msg = new StringBuilder();
        //解析原错误信息，封装后返回，此处返回非法的字段名称，原始值，错误信息
        for (FieldError error : exception.getBindingResult().getFieldErrors()) {
            msg.append(error.getDefaultMessage()).append(",");
        }

        if (msg.length() > 0) {
            msg.deleteCharAt(msg.length() - 1);
        }
        return R.error(ResultCode.PARAM_IS_INVALID.code(), msg.toString());
    }

    //处理请求参数格式错误 @RequestParam上validate失败后抛出的异常是javax.validation.ConstraintViolationException
    @ExceptionHandler(ConstraintViolationException.class)
    public Object ConstraintViolationExceptionHandler(ConstraintViolationException exception) {
        //按需重新封装需要返回的错误信息
        StringBuilder msg = new StringBuilder();
        //解析原错误信息，封装后返回，此处返回非法的字段名称，原始值，错误信息
        for (ConstraintViolation error : exception.getConstraintViolations()) {
            msg.append(error.getMessage()).append(",");
        }

        if (msg.length() > 0) {
            msg.deleteCharAt(msg.length() - 1);
        }
        return R.error(ResultCode.PARAM_IS_INVALID.code(), msg.toString());
    }
}
