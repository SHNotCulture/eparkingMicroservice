package com.eparkingdb.controller;

import com.common.entity.ActionRsp;
import com.common.entity.ControllerRsp;
import com.common.entity.eparkingCloud.TParkStatistics;
import com.common.annotation.HttpLog;
import com.eparkingdb.service.TParkStatisticsService;
import com.common.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
* @Description: TParkStatisticsController类
* @author 李书瀚
* @date 2020/06/09 16:05
*/
@RestController
@RequestMapping("/tParkStatistics")
public class TParkStatisticsController {

    private  static final Logger logger= LoggerFactory.getLogger(TParkStatisticsController.class);

    @Autowired
    private TParkStatisticsService tParkStatisticsService;

    /**
    * 查询TParkStatistics信息
    * @paramtParkStatistics
    * @return
    */
    @PostMapping(value = "/getTParkStatistics")
    @HttpLog(operationType = "0",modularTypeName = "查询TParkStatistics")
    public ActionRsp getTParkStatistics(@RequestBody TParkStatistics tParkStatistics, HttpServletRequest request){
        tParkStatistics.setParkId(SessionUtil.getParkId());
    return ActionRspUtil.Success(tParkStatisticsService.getTParkStatistics(tParkStatistics));
    }
    @PostMapping(value = "/getTParkStatisticsbyOne")
    @HttpLog(operationType = "0",modularTypeName = "查询TParkStatistics")
    public ActionRsp getTParkStatisticsbyOne(@RequestBody TParkStatistics tParkStatistics, HttpServletRequest request){
        tParkStatistics.setParkId(SessionUtil.getParkId());
        List<TParkStatistics> parkStatisticsList=tParkStatisticsService.getTParkStatistics(tParkStatistics);
        //if()
        return ActionRspUtil.Success();
    }

    /**
    * 查询TParkStatistics信息(分页)
    * @paramtParkStatistics
    * @return
    */
    @PostMapping(value = "/getTParkStatisticsbyPage")
    @HttpLog(operationType = "0",modularTypeName = "查询TParkStatistics(分页)")
    public ControllerRsp getTParkStatisticsbyPage(@RequestBody TParkStatistics tParkStatistics, HttpServletRequest request, Integer page, Integer limit){
    return ControllerRspUtil.Success(tParkStatisticsService.getTParkStatisticsbyPage(tParkStatistics,page,limit));
    }

    /**
    * 更新TParkStatistics信息
    * @paramtParkStatistics
    * @return
    */
    @PostMapping(value = "/updateTParkStatistics")
    @HttpLog(operationType = "1",modularTypeName = "更新TParkStatistics信息")
    public ActionRsp UpdateTParkStatistics(@RequestBody TParkStatistics tParkStatistics,HttpServletRequest request)
    {
        return ActionRspUtil.Success(tParkStatisticsService. UpdateTParkStatistics(tParkStatistics));
    }

    /**
    * 删除TParkStatistics信息
    * @param tParkStatistics
    * @return
    */
    @PostMapping(value = "/deleteTParkStatistics")
    @HttpLog(operationType = "1",modularTypeName = "删除TParkStatistics信息")
    public ActionRsp DeleteTParkStatistics(@RequestBody TParkStatistics tParkStatistics){
    return ActionRspUtil.Success(tParkStatisticsService.DeleteTParkStatistics( tParkStatistics));
    }
}