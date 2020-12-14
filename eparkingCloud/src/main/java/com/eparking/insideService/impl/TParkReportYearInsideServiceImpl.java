package com.eparking.insideService.impl;

import com.common.entity.eparkingCloud.TParkReportYear;
import com.eparking.insideService.TParkReportYearInsideService;
import com.common.entity.PageBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;


/**
* @Description: TParkReportYearInsideService接口熔断器
* @author 谢轩然
* @date 2020/08/04 10:38
*/
@Component
public class TParkReportYearInsideServiceImpl  implements TParkReportYearInsideService {

    private static final Logger logger = LoggerFactory.getLogger(TParkReportYearInsideServiceImpl.class);

    /**
    * 查询tParkReportYear
    * @param tParkReportYear
    * @return
    */
    @Override
    public List<TParkReportYear> getTParkReportYear(TParkReportYear tParkReportYear,String beginTime, String endTime){
        String str = "查询TParkReportYear失败！";
        logger.info("查询TParkReportYear失败！");
        return null;
    }

    /**
    *查询(分页)tParkReportYear
    * @param tParkReportYear
    * @param page
    * @param limit
    * @return
    */
    @Override
    public PageBean<TParkReportYear> getTParkReportYearbyPage(TParkReportYear tParkReportYear,String beginTime, String endTime, Integer page, Integer limit){
        String str = "查询TParkReportYear分页失败！";
        logger.info("查询TParkReportYear分页失败！");
        return null;
    }

    /**
    * 更新tParkReportYear
    * @param tParkReportYear
    * @return
    */
    @Override
    public String UpdateTParkReportYear(TParkReportYear tParkReportYear){
        String str = "更新TParkReportYear失败！";
        logger.info("更新TParkReportYear失败！");
        return str;
    }

    /**
    * 删除tParkReportYear
    * @param tParkReportYear
    * @return
    */
    @Override
    public String DeleteTParkReportYear(TParkReportYear tParkReportYear){
        String str = "删除TParkReportYear失败！";
        logger.info("删除TParkReportYear失败！");
        return str;
    }
}