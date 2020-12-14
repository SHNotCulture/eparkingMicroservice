package com.eparking.controller.work;

import com.common.annotation.HttpLog;
import com.common.entity.ActionRsp;
import com.common.entity.ControllerRsp;
import com.common.entity.eparkingCloud.TMonthBindingCar;
import com.common.entity.eparkingCloud.TParkCar;
import com.eparking.service.MonthBindingCarService;
import com.eparking.service.MonthlyCarService;
import com.common.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "monthBinding")
public class MonthBindingCarController {
    private  static final Logger logger= LoggerFactory.getLogger(MonthBindingCarController.class);

    @Autowired
    private MonthBindingCarService monthBindingCarService;
    @Autowired
    private MonthlyCarService monthlyCarService;

    @PostMapping(value = "/getMonthBindingCarbyPage")
    @HttpLog(operationType = "0",modularTypeName = "查询登记绑定车(分页)")
    public ControllerRsp getMonthBindingCarbyPage(TMonthBindingCar tMonthBindingCar, HttpServletRequest request, Integer page, Integer limit){
        tMonthBindingCar.setParkId(SessionUtil.getParkId());
        return ControllerRspUtil.Success(monthBindingCarService.getMonthBindingCarbyPage(tMonthBindingCar,page,limit));
    }

    @PostMapping(value = "/getMonthBindingCar")
    @HttpLog(operationType = "0",modularTypeName = "查询登记绑定车")
    public ActionRsp getMonthBindingCar(TMonthBindingCar tMonthBindingCar, HttpServletRequest request){
        tMonthBindingCar.setParkId(SessionUtil.getParkId());
        return ActionRspUtil.Success(monthBindingCarService.getMonthBindingCar(tMonthBindingCar));
    }

    @PostMapping(value = "/updateMonthBindingCar")
    @HttpLog(operationType = "1",modularTypeName = "编辑登记绑定车")
    public ActionRsp updateMonthBindingCar(TMonthBindingCar tMonthBindingCar, HttpServletRequest request){
        tMonthBindingCar.setParkId(SessionUtil.getParkId());
        TParkCar tParkCar = monthlyCarService.getTParkCarById(tMonthBindingCar.getParkCarId());
        if(tParkCar!=null){
            tMonthBindingCar.setCarType(tParkCar.getCarType());
            tMonthBindingCar.setUpdateTime(DateUtil.getCurDateTime());
            tMonthBindingCar.setUserId(SessionUtil.getUser().getId());
            tMonthBindingCar.setCreateTime(DateUtil.getCurDateTime());
        }
        return ActionRspUtil.Success(monthBindingCarService.updateMonthBindingCar(tMonthBindingCar));
    }

    @PostMapping(value = "/deleteMonthBindingCar")
    @HttpLog(operationType = "1",modularTypeName = "删除登记绑定车")
    public ActionRsp deleteMonthBindingCar(TMonthBindingCar tMonthBindingCar){
        return ActionRspUtil.Success(monthBindingCarService.deleteMonthBindingCar(tMonthBindingCar));
    }

}
