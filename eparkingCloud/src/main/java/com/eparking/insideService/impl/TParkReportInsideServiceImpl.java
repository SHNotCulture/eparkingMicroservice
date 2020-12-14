package com.eparking.insideService.impl;

import com.common.entity.eparkingCloud.TParkReport;
import com.eparking.insideService.TParkReportInsideService;
import com.common.entity.PageBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TParkReportInsideServiceImpl implements TParkReportInsideService {
    private static final Logger logger = LoggerFactory.getLogger(TParkReportInsideServiceImpl.class);

    @Override
    public List<TParkReport> getTParkReport(TParkReport tParkReport, String beginTime, String endTime) {
        String str = "查询TParkReport失败！";
        logger.info("查询TParkReport失败！");
        return null;
    }

    @Override
    public PageBean<TParkReport> getTParkReportbyPage(TParkReport tParkReport, String beginTime, String endTime, Integer page, Integer limit) {
        String str = "查询TParkReport(分页)失败！";
        logger.info("查询TParkReport(分页)失败！");
        return null;
    }

    @Override
    public String UpdateTParkReport(TParkReport tParkReport) {
        String str = "更新TParkReport失败！";
        logger.info("更新TParkReport失败！");
        return str;
    }

    @Override
    public String DeleteTParkReport(TParkReport tParkReport) {
        String str = "删除TParkReport失败！";
        logger.info("删除TParkReport失败！");
        return str;
    }
}
