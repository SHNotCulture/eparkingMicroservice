package com.eparkingdb.service;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TBusinesCoupon;
import com.common.entity.eparkingCloud.TParkInOut;

import java.util.List;

/**
* @Description: TBusinesCouponService接口
* @author 谢轩然
* @date 2020/04/09 11:57
*/
public interface TBusinesCouponService {
    /**
    *查询(分页)tBusinesCoupon
    * @param tBusinesCoupon
    * @param page
    * @param limit
    * @return
    */
    PageBean<TBusinesCoupon> getTBusinesCouponbyPage(TBusinesCoupon tBusinesCoupon, String couponTimeBegin, String couponTimeEnd, Integer page, Integer limit);

    /**
    * 查询tBusinesCoupon
    * @param tBusinesCoupon
    * @return
    */
    List<TBusinesCoupon> getTBusinesCoupon(TBusinesCoupon tBusinesCoupon, String couponTimeBegin, String couponTimeEnd);

    /**
    * 更新tBusinesCoupon
    * @param tBusinesCoupon
    * @return
    */
    String UpdateTBusinesCoupon(TBusinesCoupon tBusinesCoupon);

    /**
    * 删除tBusinesCoupon
    * @param tBusinesCoupon
    * @return
    */
    String DeleteTBusinesCoupon(TBusinesCoupon tBusinesCoupon);

    /**
    * 根据ID查询tBusinesCoupon
    * @param id
    * @return
    */
    TBusinesCoupon getTBusinesCouponByID(Integer id);

    List getBusinesCouponByCarplate(TParkInOut tParkInOut);
}