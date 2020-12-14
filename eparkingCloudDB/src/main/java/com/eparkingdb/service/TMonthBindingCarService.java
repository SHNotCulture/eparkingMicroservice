package com.eparkingdb.service;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TMonthBindingCar;

import java.util.List;

/**
* @Description: TMonthBindingCarService接口
* @author 谢轩然
* @date 2020/05/26 17:04
*/
public interface TMonthBindingCarService {
    /**
    *查询(分页)tMonthBindingCar
    * @param tMonthBindingCar
    * @param page
    * @param limit
    * @return
    */
    PageBean<TMonthBindingCar> getTMonthBindingCarbyPage(TMonthBindingCar tMonthBindingCar, Integer page, Integer limit);

    /**
    * 查询tMonthBindingCar
    * @param tMonthBindingCar
    * @return
    */
    List<TMonthBindingCar> getTMonthBindingCar(TMonthBindingCar tMonthBindingCar);

    /**
    * 更新tMonthBindingCar
    * @param tMonthBindingCar
    * @return
    */
    String UpdateTMonthBindingCar(TMonthBindingCar tMonthBindingCar);

    /**
    * 删除tMonthBindingCar
    * @param tMonthBindingCar
    * @return
    */
    String DeleteTMonthBindingCar(TMonthBindingCar tMonthBindingCar);

    /**
    * 根据ID查询tMonthBindingCar
    * @param id
    * @return
    */
    TMonthBindingCar getTMonthBindingCarByID(Integer id);

}