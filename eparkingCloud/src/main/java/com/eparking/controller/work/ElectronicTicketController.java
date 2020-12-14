package com.eparking.controller.work;

import com.common.annotation.HttpLog;
import com.common.entity.ActionRsp;
import com.common.entity.ControllerRsp;
import com.common.entity.eparkingCloud.TElectronicTicket;
import com.eparking.insideService.TElectronicTicketInsideService;
import com.eparking.service.ElectronicTicketService;
import com.common.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "electronicTicket")
public class ElectronicTicketController {
    private  static final Logger logger= LoggerFactory.getLogger(ElectronicTicketController.class);


    @Autowired
    private ElectronicTicketService electronicTicketService;

    @Autowired
    private TElectronicTicketInsideService tElectronicTicketInsideService;
    /**
     * 查询电子券列表
     */
    @PostMapping(value = "/getTElectronicTicketbyPage")
    @HttpLog(operationType = "0",modularTypeName = "查询电子券列表")
    public ControllerRsp getTElectronicTicketbyPage(TElectronicTicket tElectronicTicket, Integer page, Integer limit){
        tElectronicTicket.setCompanyId(SessionUtil.getCompany().getId());
        return ControllerRspUtil.Success(electronicTicketService.geteTicketListbyPage(tElectronicTicket,page,limit));
//        return tElectronicTicketInsideService.getTElectronicTicketbyPage(tElectronicTicket,page,limit);
    }

    /**
     * 编辑电子券
     */
    @PostMapping(value = "/updateTElectronicTicket")
    @HttpLog(operationType = "1",modularTypeName = "新增电子券")
    public ActionRsp updateTElectronicTicket(TElectronicTicket tElectronicTicket){
        tElectronicTicket.setCompanyId(SessionUtil.getCompany().getId());
        return ActionRspUtil.Success(electronicTicketService.updateEticket(tElectronicTicket));
//        return tElectronicTicketInsideService.UpdateTElectronicTicket(tElectronicTicket);
    }
    /**
     * 删除电子券
     */
    @PostMapping(value = "/deleteTElectronicTicket")
    @HttpLog(operationType = "1",modularTypeName = "删除电子券")
    public ActionRsp deleteTElectronicTicket(TElectronicTicket tElectronicTicket){
        return ActionRspUtil.Success(electronicTicketService.deleteEticket(tElectronicTicket));
//        return tElectronicTicketInsideService.DeleteTElectronicTicket(tElectronicTicket);
    }

}
