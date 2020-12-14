package com.eparking.insideService.impl;

import com.common.entity.ActionRsp;
import com.common.entity.ControllerRsp;
import com.common.entity.eparkingCloud.TPropertyAutobill;
import com.eparking.insideService.TPropertyAutobillInsideService;
import com.common.util.ActionRspUtil;
import com.common.util.ControllerRspUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
* @Description: TPropertyAutobillInsideService接口熔断器
* @author 谢轩然
* @date 2020/04/28 16:27
*/
@Component
public class TPropertyAutobillInsideServiceImpl  implements TPropertyAutobillInsideService {

    private static final Logger logger = LoggerFactory.getLogger(TPropertyAutobillInsideServiceImpl.class);

    /**
    * 查询tPropertyAutobill
    * @param tPropertyAutobill
    * @return
    */
    @Override
    public ActionRsp getTPropertyAutobill(TPropertyAutobill tPropertyAutobill){
        String str = "查询TPropertyAutobill失败！";
        logger.info("查询TPropertyAutobill失败！");
        return ActionRspUtil.Error(1,str);
    }

    /**
    *查询(分页)tPropertyAutobill
    * @param tPropertyAutobill
    * @param page
    * @param limit
    * @return
    */
    @Override
    public ControllerRsp getTPropertyAutobillbyPage(TPropertyAutobill tPropertyAutobill, Integer page, Integer limit){
        String str = "查询TPropertyAutobill分页失败！";
        logger.info("查询TPropertyAutobill分页失败！");
        return ControllerRspUtil.Error(1,str);
    }

    /**
    * 更新tPropertyAutobill
    * @param tPropertyAutobill
    * @return
    */
    @Override
    public ActionRsp UpdateTPropertyAutobill(TPropertyAutobill tPropertyAutobill){
        String str = "更新TPropertyAutobill失败！";
        logger.info("更新TPropertyAutobill失败！");
        return ActionRspUtil.Error(1,str);
    }

    /**
    * 删除tPropertyAutobill
    * @param tPropertyAutobill
    * @return
    */
    @Override
    public ActionRsp DeleteTPropertyAutobill(TPropertyAutobill tPropertyAutobill){
        String str = "删除TPropertyAutobill失败！";
        logger.info("删除TPropertyAutobill失败！");
        return ActionRspUtil.Error(1,str);
    }
}