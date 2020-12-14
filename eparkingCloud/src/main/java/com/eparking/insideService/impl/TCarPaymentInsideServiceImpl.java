package com.eparking.insideService.impl;

import com.common.entity.eparkingCloud.TCarPayment;
import com.eparking.insideService.TCarPaymentInsideService;
import com.common.entity.PageBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;


/**
* @Description: TCarPaymentInsideService接口熔断器
* @author 谢轩然
* @date 2020/05/14 16:54
*/
@Component
public class TCarPaymentInsideServiceImpl  implements TCarPaymentInsideService {

    private static final Logger logger = LoggerFactory.getLogger(TCarPaymentInsideServiceImpl.class);

    /**
    * 查询tCarPayment
    * @param tCarPayment
    * @return
    */
    @Override
    public List<TCarPayment> getTCarPayment(TCarPayment tCarPayment, String beginData, String endData){
        String str = "查询TCarPayment失败！";
        logger.info("查询TCarPayment失败！");
        return null;
    }

    /**
    *查询(分页)tCarPayment
    * @param tCarPayment
    * @param page
    * @param limit
    * @return
    */
    @Override
    public PageBean<TCarPayment> getTCarPaymentbyPage(TCarPayment tCarPayment, String beginData, String endData, Integer page, Integer limit){
        String str = "查询TCarPayment分页失败！";
        logger.info("查询TCarPayment分页失败！");
        return null;
    }

    /**
    * 更新tCarPayment
    * @param tCarPayment
    * @return
    */
    @Override
    public String UpdateTCarPayment(TCarPayment tCarPayment){
        String str = "更新TCarPayment失败！";
        logger.info("更新TCarPayment失败！");
        return str;
    }

    /**
    * 删除tCarPayment
    * @param tCarPayment
    * @return
    */
    @Override
    public String DeleteTCarPayment(TCarPayment tCarPayment){
        String str = "删除TCarPayment失败！";
        logger.info("删除TCarPayment失败！");
        return str;
    }
}