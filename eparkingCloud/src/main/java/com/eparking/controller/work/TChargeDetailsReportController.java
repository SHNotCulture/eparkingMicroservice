package com.eparking.controller.work;

import com.common.annotation.HttpLog;
import com.common.entity.ActionRsp;
import com.common.entity.ControllerRsp;
import com.common.entity.eparkingCloud.TChargeDetailsReport;
import com.common.entity.eparkingCloud.TCompanyUser;
import com.eparking.service.TChargeDetailsReportService;
import com.common.util.ActionRspUtil;
import com.common.util.ControllerRspUtil;
import com.common.util.DateUtil;
import com.common.util.SessionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
* @Description: TChargeDetailsReportController类
* @author 谢轩然
* @date 2020/11/03 17:02
*/
@RestController
@RequestMapping("tChargeDetailsReport")
public class TChargeDetailsReportController {

    private  static final Logger logger= LoggerFactory.getLogger(TChargeDetailsReportController.class);

    @Autowired
    private TChargeDetailsReportService tChargeDetailsReportService;

    /**
    * 查询TChargeDetailsReport信息
    * @paramtChargeDetailsReport
    * @return
    */
    @PostMapping(value = "/getTChargeDetailsReport")
    @HttpLog(operationType = "0",modularTypeName = "查询TChargeDetailsReport")
    public ActionRsp getTChargeDetailsReport(TChargeDetailsReport tChargeDetailsReport , HttpServletRequest request){
        String beginDate = request.getParameter("beginDate");
        if (beginDate==null){
            beginDate="";
        }
        String endDate = request.getParameter("endDate");
        if (endDate==null){
            endDate="";
        }
    return ActionRspUtil.Success(tChargeDetailsReportService.getTChargeDetailsReport(tChargeDetailsReport,beginDate,endDate));
    }

    /**
    * 查询TChargeDetailsReport信息(分页)
    * @paramtChargeDetailsReport
    * @return
    */
    @PostMapping(value = "/getTChargeDetailsReportbyPage")
    @HttpLog(operationType = "0",modularTypeName = "查询TChargeDetailsReport(分页)")
    public ControllerRsp getTChargeDetailsReportbyPage(TChargeDetailsReport tChargeDetailsReport, HttpServletRequest request, Integer page, Integer limit){
        tChargeDetailsReport.setParkId(SessionUtil.getParkId());
        String beginDate = request.getParameter("beginDate");
        if (beginDate==null){
            beginDate="";
        }
        String endDate = request.getParameter("endDate");
        if (endDate==null){
            endDate="";
        }
    return ControllerRspUtil.Success(tChargeDetailsReportService.getTChargeDetailsReportbyPage(tChargeDetailsReport,beginDate,endDate,page,limit));
    }

    /**
    * 更新TChargeDetailsReport信息
    * @paramtChargeDetailsReport
    * @return
    */
    @PostMapping(value = "/updateTChargeDetailsReport")
    @HttpLog(operationType = "1",modularTypeName = "更新TChargeDetailsReport信息")
    public ActionRsp UpdateTChargeDetailsReport(TChargeDetailsReport tChargeDetailsReport, HttpServletRequest request)
    {
        return ActionRspUtil.Success(tChargeDetailsReportService.UpdateTChargeDetailsReport(tChargeDetailsReport));
    }

    /**
    * 删除TChargeDetailsReport信息
    * @param tChargeDetailsReport
    * @return
    */
    @PostMapping(value = "/deleteTChargeDetailsReport")
    @HttpLog(operationType = "1",modularTypeName = "删除TChargeDetailsReport信息")
    public ActionRsp DeleteTChargeDetailsReport(TChargeDetailsReport tChargeDetailsReport){
    return ActionRspUtil.Success(tChargeDetailsReportService.DeleteTChargeDetailsReport(tChargeDetailsReport));
    }

    /**
     * 报表统计
     * @return
     */
    @PostMapping(value = "/chargeReportStatistics")
    @HttpLog(operationType = "1",modularTypeName = "分类报表统计")
    public ActionRsp chargeReportStatistics(String beginDate, String endDate) {
        Integer parkId = SessionUtil.getParkId();
        TCompanyUser user = SessionUtil.getUser();
        TChargeDetailsReport tChargeDetailsReport = new TChargeDetailsReport();
        tChargeDetailsReport.setParkId(parkId);
        tChargeDetailsReport.setCompanyId(user.getCompanyId());
        return ActionRspUtil.Success(tChargeDetailsReportService.chargeReportStatistics(tChargeDetailsReport,beginDate,endDate));
    }

    @PostMapping(value = "/getDutyStatisticsbyPage")
    @HttpLog(operationType = "0",modularTypeName = "当班即时统计查询(分页)")
    public ControllerRsp getDutyStatisticsbyPage(TChargeDetailsReport tChargeDetailsReport, HttpServletRequest request, Integer page, Integer limit){
        tChargeDetailsReport.setParkId(SessionUtil.getParkId());
        String dutyPerson = (request.getParameter("dutyPerson")==null)?"":(request.getParameter("dutyPerson"));
        String beginTime = request.getParameter("beginTime");
        if (beginTime==null || beginTime.equals("")){
            beginTime= DateUtil.getPredate()+" 00:00:00";
        }
        String endTime = request.getParameter("endTime");
        if (endTime==null || endTime.equals("")){
            endTime= DateUtil.getPredate()+" 23:59:59";
        }
        tChargeDetailsReport.setParkId(SessionUtil.getParkId());
        tChargeDetailsReport.setDutyPerson(dutyPerson);
        return ControllerRspUtil.Success(tChargeDetailsReportService.getDutyStatisticsbyPage(tChargeDetailsReport,beginTime,endTime,page,limit));
    }
}