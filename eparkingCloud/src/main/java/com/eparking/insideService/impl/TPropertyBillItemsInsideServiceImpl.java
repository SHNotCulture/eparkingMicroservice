package com.eparking.insideService.impl;

import com.common.entity.ActionRsp;
import com.common.entity.ControllerRsp;
import com.common.entity.eparkingCloud.TPropertyBillItems;
import com.eparking.insideService.TPropertyBillItemsInsideService;
import com.common.util.ActionRspUtil;
import com.common.util.ControllerRspUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
* @Description: TPropertyBillItemsInsideService接口熔断器
* @author 谢轩然
* @date 2020/04/29 10:33
*/
@Component
public class TPropertyBillItemsInsideServiceImpl  implements TPropertyBillItemsInsideService {

    private static final Logger logger = LoggerFactory.getLogger(TPropertyBillItemsInsideServiceImpl.class);

    /**
    * 查询tPropertyBillItems
    * @param tPropertyBillItems
    * @return
    */
    @Override
    public ActionRsp getTPropertyBillItems(TPropertyBillItems tPropertyBillItems){
        String str = "查询TPropertyBillItems失败！";
        logger.info("查询TPropertyBillItems失败！");
        return ActionRspUtil.Error(1,str);
    }

    /**
    *查询(分页)tPropertyBillItems
    * @param tPropertyBillItems
    * @param page
    * @param limit
    * @return
    */
    @Override
    public ControllerRsp getTPropertyBillItemsbyPage(TPropertyBillItems tPropertyBillItems, Integer page, Integer limit){
        String str = "查询TPropertyBillItems分页失败！";
        logger.info("查询TPropertyBillItems分页失败！");
        return ControllerRspUtil.Error(1,str);
    }

    /**
    * 更新tPropertyBillItems
    * @param tPropertyBillItems
    * @return
    */
    @Override
    public ActionRsp UpdateTPropertyBillItems(TPropertyBillItems tPropertyBillItems){
        String str = "更新TPropertyBillItems失败！";
        logger.info("更新TPropertyBillItems失败！");
        return ActionRspUtil.Error(1,str);
    }

    /**
    * 删除tPropertyBillItems
    * @param tPropertyBillItems
    * @return
    */
    @Override
    public ActionRsp DeleteTPropertyBillItems(TPropertyBillItems tPropertyBillItems){
        String str = "删除TPropertyBillItems失败！";
        logger.info("删除TPropertyBillItems失败！");
        return ActionRspUtil.Error(1,str);
    }
}