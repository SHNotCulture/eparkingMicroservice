package com.eparking.service;

import com.common.entity.eparkingCloud.TCarWalletReport;
import com.common.entity.PageBean;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author ：lishuhan
 * @date ：Created in 2020/10/27 15:05
 * @description：
 * @modified By：
 * @version: 1.0$
 */
public interface TCarWalletReportService {
    PageBean<TCarWalletReport> getTCarWalletReportbyPage(TCarWalletReport tCarWalletReport, String beginTime, String endTime, Integer page, Integer limit);
    List<TCarWalletReport> getTCarWalletReport(TCarWalletReport tCarWalletReport, String beginTime, String endTime);
    String UpdateTCarWalletReport(TCarWalletReport tCarWalletReport);
    String DeleteTCarWalletReport(TCarWalletReport tCarWalletReport);
    String statisticsNow(String beginTime, String endTime, TCarWalletReport tCarWalletReport);
    void exportList(String beginTime, String endTime, TCarWalletReport tCarWalletReport, String title, HttpServletResponse response);
}
