package com.eparking.controller.work;


import com.common.annotation.HttpLog;
import com.common.entity.ControllerRsp;
import com.common.entity.eparkingCloud.TParkDuty;
import com.eparking.service.CashPaymentService;
import com.common.util.ControllerRspUtil;
import com.common.util.SessionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "cashPayment")
public class CashPaymentController {

    private  static final Logger logger= LoggerFactory.getLogger(CarParkController.class);
    @Autowired
    private CashPaymentService carParkService;


    @PostMapping(value = "/getTParkDutyListbyPage")
    @HttpLog(operationType = "0",modularTypeName = "查询现金支付对账信息")
    public ControllerRsp getTParkDutyListbyPage(TParkDuty tParkDuty,String beginTime,String endTime,Integer page, Integer limit){
        if(beginTime==null){
            beginTime="";
        }
        if(endTime==null){
            endTime="";
        }
        tParkDuty.setParkId(SessionUtil.getParkId());
        return ControllerRspUtil.Success(carParkService.getTParkDutyListbyPage(tParkDuty,beginTime,endTime,page,limit));
    }


}
