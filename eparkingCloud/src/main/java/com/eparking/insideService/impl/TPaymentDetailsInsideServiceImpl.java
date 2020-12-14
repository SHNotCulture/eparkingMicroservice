package com.eparking.insideService.impl;

import com.common.entity.eparkingCloud.TPaymentDetails;
import com.eparking.insideService.TPaymentDetailsInsideService;
import com.common.entity.PageBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;


/**
* @Description: TPaymentDetailsInsideService接口熔断器
* @author 谢轩然
* @date 2020/10/13 17:31
*/
@Component
public class TPaymentDetailsInsideServiceImpl  implements TPaymentDetailsInsideService {

    private static final Logger logger = LoggerFactory.getLogger(TPaymentDetailsInsideServiceImpl.class);

    /**
    * 查询tPaymentDetails
    * @param tPaymentDetails
    * @return
    */
    @Override
    public List<TPaymentDetails> getTPaymentDetails(TPaymentDetails tPaymentDetails){
        String str = "查询TPaymentDetails失败！";
        logger.info("查询TPaymentDetails失败！");
        return null;
    }

    /**
    *查询(分页)tPaymentDetails
    * @param tPaymentDetails
    * @param page
    * @param limit
    * @return
    */
    @Override
    public PageBean<TPaymentDetails> getTPaymentDetailsbyPage(TPaymentDetails tPaymentDetails, Integer page, Integer limit){
        String str = "查询TPaymentDetails分页失败！";
        logger.info("查询TPaymentDetails分页失败！");
        return null;
    }

    /**
    * 更新tPaymentDetails
    * @param tPaymentDetails
    * @return
    */
    @Override
    public String UpdateTPaymentDetails(TPaymentDetails tPaymentDetails){
        String str = "更新TPaymentDetails失败！";
        logger.info("更新TPaymentDetails失败！");
        return str;
    }

    /**
    * 删除tPaymentDetails
    * @param tPaymentDetails
    * @return
    */
    @Override
    public String DeleteTPaymentDetails(TPaymentDetails tPaymentDetails){
        String str = "删除TPaymentDetails失败！";
        logger.info("删除TPaymentDetails失败！");
        return str;
    }
}