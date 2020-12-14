package com.eparking.service;

import com.common.entity.eparkingCloud.TBusinesCoupon;
import com.common.entity.PageBean;

import java.util.List;


public interface MerchantCouponRecordService {
    PageBean<TBusinesCoupon> FindMerchantCouponbyPage(TBusinesCoupon tBusinesCoupon, String CouponTimeBegin, String CouponTimeEnd, Integer page, Integer limit);
    List<TBusinesCoupon> FindMerchantCoupon(TBusinesCoupon tBusinesCoupon, String CouponTimeBegin, String CouponTimeEnd);
}
