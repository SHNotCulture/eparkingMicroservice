package com.eparking.controller.work;

import com.common.annotation.HttpLog;
import com.common.entity.ActionRsp;
import com.common.entity.ControllerRsp;
import com.common.entity.eparkingCloud.BusineCouponUpload;
import com.common.entity.eparkingCloud.TBusine;
import com.common.entity.eparkingCloud.TBusineTicket;
import com.common.entity.eparkingCloud.TCompanyUser;
import com.eparking.service.*;
import com.common.util.*;
import com.common.util.ActionRspUtil;
import com.common.util.ControllerRspUtil;
import com.common.util.SessionUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "Merchant")
public class MerchantController {
    @Autowired
    private MerchantService merchantService;
    @Autowired
    private PresentCarService presentCarService;
    @Autowired
    private BusineService busineService;
    @Autowired
    private ElectronicTbusineTicketService electronicTbusineTicketService;
    @Autowired
    private ElectronicTicketService electronicTicketService;

    @Autowired
    private BusinesCouponService businesCouponService;

    @PostMapping(value = "/indexMonth")
    @HttpLog(operationType = "0",modularTypeName = "按日查询支出")
    public ActionRsp indexMonth(){
        return ActionRspUtil.Success(businesCouponService.returnCouponPayDay());
    }

    @PostMapping(value = "/indexYear")
    @HttpLog(operationType = "0",modularTypeName = "按月查询支出")
    public ActionRsp indexYear(){
        return ActionRspUtil.Success(businesCouponService.returnCouponPayMonth()); }

    @PostMapping(value = "/getTBusine")
    @HttpLog(operationType = "0",modularTypeName = "查询优惠商户")
    public ActionRsp getTBusine(){
         TCompanyUser tCompanyUser = SessionUtil.getUser();
        TBusine tBusine = new TBusine();
        TBusine tBusineReturn = new TBusine();
        tBusine.setAccount(tCompanyUser.getUserAccout());
        List<TBusine> tBusineList = busineService.getBusine(tBusine);
        if(tBusineList.size()>0){
            tBusineReturn = tBusineList.get(0);
        }
//        Integer CouponType = merchantService.getMerchantCouponType(tBusine);

        ActionRsp actionRsp= ActionRspUtil.Success(tBusineReturn);

        return actionRsp;
    }

    @PostMapping(value = "/getCarIntime")
    @HttpLog(operationType = "0",modularTypeName = "查询在场车辆时间")
    public ActionRsp getIntime(String carPlate){
        String inTime=presentCarService.selectPresentCarByCarplate(SessionUtil.getParkId(),carPlate);
        return  ActionRspUtil.Success(inTime);
    }

    @PostMapping(value = "/getCarPlateFuzzy")
    @HttpLog(operationType = "0",modularTypeName = "模糊查询车牌号")
    public ActionRsp getCarPlateFuzzy(Integer parkId, String carPlate){
        parkId = SessionUtil.getParkId();
        return ActionRspUtil.Success(presentCarService.selectPresentCarLikeCarplate(parkId,carPlate));
    }

    @PostMapping(value = "/transferBusineCoupon")
    @HttpLog(operationType = "1",modularTypeName = "商户下发优惠")
    public ActionRsp transferBusineCoupon(BusineCouponUpload busineCouponUpload){
        busineCouponUpload.setParkId(SessionUtil.getParkId());
        busineCouponUpload.setBusineId(busineService.selectByAccount(SessionUtil.getUser().getUserAccout()).getId());
        if (busineCouponUpload.getTicketId()!=null){
        busineCouponUpload.setBusineTicketId(electronicTbusineTicketService.getTBusineTicketExpireSoon(busineCouponUpload.getBusineId(),busineCouponUpload.getTicketId()));}
        return ActionRspUtil.Success(merchantService.transferBusineCoupon(busineCouponUpload));
    }


    @PostMapping(value = "/getBusineTicket")
    @HttpLog(operationType = "0",modularTypeName = "查询商户拥有的可使用的电子劵")
    public ControllerRsp getBusineTicket(TBusineTicket tBusineTicket, HttpServletRequest request, Integer page, Integer limit){
        tBusineTicket.setBusineId(busineService.selectByAccount(SessionUtil.getUser().getUserAccout()).getId());
        List<Integer> ticketIdList = electronicTbusineTicketService.getIdsByUsable(tBusineTicket);
        String ticketIdStr = StringUtils.join(ticketIdList.toArray(),",");
//        System.out.println("ticketIdStr: "+ticketIdStr);
        return ControllerRspUtil.Success(electronicTicketService.geteTicketListbyPageAndIds(ticketIdStr, page,limit));
    }

    @PostMapping(value = "/selectSumPay")
    @HttpLog(operationType = "0",modularTypeName = "查询商户累计消费信息")
    public ActionRsp selectSumPay(){
        Map map = new HashMap();
        map.put("couponNeedPay",businesCouponService.couponNeedPay());
        map.put("couponSumPay",businesCouponService.couponSumPay());
        map.put("couponBalance",businesCouponService.couponBalance());
        return ActionRspUtil.Success(map);
    }
}
