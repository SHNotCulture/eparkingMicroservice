package com.eparking.controller.work;

import com.common.annotation.HttpLog;
import com.common.entity.ActionRsp;
import com.common.entity.ControllerRsp;
import com.common.entity.eparkingCloud.TParkPort;
import com.eparking.insideService.TParkPortInsideService;
import com.eparking.service.ParkPortService;
import com.common.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName tParkPort
 * @Author xiexuanran
 * @Date 2019/10/30 16:25
 **/
@RestController
@RequestMapping(value = "parkPort")
public class PortController {
    private static final Logger logger = LoggerFactory.getLogger(PortController.class);

    @Autowired
    private ParkPortService parkPortService;
    @Autowired
    private TParkPortInsideService tParkPortInsideService;


    /**
     *  查询车场通道
     * @param tParkPort
     * @param request
     * @param page
     * @param limit
     * @return
     */
    @PostMapping(value = "/getParkPortbyPage")
    @HttpLog(operationType = "0",modularTypeName = "查询车场通道")
    public ControllerRsp getParkPortbyPage(TParkPort tParkPort, HttpServletRequest request, Integer page, Integer limit){
        tParkPort.setParkId(Integer.valueOf(request.getSession().getAttribute(Common.ParkId).toString()));
        return ControllerRspUtil.Success(parkPortService.getParkPortbyPage(tParkPort,page,limit));
//        return tParkPortInsideService.getTParkPortbyPage(tParkPort, page, limit);
    }

    /**
     * 编辑车场通道
     */
    @PostMapping(value = "/updateParkPort")
    @HttpLog(operationType = "1",modularTypeName = "编辑车场通道")
    public ActionRsp updateParkPort(TParkPort tParkPort, HttpServletRequest request){
        tParkPort.setParkId(Integer.valueOf(request.getSession().getAttribute(Common.ParkId).toString()));
        return ActionRspUtil.Success(parkPortService.updateParkPort(tParkPort));
//        return tParkPortInsideService.UpdateTParkPort(tParkPort);
    }
    /**
     * 删除车场通道
     */
    @PostMapping(value = "/deleteParkPort")
    @HttpLog(operationType = "1",modularTypeName = "删除车场通道")
    public ActionRsp deleteParkPort(TParkPort tParkPort){
        return ActionRspUtil.Success(parkPortService.deleteParkPort(tParkPort));
//        return tParkPortInsideService.DeleteTParkPort(tParkPort);
    }
}
