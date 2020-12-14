package com.eparking.insideService.impl;

import com.common.entity.eparkingCloud.TParkInOut;
import com.eparking.insideService.TParkingRecordInsideService;
import com.common.entity.PageBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TParkingRecordInsideServiceImpl implements TParkingRecordInsideService {
    private static final Logger logger = LoggerFactory.getLogger(TParkingRecordInsideServiceImpl.class);

    @Override
    public PageBean<TParkInOut> getParkinoutbyPage(TParkInOut tParkInOut,String outTimeBegin,String outTimeEnd, Integer page, Integer limit) {
        String str = "查询TParkInOut分页失败！";
        logger.info("查询TParkInOut分页失败！");
        return null;
    }

    @Override
    public List<TParkInOut> getParkInOut(TParkInOut tParkInOut,String outTimeBegin,String outTimeEnd) {
        String str = "查询TParkInOut失败！";
        logger.info("查询TParkInOut失败！");
        return null;
    }

    @Override
    public String updateParkInOut(TParkInOut tParkInOut) {
        String str = "更新TParkInOut失败！";
        logger.info("更新TParkInOut失败！");
        return str;
    }

    @Override
    public PageBean<TParkInOut> getParkinoutDetailbyPage(TParkInOut tParkInOut, String parkId, Integer page, Integer limit) {
        String str = "查询TParkInOut分页失败！";
        logger.info("查询TParkInOut分页失败！");
        return null;
    }

    @Override
    public PageBean<TParkInOut> getInCarbyPage(TParkInOut tParkInOut, Integer page, Integer limit) {
        String str = "查询TParkInOut分页失败！";
        logger.info("查询TParkInOut分页失败！");
        return null;
    }

    @Override
    public List<TParkInOut> getTParkInOutForStatistics(TParkInOut tParkInOut, String outTimeBegin, String outTimeEnd) {
        String str = "查询TParkInOut失败！";
        logger.info("查询TParkInOut失败！");
        return null;
    }

    @Override
    public PageBean<TParkInOut> getTParkinoutUnlimitByPage(TParkInOut tParkInOut, Integer page, Integer limit) {
        String str = "查询TParkInOut分页(无限制)失败！";
        logger.info("查询TParkInOut分页(无限制)失败！");
        return null;
    }

    @Override
    public List<TParkInOut> getTParkInOutUnlimit(TParkInOut tParkInOut) {
        String str = "查询TParkInOut失败！";
        logger.info("查询TParkInOut失败！");
        return null;
    }

}
