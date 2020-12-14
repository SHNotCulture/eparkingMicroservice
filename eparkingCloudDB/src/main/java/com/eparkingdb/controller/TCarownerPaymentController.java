package com.eparkingdb.controller;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TCarownerPayment;
import com.common.entity.eparkingCloud.TCarownerPaymentCus;
import com.common.annotation.HttpLog;
import com.eparkingdb.service.TCarownerPaymentService;
import com.common.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* @Description: TCarownerPaymentController类
* @author 谢轩然
* @date 2020/09/11 16:29
*/
@RestController
@RequestMapping("/tCarownerPayment")
public class TCarownerPaymentController {

    private  static final Logger logger= LoggerFactory.getLogger(TCarownerPaymentController.class);

    @Autowired
    private TCarownerPaymentService tCarownerPaymentService;

    /**
    * 查询TCarownerPayment信息
    * @paramtCarownerPayment
    * @return
    */
    @PostMapping(value = "/getTCarownerPayment")
    @HttpLog(operationType = "0",modularTypeName = "查询TCarownerPayment")
    public List<TCarownerPayment> getTCarownerPayment(@RequestBody TCarownerPayment tCarownerPayment,@RequestParam("beginDate") String beginDate,@RequestParam("endDate") String endDate){
    return tCarownerPaymentService.getTCarownerPayment(tCarownerPayment,beginDate,endDate);
    }

    /**
    * 查询TCarownerPayment信息(分页)
    * @paramtCarownerPayment
    * @return
    */
    @PostMapping(value = "/getTCarownerPaymentbyPage")
    @HttpLog(operationType = "0",modularTypeName = "查询TCarownerPayment(分页)")
    public PageBean<TCarownerPayment> getTCarownerPaymentbyPage(@RequestBody TCarownerPayment tCarownerPayment, @RequestParam("beginDate") String beginDate, @RequestParam("endDate") String endDate, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit){
    return tCarownerPaymentService.getTCarownerPaymentbyPage(tCarownerPayment,beginDate,endDate,page,limit);
    }

    /**
    * 更新TCarownerPayment信息
    * @paramtCarownerPayment
    * @return
    */
    @PostMapping(value = "/updateTCarownerPayment")
    @HttpLog(operationType = "1",modularTypeName = "更新TCarownerPayment信息")
    public String UpdateTCarownerPayment(@RequestBody TCarownerPayment tCarownerPayment)
    {
        return tCarownerPaymentService.UpdateTCarownerPayment(tCarownerPayment);
    }

    /**
    * 删除TCarownerPayment信息
    * @param tCarownerPayment
    * @return
    */
    @PostMapping(value = "/deleteTCarownerPayment")
    @HttpLog(operationType = "1",modularTypeName = "删除TCarownerPayment信息")
    public String DeleteTCarownerPayment(@RequestBody TCarownerPayment tCarownerPayment){
    return tCarownerPaymentService.DeleteTCarownerPayment( tCarownerPayment);
    }

    /**
     * 根据ID查询TCarownerPayment信息
     * @param id
     * @return
     */
    @PostMapping(value = "/getTCarownerPaymentByID")
    @HttpLog(operationType = "1",modularTypeName = "删除TCarownerPayment信息")
    public TCarownerPayment getTCarownerPaymentByID(@RequestParam("id") Integer id){
        return tCarownerPaymentService.getTCarownerPaymentByID(id);
    }

    @PostMapping(value = "/getTCarownerPaymentNewbyPage")
    @HttpLog(operationType = "0",modularTypeName = "查询TCarownerPaymentNew(分页)")
    public PageBean<TCarownerPayment> getTCarownerPaymentNewbyPage(@RequestBody TCarownerPaymentCus tCarownerPaymentCus, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit){
        return tCarownerPaymentService.getTCarownerPaymentNewbyPage(tCarownerPaymentCus,page,limit);
    }
}