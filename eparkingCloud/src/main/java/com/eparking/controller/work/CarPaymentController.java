package com.eparking.controller.work;

import com.common.annotation.HttpLog;
import com.common.entity.ControllerRsp;
import com.common.entity.eparkingCloud.TCarPayment;
import com.eparking.service.CarPaymentService;
import com.common.util.ControllerRspUtil;
import com.common.util.SessionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "carPayment")
public class CarPaymentController {
    private  static final Logger logger= LoggerFactory.getLogger(CarPaymentController.class);
    @Autowired
    private CarPaymentService carPaymentService;

    @PostMapping(value = "/getCarPayment")
    @HttpLog(operationType = "0",modularTypeName = "查询月租车支付记录")
    public ControllerRsp getCarPaymentByPage(TCarPayment tCarPayment,Integer page, Integer limit,HttpServletRequest request){
        tCarPayment.setParkId(SessionUtil.getParkId());
        String beginData = (request.getParameter("payTimeBegin")==null)?"":(request.getParameter("payTimeBegin"));
        String endData = (request.getParameter("payTimeEnd")==null)?"":(request.getParameter("payTimeEnd"));
        return ControllerRspUtil.Success(carPaymentService.getCarPaymentByPage(tCarPayment,beginData,endData,page,limit));
    }
}
