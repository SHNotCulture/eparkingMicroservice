package com.eparking.insideService.impl;

import com.common.entity.eparkingCloud.TParkDuty;
import com.eparking.insideService.TParkDutyInsideService;
import com.common.entity.PageBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TParkDutyInsideServiceImpl implements TParkDutyInsideService {
    private static final Logger logger = LoggerFactory.getLogger(TParkDutyInsideServiceImpl.class);

    @Override
    public List<TParkDuty> getTParkDuty(TParkDuty tParkDuty,String beginTime, String endTime) {
        String str = "查询TParkDuty失败！";
        logger.info("查询TParkDuty失败！");
        return null;
    }

    @Override
    public PageBean<TParkDuty> getTParkDutybyPage(TParkDuty tParkDuty,String beginTime, String endTime, Integer page, Integer limit) {
        String str = "查询TParkDuty(分页)失败！";
        logger.info("查询TParkDuty(分页)失败！");
        return null;
    }

    @Override
    public String UpdateTParkDuty(TParkDuty tParkDuty) {
        String str = "更新TParkDuty失败！";
        logger.info("更新TParkDuty失败！");
        return str;
    }

    @Override
    public String DeleteTParkDuty(TParkDuty tParkDuty) {
        String str = "删除TParkDuty失败！";
        logger.info("删除TParkDuty失败！");
        return str;
    }
}
