package com.eparking.controller.work;

import com.common.annotation.HttpLog;
import com.common.entity.ActionRsp;
import com.common.entity.ControllerRsp;
import com.common.entity.eparkingCloud.TCarWalletReport;
import com.common.entity.eparkingCloud.TCompanyUser;
import com.eparking.service.TCarWalletReportService;
import com.common.util.ActionRspUtil;
import com.common.util.ControllerRspUtil;
import com.common.util.SessionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ：lishuhan
 * @date ：Created in 2020/10/27 18:44
 * @description：
 * @modified By：
 * @version: 1.0$
 */
@RestController
@RequestMapping(value = "carWalletReport")
public class TCarWalletReportController {
    private  static final Logger logger= LoggerFactory.getLogger(TCarownerPaymentController.class);
    @Autowired
    private TCarWalletReportService tCarWalletReportService;
    /**
     * 查询TCarWalletReport信息
     * @param tCarWalletReport
     * @return
     */
    @PostMapping(value = "/getTCarWalletReport")
    @HttpLog(operationType = "0",modularTypeName = "查询TCarWalletReport")
    public ActionRsp getTCarWalletReport(TCarWalletReport tCarWalletReport, HttpServletRequest request){
        TCompanyUser user=SessionUtil.getUser();
        tCarWalletReport.setParkId(SessionUtil.getParkId());
        tCarWalletReport.setCompanyId(user.getCompanyId());
        String beginDate = (request.getParameter("beginDate")==null)?"":(request.getParameter("beginDate"));
        String endDate = (request.getParameter("endDate")==null)?"":(request.getParameter("endDate"));
        return ActionRspUtil.Success(tCarWalletReportService.getTCarWalletReport(tCarWalletReport,beginDate,endDate));
    }

    /**
     * 查询TCarWalletReport信息(分页)
     * @param tCarWalletReport
     * @return
     */
    @PostMapping(value = "/getTCarWalletReportbyPage")
    @HttpLog(operationType = "0",modularTypeName = "查询TCarWalletReport(分页)")
    public ControllerRsp getTCarWalletReportbyPage(TCarWalletReport tCarWalletReport, Integer page, Integer limit, HttpServletRequest request){
        TCompanyUser user=SessionUtil.getUser();
        tCarWalletReport.setParkId(SessionUtil.getParkId());
        tCarWalletReport.setCompanyId(user.getCompanyId());
        String beginDate = (request.getParameter("beginTime")==null)?"":(request.getParameter("beginTime"));
        String endDate = (request.getParameter("endTime")==null)?"":(request.getParameter("endTime"));
        return ControllerRspUtil.Success(tCarWalletReportService.getTCarWalletReportbyPage(tCarWalletReport,beginDate,endDate,page,limit));
    }

    /**
     * 更新TCarWalletReport信息
     * @param tCarWalletReport
     * @return
     */
    @PostMapping(value = "/updateTCarWalletReport")
    @HttpLog(operationType = "1",modularTypeName = "更新TCarWalletReport信息")
    public ActionRsp UpdateTCarWalletReport(TCarWalletReport tCarWalletReport)
    {
        return ActionRspUtil.Success(tCarWalletReportService.UpdateTCarWalletReport(tCarWalletReport));
    }
    /**
     * 手动统计TCarWalletReport信息
     * @param tCarWalletReport
     * @return
     */
    @PostMapping(value = "/statistics")
    @HttpLog(operationType = "1",modularTypeName = "手动统计TCarWalletReport信息")
    public ActionRsp Statistics(TCarWalletReport tCarWalletReport, HttpServletRequest request)
    {
        TCompanyUser user=SessionUtil.getUser();
        tCarWalletReport.setParkId(SessionUtil.getParkId());
        tCarWalletReport.setCompanyId(user.getCompanyId());
        String beginDate = (request.getParameter("beginTime")==null)?"":(request.getParameter("beginTime"));
        String endDate = (request.getParameter("endTime")==null)?"":(request.getParameter("endTime"));
        return ActionRspUtil.Success(tCarWalletReportService.statisticsNow(beginDate,endDate,tCarWalletReport));
    }

    /**
     * 删除TCarWalletReport信息
     * @param tCarWalletReport
     * @return
     */
    @PostMapping(value = "/deleteTCarWalletReport")
    @HttpLog(operationType = "1",modularTypeName = "删除TCarWalletReport信息")
    public ActionRsp DeleteTCarWalletReport(TCarWalletReport tCarWalletReport){
        return ActionRspUtil.Success(tCarWalletReportService.DeleteTCarWalletReport(tCarWalletReport));
    }
}
