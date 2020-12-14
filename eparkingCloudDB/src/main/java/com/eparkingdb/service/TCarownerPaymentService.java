package com.eparkingdb.service;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TCarownerPayment;
import com.common.entity.eparkingCloud.TCarownerPaymentCus;

import java.util.List;

/**
* @Description: TCarownerPaymentService接口
* @author 谢轩然
* @date 2020/09/11 16:29
*/
public interface TCarownerPaymentService {
    /**
    *查询(分页)tCarownerPayment
    * @param tCarownerPayment
    * @param page
    * @param limit
    * @return
    */
    PageBean<TCarownerPayment> getTCarownerPaymentbyPage(TCarownerPayment tCarownerPayment, String beginDate, String endDate, Integer page, Integer limit);

    /**
    * 查询tCarownerPayment
    * @param tCarownerPayment
    * @return
    */
    List<TCarownerPayment> getTCarownerPayment(TCarownerPayment tCarownerPayment, String beginDate, String endDate);

    /**
    * 更新tCarownerPayment
    * @param tCarownerPayment
    * @return
    */
    String UpdateTCarownerPayment(TCarownerPayment tCarownerPayment);

    /**
    * 删除tCarownerPayment
    * @param tCarownerPayment
    * @return
    */
    String DeleteTCarownerPayment(TCarownerPayment tCarownerPayment);

    /**
    * 根据ID查询tCarownerPayment
    * @param id
    * @return
    */
    TCarownerPayment getTCarownerPaymentByID(Integer id);

    PageBean<TCarownerPayment> getTCarownerPaymentNewbyPage(TCarownerPaymentCus tCarownerPaymentCus, Integer page, Integer limit);
}