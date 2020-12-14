package com.eparkingdb.controller;

import com.common.entity.ActionRsp;
import com.common.entity.ControllerRsp;
import com.common.entity.eparkingCloud.TParkStatisticsDay;
import com.common.annotation.HttpLog;
import com.eparkingdb.service.TParkStatisticsDayService;
import com.common.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
* @Description: TParkStatisticsDayController类
* @author 李书瀚
* @date 2020/06/09 16:05
*/
@RestController
@RequestMapping("/tParkStatisticsDay")
public class TParkStatisticsDayController {

    private  static final Logger logger= LoggerFactory.getLogger(TParkStatisticsDayController.class);

    @Autowired
    private TParkStatisticsDayService tParkStatisticsDayService;

    /**
    * 查询TParkStatisticsDay信息
    * @paramtParkStatisticsDay
    * @return
    */
    @PostMapping(value = "/getTParkStatisticsDay")
    @HttpLog(operationType = "0",modularTypeName = "查询TParkStatisticsDay")
    public ActionRsp getTParkStatisticsDay(@RequestBody TParkStatisticsDay tParkStatisticsDay, HttpServletRequest request){
        tParkStatisticsDay.setParkId(SessionUtil.getParkId());
        tParkStatisticsDay.setStatisticTime(DateUtil.getCurDateYMR());
    return ActionRspUtil.Success(tParkStatisticsDayService.getTParkStatisticsDay(tParkStatisticsDay));
    }

    /**
    * 查询TParkStatisticsDay信息(分页)
    * @paramtParkStatisticsDay
    * @return
    */
    @PostMapping(value = "/getTParkStatisticsDaybyPage")
    @HttpLog(operationType = "0",modularTypeName = "查询TParkStatisticsDay(分页)")
    public ControllerRsp getTParkStatisticsDaybyPage(@RequestBody TParkStatisticsDay tParkStatisticsDay, HttpServletRequest request, Integer page, Integer limit){
    return ControllerRspUtil.Success(tParkStatisticsDayService.getTParkStatisticsDaybyPage(tParkStatisticsDay,page,limit));
    }

    /**
    * 更新TParkStatisticsDay信息
    * @paramtParkStatisticsDay
    * @return
    */
    @PostMapping(value = "/updateTParkStatisticsDay")
    @HttpLog(operationType = "1",modularTypeName = "更新TParkStatisticsDay信息")
    public ActionRsp UpdateTParkStatisticsDay(@RequestBody TParkStatisticsDay tParkStatisticsDay,HttpServletRequest request)
    {
        return ActionRspUtil.Success(tParkStatisticsDayService. UpdateTParkStatisticsDay(tParkStatisticsDay));
    }

    /**
    * 删除TParkStatisticsDay信息
    * @param tParkStatisticsDay
    * @return
    */
    @PostMapping(value = "/deleteTParkStatisticsDay")
    @HttpLog(operationType = "1",modularTypeName = "删除TParkStatisticsDay信息")
    public ActionRsp DeleteTParkStatisticsDay(@RequestBody TParkStatisticsDay tParkStatisticsDay){
    return ActionRspUtil.Success(tParkStatisticsDayService.DeleteTParkStatisticsDay( tParkStatisticsDay));
    }
}