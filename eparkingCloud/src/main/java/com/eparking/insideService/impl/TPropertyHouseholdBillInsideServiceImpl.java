package com.eparking.insideService.impl;

import com.common.entity.ActionRsp;
import com.common.entity.ControllerRsp;
import com.common.entity.eparkingCloud.TPropertyHouseholdBill;
import com.common.util.ActionRspUtil;
import com.common.util.ControllerRspUtil;
import com.eparking.insideService.TPropertyHouseholdBillInsideService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
* @Description: TPropertyHouseholdBillInsideService接口熔断器
* @author 谢轩然
* @date 2020/04/29 15:37
*/
@Component
public class TPropertyHouseholdBillInsideServiceImpl implements TPropertyHouseholdBillInsideService {

    private static final Logger logger = LoggerFactory.getLogger(TPropertyHouseholdBillInsideServiceImpl.class);

    /**
    * 查询tPropertyHouseholdBill
    * @param tPropertyHouseholdBill
    * @return
    */
    @Override
    public ActionRsp getTPropertyHouseholdBill(TPropertyHouseholdBill tPropertyHouseholdBill){
        String str = "查询TPropertyHouseholdBill失败！";
        logger.info("查询TPropertyHouseholdBill失败！");
        return ActionRspUtil.Error(1,str);
    }

    /**
    *查询(分页)tPropertyHouseholdBill
    * @param tPropertyHouseholdBill
    * @param page
    * @param limit
    * @return
    */
    @Override
    public ControllerRsp getTPropertyHouseholdBillbyPage(TPropertyHouseholdBill tPropertyHouseholdBill, String householdIdStr, Integer page, Integer limit){
        String str = "查询TPropertyHouseholdBill分页失败！";
        logger.info("查询TPropertyHouseholdBill分页失败！");
        return ControllerRspUtil.Error(1,str);
    }

    /**
    * 更新tPropertyHouseholdBill
    * @param tPropertyHouseholdBill
    * @return
    */
    @Override
    public ActionRsp UpdateTPropertyHouseholdBill(TPropertyHouseholdBill tPropertyHouseholdBill){
        String str = "更新TPropertyHouseholdBill失败！";
        logger.info("更新TPropertyHouseholdBill失败！");
        return ActionRspUtil.Error(1,str);
    }

    /**
    * 删除tPropertyHouseholdBill
    * @param tPropertyHouseholdBill
    * @return
    */
    @Override
    public ActionRsp DeleteTPropertyHouseholdBill(TPropertyHouseholdBill tPropertyHouseholdBill){
        String str = "删除TPropertyHouseholdBill失败！";
        logger.info("删除TPropertyHouseholdBill失败！");
        return ActionRspUtil.Error(1,str);
    }
}