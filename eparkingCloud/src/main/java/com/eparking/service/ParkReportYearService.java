package com.eparking.service;

import com.common.entity.eparkingCloud.TParkReport;
import com.common.entity.eparkingCloud.TParkReportYear;
import com.common.entity.PageBean;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author lishuhan
 * @Description:
 * @Date Create in 18:322018-11-6
 * @Modified By:
 */
public interface ParkReportYearService {
    PageBean<TParkReportYear> getParkReportYearbyPage(String beginTime, String endTime, TParkReportYear tParkReportYear, Integer page, Integer limit);
    List<TParkReportYear> getParkReportYear(String beginTime, String endTime, TParkReportYear tParkReportYear);
    void exportList(String beginTime, String endTime, TParkReportYear tParkReportYear, String title, HttpServletResponse response);
    String parkReportStatistics(String beginTime, String endTime, TParkReport tParkReport) throws Exception;
}
