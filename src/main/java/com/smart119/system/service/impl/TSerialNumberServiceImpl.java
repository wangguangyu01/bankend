package com.smart119.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.smart119.common.utils.DateUtils;
import com.smart119.system.dao.TSerialNumberDao;
import com.smart119.system.domain.TSerialNumber;
import com.smart119.system.service.TSerialNumberService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class TSerialNumberServiceImpl extends ServiceImpl<TSerialNumberDao, TSerialNumber> implements TSerialNumberService {

    @Autowired
    private TSerialNumberDao tSerialNumberMapper;



    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public String createSerialNumber() {
        Date date = new Date();
        String dateStr = DateUtils.format(date, DateUtils.DATE_PATTERN);
        Date date1 = DateUtils.parseDateNoWithTime(dateStr, DateUtils.DATE_PATTERN);
        LambdaQueryWrapper<TSerialNumber> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TSerialNumber::getUpdateDate, date1);
        List<TSerialNumber> tSerialNumbers = tSerialNumberMapper.selectList(queryWrapper);

        TSerialNumber tSerialNumber = null;
        if (CollectionUtils.isEmpty(tSerialNumbers)) {
            tSerialNumber = new TSerialNumber();
        } else {
            tSerialNumber = tSerialNumbers.get(0);
        }
        Long incrementAndGet = null;
        try {
            RedisAtomicLong redisAtomicLong = new RedisAtomicLong("tSerialNumber_key_"+dateStr, redisTemplate.getConnectionFactory());

            if (tSerialNumber.getId() == null) {
                incrementAndGet = redisAtomicLong.incrementAndGet();
            } else {
                Long serialNumber = tSerialNumber.getSerialNumber();
                incrementAndGet = redisAtomicLong.updateAndGet(value -> serialNumber + 1);
            }
            redisTemplate.expire("tSerialNumber_key_"+dateStr, 24, TimeUnit.HOURS);
        } catch (Exception e) {
            Long serialNumber = tSerialNumber.getSerialNumber();
            if (!ObjectUtils.isEmpty(serialNumber)) {
                incrementAndGet = serialNumber + 1;
            }
        }
        tSerialNumber.setSerialNumber(incrementAndGet);
        tSerialNumber.setUpdateDate(new Date());
        if (tSerialNumber.getId() == null) {
            tSerialNumberMapper.insert(tSerialNumber);
        } else {
            tSerialNumberMapper.updateById(tSerialNumber);
        }
        String incrementStr = String.format("%03d", incrementAndGet);
        String serialNumberStr = dateStr + incrementStr;
        serialNumberStr = StringUtils.replace(serialNumberStr, "-", "");
        return serialNumberStr;
    }
}
