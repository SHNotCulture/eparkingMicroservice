package com.eparkingdb.controller;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TChargeDetailsReport;
import com.common.annotation.HttpLog;
import com.eparkingdb.service.TChargeDetailsReportService;
import com.common.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* @Description: TChargeDetailsReportController类
* @author 谢轩然
* @date 2020/11/03 16:47
*/
@RestController
@RequestMapping("/tChargeDetailsReport")
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
    public List<TChargeDetailsReport> getTChargeDetailsReport(@RequestBody TChargeDetailsReport tChargeDetailsReport,@RequestParam("beginDate") String beginDate ,@RequestParam("endDate") String endDate){
    return tChargeDetailsReportService.getTChargeDetailsReport(tChargeDetailsReport,beginDate,endDate);
    }

    /**
    * 查询TChargeDetailsReport信息(分页)
    * @paramtChargeDetailsReport
    * @return
    */
    @PostMapping(value = "/getTChargeDetailsReportbyPage")
    @HttpLog(operationType = "0",modularTypeName = "查询TChargeDetailsReport(分页)")
    public PageBean<TChargeDetailsReport> getTChargeDetailsReportbyPage(@RequestBody TChargeDetailsReport tChargeDetailsReport, @RequestParam("beginDate") String beginDate , @RequestParam("endDate") String endDate, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit){
    return tChargeDetailsReportService.getTChargeDetailsReportbyPage(tChargeDetailsReport,beginDate,endDate,page,limit);
    }

    /**
    * 更新TChargeDetailsReport信息
    * @paramtChargeDetailsReport
    * @return
    */
    @PostMapping(value = "/updateTChargeDetailsReport")
    @HttpLog(operationType = "1",modularTypeName = "更新TChargeDetailsReport信息")
    public String UpdateTChargeDetailsReport(@RequestBody TChargeDetailsReport tChargeDetailsReport)
    {
        return tChargeDetailsReportService.UpdateTChargeDetailsReport(tChargeDetailsReport);
    }

    /**
    * 删除TChargeDetailsReport信息
    * @param tChargeDetailsReport
    * @return
    */
    @PostMapping(value = "/deleteTChargeDetailsReport")
    @HttpLog(operationType = "1",modularTypeName = "删除TChargeDetailsReport信息")
    public String DeleteTChargeDetailsReport(@RequestBody TChargeDetailsReport tChargeDetailsReport){
    return tChargeDetailsReportService.DeleteTChargeDetailsReport(tChargeDetailsReport);
    }

    @PostMapping(value = "/chargeReportStatistics")
    @HttpLog(operationType = "0",modularTypeName = "chargeReport统计")
    public String chargeReportStatistics(@RequestBody TChargeDetailsReport tChargeDetailsReport,@RequestParam("beginDate") String beginDate,@RequestParam("endDate") String endDate){
        return tChargeDetailsReportService.chargeReportStatistics(tChargeDetailsReport,beginDate,endDate);
    }

    @PostMapping(value = "/getDutyStatisticsbyPage")
    @HttpLog(operationType = "0",modularTypeName = "当班即时统计查询")
    public PageBean<TChargeDetailsReport> getDutyStatisticsbyPage(@RequestBody TChargeDetailsReport tChargeDetailsReport,@RequestParam("beginTime") String beginTime,@RequestParam("endTime") String endTime, @RequestParam("page") Integer page,@RequestParam("limit") Integer limit){
        return tChargeDetailsReportService.getDutyStatisticsbyPage(tChargeDetailsReport,beginTime,endTime,page,limit);
    }
}