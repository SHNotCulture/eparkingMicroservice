package com.eparkingdb.controller;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TParkInOut;
import com.common.annotation.HttpLog;
import com.eparkingdb.service.TParkInOutService;
import com.eparkingdb.service.TParkingRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parkingRecord")
public class TParkingRecordController {
    private  static final Logger logger= LoggerFactory.getLogger(TParkingRecordController.class);

    @Autowired
    private TParkingRecordService tParkingRecordService;
    @Autowired
    private TParkInOutService tParkInOutService;
    /**
     * 查询进出场记录（分页）
     * @param tParkInOut
     * @param page
     * @param limit
     * @return
     */
    @PostMapping(value = "/getParkinoutbyPage")
    @HttpLog(operationType = "0",modularTypeName = "查询进出场记录(分页)")
    public PageBean<TParkInOut> getParkinoutbyPage(@RequestBody TParkInOut tParkInOut,@RequestParam("outTimeBegin") String outTimeBegin,@RequestParam("outTimeEnd") String outTimeEnd, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit){
        return tParkingRecordService.getParkinoutbyPage(tParkInOut,outTimeBegin,outTimeEnd,page,limit);
    }

    @PostMapping(value = "/getParkInOut")
    @HttpLog(operationType = "0",modularTypeName = "查询进出场记录")
    public List<TParkInOut> getParkInOut(@RequestBody TParkInOut tParkInOut,@RequestParam("outTimeBegin") String outTimeBegin,@RequestParam("outTimeEnd") String outTimeEnd){
        return tParkingRecordService.getParkinout(tParkInOut,outTimeBegin,outTimeEnd);
    }

    @PostMapping(value = "/updateParkInOut")
    @HttpLog(operationType = "1",modularTypeName = "更新进出场记录")
    public String updateParkInOut(@RequestBody TParkInOut tParkInOut){
        return tParkingRecordService.UpdateTParkInOut(tParkInOut);
    }

    @PostMapping(value = "/getParkinoutDetailbyPage")
    @HttpLog(operationType = "0",modularTypeName = "查询进出场记录(分页)")
    public PageBean<TParkInOut> getParkinoutDetailbyPage(@RequestBody TParkInOut tParkInOut,@RequestParam("parkId") String parkId, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit){
        return tParkingRecordService.getParkinoutDetailbyPage(tParkInOut,parkId,page,limit);
    }

    @PostMapping(value = "/getInCarbyPage")
    @HttpLog(operationType = "0",modularTypeName = "查询进出场记录(分页)")
    public PageBean<TParkInOut> getInCarbyPage(@RequestBody TParkInOut tParkInOut, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit){
        return tParkingRecordService.getInCarbyPage(tParkInOut,page,limit);
    }
}
