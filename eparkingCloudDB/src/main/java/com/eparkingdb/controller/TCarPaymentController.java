package com.eparkingdb.controller;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TCarPayment;
import com.common.annotation.HttpLog;
import com.eparkingdb.service.TCarPaymentService;
import com.common.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* @Description: TCarPaymentController类
* @author 谢轩然
* @date 2020/05/14 16:54
*/
@RestController
@RequestMapping("/tCarPayment")
public class TCarPaymentController {

    private  static final Logger logger= LoggerFactory.getLogger(TCarPaymentController.class);

    @Autowired
    private TCarPaymentService tCarPaymentService;

    /**
    * 查询TCarPayment信息
    * @paramtCarPayment
    * @return
    */
    @PostMapping(value = "/getTCarPayment")
    @HttpLog(operationType = "0",modularTypeName = "查询TCarPayment")
    public List<TCarPayment> getTCarPayment(@RequestBody TCarPayment tCarPayment, @RequestParam("beginData") String beginData, @RequestParam("endData") String endData){
    return tCarPaymentService.getTCarPayment(tCarPayment,beginData,endData);
    }

    /**
    * 查询TCarPayment信息(分页)
    * @paramtCarPayment
    * @return
    */
    @PostMapping(value = "/getTCarPaymentbyPage")
    @HttpLog(operationType = "0",modularTypeName = "查询TCarPayment(分页)")
    public PageBean<TCarPayment> getTCarPaymentbyPage(@RequestBody TCarPayment tCarPayment, @RequestParam("beginData") String beginData, @RequestParam("endData") String endData, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit){
    return tCarPaymentService.getTCarPaymentbyPage(tCarPayment,beginData,endData,page,limit);
    }

    /**
    * 更新TCarPayment信息
    * @paramtCarPayment
    * @return
    */
    @PostMapping(value = "/updateTCarPayment")
    @HttpLog(operationType = "1",modularTypeName = "更新TCarPayment信息")
    public String UpdateTCarPayment(@RequestBody TCarPayment tCarPayment)
    {
        return tCarPaymentService.UpdateTCarPayment(tCarPayment);
    }

    /**
    * 删除TCarPayment信息
    * @param tCarPayment
    * @return
    */
    @PostMapping(value = "/deleteTCarPayment")
    @HttpLog(operationType = "1",modularTypeName = "删除TCarPayment信息")
    public String DeleteTCarPayment(@RequestBody TCarPayment tCarPayment){
    return tCarPaymentService.DeleteTCarPayment(tCarPayment);
    }
}