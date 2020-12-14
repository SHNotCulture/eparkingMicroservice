package com.eparkingdb.controller;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TBusine;
import com.common.entity.eparkingCloud.TBusinePay;
import com.common.annotation.HttpLog;
import com.eparkingdb.service.TBusineService;
import com.common.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* @Description: TBusineController类
* @author 谢轩然
* @date 2020/04/08 18:35
*/
@RestController
@RequestMapping("/tBusine")
public class TBusineController {

    private  static final Logger logger= LoggerFactory.getLogger(TBusineController.class);

    @Autowired
    private TBusineService tBusineService;

    /**
    * 查询TBusine信息
    * @paramtBusine
    * @return
    */
    @PostMapping(value = "/getTBusine")
    @HttpLog(operationType = "0",modularTypeName = "查询TBusine")
    public List<TBusine> getTBusine(@RequestBody TBusine tBusine){
    return tBusineService.getTBusine(tBusine);
    }

    /**
    * 查询TBusine信息(分页)
    * @paramtBusine
    * @return
    */
    @PostMapping(value = "/getTBusinebyPage")
    @HttpLog(operationType = "0",modularTypeName = "查询TBusine(分页)")
    public PageBean<TBusine> getTBusinebyPage(@RequestBody TBusine tBusine, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit){
    return tBusineService.getTBusinebyPage(tBusine,page,limit);
    }

    /**
    * 更新TBusine信息
    * @paramtBusine
    * @return
    */
    @PostMapping(value = "/updateTBusine")
    @HttpLog(operationType = "1",modularTypeName = "更新TBusine信息")
    public String UpdateTBusine(@RequestBody TBusine tBusine)
    {
        return tBusineService.UpdateTBusine(tBusine);
    }

    /**
    * 删除TBusine信息
    * @param tBusine
    * @return
    */
    @PostMapping(value = "/deleteTBusine")
    @HttpLog(operationType = "1",modularTypeName = "删除TBusine信息")
    public String DeleteTBusine(@RequestBody TBusine tBusine){
    return tBusineService.DeleteTBusine(tBusine);
    }

    @PostMapping(value = "/getBusineRecharge")
    @HttpLog(operationType = "0",modularTypeName = "查询商户消费记录")
    public String getBusineRecharge(@RequestBody TBusinePay tBusinePay,@RequestParam("type") String type){
        return tBusineService.BusineRecharge(tBusinePay,type);
    }

    @PostMapping(value = "/selectByAccount")
    @HttpLog(operationType = "0",modularTypeName = "根据账号查询商户")
    public TBusine selectByAccount(@RequestParam("account") String account){
        return tBusineService.selectByAccount(account);
    }

    @PostMapping(value = "/selectById")
    @HttpLog(operationType = "0",modularTypeName = "根据id查询商户")
    public TBusine selectById(@RequestParam("id") Integer id){
        return tBusineService.getTBusineByID(id);
    }
}