package com.eparking.controller.work;


import com.common.annotation.HttpLog;
import com.common.entity.ActionRsp;
import com.common.entity.ControllerRsp;
import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.*;
import com.eparking.insideService.TBusineInsideService;
import com.common.util.*;
import com.eparking.service.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "wechat")
public class WechatController {

    @Autowired
    private PresentCarService presentCarService;

    @Autowired
    private BusineService busineService;

    @Autowired
    private ElectronicTbusineTicketService electronicTbusineTicketService;

    @Autowired
    private MerchantCouponRecordService merchantCouponRecordService;

    @Autowired
    private BusinePayService businePayService;

    @Autowired
    private ElectronicTicketService electronicTicketService;

    @Autowired
    private TBusineInsideService tBusineInsideService;

    @Autowired
    private ElectronicCouponRecordsService electronicCouponRecordsService;


    /**
     * @param tBusinesCoupon
     * @param account        账号
     * @param couponTimeEnd
     * @param page
     * @param limit
     * @return
     */
    @PostMapping(value = "/getTBusinesCouponbyPage")
    @HttpLog(operationType = "0", modularTypeName = "查询商户消费记录")
    public ControllerRsp getTBusinesCouponbyPage(TBusinesCoupon tBusinesCoupon, String account,
                                                 String couponTimeBegin, String couponTimeEnd, Integer page, Integer limit) {
        //通过登录信息获得车场ID
        TBusine busine = busineService.selectByAccount(account);     //通过登录信息中account获得商户ID
        tBusinesCoupon.setBusineId(busine.getId());
        tBusinesCoupon.setParkId(busine.getParkId());
        if (couponTimeBegin == null) {
            couponTimeBegin = "";
        }
        if (couponTimeEnd == null) {
            couponTimeEnd = "";
        }
        return ControllerRspUtil.Success(merchantCouponRecordService.FindMerchantCouponbyPage(tBusinesCoupon, couponTimeBegin, couponTimeEnd, page, limit));
    }


    @PostMapping(value = "/getTBusinePaybyPage")
    @HttpLog(operationType = "0", modularTypeName = "查询商户充值记录")
    public ControllerRsp getTMerchantPay(TBusinePay tBusinePay, String account, String payTimeBegin, String payTimeEnd, Integer page, Integer limit) {
        TBusine busine = busineService.selectByAccount(account);

        tBusinePay.setParkId(busine.getParkId());
        if (payTimeBegin == null) {
            payTimeBegin = "";
        }
        if (payTimeEnd == null) {
            payTimeEnd = "";
        }
        return ControllerRspUtil.Success(businePayService.getTBusinePaybyPage(tBusinePay, busine.getId(), page, limit, payTimeBegin, payTimeEnd));
    }


    @PostMapping(value = "/getBusineTicket")
    @HttpLog(operationType = "0", modularTypeName = "查询商户拥有的可使用的电子劵")
    public ActionRsp getBusineTicket(TBusineTicket tBusineTicket, String account) {
        tBusineTicket.setBusineId(busineService.selectByAccount(account).getId());
        List<Integer> ticketIdList = electronicTbusineTicketService.getIdsByUsable(tBusineTicket);
        String ticketIdStr = StringUtils.join(ticketIdList.toArray(), ",");
        List<TElectronicTicket> tElectronicTickets = electronicTicketService.FindElectronicTicketByIds(ticketIdStr);
        return ActionRspUtil.Success(tElectronicTickets);
    }


    @PostMapping(value = "/getBusine")
    @HttpLog(operationType = "0", modularTypeName = "根据账号获取商户信息")
    public ActionRsp getBusine(String account) {
        TBusine tBusine = busineService.selectByAccount(account);
        return ActionRspUtil.Success(tBusine);
    }


    /**
     * 电子优惠券记录查询
     *
     * @return
     */
    @PostMapping(value = "/getTElectronicTicketbyPage")
    @HttpLog(operationType = "0", modularTypeName = "查询电子优惠券记录")
    public ControllerRsp getTElectronicTicketbyPage(String carPlate, String account,
                                                    String inTimeBegin, String inTimeEnd,
                                                    String outTimeBegin, String outTimeEnd, Integer page, Integer limit) {

        TBusine busine = busineService.selectByAccount(account);     //通过登录信息中account获得商户ID
        TTicketCoupon tTicketCoupon = new TTicketCoupon();
        tTicketCoupon.setCarplate(carPlate);
        tTicketCoupon.setParkId(busine.getParkId());
//        TBusine tBusine = new TBusine();
//        tBusine.setParkId(busine.getParkId());
//        tBusine.setAccount(accout);
//        tTicketCoupon.setBusineId(tBusineInsideService.getTBusine(tBusine).get(0).getId());
        tTicketCoupon.setBusineId(busine.getId());
        if (inTimeBegin == null) {
            inTimeBegin = "";
        }
        if (inTimeEnd == null) {
            inTimeEnd = "";
        }
        if (outTimeBegin == null) {
            outTimeBegin = "";
        }
        if (outTimeEnd == null) {
            outTimeEnd = "";
        }
        PageBean<TTicketCoupon> pb = electronicCouponRecordsService.getTElectronicTicketbyPage(tTicketCoupon, inTimeBegin, inTimeEnd, outTimeBegin, outTimeEnd, page, limit);

        return ControllerRspUtil.Success(pb);
    }


}
