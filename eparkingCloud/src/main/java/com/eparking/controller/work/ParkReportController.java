package com.eparking.controller.work;

import com.common.annotation.HttpLog;
import com.common.entity.ActionRsp;
import com.common.entity.ControllerRsp;
import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.*;
import com.eparking.service.ElectronPaymentService;
import com.eparking.service.ParkReportMonthService;
import com.eparking.service.ParkReportService;
import com.eparking.service.ParkReportYearService;
import com.common.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author lishuhan
 * @Description:
 * @Date Create in 22:192018-11-1
 * @Modified By:
 */
@RestController
@RequestMapping(value = "parkReport")
public class ParkReportController {
    private  static final Logger logger= LoggerFactory.getLogger(ParkReportController.class);
    @Autowired
    private ParkReportService parkReportService;
    @Autowired
    private ParkReportMonthService parkReportMonthService;
    @Autowired
    private ParkReportYearService  parkReportYearService;
    @Autowired
    private ElectronPaymentService electronPaymentService;

    /**
     * 查询财务报表信息
     * @return
     */
    @PostMapping(value = "/getParkReport")
    @HttpLog(operationType = "0",modularTypeName = "查询财务报表信息")
    public ControllerRsp GetParkReport(String type, String beginTime, String endTime, Integer page, Integer limit, HttpServletRequest request){
        PageBean<?> result=new PageBean<>();
        String benginMonthStr="";
        String endMonthStr="";
        String beginYearStr="";
        String endYearStr="";
        if(beginTime!=null && !beginTime.equals("")){
/*            benginMonthStr = DateUtil.beforeMonthLastDay(beginTime).substring(0,7);
            beginYearStr = DateUtil.beforeYearLastDay(beginTime).substring(0,4);*/
            benginMonthStr = beginTime.substring(0,7);
            beginYearStr = beginTime.substring(0,4);
        }else{
            beginTime="";
            benginMonthStr=beginTime;
            beginYearStr=beginTime;
        }
//        logger.info("benginMonthStr: "+benginMonthStr);
        if(endTime!=null && !endTime.equals("")){
            endMonthStr = endTime.substring(0,7);
            endYearStr = endTime.substring(0,4);
        }else{
            endTime="";
            endMonthStr=endTime;
            endYearStr=endTime;
        }
        if(type==null)
        {
            type="0";
        }
        Integer parkId=Integer.valueOf(SessionUtil.getParkId());
        switch (type){
            case "0":
                TParkReport tParkReport=new TParkReport();
                tParkReport.setCompanyId(SessionUtil.getCompany().getId());
                tParkReport.setParkId(parkId);
                result=parkReportService.getParkReportbyPage(beginTime,endTime,tParkReport,page,limit);
                break;
            case "1":
                TParkReportMonth tParkReportMonth=new TParkReportMonth();
                tParkReportMonth.setParkId(parkId);
                result=parkReportMonthService.getParkReportMonthbyPage(benginMonthStr,endMonthStr,tParkReportMonth,page,limit);
                break;
            case "2":
//                logger.info("endYearStr: "+endYearStr);
                TParkReportYear tParkReportYear=new TParkReportYear();
                tParkReportYear.setParkId(parkId);
                result=parkReportYearService.getParkReportYearbyPage(beginYearStr,endYearStr,tParkReportYear,page,limit);
                break;
            default:
                break;
        }
        return ControllerRspUtil.Success(result);
    }
    /**
     * 查询电子支付对账信息
     * @return
     */
    @PostMapping(value = "/getElectronPaymentbyPage")
    @HttpLog(operationType = "0",modularTypeName = "查询电子支付对账信息")
    public ControllerRsp getElectronPaymentbyPage(String beginTime,String endTime,Integer checkFlag, Integer page, Integer limit) {
        Integer parkId=SessionUtil.getParkId();
        if(checkFlag==null){
            checkFlag=0;
        }
        if(beginTime==null){
            beginTime="";
        }
        if(endTime==null){
            endTime="";
        }
        TElectronPayment tElectronPayment=new TElectronPayment();
        tElectronPayment.setParkId(parkId);
        tElectronPayment.setCheckFlag(checkFlag);
        PageBean<TElectronPayment> tElectronPayments=electronPaymentService.getElectronPaymentbyPage(tElectronPayment,beginTime,endTime,page,limit);
        return ControllerRspUtil.Success(tElectronPayments);
    }
    /**
     * 确认/取消电子支付对账信息
     * @return
     */
    @PostMapping(value = "/updateElectronPayment")
    @HttpLog(operationType = "1",modularTypeName = "确认/取消电子支付对账信息")
    public ActionRsp updateElectronPayment(TElectronPayment tElectronPayment, HttpServletRequest request) {
        tElectronPayment.setParkId(SessionUtil.getParkId());
        return ActionRspUtil.Success(electronPaymentService.UpdateElectronPayment(tElectronPayment));
    }

    /**
     * 查询报表统计
     * @return
     */
    @PostMapping(value = "/getParkReportSum")
    @HttpLog(operationType = "0",modularTypeName = "查询报表统计")
    public ActionRsp getParkReportSum(String beginTime, String endTime, String type) {
        String tabelName="";
        switch (type){
            case "0":
                tabelName="t_park_report";
                break;
            case "1":
                tabelName="t_park_report_month";
                break;
            case "2":
                tabelName="t_park_report_year";
                break;
            default:
                break;
        }
        return ActionRspUtil.Success(parkReportService.getParkReportSum(beginTime,endTime,tabelName));
    }

    /**
     * 报表统计
     * @return
     */
    @PostMapping(value = "/parkReportStatistics")
    @HttpLog(operationType = "1",modularTypeName = "报表统计")
    public ActionRsp parkReportStatistics(String beginTime, String endTime, String type) {
/*        String tabelName="";
        switch (type){
            case "0":
                tabelName="t_park_report";
                break;
            case "1":
                tabelName="t_park_report_month";
                break;
            case "2":
                tabelName="t_park_report_year";
                break;
            default:
                break;
        }*/
        Integer parkId = SessionUtil.getParkId();
        TCompanyUser user=SessionUtil.getUser();
        TParkReport tParkReport = new TParkReport();
        tParkReport.setParkId(parkId);
        tParkReport.setCompanyId(user.getCompanyId());
        return ActionRspUtil.Success(parkReportService.parkReportStatistics(beginTime,endTime,type,tParkReport));
    }
}
