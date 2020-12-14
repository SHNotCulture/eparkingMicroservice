package com.eparkingdb.service;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TTicketCoupon;

import java.util.List;

/**
* @Description: TTicketCouponService接口
* @author 谢轩然
* @date 2020/04/10 11:47
*/
public interface TTicketCouponService {
    /**
    *查询(分页)tTicketCoupon
    * @param tTicketCoupon
    * @param page
    * @param limit
    * @return
    */
    PageBean<TTicketCoupon> getTTicketCouponbyPage(TTicketCoupon tTicketCoupon, String inTimeBegin, String inTimeEnd, String outTimeBegin, String outTimeEnd, Integer page, Integer limit);

    /**
     * 查询某个场库某车的电子券
     * @param parkId
     * @param carPlate
     * @param isUse 电子券状态
     * @return
     */
    List<TTicketCoupon> findIsUseList(Integer parkId, String carPlate, Integer isUse);

    /**
    * 查询tTicketCoupon
    * @param tTicketCoupon
    * @return
    */
    List<TTicketCoupon> getTTicketCoupon(TTicketCoupon tTicketCoupon, String inTimeBegin, String inTimeEnd, String outTimeBegin, String outTimeEnd);

    /**
    * 更新tTicketCoupon
    * @param tTicketCoupon
    * @return
    */
    String UpdateTTicketCoupon(TTicketCoupon tTicketCoupon);

    /**
    * 删除tTicketCoupon
    * @param tTicketCoupon
    * @return
    */
    String DeleteTTicketCoupon(TTicketCoupon tTicketCoupon);

    /**
    * 根据ID查询tTicketCoupon
    * @param id
    * @return
    */
    TTicketCoupon getTTicketCouponByID(Integer id);


}