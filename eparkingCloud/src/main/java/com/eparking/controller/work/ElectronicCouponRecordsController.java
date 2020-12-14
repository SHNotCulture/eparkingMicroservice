package com.eparking.controller.work;

import com.common.annotation.HttpLog;
import com.common.entity.ControllerRsp;
import com.common.entity.eparkingCloud.TBusine;
import com.common.entity.eparkingCloud.TTicketCoupon;
import com.eparking.insideService.TBusineInsideService;
import com.eparking.insideService.TTicketCouponInsideService;
import com.eparking.service.ElectronicCouponRecordsService;
import com.common.util.ControllerRspUtil;
import com.common.util.SessionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Author xiexuanran
 * @Description: 电子券使用记录查询
 * @Date Create in 2019-4-12 15:36
 * @Modified By:
 */
@RestController
@RequestMapping(value = "ticketCoupon")
public class ElectronicCouponRecordsController {
    private  static final Logger logger= LoggerFactory.getLogger(TruckSpaceController.class);
    @Autowired
    private ElectronicCouponRecordsService electronicCouponRecordsService;

    @Autowired
    private TTicketCouponInsideService tTicketCouponInsideService;
    @Autowired
    private TBusineInsideService tBusineInsideService;

    /**
     *电子优惠券记录查询
     * @return
     */
    @PostMapping(value = "/getTElectronicTicketbyPage")
    @HttpLog(operationType = "0",modularTypeName = "查询电子优惠券记录")
    public ControllerRsp getTElectronicTicketbyPage(TTicketCoupon tTicketCoupon,String inTimeBegin,String inTimeEnd,String outTimeBegin,String outTimeEnd, Integer page, Integer limit){
        tTicketCoupon.setParkId(SessionUtil.getParkId());
        TBusine tBusine = new TBusine();
        tBusine.setParkId(SessionUtil.getParkId());
        tBusine.setAccount(SessionUtil.getUser().getUserAccout());
        tTicketCoupon.setBusineId(tBusineInsideService.getTBusine(tBusine).get(0).getId());
        if(inTimeBegin==null){
            inTimeBegin="";
        }
        if(inTimeEnd==null){
            inTimeEnd="";
        }
        if(outTimeBegin==null){
            outTimeBegin="";
        }
        if(outTimeEnd==null){
            outTimeEnd="";
        }
        return ControllerRspUtil.Success(electronicCouponRecordsService.getTElectronicTicketbyPage(tTicketCoupon,inTimeBegin,inTimeEnd,outTimeBegin,outTimeEnd,page,limit));
//        return tTicketCouponInsideService.getTTicketCouponbyPage(tTicketCoupon,inTimeBegin,inTimeEnd,outTimeBegin,outTimeEnd,page,limit);
    }

}
