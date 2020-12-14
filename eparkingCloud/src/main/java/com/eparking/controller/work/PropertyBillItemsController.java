package com.eparking.controller.work;

import com.common.annotation.HttpLog;
import com.common.entity.ActionRsp;
import com.common.entity.ControllerRsp;
import com.common.entity.eparkingCloud.TPropertyBillItems;
import com.eparking.insideService.TPropertyBillItemsInsideService;
import com.eparking.service.PropertyBillItemsService;
import com.common.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/propertybillitems")
public class PropertyBillItemsController {

    private  static final Logger logger= LoggerFactory.getLogger(PropertyBillItemsController.class);

    @Autowired
    private PropertyBillItemsService propertyBillItemsService;
    @Autowired
    private TPropertyBillItemsInsideService tPropertyBillItemsInsideService;

    @PostMapping(value = "/getPropertybillitemsList")
    @HttpLog(operationType = "0",modularTypeName = "查询缴费项目列表")
    public ActionRsp getPropertybillitemsList(TPropertyBillItems tPropertyBillItems){
        tPropertyBillItems.setCompanyId(SessionUtil.getCompany().getId());
        tPropertyBillItems.setParkId(SessionUtil.getParkId());
//        return ControllerRspUtil.Success(propertyBillItemsService.getPropertyBillItems(tPropertyBillItems));
        return tPropertyBillItemsInsideService.getTPropertyBillItems(tPropertyBillItems);
    }

    @PostMapping(value = "/getPropertybillitemsListbyPage")
    @HttpLog(operationType = "0",modularTypeName = "查询缴费项目列表(分页)")
    public ControllerRsp getPropertybillitemsListbyPage(TPropertyBillItems tPropertyBillItems, Integer page, Integer limit){
        tPropertyBillItems.setCompanyId(SessionUtil.getCompany().getId());
        tPropertyBillItems.setParkId(SessionUtil.getParkId());
//        return ControllerRspUtil.Success(propertyBillItemsService.getPropertyBillItemsbyPage(tPropertyBillItems,page,limit));
        return tPropertyBillItemsInsideService.getTPropertyBillItemsbyPage(tPropertyBillItems,page,limit);
    }

    @PostMapping(value = "/updatePropertybillitemsList")
    @HttpLog(operationType = "1",modularTypeName = "编辑缴费项目")
    public ActionRsp updatePropertybillitemsList(TPropertyBillItems tPropertyBillItems){
        tPropertyBillItems.setCompanyId(SessionUtil.getCompany().getId());
        tPropertyBillItems.setParkId(SessionUtil.getParkId());
        tPropertyBillItems.setCreateTime(DateUtil.getCurDateTime());
        tPropertyBillItems.setUpdateTime(DateUtil.getCurDateTime());
//        return ActionRspUtil.Success(propertyBillItemsService.updatePropertyBillItems(tPropertyBillItems));
        return tPropertyBillItemsInsideService.UpdateTPropertyBillItems(tPropertyBillItems);
    }

    @PostMapping(value = "/deletePropertybillitemsList")
    @HttpLog(operationType = "1",modularTypeName = "删除缴费项目")
    public ActionRsp deletePropertybillitemsList(TPropertyBillItems tPropertyBillItems){
//        return ActionRspUtil.Success(propertyBillItemsService.deletePropertyBillItems(tPropertyBillItems));
        return tPropertyBillItemsInsideService.DeleteTPropertyBillItems(tPropertyBillItems);
    }
}
