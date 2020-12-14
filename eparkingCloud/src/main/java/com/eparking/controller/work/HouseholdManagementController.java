package com.eparking.controller.work;

import com.common.annotation.HttpLog;
import com.common.entity.ActionRsp;
import com.common.entity.ControllerRsp;
import com.common.entity.eparkingCloud.TPropertyHouseholdInfo;
import com.eparking.insideService.TPropertyHouseholdInfoInsideService;
import com.eparking.service.PropertyHouseholdInfoService;
import com.common.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/householdManagement")
public class HouseholdManagementController {
    private  static final Logger logger= LoggerFactory.getLogger(HouseholdManagementController.class);

    @Autowired
    private PropertyHouseholdInfoService propertyHouseholdInfoService;
    @Autowired
    private TPropertyHouseholdInfoInsideService tPropertyHouseholdInfoInsideService;

    @PostMapping(value = "/getHouseholdList")
    @HttpLog(operationType = "0",modularTypeName = "查询未审核住户列表")
    public ControllerRsp getHouseholdList(TPropertyHouseholdInfo tPropertyHouseholdInfo, Integer page, Integer limit){
//        tPropertyHouseholdInfo1.setCompanyId(tPropertyHouseholdInfo.getCompanyId());
//        tPropertyHouseholdInfo1.setCompanyId(Integer.valueOf(request.getSession().getAttribute(Common.Company).toString()));
        tPropertyHouseholdInfo.setCompanyId(SessionUtil.getCompany().getId());
        tPropertyHouseholdInfo.setParkId(SessionUtil.getParkId());
//        return ControllerRspUtil.Success(propertyHouseholdInfoService.getPropertyHouseholeparkingdbyPage(tPropertyHouseholdInfo,page,limit));
        return tPropertyHouseholdInfoInsideService.getTPropertyHouseholdInfobyPage(tPropertyHouseholdInfo,page,limit);
    }

    @PostMapping(value = "/updateHouseholdList")
    @HttpLog(operationType = "1",modularTypeName = "编辑住户信息")
    public ActionRsp updateHouseholdList(TPropertyHouseholdInfo tPropertyHouseholdInfo){
//        tPropertyHouseholdInfo.setParkId(Integer.valueOf(request.getSession().getAttribute(Common.ParkId).toString()));
//        tPropertyHouseholdInfo.setIsAuditing(1);
        tPropertyHouseholdInfo.setMemberId((int)(Math.random()*10000) + 10000);
        tPropertyHouseholdInfo.setCompanyId(SessionUtil.getCompany().getId());
        tPropertyHouseholdInfo.setParkId(SessionUtil.getParkId());
        tPropertyHouseholdInfo.setCreateTime(DateUtil.getCurDateTime());
        tPropertyHouseholdInfo.setUpdateTime(DateUtil.getCurDateTime());
        tPropertyHouseholdInfo.setAuditingTime(DateUtil.getCurDateTime());
        return tPropertyHouseholdInfoInsideService.UpdateTPropertyHouseholdInfo(tPropertyHouseholdInfo);
//        return ActionRspUtil.Success(propertyHouseholdInfoService.updatePropertyHouseholdInfo(tPropertyHouseholdInfo));
    }

    @PostMapping(value = "/deleteInfoList")
    @HttpLog(operationType = "1",modularTypeName = "删除用户信息")
    public ActionRsp deleteInfoList(TPropertyHouseholdInfo tPropertyHouseholdInfo){
        return tPropertyHouseholdInfoInsideService.DeleteTPropertyHouseholdInfo(tPropertyHouseholdInfo);
//        return ActionRspUtil.Success(propertyHouseholdInfoService.deletePropertyHouseholdInfo(tPropertyHouseholdInfo));
    }
}
