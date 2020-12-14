package com.eparking.controller.work;

import com.common.annotation.HttpLog;
import com.common.entity.ActionRsp;
import com.common.entity.ControllerRsp;
import com.common.entity.eparkingCloud.TBusine;
import com.common.entity.eparkingCloud.TConponQrcode;
import com.common.entity.eparkingCloud.TElectronicTicket;
import com.eparking.insideService.TBusineInsideService;
import com.eparking.insideService.TConponQrcodeInsideService;
import com.eparking.insideService.TElectronicTicketInsideService;
import com.eparking.service.BusineService;
import com.eparking.service.CouponQrcodeRecordsService;
import com.eparking.service.ElectronicTicketService;
import com.common.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


/**
 * created by xiexuanran 2019-04-29
 */
@RestController
@RequestMapping(value = "couponQrcode")
public class CouponQrcodeController {

    @Autowired
    private CouponQrcodeRecordsService couponQrcodeRecordsService;
    @Autowired
    private BusineService busineService;
    @Autowired
    private ElectronicTicketService electronicTicketService;
    @Autowired
    private TConponQrcodeInsideService tConponQrcodeInsideService;
    @Autowired
    private TBusineInsideService tBusineInsideService;
    @Autowired
    private TElectronicTicketInsideService tElectronicTicketInsideService;

    /**
     * 生成二维码优惠记录
     */
    @PostMapping(value = "/createCouponQrcode")
    @HttpLog(operationType = "1",modularTypeName = "生成优惠二维码列表")
    public ActionRsp createCouponQrcode(TConponQrcode tConponQrcode){
//        tConponQrcodeInsideService.existTConponQrcodeName(tConponQrcode);
        couponQrcodeRecordsService.existQrcodeName(tConponQrcode);
        String account =  SessionUtil.getUser().getUserAccout();
        TBusine tBusine = busineService.selectByAccount(account);
//        TBusine tBusine = JsonUtil.jsonToBean(JsonUtil.beanToJson(tBusineInsideService.selectByAccount(account).getResult()),TBusine.class);
        tConponQrcode.setCompanyId(SessionUtil.getCompany().getId());
        tConponQrcode.setParkId(SessionUtil.getParkId());
        tConponQrcode.setCreateTime(DateUtil.getCurDateTime());
        tConponQrcode.setBusineId(tBusine.getId());
        if (tConponQrcode.getTicketId()==null){
            couponQrcodeRecordsService.lessDiscountAmount(tConponQrcode,tBusine.getId());
//            tConponQrcodeInsideService.lessDiscountAmount(tConponQrcode,tBusine.getId());
            tConponQrcode.setTicketId(0);
        }else {
            TElectronicTicket tElectronicTicket = electronicTicketService.getElectronicTicketByPrimaryKey(tConponQrcode.getTicketId());
//            TElectronicTicket tElectronicTicket =tElectronicTicketInsideService.getElectronicTicketByPrimaryKey(tConponQrcode.getTicketId());
            tConponQrcode.setCouponPay(tElectronicTicket.getTicketValue());
        }
        tConponQrcode.setQrcodeUrl("http://yun.eparking.top/wechat/view/scope?parkid="+SessionUtil.getParkId()+"&companyId="+SessionUtil.getCompany().getId()+"&busineId="+tBusine.getId()+"&couponType="+tBusine.getDiscountType()+"&couponPay="+tConponQrcode.getCouponPay()+"&ticketId="+tConponQrcode.getTicketId()+"&qrcodeId=");
        //        tConponQrcodeInsideService.UpdateTConponQrcode(tConponQrcode);
        couponQrcodeRecordsService.updateCouponQrcode(tConponQrcode);
//        TConponQrcode tConponQrcodeRes =(TConponQrcode) tConponQrcodeInsideService.getTConponQrcode(tConponQrcode).getResult();
        TConponQrcode tConponQrcodeRes = couponQrcodeRecordsService.getTConponQrcode(tConponQrcode).get(0);
        Integer qrcodeId =tConponQrcodeRes.getId();
        tConponQrcode.setId(qrcodeId);
        tConponQrcode.setQrcodeUrl("http://yun.eparking.top/wechat/view/scope?parkid="+SessionUtil.getParkId()+"&companyId="+SessionUtil.getCompany().getId()+"&busineId="+tBusine.getId()+"&couponType="+tBusine.getDiscountType()+"&couponPay="+tConponQrcode.getCouponPay()+"&ticketId="+tConponQrcode.getTicketId()+"&qrcodeId="+qrcodeId);
//        tConponQrcodeInsideService.UpdateTConponQrcode(tConponQrcode);
        couponQrcodeRecordsService.updateCouponQrcode(tConponQrcode);
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
//        return tConponQrcodeInsideService.getTConponQrcodebyPage(tConponQrcode,page,limit);
        return ControllerRspUtil.Success(couponQrcodeRecordsService.getCouponQrcodeListbyPage(tConponQrcode, page, limit));
    }
    /**
     * 编辑二维码优惠记录
     */
    @PostMapping(value = "/updateCouponQrcode")
    @HttpLog(operationType = "1",modularTypeName = "编辑优惠二维码")
    public ActionRsp updateCouponQrcode(TConponQrcode tConponQrcode, HttpServletRequest request){
        String account =  SessionUtil.getUser().getUserAccout();
//        TBusine tBusine =(TBusine) tBusineInsideService.selectByAccount(account).getResult();
        TBusine tBusine = busineService.selectByAccount(account);
        tConponQrcode.setCompanyId(SessionUtil.getCompany().getId());
        tConponQrcode.setParkId(SessionUtil.getParkId());
        tConponQrcode.setQrcodeUrl("http://yun.eparking.top/wechat/view/scope?parkid="+SessionUtil.getParkId()+"&companyId="+SessionUtil.getCompany().getId()+"&busineId="+tBusine.getId()+"&couponType="+tBusine.getDiscountType()+"&couponPay="+tConponQrcode.getCouponPay()+"&ticketId=0"+"&qrcodeId="+tConponQrcode.getId());
//        return ActionRspUtil.Success(tConponQrcodeInsideService.UpdateTConponQrcode(tConponQrcode));
        return ActionRspUtil.Success(couponQrcodeRecordsService.updateCouponQrcode(tConponQrcode));
    }
    /**
     * 删除二维码优惠记录
     */
    @PostMapping(value = "/deleteCouponQrcode")
    @HttpLog(operationType = "1",modularTypeName = "删除优惠二维码")
    public ActionRsp deleteCouponQrcode(TConponQrcode tConponQrcode){
//        return ActionRspUtil.Success(tConponQrcodeInsideService.DeleteTConponQrcode(tConponQrcode));
        return ActionRspUtil.Success(couponQrcodeRecordsService.deleteCouponQrcode(tConponQrcode));
    }

}
