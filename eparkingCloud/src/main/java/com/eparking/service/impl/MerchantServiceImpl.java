package com.eparking.service.impl;


import com.eparking.controller.work.TruckSpaceController;
import com.common.entity.eparkingCloud.BusineCouponUpload;
import com.eparking.service.BusineService;
import com.eparking.service.MerchantService;
import com.eparking.service.MonthlyCarService;
import com.common.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 商户平台实现类
 */

@Service
public class MerchantServiceImpl implements MerchantService {
    private  static final Logger logger= LoggerFactory.getLogger(TruckSpaceController.class);
/*    @Autowired
    private TBusineMapper tBusineMapper;*/
    @Autowired
    private BusineService busineService;
    @Autowired
    private MonthlyCarService monthlyCarService;



    /**
     * 查询优惠类型
     * @param
     * @return
     */
/*    @Override
    public Integer getMerchantCouponType(TBusine tBusine) {
        Integer couponType =busineService.getBusine(tBusine).get(0).getDiscountType();
        return couponType;
    }*/

    @Override
    public String transferBusineCoupon(BusineCouponUpload busineCouponUpload) {
        String result = null;
        if(monthlyCarService.whetherParkCar(busineCouponUpload.getParkId(),busineCouponUpload.getCarPlate(),"","").size()>0){
            result = "车牌为月租车，不能下发优惠";
        }else{
            Map<String,String> mapRequest = new HashMap<>();
            mapRequest.put("id","004");
            mapRequest.put("key",MD5Util.MD5Encode("47883bd731ae0fe24c2534eb435bb046" + StringUtil3.monthHOUR(-1)));
            mapRequest.put("parkId",busineCouponUpload.getParkId().toString());
            mapRequest.put("service","transferBusinCoupon");
            mapRequest.put("data",JsonUtil.beanToJson(busineCouponUpload));
//            result = HttpClientUtil.doPost("http://yun.eparking.top/GZBZTheThirdAPI/mainAction",mapRequest);
            //result = HttpClientUtil.doPost("http://s2.eparking.top:8082/eparkingAPI/mainAction",mapRequest);
            result = HttpUtil.doPost("http://localhost:8082/mainAction",mapRequest);
//            logger.info(result);
        }
        return result;
    }

    public static void main(String[] args) {
        /*BusineCouponUpload busineCouponUpload = new BusineCouponUpload();
        busineCouponUpload.setTicketId(19);
        busineCouponUpload.setParkId(274);
        busineCouponUpload.setInTime("2019-04-22 17:21:57");
        busineCouponUpload.setCouponType(3);
        busineCouponUpload.setCouponPay(10.00);
        busineCouponUpload.setCompanyId(1);
        busineCouponUpload.setCarPlate("粤CS0001");
        busineCouponUpload.setBusineId(360);
        busineCouponUpload.setBusineTicketId(26);
        Map<String,String> mapRequest = new HashMap<>();
        mapRequest.put("ID","001");
        mapRequest.put("key",MD5Util.MD5Encode("BDmlp48liikpom9hj7eczwj7aduGh7io" + StringUtil3.monthHOUR(-1)));
        mapRequest.put("parkId",busineCouponUpload.getParkId().toString());
        mapRequest.put("service","transferBusinCoupon");
        mapRequest.put("data",JsonUtil.beanToJson(busineCouponUpload));
        String r = HttpClientUtil.doPost("http://localhost:8080/mainAction",mapRequest);
        System.out.println(r);*/

        System.out.println("568".indexOf("5"));

    }

}
