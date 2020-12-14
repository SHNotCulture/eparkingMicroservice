package com.eparking.insideService.impl;

import com.common.entity.ActionRsp;
import com.common.entity.ControllerRsp;
import com.common.entity.eparkingCloud.TCompanyNotice;
import com.eparking.insideService.TCompanyNoticeInsideService;
import com.common.util.ActionRspUtil;
import com.common.util.ControllerRspUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
* @Description: TCompanyNoticeInsideService接口熔断器
* @author 谢轩然
* @date 2020/04/29 14:33
*/
@Component
public class TCompanyNoticeInsideServiceImpl  implements TCompanyNoticeInsideService {

    private static final Logger logger = LoggerFactory.getLogger(TCompanyNoticeInsideServiceImpl.class);

    /**
    * 查询tCompanyNotice
    * @param tCompanyNotice
    * @return
    */
    @Override
    public ActionRsp getTCompanyNotice(TCompanyNotice tCompanyNotice){
        String str = "查询TCompanyNotice失败！";
        logger.info("查询TCompanyNotice失败！");
        return ActionRspUtil.Error(1,str);
    }

    /**
    *查询(分页)tCompanyNotice
    * @param tCompanyNotice
    * @param page
    * @param limit
    * @return
    */
    @Override
    public ControllerRsp getTCompanyNoticebyPage(TCompanyNotice tCompanyNotice, Integer page, Integer limit){
        String str = "查询TCompanyNotice分页失败！";
        logger.info("查询TCompanyNotice分页失败！");
        return ControllerRspUtil.Error(1,str);
    }

    /**
    * 更新tCompanyNotice
    * @param tCompanyNotice
    * @return
    */
    @Override
    public ActionRsp UpdateTCompanyNotice(TCompanyNotice tCompanyNotice){
        String str = "更新TCompanyNotice失败！";
        logger.info("更新TCompanyNotice失败！");
        return ActionRspUtil.Error(1,str);
    }

    /**
    * 删除tCompanyNotice
    * @param tCompanyNotice
    * @return
    */
    @Override
    public ActionRsp DeleteTCompanyNotice(TCompanyNotice tCompanyNotice){
        String str = "删除TCompanyNotice失败！";
        logger.info("删除TCompanyNotice失败！");
        return ActionRspUtil.Error(1,str);
    }
}