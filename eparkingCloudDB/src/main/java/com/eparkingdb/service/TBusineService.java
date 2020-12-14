package com.eparkingdb.service;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TBusine;
import com.common.entity.eparkingCloud.TBusinePay;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @Description: TBusineService接口
* @author 谢轩然
* @date 2020/04/08 18:35
*/
public interface TBusineService {
    /**
    *查询(分页)tBusine
    * @param tBusine
    * @param page
    * @param limit
    * @return
    */
    PageBean<TBusine> getTBusinebyPage(TBusine tBusine, Integer page, Integer limit);

    /**
    * 查询tBusine
    * @param tBusine
    * @return
    */
    List<TBusine> getTBusine(TBusine tBusine);

    /**
    * 更新tBusine
    * @param tBusine
    * @return
    */
    String UpdateTBusine(TBusine tBusine);

    /**
    * 删除tBusine
    * @param tBusine
    * @return
    */
    String DeleteTBusine(TBusine tBusine);

    /**
    * 根据ID查询tBusine
    * @param id
    * @return
    */
    TBusine getTBusineByID(Integer id);

    TBusine selectByAccount(String account);

    @Transactional
    String BusineRecharge(TBusinePay tBusinePay, String type);

}