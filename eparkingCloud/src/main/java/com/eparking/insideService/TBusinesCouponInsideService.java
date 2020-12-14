package com.eparking.insideService;

import com.common.entity.eparkingCloud.TBusinesCoupon;
import com.common.entity.eparkingCloud.TParkInOut;
import com.eparking.insideService.impl.TBusinesCouponInsideServiceImpl;
import com.common.util.Common;
import com.common.entity.PageBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
* @Description: TBusinesCouponInsideService接口
* @author 谢轩然
* @date 2020/04/24 11:14
*/
@FeignClient(value = "eparkingCloudData",fallback = TBusinesCouponInsideServiceImpl.class)
public interface TBusinesCouponInsideService {
    String client= Common.Feign_eparkingCloudData;
    /**
    * 查询tBusinesCoupon
    * @param tBusinesCoupon
    * @return
    */
    @PostMapping(value = client+"/tBusinesCoupon/getTBusinesCoupon")
    List<TBusinesCoupon> getTBusinesCoupon(@RequestBody TBusinesCoupon tBusinesCoupon, @RequestParam("couponTimeBegin") String couponTimeBegin, @RequestParam("couponTimeEnd") String couponTimeEnd);

    /**
    *查询(分页)tBusinesCoupon
    * @param tBusinesCoupon
    * @param page
    * @param limit
    * @return
    */
    @PostMapping(value = client+"/tBusinesCoupon/getTBusinesCouponbyPage")
    PageBean<TBusinesCoupon> getTBusinesCouponbyPage(@RequestBody TBusinesCoupon tBusinesCoupon, @RequestParam("couponTimeBegin") String couponTimeBegin, @RequestParam("couponTimeEnd") String couponTimeEnd, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit);

    /**
    * 更新tBusinesCoupon
    * @param tBusinesCoupon
    * @return
    */
    @PostMapping(value = client+"/tBusinesCoupon/updateTBusinesCoupon")
    String UpdateTBusinesCoupon(@RequestBody TBusinesCoupon tBusinesCoupon);

    /**
    * 删除tBusinesCoupon
    * @param tBusinesCoupon
    * @return
    */
    @PostMapping(value = client+"/tBusinesCoupon/deleteTBusinesCoupon")
    String DeleteTBusinesCoupon(@RequestBody TBusinesCoupon tBusinesCoupon);

    @PostMapping(value = client+"/tBusinesCoupon/getBusinesCouponByCarplate")
    List getBusinesCouponByCarplate(@RequestBody TParkInOut tParkInOut);
}