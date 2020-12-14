package com.eparking.service;

import com.common.entity.eparkingCloud.TPaymentDetails;
import com.common.entity.PageBean;

import java.util.List;

/**
* @Description: TPaymentDetailsService接口
* @author 谢轩然
* @date 2020/10/13 17:31
*/
public interface TPaymentDetailsService {
    /**
    *查询(分页)tPaymentDetails
    * @param tPaymentDetails
    * @param page
    * @param limit
    * @return
    */
    PageBean<TPaymentDetails> getTPaymentDetailsbyPage(TPaymentDetails tPaymentDetails, Integer page, Integer limit);

    /**
    * 查询tPaymentDetails
    * @param tPaymentDetails
    * @return
    */
    List<TPaymentDetails> getTPaymentDetails(TPaymentDetails tPaymentDetails);

    /**
    * 更新tPaymentDetails
    * @param tPaymentDetails
    * @return
    */
    String UpdateTPaymentDetails(TPaymentDetails tPaymentDetails);

    /**
    * 删除tPaymentDetails
    * @param tPaymentDetails
    * @return
    */
    String DeleteTPaymentDetails(TPaymentDetails tPaymentDetails);

}