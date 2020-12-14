package com.eparkingdb.controller;

import com.common.entity.eparkingCloud.TPropertyAutobill;
import com.common.annotation.HttpLog;
import com.eparkingdb.service.TPropertyAutobillService;
import com.common.entity.ActionRsp;
import com.common.util.ActionRspUtil;
import com.common.entity.ControllerRsp;
import com.common.util.ControllerRspUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
* @Description: TPropertyAutobillController类
* @author 谢轩然
* @date 2020/04/28 16:27
*/
@RestController
@RequestMapping("/tPropertyAutobill")
public class TPropertyAutobillController {

    private  static final Logger logger= LoggerFactory.getLogger(TPropertyAutobillController.class);

    @Autowired
    private TPropertyAutobillService tPropertyAutobillService;

    /**
    * 查询TPropertyAutobill信息
    * @paramtPropertyAutobill
    * @return
    */
    @PostMapping(value = "/getTPropertyAutobill")
    @HttpLog(operationType = "0",modularTypeName = "查询TPropertyAutobill")
    public ActionRsp getTPropertyAutobill(@RequestBody TPropertyAutobill tPropertyAutobill){
    return ActionRspUtil.Success(tPropertyAutobillService.getTPropertyAutobill(tPropertyAutobill));
    }

    /**
    * 查询TPropertyAutobill信息(分页)
    * @paramtPropertyAutobill
    * @return
    */
    @PostMapping(value = "/getTPropertyAutobillbyPage")
    @HttpLog(operationType = "0",modularTypeName = "查询TPropertyAutobill(分页)")
    public ControllerRsp getTPropertyAutobillbyPage(@RequestBody TPropertyAutobill tPropertyAutobill, @RequestParam("page") Integer page,@RequestParam("limit") Integer limit){
    return ControllerRspUtil.Success(tPropertyAutobillService.getTPropertyAutobillbyPage(tPropertyAutobill,page,limit));
    }

    /**
    * 更新TPropertyAutobill信息
    * @paramtPropertyAutobill
    * @return
    */
    @PostMapping(value = "/updateTPropertyAutobill")
    @HttpLog(operationType = "1",modularTypeName = "更新TPropertyAutobill信息")
    public ActionRsp UpdateTPropertyAutobill(@RequestBody TPropertyAutobill tPropertyAutobill)
    {
        return ActionRspUtil.Success(tPropertyAutobillService. UpdateTPropertyAutobill(tPropertyAutobill));
    }

    /**
    * 删除TPropertyAutobill信息
    * @param tPropertyAutobill
    * @return
    */
    @PostMapping(value = "/deleteTPropertyAutobill")
    @HttpLog(operationType = "1",modularTypeName = "删除TPropertyAutobill信息")
    public ActionRsp DeleteTPropertyAutobill(@RequestBody TPropertyAutobill tPropertyAutobill){
    return ActionRspUtil.Success(tPropertyAutobillService.DeleteTPropertyAutobill( tPropertyAutobill));
    }
}