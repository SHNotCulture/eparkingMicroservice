package com.eparkingdb.controller;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TParkDuty;
import com.common.annotation.HttpLog;
import com.eparkingdb.service.TParkDutyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tParkDuty")
public class TParkDutyController {

    private static final Logger logger = LoggerFactory.getLogger(TParkDutyController.class);

    @Autowired
    private TParkDutyService tParkDutyService;

    /**
     * 查询TParkDuty信息
     *
     * @return
     * @param tParkDuty
     */
    @PostMapping(value = "/getTParkDuty")
    @HttpLog(operationType = "0", modularTypeName = "查询TParkDuty")
    public List<TParkDuty> getTParkDuty(@RequestBody TParkDuty tParkDuty, @RequestParam("beginTime") String beginTime, @RequestParam("endTime") String endTime) {
        return tParkDutyService.getTParkDuty(tParkDuty,beginTime,endTime);
    }

    @PostMapping(value = "/getTParkDutybyPage")
    @HttpLog(operationType = "0", modularTypeName = "查询TParkDuty(分页)")
    public PageBean<TParkDuty> getTParkDutybyPage(@RequestBody TParkDuty tParkDuty, @RequestParam("beginTime") String beginTime, @RequestParam("endTime") String endTime, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit){
        return tParkDutyService.getTParkDutyListbyPage(tParkDuty, beginTime, endTime, page, limit);
    }

    @PostMapping(value = "/updateTParkDuty")
    @HttpLog(operationType = "1", modularTypeName = "更新TParkDuty信息")
    public String UpdateTParkDuty(@RequestBody TParkDuty tParkDuty) {
        return tParkDutyService.UpdateTParkDuty(tParkDuty);
    }

    @PostMapping(value = "/deleteTParkDuty")
    @HttpLog(operationType = "1", modularTypeName = "删除TParkDuty信息")
    public String DeleteTParkDuty(@RequestBody TParkDuty tParkDuty) {
        return tParkDutyService.DeleteTParkDuty(tParkDuty);
    }
}
