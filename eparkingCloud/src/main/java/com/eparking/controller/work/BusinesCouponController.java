package com.eparking.controller.work;

import com.common.annotation.HttpLog;
import com.common.entity.ControllerRsp;
import com.common.entity.eparkingCloud.TBusinesCoupon;
import com.eparking.insideService.TBusineInsideService;
import com.eparking.insideService.TBusinesCouponInsideService;
import com.eparking.service.BusineService;
import com.eparking.service.MerchantCouponRecordService;
import com.common.util.ControllerRspUtil;
import com.common.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author xiexuanran
 * @Description: 查询商户消费记录
 * @Date Create in 2019-4-15 11:36
 * @Modified By:
 */

@RestController
@RequestMapping(value = "businesCoupon")
public class BusinesCouponController {

    @Autowired
    private MerchantCouponRecordService merchantCouponRecordService;
    @Autowired
    private BusineService busineService;
    @Autowired
    private TBusineInsideService tBusineInsideService;
    @Autowired
    private TBusinesCouponInsideService tBusinesCouponInsideService;

    @PostMapping(value = "/getTBusinesCouponbyPage")
    @HttpLog(operationType = "0",modularTypeName = "查询商户消费记录")
    public ControllerRsp getTBusinesCouponbyPage(TBusinesCoupon tBusinesCoupon,
                                                  String couponTimeBegin, String couponTimeEnd,Integer page, Integer limit){
        tBusinesCoupon.setParkId(SessionUtil.getParkId());   //通过登录信息获得车场ID
        String account =  SessionUtil.getUser().getUserAccout();
        Integer busineId = busineService.selectByAccount(account).getId();     //通过登录信息中account获得商户ID
//        Integer busineId = JsonUtil.jsonToBean(JsonUtil.beanToJson(tBusineInsideService.selectByAccount(account).getResult()),TBusinesCoupon.class).getId();
        tBusinesCoupon.setBusineId(busineId);
        if(couponTimeBegin==null){
            couponTimeBegin="";
        }
        if(couponTimeEnd==null){
            couponTimeEnd="";
        }
        return ControllerRspUtil.Success(merchantCouponRecordService.FindMerchantCouponbyPage(tBusinesCoupon, couponTimeBegin, couponTimeEnd,page,limit));
//        return tBusinesCouponInsideService.getTBusinesCouponbyPage(tBusinesCoupon,couponTimeBegin,couponTimeEnd,page,limit);
    }
}
