package com.eparking.insideService.impl;

import com.common.entity.eparkingCloud.TChargeDetailsReport;
import com.eparking.insideService.TChargeDetailsReportInsideService;
import com.common.entity.PageBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;


/**
* @Description: TChargeDetailsReportInsideService接口熔断器
* @author 谢轩然
* @date 2020/11/03 17:02
*/
@Component
public class TChargeDetailsReportInsideServiceImpl  implements TChargeDetailsReportInsideService {

    private static final Logger logger = LoggerFactory.getLogger(TChargeDetailsReportInsideServiceImpl.class);

    /**
    * 查询tChargeDetailsReport
    * @param tChargeDetailsReport
    * @return
    */
    @Override
    public List<TChargeDetailsReport> getTChargeDetailsReport(TChargeDetailsReport tChargeDetailsReport,String beginDate ,String endDate){
        String str = "查询TChargeDetailsReport失败！";
        logger.info("查询TChargeDetailsReport失败！");
        return null;
    }

    /**
    *查询(分页)tChargeDetailsReport
    * @param tChargeDetailsReport
    * @param page
    * @param limit
    * @return
    */
    @Override
    public PageBean<TChargeDetailsReport> getTChargeDetailsReportbyPage(TChargeDetailsReport tChargeDetailsReport,String beginDate ,String endDate, Integer page, Integer limit){
        String str = "查询TChargeDetailsReport分页失败！";
        logger.info("查询TChargeDetailsReport分页失败！");
        return null;
    }

    /**
    * 更新tChargeDetailsReport
    * @param tChargeDetailsReport
    * @return
    */
    @Override
    public String UpdateTChargeDetailsReport(TChargeDetailsReport tChargeDetailsReport){
        String str = "更新TChargeDetailsReport失败！";
        logger.info("更新TChargeDetailsReport失败！");
        return str;
    }

    /**
    * 删除tChargeDetailsReport
    * @param tChargeDetailsReport
    * @return
    */
    @Override
    public String DeleteTChargeDetailsReport(TChargeDetailsReport tChargeDetailsReport){
        String str = "删除TChargeDetailsReport失败！";
        logger.info("删除TChargeDetailsReport失败！");
        return str;
    }

    @Override
    public String chargeReportStatistics(TChargeDetailsReport tChargeDetailsReport, String beginDate, String endDate) {
        String str = "统计缴费明细报表失败！";
        logger.info("统计缴费明细报表失败！");
        return str;
    }

    @Override
    public PageBean<TChargeDetailsReport> getDutyStatisticsbyPage(TChargeDetailsReport tChargeDetailsReport, String beginTime, String endTime, Integer page, Integer limit) {
        String str = "当班即时统计查询分页失败！";
        logger.info("当班即时统计查询分页失败！");
        return null;
    }
}