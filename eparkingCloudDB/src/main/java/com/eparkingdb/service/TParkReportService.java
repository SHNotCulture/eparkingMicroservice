package com.eparkingdb.service;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TParkReport;

import java.util.List;
import java.util.Map;

/**
 * @Author lishuhan
 * @Description:
 * @Date Create in 22:122018-11-1
 * @Modified By:
 */
public interface TParkReportService {
    PageBean<TParkReport> getTParkReportbyPage(TParkReport tParkReport, String beginTime, String endTime, Integer page, Integer limit);
    List<TParkReport> getTParkReport(TParkReport tParkReport, String beginTime, String endTime);
    String paymentForDay();
    String updateTParkReport(TParkReport tParkReport);
    String deleteTParkReport(TParkReport tParkReport);
    Map getTParkReportSum(String beginTime, String endTime, String tabelName);
}
