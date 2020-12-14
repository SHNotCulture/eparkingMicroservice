package com.eparkingdb.service;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TCompanyPark;

import java.util.List;

/**
* @Description: TCompanyParkService接口
* @author 谢轩然
* @date 2020/04/09 14:50
*/
public interface TCompanyParkService {
    /**
    *查询(分页)tCompanyPark
    * @param tCompanyPark
    * @param page
    * @param limit
    * @return
    */
    PageBean<TCompanyPark> getTCompanyParkbyPage(TCompanyPark tCompanyPark, Integer page, Integer limit);

    /**
    * 查询tCompanyPark
    * @param tCompanyPark
    * @return
    */
    List<TCompanyPark> getTCompanyPark(TCompanyPark tCompanyPark);

    /**
    * 更新tCompanyPark
    * @param tCompanyPark
    * @return
    */
    String UpdateTCompanyPark(TCompanyPark tCompanyPark);

    /**
     * 更新今天入场车辆
     * @param parkId
     * @param number 入场车辆
     * @param inTime 入场时间
     * @return
     */
    String updateInCarsToday(Integer parkId, int number, String inTime);

    /**
    * 删除tCompanyPark
    * @param tCompanyPark
    * @return
    */
    String DeleteTCompanyPark(TCompanyPark tCompanyPark);

    /**
    * 根据ID查询tCompanyPark
    * @param id
    * @return
    */
    TCompanyPark getTCompanyParkByID(Integer id);

    List<TCompanyPark> getCarParkbyIDIn(String ids);
}