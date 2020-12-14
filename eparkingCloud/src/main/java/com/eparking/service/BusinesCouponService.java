package com.eparking.service;

import com.common.entity.eparkingCloud.TBusinesCoupon;
import com.common.entity.eparkingCloud.TParkInOut;

import java.util.List;
import java.util.Map;

public interface BusinesCouponService {
    List<Map> getCouponPayMonth(TBusinesCoupon tBusinesCoupon, String year);
    List<Map> getCouponPayDay(TBusinesCoupon tBusinesCoupon, String year, String month);
    TBusinesCoupon setTBusinesCoupon();
    List<Map> returnCouponPayDay();
    List<Map> returnCouponPayMonth();
    Double couponSumPay();
    Double couponNeedPay();
    Double couponBalance();
    List getBusinesCouponByCarplate(TParkInOut tParkInOut);
    }

