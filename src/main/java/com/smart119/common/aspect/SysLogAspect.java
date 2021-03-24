package com.smart119.common.aspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.smart119.common.config.ApplicationContextRegister;
import com.smart119.common.enums.EventResultEnum;
import com.smart119.common.enums.LogTypeEnum;
import com.smart119.common.utils.DateUtils;
import com.smart119.common.utils.HttpContextUtils;
import com.smart119.common.utils.IPUtils;
import com.smart119.common.utils.ShiroUtils;
import com.smart119.system.domain.TAuditLogConfigEntity;
import com.smart119.system.domain.TAuditLogEntity;
import com.smart119.system.domain.UserDO;
import com.smart119.system.service.TAuditLogConfigService;
import com.smart119.system.service.TAuditLogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * 系统日志：切面处理类
 */
@Aspect
@Component
@Slf4j
public class SysLogAspect implements Ordered {


    @Autowired
    private TAuditLogService auditLogService;

    @Autowired
    private TAuditLogConfigService auditLogConfigService;

    //定义切点 @Pointcut
    //在注解的位置切入代码
    @Pointcut("@annotation( io.swagger.annotations.ApiOperation)")
    public void logPoinCut() {
    }

    @Override
    public int getOrder() {
        // TODO Auto-generated method stub
        return 1;
    }

    /**
     * 切面 配置通知
     */
    @AfterReturning(pointcut = "logPoinCut()", returning = "res")
    public void saveSysLog(JoinPoint joinPoint, Object res) {
        log.debug("saveSysLog 切面开始！" + res);
        try {
            saveLog(joinPoint, res, null);
        } catch (Exception e) {
            log.error("saveSysLog error={}", e);
        }

    }

    /**
     * 切面 配置通知
     */
    @AfterThrowing(value = "logPoinCut()", throwing = "ex")
    public void saveSysLogThrow(JoinPoint joinPoint, Throwable ex) {
        log.debug("saveSysLogThrow 切面开始！" + ex);
        try {
            saveLog(joinPoint, null, ex);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            log.error("saveSysLogThrow error={}", e);
        }

    }

    /**
     * 保存审计日志主方法
     *
     * @param joinPoint
     * @param res
     * @param ex
     */
    private void saveLog(JoinPoint joinPoint, Object res, Throwable ex) {
        String retResult = JSONObject.toJSONString(res);
        //从切面织入点处通过反射机制获取织入点处的joinPoint方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取请求的类名
        String className = joinPoint.getTarget().getClass().getName();
        //获取切入点所在的方法
        Method method = signature.getMethod();
        //获取请求的方法名
        String methodName = method.getName();
        //请求的参数
        Object[] args = joinPoint.getArgs();
        //将参数所在的数组转换成json
        String params = "";
        if (args != null) {
            try {
                params = JSON.toJSONString(args);
            } catch (Exception e) {
                params = params.toString();
            }
        }
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        String httpMethod = request.getMethod();
        RequiresPermissions requiresPermissions = method.getAnnotation(RequiresPermissions.class);
        String url;
        if(requiresPermissions != null){
            url = requiresPermissions.value()[0];
        }else{
            url = getUrl(method, httpMethod);
        }
        String realUrl = request.getServletPath();
        TAuditLogConfigEntity auditLogConfigEntity = getAuditLogConfig(url);
        //判断是否配置需要记录审计日志
        if (auditLogConfigEntity != null && auditLogConfigEntity.getIsOpen().toString().equals("1")) {
            UserDO userDO = ShiroUtils.getUser();
            if (userDO == null) {
                    System.out.println("Token无效或特殊地址不记录日志");
                    return;
                }
            String userName = "";
            String userId = "";
            if (userDO != null) {
                userName = userDO.getUsername();
                userId = userDO.getUserId() + "";
            }
            Date createDate = new Date();
            String ip = IPUtils.getIpAddr(request);
            String[] result = getResultAndExtraNote(retResult, auditLogConfigEntity.getOperationCode());
            String note = "用户【" + userName + "】"
                    + "IP为【" + ip + "】,"
                    + "在时间【" + DateUtils.format(createDate, DateUtils.DATE_TIME_PATTERN) + "】"
                    + "访问【" + auditLogConfigEntity.getBussiness() + "】,";
            //如果开启额外输出替换，将不输出下面语句，个性化语句由controller方法内的extraNote自身组织日志输出语句
            if (auditLogConfigEntity.getIsReplace().toString().equals("0")) {
                note += "进行了【" + auditLogConfigEntity.getOperationType() + "】操作,";
            }
            if (result[1] != null) {
                note += result[1] + "操作结果:" + result[0];
            } else {
                note += " 操作结果: " + result[0];
            }
            //保存日志
            TAuditLogEntity auditLog = new TAuditLogEntity();
            //请求的方法
            auditLog.setMode(httpMethod);
            //操作描述
            auditLog.setNote(note);
            //方法名称
            auditLog.setMethodName(className + "." + methodName);
            //请求参数
            auditLog.setParam(params);
            //操作时间
            auditLog.setCreateTime(createDate);
            //登录用户名称
            auditLog.setUserName(userName);
            //登录用户id
            auditLog.setUserId(userId);
            //获取用户ip地址
            auditLog.setIp(ip);
            //访问url
            auditLog.setUri(url);
            //操作类型
            auditLog.setOperationType(auditLogConfigEntity.getOperationType());
            //事件类型
            auditLog.setEventType(auditLogConfigEntity.getEventType());
            //事件级别
            auditLog.setEventLevel(auditLogConfigEntity.getEventLevel());
            //业务类型
            auditLog.setBussiness(auditLogConfigEntity.getBussiness());
            //日志类型
            auditLog.setLogType(getLogType());
            //事件结果
            auditLog.setResult(result[0]);
            //前端真实访问资源地址
            auditLog.setRealUrl(realUrl);
            //如果开启结果详情输出
            if (auditLogConfigEntity.getIsWriteResultDetails().toString().equals("1")) {
                auditLog.setResultDetails(retResult);
            }
            //日志配置关联id
            auditLog.setConfigId(auditLogConfigEntity.getId());
            log.info("request auditLog={}", auditLog);
            //调用service保存SysLog实体类到数据库
            auditLogService.save(auditLog);
        }
    }

