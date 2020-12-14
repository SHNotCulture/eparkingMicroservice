package com.eparking.service.impl;

import com.common.entity.eparkingCloud.ExcelTChargeDetailsReport;
import com.common.entity.eparkingCloud.TChargeDetailsReport;
import com.common.entity.eparkingCloud.TCompanyPark;
import com.eparking.insideService.TChargeDetailsReportInsideService;
import com.eparking.insideService.TCompanyParkInsideService;
import com.eparking.service.TChargeDetailsReportService;
import com.common.util.BeanCopyUtil;
import com.common.util.ExportExcelUtil;
import com.common.entity.PageBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
* @Description: TChargeDetailsReportService接口实现类
* @author 谢轩然
* @date 2020/11/03 17:02
*/
@Service
public class TChargeDetailsReportServiceImpl  implements TChargeDetailsReportService {

    private  static final Logger logger= LoggerFactory.getLogger(TChargeDetailsReportServiceImpl.class);

    @Autowired
    private TChargeDetailsReportInsideService tChargeDetailsReportInsideService;
    @Autowired
    private TCompanyParkInsideService tCompanyParkInsideService;

    /**
    *查询tChargeDetailsReport(分页)
    * @param tChargeDetailsReport
    * @param page
    * @param limit
    * @return
    */
    public PageBean<TChargeDetailsReport> getTChargeDetailsReportbyPage(TChargeDetailsReport tChargeDetailsReport,String beginDate ,String endDate, Integer page, Integer limit){

            return tChargeDetailsReportInsideService.getTChargeDetailsReportbyPage(tChargeDetailsReport, beginDate, endDate, page, limit);
        }

    /**
    * 查询tChargeDetailsReport
    * @param tChargeDetailsReport
    * @return
    */
    public List<TChargeDetailsReport> getTChargeDetailsReport(TChargeDetailsReport tChargeDetailsReport,String beginDate ,String endDate){
    return tChargeDetailsReportInsideService.getTChargeDetailsReport(tChargeDetailsReport, beginDate, endDate);
    }

    /**
    * 更新tChargeDetailsReport
    * @param tChargeDetailsReport
    * @return
    */
    public String UpdateTChargeDetailsReport(TChargeDetailsReport tChargeDetailsReport)
    {
            return tChargeDetailsReportInsideService.UpdateTChargeDetailsReport(tChargeDetailsReport);
    }

    /**
    * 删除tChargeDetailsReport
    * @param tChargeDetailsReport
    * @return
    */
    public String DeleteTChargeDetailsReport(TChargeDetailsReport tChargeDetailsReport){
            return tChargeDetailsReportInsideService.DeleteTChargeDetailsReport(tChargeDetailsReport);
    }

    @Override
    public String chargeReportStatistics(TChargeDetailsReport tChargeDetailsReport,String beginDate,String endDate) {
        TCompanyPark tCompanyParkSel = new TCompanyPark();
        tCompanyParkSel.setCompanyId(tChargeDetailsReport.getCompanyId());
        tCompanyParkSel.setId(tChargeDetailsReport.getParkId());
        List<TCompanyPark> tCompanyParkResultList = tCompanyParkInsideService.getTCompanyPark(tCompanyParkSel);
        //传入后台查询日期区间格式 yyyy-mm-dd xx:xx:xx
        if(tCompanyParkResultList.size()>0){
            String beginTime = tCompanyParkResultList.get(0).getStatisticsBeginTime();
            String endTime =  tCompanyParkResultList.get(0).getStatisticsEndTime();
            if(beginTime!=null && !beginTime.equals("")){
                beginDate = beginDate +" "+ beginTime;
            }else{
                beginDate = beginDate +" 00:00:00";
            }
            if(endTime!=null && !endTime.equals("")){
                endDate = endDate +" "+ endTime;
            }else{
                endDate = endDate +" 23:59:59";
            }
        }else{
            beginDate = beginDate +" 00:00:00";
            endDate = endDate +" 23:59:59";
        }
        return tChargeDetailsReportInsideService.chargeReportStatistics(tChargeDetailsReport,beginDate,endDate);
    }

    @Override
    public void exportList(TChargeDetailsReport tChargeDetailsReport, String beginDate, String endDate, String title, HttpServletResponse response) {
        List<TChargeDetailsReport> tChargeDetailsReports = getTChargeDetailsReport(tChargeDetailsReport, beginDate, endDate);
        List<ExcelTChargeDetailsReport> excelTChargeDetailsReports = new ArrayList<>();
//        logger.info("tChargeDetailsReport:"+tChargeDetailsReport);
        //当查找记录返回无数据时初始化ExcelTChargeDetailsReport防止数组越界
        if (tChargeDetailsReports.size() <= 0) {
            ExcelTChargeDetailsReport excelTCarWalletReport = new ExcelTChargeDetailsReport();
            excelTChargeDetailsReports.add(excelTCarWalletReport);
        } else {
            for (TChargeDetailsReport tChargeDetailsReportNew : tChargeDetailsReports) {
                ExcelTChargeDetailsReport excelTChargeDetailsReport = new ExcelTChargeDetailsReport();
                try {
                    excelTChargeDetailsReport = (ExcelTChargeDetailsReport) BeanCopyUtil.CopyBeanToBean(tChargeDetailsReportNew, excelTChargeDetailsReport);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                excelTChargeDetailsReports.add(excelTChargeDetailsReport);
            }
        }
        try {
            ExportExcelUtil.exportExcel(response, title, title, title, excelTChargeDetailsReports);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public PageBean<TChargeDetailsReport> getDutyStatisticsbyPage(TChargeDetailsReport tChargeDetailsReport, String beginTime, String endTime, Integer page, Integer limit) {
        return tChargeDetailsReportInsideService.getDutyStatisticsbyPage(tChargeDetailsReport, beginTime, endTime, page, limit);
    }

}
