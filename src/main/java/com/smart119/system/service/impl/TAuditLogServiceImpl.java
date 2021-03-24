package com.smart119.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart119.common.config.ApplicationContextRegister;
import com.smart119.common.enums.LogTypeEnum;
import com.smart119.common.utils.*;
import com.smart119.system.dao.TAuditLogDao;
import com.smart119.system.dao.TElementDirectionDao;
import com.smart119.system.domain.TAuditLogEntity;
import com.smart119.system.domain.TElementDirectionEntity;
import com.smart119.system.service.TAuditLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

@Service("tAuditLogService")
@Slf4j
public class TAuditLogServiceImpl extends ServiceImpl<TAuditLogDao, TAuditLogEntity> implements
        TAuditLogService {


    @Autowired
    private TAuditLogDao auditLogDao;


    @Autowired
    private TElementDirectionDao elementDirectionDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        //查询列表数据
        QueryWrapper queryWrapper = new QueryWrapper();
        Object sort = params.get("sort");
        if(!ObjectUtils.isEmpty(sort)){
            Object order = params.get("order");
            if(!ObjectUtils.isEmpty(order) && "desc".equals(order.toString().toLowerCase())){
                queryWrapper.orderByDesc(sort.toString());
            }else{
                queryWrapper.orderByAsc(sort.toString());
            }
        }
        Object offset = params.get("offset");
        Object limit = params.get("limit");
        if(!ObjectUtils.isEmpty(offset) && !ObjectUtils.isEmpty(limit)){
            queryWrapper.last("limit " + offset + "," + limit);
        }
        params.remove("sort");
        params.remove("order");
        params.remove("offset");
        params.remove("limit");
        params.forEach((s, o) -> {
            queryWrapper.eq(ParamChangeUtil.humpToLine2(s), o);
        });
        PageUtils pageUtils = new PageUtils(list(queryWrapper), count(queryWrapper));
        return pageUtils;
    }



    /**
     * @param @param  methodName
     * @param @return
     * @return String 返回类型
     * @throws
     * @Title: getEventType
     * @Description: 事件类型（系统级<日志>，业务级<other>）
     */
