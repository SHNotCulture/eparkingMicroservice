package com.eparkingdb.controller;

import com.common.entity.ActionRsp;
import com.common.entity.ControllerRsp;
import com.common.entity.eparkingCloud.TPropertyHouseholdInfo;
import com.common.annotation.HttpLog;
import com.eparkingdb.service.TPropertyHouseholdInfoService;
import com.common.util.ActionRspUtil;
import com.common.util.ControllerRspUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* @Description: TPropertyHouseholdInfoController类
* @author 谢轩然
* @date 2020/04/28 15:20
*/
@RestController
@RequestMapping("/tPropertyHouseholdInfo")
public class TPropertyHouseholdInfoController {

    private  static final Logger logger= LoggerFactory.getLogger(TPropertyHouseholdInfoController.class);

    @Autowired
    private TPropertyHouseholdInfoService tPropertyHouseholdInfoService;

    /**
    * 查询TPropertyHouseholdInfo信息
    * @paramtPropertyHouseholdInfo
    * @return
    */
    @PostMapping(value = "/getTPropertyHouseholdInfo")
    @HttpLog(operationType = "0",modularTypeName = "查询TPropertyHouseholdInfo")
    public ActionRsp getTPropertyHouseholdInfo(@RequestBody TPropertyHouseholdInfo tPropertyHouseholdInfo){
    return ActionRspUtil.Success(tPropertyHouseholdInfoService.getTPropertyHouseholdInfo(tPropertyHouseholdInfo));
    }

    /**
    * 查询TPropertyHouseholdInfo信息(分页)
    * @paramtPropertyHouseholdInfo
    * @return
    */
    @PostMapping(value = "/getTPropertyHouseholdInfobyPage")
    @HttpLog(operationType = "0",modularTypeName = "查询TPropertyHouseholdInfo(分页)")
    public ControllerRsp getTPropertyHouseholdInfobyPage(@RequestBody TPropertyHouseholdInfo tPropertyHouseholdInfo, Integer page, Integer limit){
    return ControllerRspUtil.Success(tPropertyHouseholdInfoService.getTPropertyHouseholdInfobyPage(tPropertyHouseholdInfo,page,limit));
    }

    /**
    * 更新TPropertyHouseholdInfo信息
    * @paramtPropertyHouseholdInfo
    * @return
    */
    @PostMapping(value = "/updateTPropertyHouseholdInfo")
    @HttpLog(operationType = "1",modularTypeName = "更新TPropertyHouseholdInfo信息")
    public ActionRsp UpdateTPropertyHouseholdInfo(@RequestBody TPropertyHouseholdInfo tPropertyHouseholdInfo)
    {
        return ActionRspUtil.Success(tPropertyHouseholdInfoService.UpdateTPropertyHouseholdInfo(tPropertyHouseholdInfo));
    }

    /**
    * 删除TPropertyHouseholdInfo信息
    * @param tPropertyHouseholdInfo
    * @return
    */
    @PostMapping(value = "/deleteTPropertyHouseholdInfo")
    @HttpLog(operationType = "1",modularTypeName = "删除TPropertyHouseholdInfo信息")
    public ActionRsp DeleteTPropertyHouseholdInfo(@RequestBody TPropertyHouseholdInfo tPropertyHouseholdInfo){
    return ActionRspUtil.Success(tPropertyHouseholdInfoService.DeleteTPropertyHouseholdInfo(tPropertyHouseholdInfo));
    }
}