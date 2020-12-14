package com.eparking.controller.work;

import com.common.entity.eparkingCloud.GlobalInfo;
import com.common.util.ActionRspUtil;
import com.common.util.SessionUtil;
import com.common.annotation.HttpLog;
import com.common.entity.ActionRsp;
import com.common.entity.eparkingCloud.GlobalInfo;
import com.common.entity.eparkingCloud.TGlobalInfo;
import com.eparking.service.GlobalInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName tGlobalInfo
 * @Author xiexuanran
 * @Date 2019/11/06 16:25
 **/
@RestController
@RequestMapping(value = "globalInfo")
public class GlobalInfoController {
    private static final Logger logger = LoggerFactory.getLogger(GlobalInfoController.class);

    @Autowired
    private GlobalInfoService globalInfoService;

    /**
     *  查询参数配置
     * @return
     */
    @PostMapping(value = "/getGlobalInfo")
    @HttpLog(operationType = "0",modularTypeName = "查询参数配置")
    public ActionRsp getTGlobalInfo(){
        TGlobalInfo tGlobalInfo= new TGlobalInfo();
        tGlobalInfo.setParkId(SessionUtil.getParkId());
        return ActionRspUtil.Success(globalInfoService.getGlobalInfo(tGlobalInfo));
    }

    /**
     * 编辑参数配置
     */
    @PostMapping(value = "/updateGlobalInfo")
    @HttpLog(operationType = "1",modularTypeName = "编辑参数配置")
    public ActionRsp updateGlobalInfo(GlobalInfo globalInfo){
        globalInfo.setParkId(SessionUtil.getParkId());
        return ActionRspUtil.Success(globalInfoService.updateGlobalInfo(globalInfo));
    }
}
