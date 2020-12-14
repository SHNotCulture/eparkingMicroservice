package com.eparking.service;

import com.common.entity.eparkingCloud.TCarownerPayment;
import com.common.entity.eparkingCloud.TCarownerPaymentCus;
import com.common.entity.PageBean;

import javax.servlet.http.HttpServletResponse;
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
    PageBean<TCarownerPayment> getTCarownerPaymentbyPage(TCarownerPaymentCus tCarownerPaymentCus, Integer page, Integer limit);

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

    void exportList(TCarownerPayment tCarownerPayment, String title, String beginDate, String endDate, HttpServletResponse response);
}