package com.eparking.controller.work;

import com.common.annotation.HttpLog;
import com.common.entity.ActionRsp;
import com.common.entity.ControllerRsp;
import com.common.entity.eparkingCloud.*;
import com.eparking.insideService.TBusineInsideService;
import com.eparking.insideService.TBusineTicketInsideService;
import com.eparking.insideService.TCompanyUserInsideService;
import com.eparking.insideService.TElectronicTicketInsideService;
import com.eparking.service.BusineService;
import com.eparking.service.CarParkService;
import com.eparking.service.ElectronicTbusineTicketService;
import com.common.util.ActionRspUtil;
import com.common.util.ControllerRspUtil;
import com.common.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author jin
 * @Data 2019/4/28 15:36
 **/
@RestController
@RequestMapping(value = "busineTicket")
public class BusineTicketController {

    @Autowired
    private ElectronicTbusineTicketService electronicTbusineTicketService;
    @Autowired
    private TBusineInsideService tBusineInsideService;
    @Autowired
    private TBusineTicketInsideService tBusineTicketInsideService;
    @Autowired
    private TElectronicTicketInsideService tElectronicTicketInsideService;
    @Autowired
    private BusineService busineService;
    @Autowired
    private TCompanyUserInsideService tCompanyUserInsideService;
    @Autowired
    private CarParkService carParkService;
    /**
     * 查询商户电子券列表
     */
    @PostMapping(value = "/getBusineTicketListbyPage")
    @HttpLog(operationType = "0",modularTypeName = "查询电子券列表")
    public ControllerRsp getBusineTicketListbyPage(TBusineTicket tBusineTicket, Integer page, Integer limit){
        TCompanyPark companyPark=new TCompanyPark();
        companyPark.setCompanyId(SessionUtil.getUser().getCompanyId());
        Integer parkId = carParkService.getCarPark(companyPark).get(0).getId();
        TCompanyUser tCompanyUser = new TCompanyUser();
        tCompanyUser.setUserAccout(SessionUtil.getUser().getUserAccout());
        List<TCompanyUser> tCompanyUserList = tCompanyUserInsideService.getTCompanyUser(tCompanyUser);
        TBusine tBusine = new TBusine();
        tBusine.setParkId(parkId);
        //普通商户角色只显示属于自家的记录
        if(tCompanyUserList.get(0).getRoleId()==212){
            tBusine.setAccount(SessionUtil.getUser().getUserAccout());
            tBusineTicket.setBusineId(tBusineInsideService.getTBusine(tBusine).get(0).getId());
        }else{

            tBusineTicket.setCompanyId(tCompanyUserList.get(0).getCompanyId());
        }
//        return tBusineTicketInsideService.getTBusineTicketbyPage(tBusineTicket,page,limit,parkId);
        return ControllerRspUtil.Success(electronicTbusineTicketService.getTBusineTicketByPage(tBusineTicket,parkId, page, limit));
    }

    /**
     * 回购电子券
     */
    @PostMapping(value = "/buyBackBusineTicket")
    @HttpLog(operationType = "1",modularTypeName = "回购电子券")
    public ActionRsp buyBackBusineTicket(TBusineTicket tBusineTicket, Integer buyBackNum){
        TBusinePay tBusinePay = new TBusinePay();
//        TElectronicTicket tbt = (TElectronicTicket)tElectronicTicketInsideService.getElectronicTicketByPrimaryKey(tBusineTicket.getTicketId()).getResult();
        TElectronicTicket tbt = tElectronicTicketInsideService.getElectronicTicketByPrimaryKey(tBusineTicket.getTicketId());
        Double buyBackSum = tbt.getTicketPay() * buyBackNum;
        tBusinePay.setNeedPay(buyBackSum);
        tBusinePay.setActualPay(buyBackSum);
        tBusinePay.setBusineId(tBusineTicket.getBusineId());
        tBusinePay.setBusineName(tBusineTicket.getBusineName());
        TCompanyUser user= SessionUtil.getUser();
        tBusinePay.setCompanyId(user.getCompanyId());
        tBusinePay.setParkId(SessionUtil.getParkId());
//        String aaa= (String) tBusineInsideService.BusineRecharge(tBusinePay,"2").getResult();
        String aaa = busineService.BusineRecharge(tBusinePay,"2");
        return ActionRspUtil.Success(electronicTbusineTicketService.buyBackBusineTicket(tBusineTicket));
//        return ActionRspUtil.Success(tBusineTicketInsideService.buyBackBusineTicket(tBusineTicket));
    }

    /**
     * 购买电子券
     */
    @PostMapping(value = "/buyBusineTicket")
    @HttpLog(operationType = "1",modularTypeName = "购买电子券")
    public ActionRsp buyBusineTicket(TBusineTicket tBusineTicket, Double ticketPay, Integer ticketNumNew){
//        System.out.println("ticketNumNew: "+ticketNumNew.toString());
        return ActionRspUtil.Success(electronicTbusineTicketService.eleBuy(tBusineTicket,ticketNumNew));
//        return ActionRspUtil.Success(tBusineTicketInsideService.eleBuy(tBusineTicket,ticketNumNew));
    }

    /**
     * 修改批次权限
     */
    @PostMapping(value = "/changeIsPay")
    @HttpLog(operationType = "1",modularTypeName = "修改批次权限")
    public ActionRsp changeIsPay(TBusineTicket tBusineTicket){
        return ActionRspUtil.Success(electronicTbusineTicketService.changeIsPay(tBusineTicket));
//        return ActionRspUtil.Success(tBusineTicketInsideService.changeIsPay(tBusineTicket));
    }
}
