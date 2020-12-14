package com.eparking.controller.work;

import com.common.annotation.HttpLog;
import com.common.entity.ActionRsp;
import com.common.entity.ControllerRsp;
import com.common.entity.eparkingCloud.TPropertyAutobill;
import com.eparking.insideService.TPropertyAutobillInsideService;
import com.eparking.service.PropertyAutobillService;
import com.common.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/propertyAutobill")
public class PropertyAutobillController {
    private  static final Logger logger= LoggerFactory.getLogger(HouseholdManagementController.class);

    @Autowired
    private PropertyAutobillService propertyAutobillService;
    @Autowired
    private TPropertyAutobillInsideService tPropertyAutobillInsideService;

    @PostMapping(value = "/getPropertyAutobillList")
    @HttpLog(operationType = "0",modularTypeName = "查询住户自动账单列表")
    public ControllerRsp getPropertyAutobillList(TPropertyAutobill tPropertyAutobill, Integer page, Integer limit){
        tPropertyAutobill.setCompanyId(SessionUtil.getCompany().getId());
        tPropertyAutobill.setParkId(SessionUtil.getParkId());
        return tPropertyAutobillInsideService.getTPropertyAutobillbyPage(tPropertyAutobill,page,limit);
//        return ControllerRspUtil.Success(propertyAutobillService.getPropertyAutobillbyPage(tPropertyAutobill,page,limit));
    }

    @PostMapping(value = "/updateHouseholdPropertyAutobillList")
    @HttpLog(operationType = "1",modularTypeName = "编辑住户自动账单信息")
    public ActionRsp updateHouseholdPropertyAutobillList(TPropertyAutobill tPropertyAutobill){
        tPropertyAutobill.setCompanyId(SessionUtil.getCompany().getId());
        tPropertyAutobill.setParkId(SessionUtil.getParkId());
//        tPropertyAutobill.setBillItemId((int)(Math.random()*10000) + 10000);
/*        tPropertyAutobill.setCheckoutDate(DateUtil.getCurDateYMR());
        tPropertyAutobill.setAutobillPushDate(DateUtil.getCurDateYMR());*/
        tPropertyAutobill.setCreateTime(DateUtil.getCurDateTime());
        tPropertyAutobill.setUpdateTime(DateUtil.getCurDateTime());
        return tPropertyAutobillInsideService.UpdateTPropertyAutobill(tPropertyAutobill);
//        return ActionRspUtil.Success(propertyAutobillService.updatePropertyAutobill(tPropertyAutobill));
    }

    @PostMapping(value = "/deleteHouseholdPropertyAutobillList")
    @HttpLog(operationType = "1",modularTypeName = "删除用户自动账单信息")
    public ActionRsp deleteHouseholdPropertyAutobillList(TPropertyAutobill tPropertyAutobill){
        return tPropertyAutobillInsideService.DeleteTPropertyAutobill(tPropertyAutobill);
//        return ActionRspUtil.Success(propertyAutobillService.deletePropertyAutobill(tPropertyAutobill));
    }
}
