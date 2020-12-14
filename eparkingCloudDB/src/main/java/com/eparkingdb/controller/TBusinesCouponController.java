package com.eparkingdb.controller;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TBusinesCoupon;
import com.common.entity.eparkingCloud.TParkInOut;
import com.common.annotation.HttpLog;
import com.eparkingdb.service.TBusinesCouponService;
import com.common.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
* @Description: TBusinesCouponController类
* @author 谢轩然
* @date 2020/04/09 11:57
*/
@RestController
@RequestMapping("/tBusinesCoupon")
public class TBusinesCouponController {

    private  static final Logger logger= LoggerFactory.getLogger(TBusinesCouponController.class);

    @Autowired
    private TBusinesCouponService tBusinesCouponService;

    /**
    * 查询TBusinesCoupon信息
    * @paramtBusinesCoupon
    * @return
    */
    @PostMapping(value = "/getTBusinesCoupon")
    @HttpLog(operationType = "0",modularTypeName = "查询TBusinesCoupon")
    public List<TBusinesCoupon> getTBusinesCoupon(@RequestBody TBusinesCoupon tBusinesCoupon, @RequestParam("couponTimeBegin") String couponTimeBegin, @RequestParam("couponTimeEnd") String couponTimeEnd){
    return tBusinesCouponService.getTBusinesCoupon(tBusinesCoupon,couponTimeBegin,couponTimeEnd);
    }

    /**
    * 查询TBusinesCoupon信息(分页)
    * @paramtBusinesCoupon
    * @return
    */
    @PostMapping(value = "/getTBusinesCouponbyPage")
    @HttpLog(operationType = "0",modularTypeName = "查询TBusinesCoupon(分页)")
    public PageBean<TBusinesCoupon> getTBusinesCouponbyPage(@RequestBody TBusinesCoupon tBusinesCoupon, @RequestParam("couponTimeBegin") String couponTimeBegin, @RequestParam("couponTimeEnd") String couponTimeEnd, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit){
    return tBusinesCouponService.getTBusinesCouponbyPage(tBusinesCoupon,couponTimeBegin,couponTimeEnd,page,limit);
    }

    /**
    * 更新TBusinesCoupon信息
    * @paramtBusinesCoupon
    * @return
    */
    @PostMapping(value = "/updateTBusinesCoupon")
    @HttpLog(operationType = "1",modularTypeName = "更新TBusinesCoupon信息")
    public String UpdateTBusinesCoupon(@RequestBody TBusinesCoupon tBusinesCoupon)
    {
        return tBusinesCouponService.UpdateTBusinesCoupon(tBusinesCoupon);
    }

    /**
    * 删除TBusinesCoupon信息
    * @param tBusinesCoupon
    * @return
    */
    @PostMapping(value = "/deleteTBusinesCoupon")
    @HttpLog(operationType = "1",modularTypeName = "删除TBusinesCoupon信息")
    public String DeleteTBusinesCoupon(@RequestBody TBusinesCoupon tBusinesCoupon){
    return tBusinesCouponService.DeleteTBusinesCoupon(tBusinesCoupon);
    }

    @PostMapping(value = "/getBusinesCouponByCarplate")
    @HttpLog(operationType = "0",modularTypeName = "查询车辆优惠详情")
    public List getBusinesCouponByCarplate(@RequestBody TParkInOut tParkInOut){
        return tBusinesCouponService.getBusinesCouponByCarplate(tParkInOut);
    }
}