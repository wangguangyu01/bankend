package com.smart119.common.task;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.smart119.common.domain.DictDO;
import com.smart119.common.redis.shiro.RedisManager;
import com.smart119.common.service.BaiduMapService;
import com.smart119.common.service.DictService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import com.smart119.oa.domain.Response;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class DictTransferRedisJob implements Job {

    @Autowired
    SimpMessagingTemplate template;

    @Autowired
    private DictService dictService;

    @Autowired
    private RedisManager redisManager;

    @Autowired
    private BaiduMapService baiduMapService;

    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        // 查询处数据字典的所有的类型
        List<String> typeList = dictService.queryDictTypeList();
        if (!ObjectUtils.isEmpty(typeList)) {
            for (String type : typeList) {
                if (redisManager.exist(DictService.REDISDiCTKEY + type)) {
                    redisManager.del(DictService.REDISDiCTKEY + type);
                }
                // 根据类型查询所有的数据字典的数据
                List<DictDO> dictDOList = dictService.queryListByType(type);
                if (!ObjectUtils.isEmpty(dictDOList)) {
                    for (DictDO dictDO : dictDOList) {
                        if (NumberUtils.compare(dictDO.getParentId(), 0) != 0) {
                            Map breadCrumbsHandle = dictService.dictBreadCrumbsHandle(dictDO.getId());
                            if (!ObjectUtils.isEmpty(breadCrumbsHandle)) {
                                dictDO.setIdHierarchy((String) breadCrumbsHandle.get("idhierarchy"));
                                dictDO.setNameHierarchy((String) breadCrumbsHandle.get("nameHierarchy"));
                            }
                            String childrenIds = dictService.queryGroupConcat(dictDO.getId());
                            if (StringUtils.isBlank(childrenIds)) {
                                childrenIds = "";
                            }
                            dictDO.setChildrenIds(childrenIds);
                            redisManager.lpush(DictService.REDISDiCTKEY + dictDO.getType(),
                                    JSONObject.toJSONString(dictDO));
                            if (StringUtils.equals(dictDO.getType(), "XZQHDM")) {
                                String center = baiduMapService.getGdRegionCenterCoordinates(dictDO.getValue());
                                if (StringUtils.isNotBlank(center)) {
                                    dictDO.setCenter(center);
                                    Map<String, String> redisHash = new HashMap<>();
                                    redisHash.put(dictDO.getId() + "", center);
                                    redisManager.hmset(DictService.REDISDEPTTKEY, redisHash);
                                }

                            }

                        }
                    }
                }

            }
        }
    }

}
