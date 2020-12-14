package com.eparkingdb.controller;

import com.common.entity.ActionRsp;
import com.common.entity.ControllerRsp;
import com.common.entity.eparkingCloud.TPropertyBillItems;
import com.common.annotation.HttpLog;
import com.eparkingdb.service.TPropertyBillItemsService;
import com.common.util.ActionRspUtil;
import com.common.util.ControllerRspUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
* @Description: TPropertyBillItemsController类
* @author 谢轩然
* @date 2020/04/29 10:33
*/
@RestController
@RequestMapping("/tPropertyBillItems")
public class TPropertyBillItemsController {

    private  static final Logger logger= LoggerFactory.getLogger(TPropertyBillItemsController.class);

    @Autowired
    private TPropertyBillItemsService tPropertyBillItemsService;

    /**
    * 查询TPropertyBillItems信息
    * @paramtPropertyBillItems
    * @return
    */
    @PostMapping(value = "/getTPropertyBillItems")
    @HttpLog(operationType = "0",modularTypeName = "查询TPropertyBillItems")
    public ActionRsp getTPropertyBillItems(@RequestBody TPropertyBillItems tPropertyBillItems){
    return ActionRspUtil.Success(tPropertyBillItemsService.getTPropertyBillItems(tPropertyBillItems));
    }

    /**
    * 查询TPropertyBillItems信息(分页)
    * @paramtPropertyBillItems
    * @return
    */
    @PostMapping(value = "/getTPropertyBillItemsbyPage")
    @HttpLog(operationType = "0",modularTypeName = "查询TPropertyBillItems(分页)")
    public ControllerRsp getTPropertyBillItemsbyPage(@RequestBody TPropertyBillItems tPropertyBillItems, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit){
    return ControllerRspUtil.Success(tPropertyBillItemsService.getTPropertyBillItemsbyPage(tPropertyBillItems,page,limit));
    }

    /**
    * 更新TPropertyBillItems信息
    * @paramtPropertyBillItems
    * @return
    */
    @PostMapping(value = "/updateTPropertyBillItems")
    @HttpLog(operationType = "1",modularTypeName = "更新TPropertyBillItems信息")
    public ActionRsp UpdateTPropertyBillItems(@RequestBody TPropertyBillItems tPropertyBillItems)
    {
        return ActionRspUtil.Success(tPropertyBillItemsService.UpdateTPropertyBillItems(tPropertyBillItems));
    }

    /**
    * 删除TPropertyBillItems信息
    * @param tPropertyBillItems
    * @return
    */
    @PostMapping(value = "/deleteTPropertyBillItems")
    @HttpLog(operationType = "1",modularTypeName = "删除TPropertyBillItems信息")
    public ActionRsp DeleteTPropertyBillItems(@RequestBody TPropertyBillItems tPropertyBillItems){
    return ActionRspUtil.Success(tPropertyBillItemsService.DeleteTPropertyBillItems(tPropertyBillItems));
    }
}