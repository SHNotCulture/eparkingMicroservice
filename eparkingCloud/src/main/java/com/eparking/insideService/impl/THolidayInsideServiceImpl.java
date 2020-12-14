package com.eparking.insideService.impl;

import com.common.entity.eparkingCloud.THoliday;
import com.eparking.insideService.THolidayInsideService;
import com.common.entity.PageBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class THolidayInsideServiceImpl implements THolidayInsideService {
    private static final Logger logger = LoggerFactory.getLogger(THolidayInsideServiceImpl.class);


    @Override
    public List<THoliday> getTHoliday(THoliday tHoliday) {
        String str = "查询THoliday失败！";
        logger.info("查询THoliday失败！");
        return null;
    }

    @Override
    public PageBean<THoliday> getTHolidaybyPage(THoliday tHoliday, Integer page, Integer limit) {
        String str = "查询THoliday分页失败！";
        logger.info("查询THoliday分页失败！");
        return null;
    }

    @Override
    public String UpdateTHoliday(THoliday tHoliday) {
        String str = "更新THoliday失败！";
        logger.info("更新THoliday失败！");
        return str;
    }

    @Override
    public String DeleteTHoliday(THoliday tHoliday) {
        String str = "删除THoliday失败！";
        logger.info("删除THoliday失败！");
        return str;
    }
}
