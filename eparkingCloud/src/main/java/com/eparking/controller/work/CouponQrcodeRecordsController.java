package com.eparking.controller.work;

import com.common.annotation.HttpLog;
import com.common.entity.ActionRsp;
import com.common.entity.ControllerRsp;
import com.common.entity.eparkingCloud.TBusine;
import com.common.entity.eparkingCloud.TConponQrcode;
import com.eparking.insideService.TBusineInsideService;
import com.eparking.insideService.TConponQrcodeInsideService;
import com.eparking.service.BusineService;
import com.eparking.service.CouponQrcodeRecordsService;
import com.common.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * created by xiexuanran 2019-04-29
 */
@RestController
@RequestMapping(value = "couponQrcodeRecords")
public class CouponQrcodeRecordsController {

    @Autowired
    private CouponQrcodeRecordsService couponQrcodeRecordsService;
    @Autowired
    private BusineService busineService;
    @Autowired
    private TConponQrcodeInsideService tConponQrcodeInsideService;
    @Autowired
    private TBusineInsideService tBusineInsideService;

    /**
     * 生成二维码优惠记录
     */
    @PostMapping(value = "/")
    @HttpLog(operationType = "1",modularTypeName = "生成优惠二维码列表")
    public ActionRsp createCouponQrcode(TConponQrcode tConponQrcode){
        couponQrcodeRecordsService.existQrcodeName(tConponQrcode);
//        tConponQrcodeInsideService.existTConponQrcodeName(tConponQrcode);
        String account =  SessionUtil.getUser().getUserAccout();
        TBusine tBusine = busineService.selectByAccount(account);
//        TBusine tBusine =JsonUtil.jsonToBean(JsonUtil.beanToJson(tBusineInsideService.selectByAccount(account).getResult()),TBusine.class);
        tConponQrcode.setCompanyId(SessionUtil.getCompany().getId());
        tConponQrcode.setParkId(SessionUtil.getParkId());
        tConponQrcode.setCreateTime(DateUtil.getCurDateTime());
        tConponQrcode.setBusineId(tBusine.getId());
        if(tConponQrcode.getValidTime()=="" || tConponQrcode.getValidTime()==null){
            tConponQrcode.setValidTime("null");
        }
        //小于消费最大金额判断
        couponQrcodeRecordsService.lessDiscountAmount(tConponQrcode,tBusine.getId());
//        tConponQrcodeInsideService.lessDiscountAmount(tConponQrcode,tBusine.getId());
        tConponQrcode.setTicketId(0);
        tConponQrcode.setQrcodeUrl("http://yun.eparking.top/wechat/view/scope?parkid="+SessionUtil.getParkId()+"&companyId="+SessionUtil.getCompany().getId()+"&busineId="+tBusine.getId()+"&couponType="+tBusine.getDiscountType()+"&couponPay="+tConponQrcode.getCouponPay()+"&ticketId=0"+"&qrcodeId="+"&validTime="+tConponQrcode.getValidTime());
        couponQrcodeRecordsService.updateCouponQrcode(tConponQrcode);
//        tConponQrcodeInsideService.UpdateTConponQrcode(tConponQrcode);
//        TConponQrcode tConponQrcodeRes =JsonUtil.jsonToBean(JsonUtil.beanToJson(tConponQrcodeInsideService.getTConponQrcode(tConponQrcode).getResult()),TConponQrcode.class);
        TConponQrcode tConponQrcodeRes = couponQrcodeRecordsService.getTConponQrcode(tConponQrcode).get(0);
        Integer qrcodeId = tConponQrcodeRes.getId();
        tConponQrcode.setId(qrcodeId);
        tConponQrcode.setQrcodeUrl("http://yun.eparking.top/wechat/view/scope?parkid="+SessionUtil.getParkId()+"&companyId="+SessionUtil.getCompany().getId()+"&busineId="+tBusine.getId()+"&couponType="+tBusine.getDiscountType()+"&couponPay="+tConponQrcode.getCouponPay()+"&ticketId=0"+"&qrcodeId="+qrcodeId+"&validTime="+tConponQrcode.getValidTime());
        couponQrcodeRecordsService.updateCouponQrcode(tConponQrcode);
//        tConponQrcodeInsideService.UpdateTConponQrcode(tConponQrcode);
        return ActionRspUtil.Success(tConponQrcode.getQrcodeUrl());
    }

    /**
     * 查询二维码优惠记录
     */
    @PostMapping(value = "/getCouponQrcodeList")
    @HttpLog(operationType = "0",modularTypeName = "查询优惠二维码列表")
    public ControllerRsp getCouponQrcodeListbyPage(TConponQrcode tConponQrcode, Integer page, Integer limit){
        tConponQrcode.setCompanyId(SessionUtil.getCompany().getId());
        tConponQrcode.setParkId(SessionUtil.getParkId());
        TBusine tBusine = new TBusine();
        tBusine.setParkId(SessionUtil.getParkId());
        tBusine.setAccount(SessionUtil.getUser().getUserAccout());
        tConponQrcode.setBusineId(tBusineInsideService.getTBusine(tBusine).get(0).getId());
        return ControllerRspUtil.Success(couponQrcodeRecordsService.getCouponQrcodeListbyPage(tConponQrcode,page,limit));
//        return tConponQrcodeInsideService.getTConponQrcodebyPage(tConponQrcode,page,limit);
    }
    /**
     * 编辑二维码优惠记录
     */
    @PostMapping(value = "/updateCouponQrcode")
    @HttpLog(operationType = "1",modularTypeName = "编辑优惠二维码")
    public ActionRsp updateCouponQrcode(TConponQrcode tConponQrcode){
        String account =  SessionUtil.getUser().getUserAccout();
        TBusine tBusine = busineService.selectByAccount(account);
//        TBusine tBusine =JsonUtil.jsonToBean(JsonUtil.beanToJson(tBusineInsideService.selectByAccount(account).getResult()),TBusine.class);
        tConponQrcode.setCompanyId(SessionUtil.getCompany().getId());
        tConponQrcode.setParkId(SessionUtil.getParkId());
        tConponQrcode.setQrcodeUrl("http://yun.eparking.top/wechat/view/scope?parkid="+SessionUtil.getParkId()+"&companyId="+SessionUtil.getCompany().getId()+"&busineId="+tBusine.getId()+"&couponType="+tBusine.getDiscountType()+"&couponPay="+tConponQrcode.getCouponPay()+"&ticketId=0"+"&qrcodeId="+tConponQrcode.getId()+"validTime="+tConponQrcode.getValidTime());
        return ActionRspUtil.Success(couponQrcodeRecordsService.updateCouponQrcode(tConponQrcode));
//        return ActionRspUtil.Success(tConponQrcodeInsideService.UpdateTConponQrcode(tConponQrcode));
    }
    /**
     * 删除二维码优惠记录
     */
    @PostMapping(value = "/deleteCouponQrcode")
    @HttpLog(operationType = "1",modularTypeName = "删除优惠二维码")
    public ActionRsp deleteCouponQrcode(TConponQrcode tConponQrcode){
        return ActionRspUtil.Success(couponQrcodeRecordsService.deleteCouponQrcode(tConponQrcode));
//        return ActionRspUtil.Success(tConponQrcodeInsideService.DeleteTConponQrcode(tConponQrcode));
    }

}
