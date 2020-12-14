package com.eparkingdb.controller;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TParkInOut;
import com.common.annotation.HttpLog;
import com.eparkingdb.service.TPresentCarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Present")
public class TPresentCarController {

    private  static final Logger logger= LoggerFactory.getLogger(TPresentCarController.class);

    @Autowired
    private TPresentCarService tPresentCarService;

    /**
     * 查询TPresentCar信息(分页)
     * @paramtPropertyAutobill
     * @return
     */
    @PostMapping(value = "/getTPresentCarbyPage")
    @HttpLog(operationType = "0",modularTypeName = "查询在场车辆(分页)")
    public PageBean<TParkInOut> getTPresentCarbyPage(@RequestBody TParkInOut tParkInOut, @RequestParam("parkId") String parkId, @RequestParam("timeType") String timeType, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit){
        return tPresentCarService.getTPresentCarbyPage(tParkInOut,parkId,timeType,page,limit);
    }

    @PostMapping(value = "/getTPresentCar")
    @HttpLog(operationType = "0",modularTypeName = "查询在场车辆")
    public List<TParkInOut> getTPresentCar(@RequestBody TParkInOut tParkInOut, @RequestParam("parkId") String parkId, @RequestParam("timeType") String timeType){
        return tPresentCarService.getTPresentCar(tParkInOut,parkId,timeType);
    }
}
