package com.eparking.service;

import com.common.entity.eparkingCloud.TParkReport;
import com.common.entity.PageBean;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @Author lishuhan
 * @Description:
 * @Date Create in 22:122018-11-1
 * @Modified By:
 */
public interface ParkReportService {
    PageBean<TParkReport> getParkReportbyPage(String beginTime, String endTime, TParkReport tParkReport, Integer page, Integer limit);
    List<TParkReport> getParkReport(String beginTime, String endTime, TParkReport tParkReport);
    String paymentForDay();
    void exportList(String beginTime, String endTime, TParkReport tParkReport, String title, HttpServletResponse response);
    Map getParkReportSum(String beginTime, String endTime, String tabelName);
    String parkReportStatistics(String beginTime, String endTime, String type, TParkReport tParkReport);
}
