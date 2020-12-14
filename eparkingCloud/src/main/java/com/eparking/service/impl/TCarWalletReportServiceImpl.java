package com.eparking.service.impl;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.*;
import com.eparking.insideService.TCarWalletReportInsideService;
import com.eparking.insideService.TCarownerPaymentInsideService;
import com.eparking.insideService.TParkingRecordInsideService;
import com.eparking.service.MonthlyCarService;
import com.eparking.service.TCarWalletReportService;
import com.common.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ：lishuhan
 * @date ：Created in 2020/10/27 15:06
 * @description：
 * @modified By：
 * @version: 1.0$
 */
@Service
public class TCarWalletReportServiceImpl implements TCarWalletReportService {

    @Autowired
    private TCarWalletReportInsideService tCarWalletReportInsideService;
    @Autowired
    private TCarownerPaymentInsideService tCarownerPaymentInsideService;
    @Autowired
    private TParkingRecordInsideService tParkingRecordInsideService;
    @Autowired
    private MonthlyCarService monthlyCarService;
    /**
     *查询tCarWalletReport信息(分页)
     * @param tCarWalletReport
     * @param page
     * @param limit
     * @return
     */
    public PageBean<TCarWalletReport> getTCarWalletReportbyPage(TCarWalletReport tCarWalletReport, String beginTime, String endTime, Integer page, Integer limit){
        return tCarWalletReportInsideService.getTCarWalletReportbyPage(tCarWalletReport,beginTime,endTime,page,limit);
    }

    /**
     * 查询tCarWalletReport信息
     * @param tCarWalletReport
     * @return
     */
    public List<TCarWalletReport> getTCarWalletReport(TCarWalletReport tCarWalletReport,String beginTime,String endTime){
        List<TCarWalletReport>  tCarWalletReports=tCarWalletReportInsideService.getTCarWalletReport(tCarWalletReport,beginTime,endTime);
        return tCarWalletReports;
    }

    /**
     * 更新tCarWalletReport信息
     * @param tCarWalletReport
     * @return
     */
    public String UpdateTCarWalletReport(TCarWalletReport tCarWalletReport)
    {
        return tCarWalletReportInsideService.UpdateTCarWalletReport(tCarWalletReport);
    }

    /**
     * 删除tCarWalletReport信息
     * @param tCarWalletReport
     * @return
     */
    public String DeleteTCarWalletReport(TCarWalletReport tCarWalletReport){
        return tCarWalletReportInsideService.DeleteTCarWalletReport(tCarWalletReport);
    }

    /**
     * 车主钱包报表统计方法（日报）
     * @param beginTime
     * @param endTime
     * @param tCarWalletReport
     * @return
     */
    public String statisticsNow(String beginTime,String endTime,TCarWalletReport tCarWalletReport){
        String message="统计成功";
        try {
            Integer day = StringUtil3.daysBetween(DateUtil.parse(beginTime, DateUtil.g_SimpleDateFormat_I), DateUtil.parse(endTime, DateUtil.g_SimpleDateFormat_I));
            for (int i = 0; i <= day; i++) {
                String date = StringUtil3.dateModified(beginTime, i);//统计时间范围内的某一天时间
                String date1 = date + " 00:00:00";
                String date2 = date + " 23:59:59";
                TCarWalletReport tCarWalletReportSave=new TCarWalletReport();
                tCarWalletReportSave.setUsePay(0.0);
                tCarWalletReportSave.setNeedPay(0.0);
                tCarWalletReportSave.setActualPay(0.0);
                tCarWalletReportSave.setTotalWalletBanance(0.0);
                tCarWalletReportSave.setParkId(tCarWalletReport.getParkId());
                tCarWalletReportSave.setCompanyId(tCarWalletReport.getCompanyId());
                TParkInOut tParkInOut=new TParkInOut();
                tParkInOut.setParkId(tCarWalletReport.getParkId());
                tParkInOut.setEpaytype(12);
                //查询时间范围内的进出场记录
                List<TParkInOut> tParkInOutList =tParkingRecordInsideService.getTParkInOutForStatistics(tParkInOut,date1,date2);
                if(tParkInOutList!=null){
                    for (TParkInOut t: tParkInOutList){
                        tCarWalletReportSave.setUsePay(tCarWalletReportSave.getUsePay()+t.getQpassPay());//汇总车主钱包使用金额
                    }
                }
                TParkCar tParkCarSel=new TParkCar();
                tParkCarSel.setParkId(tCarWalletReport.getParkId());
                //查询时间范围内的月租车记录
                List<TParkCar> tParkCars =monthlyCarService.getTParkCarlist(tParkCarSel,"","");
                if(tParkCars!=null){
                    for (TParkCar t: tParkCars){
                        tCarWalletReportSave.setTotalWalletBanance(tCarWalletReportSave.getTotalWalletBanance()+Double.valueOf(t.getWalletBanance()));//汇总车主钱包余额
                    }
                }
                TCarownerPayment tCarownerPaymentSel=new TCarownerPayment();
                tCarownerPaymentSel.setParkId(tCarWalletReport.getParkId());
                //查询时间范围内的车主钱包充值记录
                List<TCarownerPayment> tCarownerPayments =tCarownerPaymentInsideService.getTCarownerPayment(tCarownerPaymentSel,date1,date2);
                if(tCarownerPayments!=null){
                    for (TCarownerPayment t: tCarownerPayments){
                        tCarWalletReportSave.setNeedPay(tCarWalletReportSave.getNeedPay()+Double.valueOf(t.getNeedPay()));//汇总车主应付金额
                        tCarWalletReportSave.setActualPay(tCarWalletReportSave.getActualPay()+Double.valueOf(t.getActualPay()));//汇总车主实付余额
                    }
                }
                tCarWalletReportSave.setStatisticsTime(date);
                tCarWalletReportSave.setCreateTime(DateUtil.getCurDateTime());
                tCarWalletReportInsideService.DeleteTCarWalletReport(tCarWalletReportSave);
                String msg= tCarWalletReportInsideService.UpdateTCarWalletReport(tCarWalletReportSave);
            }
        } catch (ParseException e) {
            e.printStackTrace();
            message="统计异常";
        }


        return message;
    }
    /**
     * 导出电子对账单信息
     *
     * @param beginTime
     * @param endTime
     * @param tCarWalletReport
     * @param title
     * @param response
     */
    public void exportList(String beginTime,String endTime,TCarWalletReport tCarWalletReport, String title, HttpServletResponse response) {
        List<TCarWalletReport> tCarWalletReports = getTCarWalletReport(tCarWalletReport, beginTime, endTime);
        List<ExcelTCarWalletReport> excelTCarWalletReports = new ArrayList<>();
//        logger.info("tParkDuty:"+tParkDuty);
        //当查找记录返回无数据时初始化ExcelTParkDuty防止数组越界
        if (tCarWalletReports.size() <= 0) {
            ExcelTCarWalletReport excelTCarWalletReport = new ExcelTCarWalletReport();
            excelTCarWalletReports.add(excelTCarWalletReport);
        } else {
            for (TCarWalletReport tCarWalletReportNew : tCarWalletReports) {
                ExcelTCarWalletReport excelTCarWalletReport = new ExcelTCarWalletReport();
                try {
                    excelTCarWalletReport = (ExcelTCarWalletReport) BeanCopyUtil.CopyBeanToBean(tCarWalletReportNew, excelTCarWalletReport);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                excelTCarWalletReports.add(excelTCarWalletReport);
            }
        }
        try {
            ExportExcelUtil.exportExcel(response, title, title, title, excelTCarWalletReports);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
