package com.eparkingdb.controller;

import com.common.entity.ActionRsp;
import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TBusinePay;
import com.common.annotation.HttpLog;
import com.eparkingdb.service.TBusinePayService;
import com.common.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
* @Description: TBusinePayController类
* @author 谢轩然
* @date 2020/04/09 10:50
*/
@RestController
@RequestMapping("/tBusinePay")
public class TBusinePayController {

    private  static final Logger logger= LoggerFactory.getLogger(TBusinePayController.class);

    @Autowired
    private TBusinePayService tBusinePayService;

    /**
    * 查询TBusinePay信息
    * @paramtBusinePay
    * @return
    */
    @PostMapping(value = "/getTBusinePay")
    @HttpLog(operationType = "0",modularTypeName = "查询TBusinePay")
    public List<TBusinePay> getTBusinePay(@RequestBody TBusinePay tBusinePay, @RequestParam("busineId") Integer busineId, @RequestParam("payTimeBegin") String payTimeBegin, @RequestParam("payTimeEnd") String payTimeEnd){
        if(busineId==0){
            return tBusinePayService.getTBusinePay(tBusinePay,null,payTimeBegin,payTimeEnd);
        }else{
            return tBusinePayService.getTBusinePay(tBusinePay,busineId,payTimeBegin,payTimeEnd);
        }
    }

    /**
    * 查询TBusinePay信息(分页)
    * @paramtBusinePay
    * @return
    */
    @PostMapping(value = "/getTBusinePaybyPage")
    @HttpLog(operationType = "0",modularTypeName = "查询TBusinePay(分页)")
    public PageBean<TBusinePay> getTBusinePaybyPage(@RequestBody TBusinePay tBusinePay , @RequestParam("busineId") Integer busineId, @RequestParam("payTimeBegin") String payTimeBegin, @RequestParam("payTimeEnd") String payTimeEnd, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit){
    return tBusinePayService.getTBusinePaybyPage(tBusinePay ,busineId,payTimeBegin,payTimeEnd,page,limit);
    }

    /**
    * 更新TBusinePay信息
    * @paramtBusinePay
    * @return
    */
    @PostMapping(value = "/updateTBusinePay")
    @HttpLog(operationType = "1",modularTypeName = "更新TBusinePay信息")
    public ActionRsp UpdateTBusinePay(@RequestBody TBusinePay tBusinePay)
    {
        return ActionRspUtil.Success(tBusinePayService.UpdateTBusinePay(tBusinePay));
    }

    /**
    * 删除TBusinePay信息
    * @param tBusinePay
    * @return
    */
    @PostMapping(value = "/deleteTBusinePay")
    @HttpLog(operationType = "1",modularTypeName = "删除TBusinePay信息")
    public ActionRsp DeleteTBusinePay(@RequestBody TBusinePay tBusinePay){
    return ActionRspUtil.Success(tBusinePayService.DeleteTBusinePay(tBusinePay));
    }

    /**
     * 导出商户充值记录
     * @param
     * @return
     */
    @PostMapping(value = "/exportListforBusinePay")
    @HttpLog(operationType = "0",modularTypeName = "导出商户充值记录")
    public void exportListforBusinePay(@RequestBody TBusinePay tBusinePay,@RequestParam("busineId") Integer busineId,@RequestParam("payTimeBegin") String payTimeBegin,@RequestParam("payTimeEnd") String payTimeEnd,@RequestParam("response") HttpServletResponse response){
        tBusinePayService.exportList(tBusinePay,busineId,payTimeBegin,payTimeEnd,"商户充值记录",response);
    }
}