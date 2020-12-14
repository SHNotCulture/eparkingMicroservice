package com.eparking.service;

import com.common.entity.eparkingCloud.TBusinePay;
import com.common.entity.PageBean;

public interface MerchantRechargeRecordService {
    PageBean<TBusinePay> findMerchantRechargeRecoreparkingdbyPage(TBusinePay tBusinePay, Integer busineId, String PayTimeBegin, String PayTimeEnd, Integer page, Integer limit);
    //List<TBusinePay> findMerchantPay(TBusinePay tBusinePay, String PayTimeBegin, String PayTimeEnd);
}
