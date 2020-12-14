package com.eparkingdb.service;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TParkStatisticsDay;

import java.util.List;

/**
* @Description: TParkStatisticsDayService接口
* @author 李书瀚
* @date 2020/06/09 16:05
*/
public interface TParkStatisticsDayService {
    /**
    *查询tParkStatisticsDay信息(分页)
    * @param tParkStatisticsDay
    * @param page
    * @param limit
    * @return
    */
    PageBean<TParkStatisticsDay> getTParkStatisticsDaybyPage(TParkStatisticsDay tParkStatisticsDay, Integer page, Integer limit);

    /**
    * 查询tParkStatisticsDay信息
    * @param tParkStatisticsDay
    * @return
    */
    List<TParkStatisticsDay> getTParkStatisticsDay(TParkStatisticsDay tParkStatisticsDay);

    /**
    * 更新tParkStatisticsDay信息
    * @param tParkStatisticsDay
    * @return
    */
    String UpdateTParkStatisticsDay(TParkStatisticsDay tParkStatisticsDay);

    /**
    * 删除tParkStatisticsDay信息
    * @param tParkStatisticsDay
    * @return
    */
    String DeleteTParkStatisticsDay(TParkStatisticsDay tParkStatisticsDay);

    /**
    * 根据ID查询tParkStatisticsDay信息
    * @param id
    * @return
    */
    TParkStatisticsDay getTParkStatisticsDayByID(Integer id);

}