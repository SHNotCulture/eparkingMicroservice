package com.eparking.insideService.impl;

import com.common.entity.ActionRsp;
import com.common.entity.ControllerRsp;
import com.common.entity.eparkingCloud.TPropertyHouseholdInfo;
import com.eparking.insideService.TPropertyHouseholdInfoInsideService;
import com.common.util.ActionRspUtil;
import com.common.util.ControllerRspUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
* @Description: TPropertyHouseholdInfoInsideService接口熔断器
* @author 谢轩然
* @date 2020/04/28 15:20
*/
@Component
public class TPropertyHouseholdInfoInsideServiceImpl  implements TPropertyHouseholdInfoInsideService {

    private static final Logger logger = LoggerFactory.getLogger(TPropertyHouseholdInfoInsideServiceImpl.class);

    /**
    * 查询tPropertyHouseholdInfo
    * @param tPropertyHouseholdInfo
    * @return
    */
    @Override
    public ActionRsp getTPropertyHouseholdInfo(TPropertyHouseholdInfo tPropertyHouseholdInfo){
        String str = "查询TPropertyHouseholdInfo失败！";
        logger.info("查询TPropertyHouseholdInfo失败！");
        return ActionRspUtil.Error(1,str);
    }

    /**
    *查询(分页)tPropertyHouseholdInfo
    * @param tPropertyHouseholdInfo
    * @param page
    * @param limit
    * @return
    */
    @Override
    public ControllerRsp getTPropertyHouseholdInfobyPage(TPropertyHouseholdInfo tPropertyHouseholdInfo, Integer page, Integer limit){
        String str = "查询TPropertyHouseholdInfo分页失败！";
        logger.info("查询TPropertyHouseholdInfo分页失败！");
        return ControllerRspUtil.Error(1,str);
    }

    /**
    * 更新tPropertyHouseholdInfo
    * @param tPropertyHouseholdInfo
    * @return
    */
    @Override
    public ActionRsp UpdateTPropertyHouseholdInfo(TPropertyHouseholdInfo tPropertyHouseholdInfo){
        String str = "更新TPropertyHouseholdInfo失败！";
        logger.info("更新TPropertyHouseholdInfo失败！");
        return ActionRspUtil.Error(1,str);
    }

    /**
    * 删除tPropertyHouseholdInfo
    * @param tPropertyHouseholdInfo
    * @return
    */
    @Override
    public ActionRsp DeleteTPropertyHouseholdInfo(TPropertyHouseholdInfo tPropertyHouseholdInfo){
        String str = "删除TPropertyHouseholdInfo失败！";
        logger.info("删除TPropertyHouseholdInfo失败！");
        return ActionRspUtil.Error(1,str);
    }
}