package com.eparkingdb.controller;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TParkReport;
import com.common.annotation.HttpLog;
import com.eparkingdb.service.TParkReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tParkReport")
public class TParkReportController {
    private static final Logger logger = LoggerFactory.getLogger(TParkReportController.class);
    @Autowired
    private TParkReportService tParkReportService;

    /**
     * 查询TParkReport信息
     *
     * @return
     * @param tParkReport
     */
    @PostMapping(value = "/getTParkReport")
    @HttpLog(operationType = "0", modularTypeName = "查询TParkReport")
    public List<TParkReport> getTParkReport(@RequestBody TParkReport tParkReport, @RequestParam("beginTime") String beginTime, @RequestParam("endTime") String endTime) {
        return tParkReportService.getTParkReport(tParkReport,beginTime,endTime);
    }

    /**
     * 查询TParkReport信息(分页)
     *
     * @return
     * @param tParkReport
     */
    @PostMapping(value = "/getTParkReportbyPage")
    @HttpLog(operationType = "0", modularTypeName = "查询TParkReport(分页)")
    public PageBean<TParkReport> getTParkReportbyPage(@RequestBody TParkReport tParkReport, @RequestParam("beginTime") String beginTime, @RequestParam("endTime") String endTime, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit) {
        return tParkReportService.getTParkReportbyPage(tParkReport,beginTime,endTime, page, limit);
    }

    @PostMapping(value = "/updateTParkReport")
    @HttpLog(operationType = "1", modularTypeName = "更新TParkReport")
    public String updateTParkReport(@RequestBody TParkReport tParkReport) {
        return tParkReportService.updateTParkReport(tParkReport);
    }

    @PostMapping(value = "/deleteTParkReport")
    @HttpLog(operationType = "1", modularTypeName = "删除TParkReport")
    public String deleteTParkReport(@RequestBody TParkReport tParkReport) {
        return tParkReportService.deleteTParkReport(tParkReport);
    }
}
