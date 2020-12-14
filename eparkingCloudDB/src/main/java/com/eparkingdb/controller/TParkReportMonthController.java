package com.eparkingdb.controller;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TParkReportMonth;
import com.common.annotation.HttpLog;
import com.eparkingdb.service.TParkReportMonthService;
import com.common.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* @Description: TParkReportMonthController类
* @author 谢轩然
* @date 2020/08/03 16:21
*/
@RestController
@RequestMapping("/tParkReportMonth")
public class TParkReportMonthController {

    private  static final Logger logger= LoggerFactory.getLogger(TParkReportMonthController.class);

    @Autowired
    private TParkReportMonthService tParkReportMonthService;

    /**
    * 查询TParkReportMonth信息
    * @paramtParkReportMonth
    * @return
    */
    @PostMapping(value = "/getTParkReportMonth")
    @HttpLog(operationType = "0",modularTypeName = "查询TParkReportMonth")
    public List<TParkReportMonth> getTParkReportMonth(@RequestBody TParkReportMonth tParkReportMonth, @RequestParam("beginTime") String beginTime, @RequestParam("endTime") String endTime){
    return tParkReportMonthService.getTParkReportMonth(tParkReportMonth,beginTime,endTime);
    }

    /**
    * 查询TParkReportMonth信息(分页)
    * @paramtParkReportMonth
    * @return
    */
    @PostMapping(value = "/getTParkReportMonthbyPage")
    @HttpLog(operationType = "0",modularTypeName = "查询TParkReportMonth(分页)")
    public PageBean<TParkReportMonth> getTParkReportMonthbyPage(@RequestBody TParkReportMonth tParkReportMonth, @RequestParam("beginTime") String beginTime, @RequestParam("endTime") String endTime, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit){
    return tParkReportMonthService.getTParkReportMonthbyPage(tParkReportMonth,beginTime,endTime,page,limit);
    }

    /**
    * 更新TParkReportMonth信息
    * @paramtParkReportMonth
    * @return
    */
    @PostMapping(value = "/updateTParkReportMonth")
    @HttpLog(operationType = "1",modularTypeName = "更新TParkReportMonth信息")
    public String UpdateTParkReportMonth(@RequestBody TParkReportMonth tParkReportMonth)
    {
        return tParkReportMonthService.UpdateTParkReportMonth(tParkReportMonth);
    }

    /**
    * 删除TParkReportMonth信息
    * @param tParkReportMonth
    * @return
    */
    @PostMapping(value = "/deleteTParkReportMonth")
    @HttpLog(operationType = "1",modularTypeName = "删除TParkReportMonth信息")
    public String DeleteTParkReportMonth(@RequestBody TParkReportMonth tParkReportMonth){
    return tParkReportMonthService.DeleteTParkReportMonth(tParkReportMonth);
    }
}