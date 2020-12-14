package com.eparking.insideService.impl;

import com.common.entity.eparkingCloud.TElectronPayment;
import com.eparking.insideService.ElectronPaymentServiceInsideService;
import com.common.entity.PageBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ElectronPaymentServiceInsideServiceImpl implements ElectronPaymentServiceInsideService {
    private static final Logger logger = LoggerFactory.getLogger(ElectronPaymentServiceInsideServiceImpl.class);
    @Override
    public List<TElectronPayment> getTElectronPaymentList(TElectronPayment tElectronPayment, String beginTime, String endTime) {
        String str = "查询TElectronPayment失败！";
        logger.info("查询TElectronPayment失败！");
        return null;
    }

    @Override
    public PageBean<TElectronPayment> getTElectronPaymentbyPage(TElectronPayment tElectronPayment, String beginTime, String endTime, Integer page, Integer limit) {
        String str = "查询TElectronPayment分页失败！";
        logger.info("查询TElectronPayment分页失败！");
        return null;
    }

    @Override
    public String UpdateTElectronPayment(TElectronPayment tElectronPayment) {
        String str = "更新TElectronPayment失败！";
        logger.info("更新TElectronPayment失败！");
        return str;
    }
}
