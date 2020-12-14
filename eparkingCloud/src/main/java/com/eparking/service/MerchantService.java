package com.eparking.service;


import com.common.entity.eparkingCloud.BusineCouponUpload;

public interface MerchantService {
//    Integer getMerchantCouponType(TBusine tBusine);
    String transferBusineCoupon(BusineCouponUpload busineCouponUpload);
}
