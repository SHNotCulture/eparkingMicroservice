package com.eparking.insideService.impl;

import com.common.entity.eparkingCloud.TCarownerPayment;
import com.common.entity.eparkingCloud.TCarownerPaymentCus;
import com.eparking.insideService.TCarownerPaymentInsideService;
import com.common.entity.PageBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;


/**
* @Description: TCarownerPaymentInsideService接口熔断器
* @author 谢轩然
* @date 2020/09/11 16:29
*/
@Component
public class TCarownerPaymentInsideServiceImpl  implements TCarownerPaymentInsideService {

    private static final Logger logger = LoggerFactory.getLogger(TCarownerPaymentInsideServiceImpl.class);

    /**
    * 查询tCarownerPayment
    * @param tCarownerPayment
    * @return
    */
    @Override
    public List<TCarownerPayment> getTCarownerPayment(TCarownerPayment tCarownerPayment,String beginDate,String endDate){
        String str = "查询TCarownerPayment失败！";
        logger.info("查询TCarownerPayment失败！");
        return null;
    }

    /**
    *查询(分页)tCarownerPayment
    * @param tCarownerPayment
    * @param page
    * @param limit
    * @return
    */
    @Override
    public PageBean<TCarownerPayment> getTCarownerPaymentbyPage(TCarownerPaymentCus tCarownerPaymentCus, Integer page, Integer limit){
        String str = "查询TCarownerPayment分页失败！";
        logger.info("查询TCarownerPayment分页失败！");
        return null;
    }

    /**
    * 更新tCarownerPayment
    * @param tCarownerPayment
    * @return
    */
    @Override
    public String UpdateTCarownerPayment(TCarownerPayment tCarownerPayment){
        String str = "更新TCarownerPayment失败！";
        logger.info("更新TCarownerPayment失败！");
        return str;
    }

    /**
    * 删除tCarownerPayment
    * @param tCarownerPayment
    * @return
    */
    @Override
    public String DeleteTCarownerPayment(TCarownerPayment tCarownerPayment){
        String str = "删除TCarownerPayment失败！";
        logger.info("删除TCarownerPayment失败！");
        return str;
    }

    @Override
    public TCarownerPayment getTCarownerPaymentByID(Integer id) {
        String str = "根据id查找TCarownerPayment失败！";
        logger.info("根据id查找TCarownerPayment失败！");
        return null;
    }
}