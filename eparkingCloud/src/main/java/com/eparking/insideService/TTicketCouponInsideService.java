package com.eparking.insideService;

import com.common.entity.eparkingCloud.TTicketCoupon;
import com.eparking.insideService.impl.TTicketCouponInsideServiceImpl;
import com.common.util.Common;
import com.common.entity.PageBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
* @Description: TTicketCouponInsideService接口
* @author 谢轩然
* @date 2020/04/10 11:47
*/
@FeignClient(value = "eparkingCloudData",fallback = TTicketCouponInsideServiceImpl.class)
public interface TTicketCouponInsideService {
    String client= Common.Feign_eparkingCloudData;
    /**
    * 查询tTicketCoupon
    * @param tTicketCoupon
    * @return
    */
    @PostMapping(value = client+"/tTicketCoupon/getTTicketCoupon")
    List<TTicketCoupon> getTTicketCoupon(@RequestBody TTicketCoupon tTicketCoupon, @RequestParam("inTimeBegin") String inTimeBegin, @RequestParam("inTimeEnd") String inTimeEnd, @RequestParam("outTimeBegin") String outTimeBegin, @RequestParam("outTimeEnd") String outTimeEnd);

    /**
    *查询(分页)tTicketCoupon
    * @param tTicketCoupon
    * @param page
    * @param limit
    * @return
    */
    @PostMapping(value = client+"/tTicketCoupon/getTTicketCouponbyPage")
    PageBean<TTicketCoupon> getTTicketCouponbyPage(@RequestBody TTicketCoupon tTicketCoupon, @RequestParam("inTimeBegin") String inTimeBegin, @RequestParam("inTimeEnd") String inTimeEnd, @RequestParam("outTimeBegin") String outTimeBegin, @RequestParam("outTimeEnd") String outTimeEnd, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit);

    /**
    * 更新tTicketCoupon
    * @param tTicketCoupon
    * @return
    */
    @PostMapping(value = client+"/tTicketCoupon/updateTTicketCoupon")
    String UpdateTTicketCoupon(@RequestBody TTicketCoupon tTicketCoupon);

    /**
    * 删除tTicketCoupon
    * @param tTicketCoupon
    * @return
    */
    @PostMapping(value = client+"/tTicketCoupon/deleteTTicketCoupon")
    String DeleteTTicketCoupon(@RequestBody TTicketCoupon tTicketCoupon);

}