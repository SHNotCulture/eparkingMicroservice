package com.eparking.insideService.impl;

import com.common.entity.eparkingCloud.TParkReportMonth;
import com.eparking.insideService.TParkReportMonthInsideService;
import com.common.entity.PageBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;


/**
* @Description: TParkReportMonthInsideService接口熔断器
* @author 谢轩然
* @date 2020/08/03 16:21
*/
@Component
public class TParkReportMonthInsideServiceImpl  implements TParkReportMonthInsideService {

    private static final Logger logger = LoggerFactory.getLogger(TParkReportMonthInsideServiceImpl.class);

    /**
    * 查询tParkReportMonth
    * @param tParkReportMonth
    * @return
    */
    @Override
    public List<TParkReportMonth> getTParkReportMonth(TParkReportMonth tParkReportMonth,String beginTime,String endTime){
        String str = "查询TParkReportMonth失败！";
        logger.info("查询TParkReportMonth失败！");
        return null;
    }

    /**
    *查询(分页)tParkReportMonth
    * @param tParkReportMonth
    * @param page
    * @param limit
    * @return
    */
    @Override
    public PageBean<TParkReportMonth> getTParkReportMonthbyPage(TParkReportMonth tParkReportMonth,String beginTime,String endTime, Integer page, Integer limit){
        String str = "查询TParkReportMonth分页失败！";
        logger.info("查询TParkReportMonth分页失败！");
        return null;
    }

    /**
    * 更新tParkReportMonth
    * @param tParkReportMonth
    * @return
    */
    @Override
    public String UpdateTParkReportMonth(TParkReportMonth tParkReportMonth){
        String str = "更新TParkReportMonth失败！";
        logger.info("更新TParkReportMonth失败！");
        return str;
    }

    /**
    * 删除tParkReportMonth
    * @param tParkReportMonth
    * @return
    */
    @Override
    public String DeleteTParkReportMonth(TParkReportMonth tParkReportMonth){
        String str = "删除TParkReportMonth失败！";
        logger.info("删除TParkReportMonth失败！");
        return str;
    }
}