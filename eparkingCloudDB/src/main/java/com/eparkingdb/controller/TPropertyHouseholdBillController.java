package com.eparkingdb.controller;

import com.common.entity.eparkingCloud.TPropertyHouseholdBill;
import com.common.annotation.HttpLog;
import com.eparkingdb.service.TPropertyHouseholdBillService;
import com.common.entity.ActionRsp;
import com.common.util.ActionRspUtil;
import com.common.entity.ControllerRsp;
import com.common.util.ControllerRspUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
* @Description: TPropertyHouseholdBillController类
* @author 谢轩然
* @date 2020/04/29 15:37
*/
@RestController
@RequestMapping("/TPropertyHouseholdBill")
public class TPropertyHouseholdBillController {

    private  static final Logger logger= LoggerFactory.getLogger(TPropertyHouseholdBillController.class);

    @Autowired
    private TPropertyHouseholdBillService TPropertyHouseholdBillService;

    /**
    * 查询TPropertyHouseholdBill信息
    * @paramTPropertyHouseholdBill
    * @return
    */
    @PostMapping(value = "/getTPropertyHouseholdBill")
    @HttpLog(operationType = "0",modularTypeName = "查询TPropertyHouseholdBill")
    public ActionRsp getTPropertyHouseholdBill(@RequestBody TPropertyHouseholdBill TPropertyHouseholdBill,@RequestParam("householdIdStr") String householdIdStr){
        List<Integer> list = new ArrayList<>();
        if(householdIdStr!=null){
            List<String> listStr = Arrays.asList(householdIdStr.split(","));
            for(int i=0;i<listStr.size();i++){
                list.add(Integer.valueOf(listStr.get(i)));
            }
        }
        return ActionRspUtil.Success(TPropertyHouseholdBillService.getTPropertyHouseholdBill(TPropertyHouseholdBill,list));
    }

    /**
    * 查询TPropertyHouseholdBill信息(分页)
    * @paramTPropertyHouseholdBill
    * @return
    */
    @PostMapping(value = "/getTPropertyHouseholdBillbyPage")
    @HttpLog(operationType = "0",modularTypeName = "查询TPropertyHouseholdBill(分页)")
    public ControllerRsp getTPropertyHouseholdBillbyPage(@RequestBody TPropertyHouseholdBill TPropertyHouseholdBill,@RequestParam("householdIdStr") String householdIdStr,@RequestParam("page") Integer page,@RequestParam("limit") Integer limit){
        List<Integer> list = new ArrayList<>();
        if(householdIdStr!=null){
            List<String> listStr = Arrays.asList(householdIdStr.split(","));
            for(int i=0;i<listStr.size();i++){
                list.add(Integer.valueOf(listStr.get(i)));
            }
        }
    return ControllerRspUtil.Success(TPropertyHouseholdBillService.getTPropertyHouseholdBillbyPage(TPropertyHouseholdBill,list,page,limit));
    }

    /**
    * 更新TPropertyHouseholdBill信息
    * @paramTPropertyHouseholdBill
    * @return
    */
    @PostMapping(value = "/updateTPropertyHouseholdBill")
    @HttpLog(operationType = "1",modularTypeName = "更新TPropertyHouseholdBill信息")
    public ActionRsp UpdateTPropertyHouseholdBill(@RequestBody TPropertyHouseholdBill TPropertyHouseholdBill)
    {
        return ActionRspUtil.Success(TPropertyHouseholdBillService.UpdateTPropertyHouseholdBill(TPropertyHouseholdBill));
    }

    /**
    * 删除TPropertyHouseholdBill信息
    * @param TPropertyHouseholdBill
    * @return
    */
    @PostMapping(value = "/deleteTPropertyHouseholdBill")
    @HttpLog(operationType = "1",modularTypeName = "删除TPropertyHouseholdBill信息")
    public ActionRsp DeleteTPropertyHouseholdBill(@RequestBody TPropertyHouseholdBill TPropertyHouseholdBill){
    return ActionRspUtil.Success(TPropertyHouseholdBillService.DeleteTPropertyHouseholdBill(TPropertyHouseholdBill));
    }
}