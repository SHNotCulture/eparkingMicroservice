package com.eparking.insideService.impl;

import com.common.entity.ActionRsp;
import com.common.entity.ControllerRsp;
import com.common.entity.eparkingCloud.TCompanyArea;
import com.eparking.insideService.TCompanyAreaInsideService;
import com.common.util.ActionRspUtil;
import com.common.util.ControllerRspUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
* @Description: TCompanyAreaInsideService接口熔断器
* @author 谢轩然
* @date 2020/04/23 17:28
*/
@Component
public class TCompanyAreaInsideServiceImpl  implements TCompanyAreaInsideService {

    private static final Logger logger = LoggerFactory.getLogger(TCompanyAreaInsideServiceImpl.class);

    /**
    * 查询tCompanyArea
    * @param tCompanyArea
    * @return
    */
    @Override
    public ActionRsp getTCompanyArea(TCompanyArea tCompanyArea){
        String str = "查询TCompanyArea失败！";
        logger.info("查询TCompanyArea失败！");
        return ActionRspUtil.Error(1,str);
    }

    /**
    *查询(分页)tCompanyArea
    * @param tCompanyArea
    * @param page
    * @param limit
    * @return
    */
    @Override
    public ControllerRsp getTCompanyAreabyPage(TCompanyArea tCompanyArea, Integer page, Integer limit){
        String str = "查询TCompanyArea分页失败！";
        logger.info("查询TCompanyArea分页失败！");
        return ControllerRspUtil.Error(1,str);
    }

    /**
    * 更新tCompanyArea
    * @param tCompanyArea
    * @return
    */
    @Override
    public ActionRsp UpdateTCompanyArea(TCompanyArea tCompanyArea){
        String str = "更新TCompanyArea失败！";
        logger.info("更新TCompanyArea失败！");
        return ActionRspUtil.Error(1,str);
    }

    /**
    * 删除tCompanyArea
    * @param tCompanyArea
    * @return
    */
    @Override
    public ActionRsp DeleteTCompanyArea(TCompanyArea tCompanyArea){
        String str = "删除TCompanyArea失败！";
        logger.info("删除TCompanyArea失败！");
        return ActionRspUtil.Error(1,str);
    }
}