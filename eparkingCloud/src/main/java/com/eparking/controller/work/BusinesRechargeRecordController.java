package com.eparking.controller.work;


import com.common.annotation.HttpLog;
import com.common.entity.ControllerRsp;
import com.common.entity.eparkingCloud.TBusinePay;
import com.eparking.insideService.TBusineInsideService;
import com.eparking.insideService.TBusinePayInsideService;
import com.eparking.service.BusinePayService;
import com.eparking.service.BusineService;
import com.eparking.service.ElectronicTicketService;
import com.eparking.service.MerchantRechargeRecordService;
import com.common.util.ControllerRspUtil;
import com.common.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author xiexuanran
 * @Description:
 * @Date Create in 2019-4-24 11:36
 * @Modified By:
 */

@RestController
@RequestMapping(value = "businesRechargeRecord")
public class BusinesRechargeRecordController {
    @Autowired
    private MerchantRechargeRecordService merchantRechargeRecordService;
    @Autowired
    private BusineService busineService;
    @Autowired
    private BusinePayService businePayService;
    @Autowired
    private ElectronicTicketService electronicTicketService;
    @Autowired
    private TBusinePayInsideService tBusinePayInsideService;
    @Autowired
    private TBusineInsideService tBusineInsideService;

    @PostMapping(value = "/getTBusinePaybyPage")
    @HttpLog(operationType = "0",modularTypeName = "查询商户充值记录")
    public ControllerRsp getTMerchantPay(TBusinePay tBusinePay, String payTimeBegin, String payTimeEnd, Integer page, Integer limit){
//        String timeType = request.getParameter("timeType");
        tBusinePay.setParkId(SessionUtil.getParkId());
        String account =  SessionUtil.getUser().getUserAccout();
        Integer busineId = busineService.selectByAccount(account).getId();
//        Integer busineId = JsonUtil.jsonToBean(JsonUtil.beanToJson(tBusineInsideService.selectByAccount(account).getResult()),TBusinesCoupon.class).getId();
//        return ControllerRspUtil.Success(merchantRechargeRecordService.findMerchantRechargeRecoreparkingdbyPage(tBusinePay,busineId, PayTimeBegin, PayTimeEnd,page,limit));
        if(payTimeBegin==null){
            payTimeBegin="";
        }
        if(payTimeEnd==null){
            payTimeEnd="";
        }
//        return tBusinePayInsideService.getTBusinePaybyPage(tBusinePay,busineId,payTimeBegin,payTimeEnd,page,limit);
        return ControllerRspUtil.Success(businePayService.getTBusinePaybyPage(tBusinePay,busineId,page,limit,payTimeBegin,payTimeEnd));
    }

}
