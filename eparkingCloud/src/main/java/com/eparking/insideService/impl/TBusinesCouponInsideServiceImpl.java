package com.eparking.insideService.impl;

import com.common.entity.eparkingCloud.TBusinesCoupon;
import com.common.entity.eparkingCloud.TParkInOut;
import com.eparking.insideService.TBusinesCouponInsideService;
import com.common.entity.PageBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;


/**
* @Description: TBusinesCouponInsideService接口熔断器
* @author 谢轩然
* @date 2020/04/24 11:14
*/
@Component
public class TBusinesCouponInsideServiceImpl  implements TBusinesCouponInsideService {

    private static final Logger logger = LoggerFactory.getLogger(TBusinesCouponInsideServiceImpl.class);

    /**
    * 查询tBusinesCoupon
    * @param tBusinesCoupon
    * @return
    */
    @Override
    public List<TBusinesCoupon> getTBusinesCoupon(TBusinesCoupon tBusinesCoupon, String couponTimeBegin, String couponTimeEnd){
        String str = "查询TBusinesCoupon失败！";
        logger.info("查询TBusinesCoupon失败！");
        return null;
    }

    /**
    *查询(分页)tBusinesCoupon
    * @param tBusinesCoupon
    * @param page
    * @param limit
    * @return
    */
    @Override
    public PageBean<TBusinesCoupon> getTBusinesCouponbyPage(TBusinesCoupon tBusinesCoupon, String couponTimeBegin, String couponTimeEnd, Integer page, Integer limit) {
        String str = "查询TBusinesCoupon分页失败！";
        logger.info("查询TBusinesCoupon分页失败！");
        return null;
    }

    /**
    * 更新tBusinesCoupon
    * @param tBusinesCoupon
    * @return
    */
    @Override
    public String UpdateTBusinesCoupon(TBusinesCoupon tBusinesCoupon){
        String str = "更新TBusinesCoupon失败！";
        logger.info("更新TBusinesCoupon失败！");
        return str;
    }

    /**
    * 删除tBusinesCoupon
    * @param tBusinesCoupon
    * @return
    */
    @Override
    public String DeleteTBusinesCoupon(TBusinesCoupon tBusinesCoupon){
        String str = "删除TBusinesCoupon失败！";
        logger.info("删除TBusinesCoupon失败！");
        return str;
    }

    @Override
    public List getBusinesCouponByCarplate(TParkInOut tParkInOut) {
        String str = "查询车辆优惠详情失败！";
        logger.info("查询车辆优惠详情失败！");
        return null;
    }
}