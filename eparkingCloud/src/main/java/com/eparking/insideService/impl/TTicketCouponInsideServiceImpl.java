package com.eparking.insideService.impl;

import com.common.entity.eparkingCloud.TTicketCoupon;
import com.eparking.insideService.TTicketCouponInsideService;
import com.common.entity.PageBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;


/**
* @Description: TTicketCouponInsideService接口熔断器
* @author 谢轩然
* @date 2020/04/10 11:47
*/
@Component
public class TTicketCouponInsideServiceImpl  implements TTicketCouponInsideService {

    private static final Logger logger = LoggerFactory.getLogger(TTicketCouponInsideServiceImpl.class);

    /**
    * 查询tTicketCoupon
    * @param tTicketCoupon
    * @return
    */
    @Override
    public List<TTicketCoupon> getTTicketCoupon(TTicketCoupon tTicketCoupon, String inTimeBegin, String inTimeEnd, String outTimeBegin, String outTimeEnd){
        String str = "查询TTicketCoupon失败！";
        logger.info("查询TTicketCoupon失败！");
        return null;
    }

    /**
    *查询(分页)tTicketCoupon
    * @param tTicketCoupon
    * @param page
    * @param limit
    * @return
    */
    @Override
    public PageBean<TTicketCoupon> getTTicketCouponbyPage(TTicketCoupon tTicketCoupon, String inTimeBegin, String inTimeEnd, String outTimeBegin, String outTimeEnd, Integer page, Integer limit){
        String str = "查询TTicketCoupon分页失败！";
        logger.info("查询TTicketCoupon分页失败！");
        return null;
    }

    /**
    * 更新tTicketCoupon
    * @param tTicketCoupon
    * @return
    */
    @Override
    public String UpdateTTicketCoupon(TTicketCoupon tTicketCoupon){
        String str = "更新TTicketCoupon失败！";
        logger.info("更新TTicketCoupon失败！");
        return str;
    }

    /**
    * 删除tTicketCoupon
    * @param tTicketCoupon
    * @return
    */
    @Override
    public String DeleteTTicketCoupon(TTicketCoupon tTicketCoupon){
        String str = "删除TTicketCoupon失败！";
        logger.info("删除TTicketCoupon失败！");
        return str;
    }

}