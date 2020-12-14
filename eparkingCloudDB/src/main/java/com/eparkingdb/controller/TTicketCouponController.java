package com.eparkingdb.controller;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TTicketCoupon;
import com.common.annotation.HttpLog;
import com.eparkingdb.service.TTicketCouponService;
import com.common.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* @Description: TTicketCouponController类
* @author 谢轩然
* @date 2020/04/10 11:47
*/
@RestController
@RequestMapping("/tTicketCoupon")
public class TTicketCouponController {

    private  static final Logger logger= LoggerFactory.getLogger(TTicketCouponController.class);

    @Autowired
    private TTicketCouponService tTicketCouponService;

    /**
    * 查询TTicketCoupon信息
    * @paramtTicketCoupon
    * @return
    */
    @PostMapping(value = "/getTTicketCoupon")
    @HttpLog(operationType = "0",modularTypeName = "查询TTicketCoupon")
    public List<TTicketCoupon> getTTicketCoupon(@RequestBody TTicketCoupon tTicketCoupon, @RequestParam("inTimeBegin") String inTimeBegin, @RequestParam("inTimeEnd") String inTimeEnd, @RequestParam("outTimeBegin") String outTimeBegin, @RequestParam("outTimeEnd") String outTimeEnd){
    return tTicketCouponService.getTTicketCoupon(tTicketCoupon,inTimeBegin,inTimeEnd,outTimeBegin,outTimeEnd);
    }

    @PostMapping(value = "/findIsUseList")
    @HttpLog(operationType = "0",modularTypeName = "findIsUseList")
    public List<TTicketCoupon> findIsUseList(@RequestParam("parkId") Integer parkId,@RequestParam("carPlate") String carPlate,@RequestParam("isUse") Integer isUse){
        return tTicketCouponService.findIsUseList(parkId,carPlate,isUse);
    }

    /**
    * 查询TTicketCoupon信息(分页)
    * @paramtTicketCoupon
    * @return
    */
    @PostMapping(value = "/getTTicketCouponbyPage")
    @HttpLog(operationType = "0",modularTypeName = "查询TTicketCoupon(分页)")
    public PageBean<TTicketCoupon> getTTicketCouponbyPage(@RequestBody TTicketCoupon tTicketCoupon, @RequestParam("inTimeBegin") String inTimeBegin, @RequestParam("inTimeEnd") String inTimeEnd, @RequestParam("outTimeBegin") String outTimeBegin, @RequestParam("outTimeEnd") String outTimeEnd, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit){
    return tTicketCouponService.getTTicketCouponbyPage(tTicketCoupon,inTimeBegin,inTimeEnd,outTimeBegin,outTimeEnd,page,limit);
    }

    /**
    * 更新TTicketCoupon信息
    * @paramtTicketCoupon
    * @return
    */
    @PostMapping(value = "/updateTTicketCoupon")
    @HttpLog(operationType = "1",modularTypeName = "更新TTicketCoupon信息")
    public String UpdateTTicketCoupon(@RequestBody TTicketCoupon tTicketCoupon)
    {
        return tTicketCouponService.UpdateTTicketCoupon(tTicketCoupon);
    }

    /**
    * 删除TTicketCoupon信息
    * @param tTicketCoupon
    * @return
    */
    @PostMapping(value = "/deleteTTicketCoupon")
    @HttpLog(operationType = "1",modularTypeName = "删除TTicketCoupon信息")
    public String DeleteTTicketCoupon(@RequestBody TTicketCoupon tTicketCoupon){
    return tTicketCouponService.DeleteTTicketCoupon( tTicketCoupon);
    }

}