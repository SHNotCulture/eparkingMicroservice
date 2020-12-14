package com.eparkingdb.controller;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TParkReportYear;
import com.common.annotation.HttpLog;
import com.eparkingdb.service.TParkReportYearService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* @Description: TParkReportYearController类
* @author 谢轩然
* @date 2020/08/04 10:38
*/
@RestController
@RequestMapping("/tParkReportYear")
public class TParkReportYearController {

    private  static final Logger logger= LoggerFactory.getLogger(TParkReportYearController.class);

    @Autowired
    private TParkReportYearService tParkReportYearService;

    /**
    * 查询TParkReportYear信息
    * @paramtParkReportYear
    * @return
    */
    @PostMapping(value = "/getTParkReportYear")
    @HttpLog(operationType = "0",modularTypeName = "查询TParkReportYear")
    public List<TParkReportYear> getTParkReportYear(@RequestBody TParkReportYear tParkReportYear,@RequestParam("beginTime") String beginTime,@RequestParam("endTime") String endTime){
    return tParkReportYearService.getTParkReportYear(tParkReportYear,beginTime,endTime);
    }

    /**
    * 查询TParkReportYear信息(分页)
    * @paramtParkReportYear
    * @return
    */
    @PostMapping(value = "/getTParkReportYearbyPage")
    @HttpLog(operationType = "0",modularTypeName = "查询TParkReportYear(分页)")
    public PageBean<TParkReportYear> getTParkReportYearbyPage(@RequestBody TParkReportYear tParkReportYear, @RequestParam("beginTime") String beginTime, @RequestParam("endTime") String endTime, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit){
    return tParkReportYearService.getTParkReportYearbyPage(tParkReportYear,beginTime,endTime,page,limit);
    }

    /**
    * 更新TParkReportYear信息
    * @paramtParkReportYear
    * @return
    */
    @PostMapping(value = "/updateTParkReportYear")
    @HttpLog(operationType = "1",modularTypeName = "更新TParkReportYear信息")
    public String UpdateTParkReportYear(@RequestBody TParkReportYear tParkReportYear)
    {
        return tParkReportYearService.UpdateTParkReportYear(tParkReportYear);
    }

    /**
    * 删除TParkReportYear信息
    * @param tParkReportYear
    * @return
    */
    @PostMapping(value = "/deleteTParkReportYear")
    @HttpLog(operationType = "1",modularTypeName = "删除TParkReportYear信息")
    public String DeleteTParkReportYear(@RequestBody TParkReportYear tParkReportYear){
    return tParkReportYearService.DeleteTParkReportYear(tParkReportYear);
    }
}