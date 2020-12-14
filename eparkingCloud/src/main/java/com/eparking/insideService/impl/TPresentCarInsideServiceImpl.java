package com.eparking.insideService.impl;

import com.common.entity.eparkingCloud.TParkInOut;
import com.eparking.insideService.TPresentCarInsideService;
import com.common.entity.PageBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TPresentCarInsideServiceImpl implements TPresentCarInsideService {
    private static final Logger logger = LoggerFactory.getLogger(TPresentCarInsideServiceImpl.class);
    @Override
    public PageBean<TParkInOut> getTPresentCarbyPage(TParkInOut tParkInOut, String parkId, String timeType, Integer page, Integer limit) {
        String str = "查询(分页)在场车辆失败！";
        logger.info("查询(分页)在场车辆失败！");
        return null;
    }

    @Override
    public List<TParkInOut> getTPresentCar(TParkInOut tParkInOut, String parkId, String timeType) {
        String str = "查询在场车辆失败！";
        logger.info("查询在场车辆失败！");
        return null;
    }
}
