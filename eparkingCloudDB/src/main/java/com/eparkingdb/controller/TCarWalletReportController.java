package com.eparkingdb.controller;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TCarWalletReport;
import com.common.annotation.HttpLog;
import com.eparkingdb.service.TCarWalletReportService;
import com.common.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
* @Description: TCarWalletReportController类
* @author 李书瀚
* @date 2020/10/26 23:19
*/
@RestController
@RequestMapping("/tCarWalletReport")
public class TCarWalletReportController {

    private  static final Logger logger= LoggerFactory.getLogger(TCarWalletReportController.class);

    @Autowired
    private TCarWalletReportService tCarWalletReportService;

    /**
    * 查询TCarWalletReport信息
    * @paramtCarWalletReport
    * @return
    */
    @PostMapping(value = "/getTCarWalletReport")
    @HttpLog(operationType = "0",modularTypeName = "查询TCarWalletReport")
    public List<TCarWalletReport> getTCarWalletReport(@RequestBody TCarWalletReport tCarWalletReport, @RequestParam("beginTime") String beginTime, @RequestParam("endTime") String endTime, HttpServletRequest request){
        return tCarWalletReportService.getTCarWalletReport(tCarWalletReport,beginTime,endTime);
    }

    /**
    * 查询TCarWalletReport信息(分页)
    * @paramtCarWalletReport
    * @return
    */
    @PostMapping(value = "/getTCarWalletReportbyPage")
    @HttpLog(operationType = "0",modularTypeName = "查询TCarWalletReport(分页)")
    public PageBean<TCarWalletReport> getTCarWalletReportbyPage(@RequestBody TCarWalletReport tCarWalletReport, @RequestParam("beginTime") String beginTime, @RequestParam("endTime") String endTime, HttpServletRequest request, Integer page, Integer limit){
        return tCarWalletReportService.getTCarWalletReportbyPage(tCarWalletReport,beginTime,endTime,page,limit);
    }

    /**
    * 更新TCarWalletReport信息
    * @paramtCarWalletReport
    * @return
    */
    @PostMapping(value = "/updateTCarWalletReport")
    @HttpLog(operationType = "1",modularTypeName = "更新TCarWalletReport信息")
    public String UpdateTCarWalletReport(@RequestBody TCarWalletReport tCarWalletReport,HttpServletRequest request)
    {
        return  tCarWalletReportService. UpdateTCarWalletReport(tCarWalletReport);
    }

    /**
    * 删除TCarWalletReport信息
    * @param tCarWalletReport
    * @return
    */
    @PostMapping(value = "/deleteTCarWalletReport")
    @HttpLog(operationType = "1",modularTypeName = "删除TCarWalletReport信息")
    public String DeleteTCarWalletReport(@RequestBody TCarWalletReport tCarWalletReport){
    return tCarWalletReportService.DeleteTCarWalletReport( tCarWalletReport);
    }
}