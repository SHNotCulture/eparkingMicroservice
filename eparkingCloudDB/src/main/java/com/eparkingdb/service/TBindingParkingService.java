package com.eparkingdb.service;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TBindingParking;

import java.util.List;

/**
* @Description: TBindingParkingService接口
* @author 谢轩然
* @date 2020/04/08 18:18
*/
public interface TBindingParkingService {
    /**
    *查询(分页)tBindingParking
    * @param tBindingParking
    * @param page
    * @param limit
    * @return
    */
    PageBean<TBindingParking> getTBindingParkingbyPage(TBindingParking tBindingParking, Integer page, Integer limit);

    /**
    * 查询tBindingParking
    * @param tBindingParking
    * @return
    */
    List<TBindingParking> getTBindingParking(TBindingParking tBindingParking);

    /**
    * 更新tBindingParking
    * @param tBindingParking
    * @return
    */
    String UpdateTBindingParking(TBindingParking tBindingParking);

    /**
    * 删除tBindingParking
    * @param tBindingParking
    * @return
    */
    String DeleteTBindingParking(TBindingParking tBindingParking);

    /**
    * 根据ID查询tBindingParking
    * @param id
    * @return
    */
    TBindingParking getTBindingParkingByID(Integer id);

}