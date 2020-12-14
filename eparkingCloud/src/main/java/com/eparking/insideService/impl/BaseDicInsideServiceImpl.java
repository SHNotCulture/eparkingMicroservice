package com.eparking.insideService.impl;

import com.common.entity.eparkingCloud.TDicOuttype;
import com.common.entity.eparkingCloud.TDicPrepayType;
import com.common.entity.eparkingCloud.TParkPort;
import com.eparking.insideService.BaseDicInsideService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BaseDicInsideServiceImpl implements BaseDicInsideService {
    private static final Logger logger = LoggerFactory.getLogger(BaseDicInsideServiceImpl.class);
    @Override
    public List<TParkPort> selectParkPort(String portType, Integer parkId) {
        String str = "查询ParkPort失败！";
        logger.info("查询ParkPort失败！");
        return null;
    }

    @Override
    public List<TDicOuttype> selectOutType() {
        String str = "查询出场方式失败！";
        logger.info("查询出场方式失败！");
        return null;
    }

    @Override
    public List<TDicPrepayType> selectgetPayType() {
        String str = "查询支付方式失败！";
        logger.info("查询支付方式失败！");
        return null;
    }
}
