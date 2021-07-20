package com.smart119.common.task;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.smart119.common.service.ExliveService;
import com.smart119.jczy.dao.XfclDao;
import com.smart119.jczy.domain.XfclDO;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import sun.jvm.hotspot.opto.HaltNode;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
@Slf4j
public class LoadHistoryTaskJob implements Job {

    @Resource
    private XfclDao xfclDao;

    @Resource
    private ExliveService exliveService;


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Map<String, Object> param = new HashMap<>();
        param.put("clqwztlbdm", "0300");
        List<XfclDO> carlist = xfclDao.carlist(param);


    }


}
