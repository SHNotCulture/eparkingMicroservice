package com.eparking.service;

import com.common.entity.eparkingCloud.TTicketCoupon;
import com.common.entity.PageBean;


public interface ElectronicCouponRecordsService {
//    List<TTicketCoupon> getTElectronicTicket(TTicketCoupon tTicketCoupon,String InTimeBegin,String InTimeEnd,String OutTimeBegin,String OutTimeEnd);
    PageBean<TTicketCoupon> getTElectronicTicketbyPage(TTicketCoupon tTicketCoupon, String inTimeBegin, String inTimeEnd, String outTimeBegin, String outTimeEnd, Integer page, Integer limit);
}