    /**
     * 获取调用结果和额外输出文本内容
     *
     * @param retResult
     * @param operationCode
     * @return
     */
    private String[] getResultAndExtraNote(String retResult, String operationCode) {
        String result = "";
        String extraNote = "";
        try {
            if (!StringUtils.isEmpty(retResult) && !"null".equals(retResult)) {
                JSONObject jsonObject = JSONObject.parseObject(retResult);
                Integer code = jsonObject.getInteger("code");
                extraNote = jsonObject.getString("extraNote");
                if(code != null){
                    if (code == 0) {
                        result = EventResultEnum.SUCCESS.getCode();
                    } else {
                        result = EventResultEnum.ERROR.getCode();
                    }
                } else {
                    if(jsonObject.get("total") != null){
                        result = EventResultEnum.SUCCESS.getCode();
                    }else{
                        result = EventResultEnum.ERROR.getCode();
                        log.error("未知返回：{}", retResult);
                    }
                }


            } else {
                if (operationCode.contains("export") || operationCode.endsWith("download")) {
                    result = EventResultEnum.SUCCESS.getCode();
                } else {
                    result = EventResultEnum.ERROR.getCode();
                }
            }
        } catch (Throwable e) {
            // TODO Auto-generated catch block
            result = EventResultEnum.ERROR.getCode();
            log.error("get method error={}", e);
        }
        return new String[]{result, extraNote};
    }

    /**
     * 获取日志配置信息
     *
     * @param url
     * @return
     */
    private TAuditLogConfigEntity getAuditLogConfig(String url) {
        TAuditLogConfigEntity auditLogConfigEntity = auditLogConfigService.getOne(new QueryWrapper<TAuditLogConfigEntity>().eq("url", url));
        return auditLogConfigEntity;
    }

    /**
     * 获取日志类型
     *
     * @return
     */
    private String getLogType() {
        String profile = ApplicationContextRegister.getApplicationContext().getEnvironment().getActiveProfiles()[0];
        if (profile.equalsIgnoreCase("pro") || profile.equalsIgnoreCase("product")
                || profile.equalsIgnoreCase("master")) {
            return LogTypeEnum.RUNNING.getCode();
        } else {
            return LogTypeEnum.DEBUG.getCode();
        }

    }

    /**
     * 获取url，用于查询日志配置信息
     *
     * @param method
     * @param httpMethod
     * @return
     */
    private String getUrl(Method method, String httpMethod) {
        String url = null;
        RequestMapping controllerRequestMapping = method.getDeclaringClass().getAnnotation(RequestMapping.class);
        String allUrl = "";
        if(controllerRequestMapping != null){
            allUrl = controllerRequestMapping.value()[0];
        }
        RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
        if (requestMapping != null) {
            url = requestMapping.value()[0];
        } else {
            if (httpMethod.equals("POST")) {
                PostMapping postMapping = method.getAnnotation(PostMapping.class);
                url = postMapping.value()[0];
            } else if (httpMethod.equals("GET")) {
                GetMapping getMapping = method.getAnnotation(GetMapping.class);
                url = getMapping.value()[0];
            } else if (httpMethod.equals("PUT")) {
                PutMapping putMapping = method.getAnnotation(PutMapping.class);
                url = putMapping.value()[0];
            } else if (httpMethod.equals("DELETE")) {
                DeleteMapping deleteMapping = method.getAnnotation(DeleteMapping.class);
                url = deleteMapping.value()[0];
            }
        }
        return allUrl + url;
    }
}