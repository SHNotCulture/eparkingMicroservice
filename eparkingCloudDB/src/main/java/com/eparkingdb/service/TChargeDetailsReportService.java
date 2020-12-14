package com.eparkingdb.service;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TChargeDetailsReport;

import java.util.List;

/**
* @Description: TChargeDetailsReportService接口
* @author 谢轩然
* @date 2020/11/03 16:47
*/
public interface TChargeDetailsReportService {
    /**
    *查询(分页)tChargeDetailsReport
    * @param tChargeDetailsReport
    * @param page
    * @param limit
    * @return
    */
    PageBean<TChargeDetailsReport> getTChargeDetailsReportbyPage(TChargeDetailsReport tChargeDetailsReport, String beginDate, String endDate, Integer page, Integer limit);

    /**
    * 查询tChargeDetailsReport
    * @param tChargeDetailsReport
    * @return
    */
    List<TChargeDetailsReport> getTChargeDetailsReport(TChargeDetailsReport tChargeDetailsReport, String beginDate, String endDate);

    /**
    * 更新tChargeDetailsReport
    * @param tChargeDetailsReport
    * @return
    */
    String UpdateTChargeDetailsReport(TChargeDetailsReport tChargeDetailsReport);

    /**
    * 删除tChargeDetailsReport
    * @param tChargeDetailsReport
    * @return
    */
    String DeleteTChargeDetailsReport(TChargeDetailsReport tChargeDetailsReport);

    /**
    * 根据ID查询tChargeDetailsReport
    * @param id
    * @return
    */
    TChargeDetailsReport getTChargeDetailsReportByID(Integer id);

    String chargeReportStatistics(TChargeDetailsReport tChargeDetailsReport, String beginDate, String endDate);

    PageBean<TChargeDetailsReport> getDutyStatisticsbyPage(TChargeDetailsReport tChargeDetailsReport, String beginTime, String endTime, Integer page, Integer limit);
}