package com.smart119.common.task;

import com.smart119.common.service.ExliveService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@Component
@Slf4j
public class GPSPostitionTaskJob implements Job {

    @Resource
    private ExliveService exliveService;


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            exliveService.updateXfclPostion();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
