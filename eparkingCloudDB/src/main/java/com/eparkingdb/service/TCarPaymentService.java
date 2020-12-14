package com.eparkingdb.service;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TCarPayment;

import java.util.List;

/**
* @Description: TCarPaymentService接口
* @author 谢轩然
* @date 2020/05/14 16:54
*/
public interface TCarPaymentService {
    /**
    *查询(分页)tCarPayment
    * @param tCarPayment
    * @param page
    * @param limit
    * @return
    */
    PageBean<TCarPayment> getTCarPaymentbyPage(TCarPayment tCarPayment, String beginData, String endData, Integer page, Integer limit);

    /**
    * 查询tCarPayment
    * @param tCarPayment
    * @return
    */
    List<TCarPayment> getTCarPayment(TCarPayment tCarPayment, String beginData, String endData);

    /**
    * 更新tCarPayment
    * @param tCarPayment
    * @return
    */
    String UpdateTCarPayment(TCarPayment tCarPayment);

    /**
    * 删除tCarPayment
    * @param tCarPayment
    * @return
    */
    String DeleteTCarPayment(TCarPayment tCarPayment);

    /**
    * 根据ID查询tCarPayment
    * @param id
    * @return
    */
    TCarPayment getTCarPaymentByID(Integer id);

}