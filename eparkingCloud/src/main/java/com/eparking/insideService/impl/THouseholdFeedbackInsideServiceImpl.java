package com.eparking.insideService.impl;

import com.common.entity.ActionRsp;
import com.common.entity.ControllerRsp;
import com.common.entity.eparkingCloud.THouseholdFeedback;
import com.eparking.insideService.THouseholdFeedbackInsideService;
import com.common.util.ActionRspUtil;
import com.common.util.ControllerRspUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
* @Description: THouseholdFeedbackInsideService接口熔断器
* @author 谢轩然
* @date 2020/04/29 15:30
*/
@Component
public class THouseholdFeedbackInsideServiceImpl  implements THouseholdFeedbackInsideService {

    private static final Logger logger = LoggerFactory.getLogger(THouseholdFeedbackInsideServiceImpl.class);

    /**
    * tHouseholdFeedback
    * @param tHouseholdFeedback
    * @return
    */
    @Override
    public ActionRsp getTHouseholdFeedback(THouseholdFeedback tHouseholdFeedback){
        String str = "查询THouseholdFeedback失败！";
        logger.info("查询THouseholdFeedback失败！");
        return ActionRspUtil.Error(1,str);
    }

    /**
    *查询(分页)THouseholdFeedback
    * @param THouseholdFeedback
    * @param page
    * @param limit
    * @return
    */
    @Override
    public ControllerRsp getTHouseholdFeedbackbyPage(THouseholdFeedback THouseholdFeedback, Integer page, Integer limit){
        String str = "查询THouseholdFeedback分页失败！";
        logger.info("查询THouseholdFeedback分页失败！");
        return ControllerRspUtil.Error(1,str);
    }

    /**
    * 更新THouseholdFeedback
    * @param THouseholdFeedback
    * @return
    */
    @Override
    public ActionRsp UpdateTHouseholdFeedback(THouseholdFeedback THouseholdFeedback){
        String str = "更新THouseholdFeedback失败！";
        logger.info("更新THouseholdFeedback失败！");
        return ActionRspUtil.Error(1,str);
    }

    /**
    * 删除THouseholdFeedback
    * @param THouseholdFeedback
    * @return
    */
    @Override
    public ActionRsp DeleteTHouseholdFeedback(THouseholdFeedback THouseholdFeedback){
        String str = "删除THouseholdFeedback失败！";
        logger.info("删除THouseholdFeedback失败！");
        return ActionRspUtil.Error(1,str);
    }
}