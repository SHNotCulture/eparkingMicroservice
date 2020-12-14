package com.eparkingdb.controller;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TParkDuty;
import com.common.annotation.HttpLog;
import com.eparkingdb.service.CashPaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "cashPayment")
public class CashPaymentController {
    private  static final Logger logger= LoggerFactory.getLogger(CashPaymentController.class);
    @Autowired
    private CashPaymentService cashPaymentService;


    /**
     * 查询TParkDuty信息
     * @param tParkDuty
     * @return
     */
    @PostMapping(value = "/getTParkDuty")
    @HttpLog(operationType = "0",modularTypeName = "查询TParkDuty")
    public List<TParkDuty> getTParkDuty(@RequestBody TParkDuty tParkDuty, @RequestParam("beginTime") String beginTime, @RequestParam("endTime") String endTime){
        return cashPaymentService.getTParkDuty(tParkDuty,beginTime,endTime);
    }

    /**
     * 查询TParkDuty信息(分页)
     * @param tElectronPayment
     * @return
     */
    @PostMapping(value = "/getTParkDutybyPage")
    @HttpLog(operationType = "0",modularTypeName = "查询TParkDuty(分页)")
    public PageBean<TParkDuty> getTParkDutybyPage(@RequestBody TParkDuty tElectronPayment, @RequestParam("beginTime") String beginTime, @RequestParam("endTime") String endTime, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit){
        return cashPaymentService.getTParkDutybyPage(tElectronPayment,beginTime,endTime,page,limit);
    }

    /**
     * 更新TParkDuty信息
     * @param tParkDuty
     * @return
     */
    @PostMapping(value = "/UpdateTParkDuty")
    @HttpLog(operationType = "1",modularTypeName = "更新TParkDuty信息")
    public String UpdateTParkDuty(@RequestBody TParkDuty tParkDuty)
    {
        return cashPaymentService.UpdateTParkDuty(tParkDuty);
    }

    /**
     * 删除TParkDuty信息
     * @param tParkDuty
     * @return
     */
    @PostMapping(value = "/DeleteTParkDuty")
    @HttpLog(operationType = "1",modularTypeName = "删除TParkDuty信息")
    public String DeleteTParkDuty(@RequestBody TParkDuty tParkDuty)
    {
        return cashPaymentService.DeleteTParkDuty(tParkDuty);
    }
}
