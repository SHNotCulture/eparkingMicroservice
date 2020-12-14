package com.eparking.service;

import com.common.entity.eparkingCloud.TParkReport;
import com.common.entity.eparkingCloud.TParkReportMonth;
import com.common.entity.PageBean;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author lishuhan
 * @Description:
 * @Date Create in 18:262018-11-6
 * @Modified By:
 */
public interface ParkReportMonthService {
    PageBean<TParkReportMonth> getParkReportMonthbyPage(String beginTime, String endTime, TParkReportMonth tParkReportMonth, Integer page, Integer limit);
    List<TParkReportMonth> getParkReportMonth(String beginTime, String endTime, TParkReportMonth tParkReportMonth);
    void exportList(String beginTime, String endTime, TParkReportMonth tParkReportMonth, String title, HttpServletResponse response);
    String parkReportStatistics(String beginTime, String endTime, TParkReport tParkReport) throws Exception;
}
