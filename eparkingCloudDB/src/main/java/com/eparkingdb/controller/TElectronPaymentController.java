package com.eparkingdb.controller;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TElectronPayment;
import com.common.annotation.HttpLog;
import com.eparkingdb.service.TElectronPaymentService;
import com.common.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* @Description: TElectronPaymentController类
* @author 谢轩然
* @date 2020/04/09 14:58
*/
@RestController
@RequestMapping("/tElectronPayment")
public class TElectronPaymentController {

    private  static final Logger logger= LoggerFactory.getLogger(TElectronPaymentController.class);

    @Autowired
    private TElectronPaymentService tElectronPaymentService;

    /**
    * 查询TElectronPayment信息
    * @paramtElectronPayment
    * @return
    */
    @PostMapping(value = "/getTElectronPayment")
    @HttpLog(operationType = "0",modularTypeName = "查询TElectronPayment")
    public List<TElectronPayment> getTElectronPayment(@RequestBody TElectronPayment tElectronPayment, @RequestParam("beginTime") String beginTime, @RequestParam("endTime") String endTime){
    return tElectronPaymentService.getTElectronPayment(tElectronPayment,beginTime,endTime);
    }

    /**
    * 查询TElectronPayment信息(分页)
    * @paramtElectronPayment
    * @return
    */
    @PostMapping(value = "/getTElectronPaymentbyPage")
    @HttpLog(operationType = "0",modularTypeName = "查询TElectronPayment(分页)")
    public PageBean<TElectronPayment> getTElectronPaymentbyPage(@RequestBody TElectronPayment tElectronPayment, @RequestParam("beginTime") String beginTime, @RequestParam("endTime") String endTime, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit){
    return tElectronPaymentService.getTElectronPaymentbyPage(tElectronPayment,beginTime,endTime,page,limit);
    }

    /**
    * 更新TElectronPayment信息
    * @paramtElectronPayment
    * @return
    */
    @PostMapping(value = "/updateTElectronPayment")
    @HttpLog(operationType = "1",modularTypeName = "更新TElectronPayment信息")
    public String UpdateTElectronPayment(@RequestBody TElectronPayment tElectronPayment)
    {
        return tElectronPaymentService.UpdateTElectronPayment(tElectronPayment);
    }

    /**
    * 删除TElectronPayment信息
    * @param tElectronPayment
    * @return
    */
    @PostMapping(value = "/deleteTElectronPayment")
    @HttpLog(operationType = "1",modularTypeName = "删除TElectronPayment信息")
    public String DeleteTElectronPayment(@RequestBody TElectronPayment tElectronPayment){
    return tElectronPaymentService.DeleteTElectronPayment( tElectronPayment);
    }
}