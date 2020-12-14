package com.eparkingdb.service;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TBusinePay;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
* @Description: TBusinePayService接口
* @author 谢轩然
* @date 2020/04/09 10:50
*/
public interface TBusinePayService {
    /**
    *查询(分页)tBusinePay
    * @param tBusinePay
    * @param page
    * @param limit
    * @return
    */
    PageBean<TBusinePay> getTBusinePaybyPage(TBusinePay tBusinePay, Integer busineId, String payTimeBegin, String payTimeEnd, Integer page, Integer limit);

    /**
    * 查询tBusinePay
    * @param tBusinePay
    * @return
    */
    List<TBusinePay> getTBusinePay(TBusinePay tBusinePay, Integer busineId, String payTimeBegin, String payTimeEnd);

    /**
    * 更新tBusinePay
    * @param tBusinePay
    * @return
    */
    String UpdateTBusinePay(TBusinePay tBusinePay);

    /**
    * 删除tBusinePay
    * @param tBusinePay
    * @return
    */
    String DeleteTBusinePay(TBusinePay tBusinePay);

    /**
    * 根据ID查询tBusinePay
    * @param id
    * @return
    */
    TBusinePay getTBusinePayByID(Integer id);

    void exportList(TBusinePay tBusinePay, Integer busineId, String payTimeBegin, String payTimeEnd, String title, HttpServletResponse response);

}