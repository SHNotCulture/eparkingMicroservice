package com.eparkingdb.controller;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TParkInOut;
import com.common.annotation.HttpLog;
import com.eparkingdb.service.TParkInOutService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tParkInOut")
public class TParkInOutController {
    private  static final Logger logger= LoggerFactory.getLogger(TParkInOutController.class);

    @Autowired
    private TParkInOutService tParkInOutService;
    @PostMapping(value = "/getTParkInOutbyorderId")
    public TParkInOut getTParkInOutbyorderId(String orderId, Integer parkId){
        return  tParkInOutService.getTParkInOutbyorderId(orderId,parkId);
    }

    @PostMapping(value = "/getTParkInOutForStatistics")
    public List<TParkInOut> getTParkInOutForStatistics(@RequestBody TParkInOut tParkInOut, @RequestParam("outTimeBegin") String outTimeBegin, @RequestParam("outTimeEnd") String outTimeEnd){
        return  tParkInOutService.getTParkInOutForStatistics(tParkInOut,outTimeBegin,outTimeEnd);
    }

    @PostMapping(value = "/getTParkinoutUnlimitByPage")
    @HttpLog(operationType = "0",modularTypeName = "查询进出场记录无限制(分页)")
    public PageBean<TParkInOut> getTParkinoutUnlimitByPage(@RequestBody TParkInOut tParkInOut, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit){
        return tParkInOutService.getTParkinoutUnlimitByPage(tParkInOut,page,limit);
    }
    @PostMapping(value = "/UpdateTParkInOut")
    @HttpLog(operationType = "0",modularTypeName = "更新进出场记录")
    public  String UpdateTParkInOut(@RequestBody TParkInOut tParkInOut){
        return tParkInOutService.UpdateTParkInOut(tParkInOut);
    }

    @PostMapping(value = "/getTParkInOutUnlimit")
    @HttpLog(operationType = "0",modularTypeName = "查询进出场记录无限制")
    public List<TParkInOut> getTParkInOutUnlimit(@RequestBody TParkInOut tParkInOut){
        return tParkInOutService.getTParkInOutUnlimit(tParkInOut);
    }
}
