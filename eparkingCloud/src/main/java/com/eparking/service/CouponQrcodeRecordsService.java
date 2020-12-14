package com.eparking.service;

import com.common.entity.eparkingCloud.TConponQrcode;
import com.common.entity.PageBean;

import java.util.List;

public interface CouponQrcodeRecordsService {
    PageBean<TConponQrcode> getCouponQrcodeListbyPage(TConponQrcode tConponQrcode, Integer page, Integer limit);
    String updateCouponQrcode(TConponQrcode tConponQrcode);
    String deleteCouponQrcode(TConponQrcode tConponQrcode);
    List<TConponQrcode> getTConponQrcode(TConponQrcode tConponQrcode);
//    String createCouponQrcode(TConponQrcode tConponQrcode);
    Integer getIdCouponQrcode(TConponQrcode tConponQrcode);
    void existQrcodeName(TConponQrcode tConponQrcode);
    void lessDiscountAmount(TConponQrcode tConponQrcode, Integer busineId);
}