//    String getEventType(String methodName, String tags) {
//
//        if (tags.contains("auditLog")) {
//            return EventTypeEnum.SYSTEM.getCode();
//        } else {
//            return EventTypeEnum.BUSINESS.getCode();
//        }
//
//    }

    String getLogType() {
        String profile = ApplicationContextRegister.getApplicationContext().getEnvironment().getActiveProfiles()[0];
        if (profile.equalsIgnoreCase("pro") || profile.equalsIgnoreCase("product")
                || profile.equalsIgnoreCase("master")) {
            return LogTypeEnum.RUNNING.getCode();
        } else {
            return LogTypeEnum.DEBUG.getCode();
        }

    }

    private String getOperationLogInfo(String code) {

        TElementDirectionEntity elementDirectionEntity = new TElementDirectionEntity();
        elementDirectionEntity = elementDirectionDao.selectOne(new QueryWrapper<TElementDirectionEntity>().eq("code", code));
        String value = elementDirectionEntity != null ? (elementDirectionEntity.getName() == null ? code : elementDirectionEntity.getName()) : code;

        log.debug("getOperationLogInfo code={} value= {}", code + " --> " + value);
        return value;
    }
    @Transactional
    @Override
    public R logbackup(Date dataParam) {
        List<TAuditLogEntity> dblist = this.lambdaQuery()
                .lt(TAuditLogEntity::getCreateTime, dataParam).list();
        List<String> insertsqlList = new ArrayList<>();
        String tabName = "t_audit_log";
        String filePath = "logbackup";
        String titleTime = DateUtils.format(dataParam, "yyyy-MM-dd");
        String fileName = tabName + "_Before_" + titleTime + ".sql";
        Long userId = ShiroUtils.getUser().getUserId();
        String fileTime = DateUtils.format(new Date(), "yyyy-MM-dd HH_mm_ss");
        String additionalInfo = fileTime + "_" + userId + "_" + filePath + "_" + fileName;
        final Base64.Encoder encoder = Base64.getEncoder();
        String flagText = "";
        try {
            byte[] textByte = additionalInfo.getBytes("UTF-8");
            flagText = encoder.encodeToString(textByte);
            //final Base64.Decoder decoder = Base64.getDecoder();
            //System.out.println(new String(decoder.decode(encodedText), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return R.error(-1, "日志备份失败");
        }
        //日志说明
        insertsqlList.add(" #\t\t 日期 " + titleTime + " 前审计日志记录");
        dblist.forEach(element -> {
            Map<String, String> kvStrMap = createColumnNameAndColumnValue(element);
            String columnName = kvStrMap.get("ColumnName");
            String columnValue = kvStrMap.get("ColumnValue");
            insertsqlList.add(creatInsertSQL(tabName, columnName, columnValue));
        });
        insertsqlList.add(" #\t\t 本次数据输出结束: " + flagText);
        if (!dblist.isEmpty()) {
            try {
                //备份日志数据成sql文件
                createSQLFile(filePath, fileName, insertsqlList);
                //删除指定日志数据
                UpdateWrapper updateWrapper = new UpdateWrapper();
                updateWrapper.in("id",
                        dblist.stream().map(TAuditLogEntity::getId).collect(Collectors.toList()));
                this.remove(updateWrapper);
            } catch (Exception e) {
                e.printStackTrace();
                return R.error(-1, "日志备份失败");
            }
        } else {
            return R.ok("暂无数据");
        }
        return R.ok();
    }

    private Map<String, String> createColumnNameAndColumnValue(TAuditLogEntity auditLog) {
        StringBuffer columnName = new StringBuffer();
        StringBuffer columnValue = new StringBuffer();
        //Field[] fs = auditLog.getClass().getFields();
        Field[] fs = auditLog.getClass().getDeclaredFields();
        Arrays.stream(fs.clone()).forEach(e -> {
            String attributeName = e.getName();
            if (!(attributeName.equals("serialVersionUID") || attributeName.equals("id"))) {
                String methodName = attributeName.substring(0, 1).toUpperCase()
                        + attributeName.substring(1);
                Object result = null;
                try {
                    Method getMethod = auditLog.getClass().getMethod("get" + methodName);
                    result = getMethod.invoke(auditLog);

                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException ex) {
                    try {
                        result = e.get(auditLog);
                    } catch (IllegalAccessException illegalAccessException) {
                        illegalAccessException.printStackTrace();
                    }
                }
                if (result != null) {
                    //columnName 转义
                    columnName.append("`").append(ParamChangeUtil.humpToLine2(attributeName)).append("`").append(",");
                    if (result instanceof Date) {
                        String dateStr = DateUtils.format((Date) result, "yyyy-MM-dd HH:mm:ss");
                        columnValue.append("'").append(dateStr).append("'").append(",");
                    } else {
                        //TODO 特殊字符处理
                        String resultCheck = String.valueOf(result);
                        if (resultCheck.indexOf("'") > -1) {
                            resultCheck = resultCheck.replaceAll("'", "");
                        }
                        columnValue.append("'").append(resultCheck).append("'").append(",");
                    }
                } else {
                    //System.out.println("空值不处理");
                    //columnName.append("`").append(HumpToUnderlineUtil.humpToUnderline(attributeName)).append("`").append(",");
                    //columnValue.append("'',");
                }
            }
        });
        columnName.deleteCharAt(columnName.length() - 1);
        columnValue.deleteCharAt(columnValue.length() - 1);
        Map<String, String> result = new HashMap<>();
        result.put("ColumnName", columnName.toString());
        result.put("ColumnValue", columnValue.toString());
        return result;
    }


    private String creatInsertSQL(String tabName, String columnName,
                                  String columnValue) {
        String insert = " INSERT INTO ";
        String values = " VALUES ";
        StringBuffer insertSQL = new StringBuffer();
        insertSQL.append(insert).append(" ")
                .append(tabName).append("(").append(columnName)
                .append(")").append(values).append("(").append(columnValue).append(");");
        return insertSQL.toString();
    }

    private void createSQLFile(String filePath, String fileName, List<String> insertList) {
        File path = new File("." + System.getProperty("file.separator") + filePath);
        if (!path.exists()) {
            //path.createNewFile();
            path.mkdir();
            System.out.println("log folder created");
        }
        FileWriter fw = null;
        BufferedWriter bw = null;
        //路径兼容符 File.separator
        try {
            fw = new FileWriter(path + System.getProperty("file.separator") + fileName, true);
            //fw 写文件，如果不存在自己创建，已存在会自动追加
            bw = new BufferedWriter(fw);
            if (insertList.size() > 0) {
                for (int i = 0; i < insertList.size(); i++) {
                    bw.append(insertList.get(i));
                    bw.append("\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bw.close();
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
