package com.eparkingdb.controller;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TPaymentDetails;
import com.common.annotation.HttpLog;
import com.eparkingdb.service.TPaymentDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* @Description: TPaymentDetailsController类
* @author 谢轩然
* @date 2020/10/13 17:31
*/
@RestController
@RequestMapping("/tPaymentDetails")
public class TPaymentDetailsController {

    private  static final Logger logger= LoggerFactory.getLogger(TPaymentDetailsController.class);

    @Autowired
    private TPaymentDetailsService tPaymentDetailsService;

    /**
    * 查询TPaymentDetails信息
    * @paramtPaymentDetails
    * @return
    */
    @PostMapping(value = "/getTPaymentDetails")
    @HttpLog(operationType = "0",modularTypeName = "查询TPaymentDetails")
    public List<TPaymentDetails> getTPaymentDetails(@RequestBody TPaymentDetails tPaymentDetails){
    return tPaymentDetailsService.getTPaymentDetails(tPaymentDetails);
    }

    /**
    * 查询TPaymentDetails信息(分页)
    * @paramtPaymentDetails
    * @return
    */
    @PostMapping(value = "/getTPaymentDetailsbyPage")
    @HttpLog(operationType = "0",modularTypeName = "查询TPaymentDetails(分页)")
    public PageBean<TPaymentDetails> getTPaymentDetailsbyPage(@RequestBody TPaymentDetails tPaymentDetails, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit){
    return tPaymentDetailsService.getTPaymentDetailsbyPage(tPaymentDetails,page,limit);
    }

    /**
    * 更新TPaymentDetails信息
    * @paramtPaymentDetails
    * @return
    */
    @PostMapping(value = "/updateTPaymentDetails")
    @HttpLog(operationType = "1",modularTypeName = "更新TPaymentDetails信息")
    public String UpdateTPaymentDetails(@RequestBody TPaymentDetails tPaymentDetails)
    {
        return tPaymentDetailsService.UpdateTPaymentDetails(tPaymentDetails);
    }

    /**
    * 删除TPaymentDetails信息
    * @param tPaymentDetails
    * @return
    */
    @PostMapping(value = "/deleteTPaymentDetails")
    @HttpLog(operationType = "1",modularTypeName = "删除TPaymentDetails信息")
    public String DeleteTPaymentDetails(@RequestBody TPaymentDetails tPaymentDetails){
    return tPaymentDetailsService.DeleteTPaymentDetails(tPaymentDetails);
    }
}