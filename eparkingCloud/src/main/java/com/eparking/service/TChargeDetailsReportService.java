package com.eparking.service;

import com.common.entity.eparkingCloud.TChargeDetailsReport;
import com.common.entity.PageBean;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
* @Description: TChargeDetailsReportService接口
* @author 谢轩然
* @date 2020/11/03 17:02
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

    String chargeReportStatistics(TChargeDetailsReport tChargeDetailsReport, String beginDate, String endDate);

    void exportList(TChargeDetailsReport tChargeDetailsReport, String beginDate, String endDate, String title, HttpServletResponse response);

    PageBean<TChargeDetailsReport> getDutyStatisticsbyPage(TChargeDetailsReport tChargeDetailsReport, String beginTime, String endTime, Integer page, Integer limit);
}