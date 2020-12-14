package com.eparkingdb.service;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TParkStatistics;

import java.util.List;

/**
* @Description: TParkStatisticsService接口
* @author 李书瀚
* @date 2020/06/09 16:05
*/
public interface TParkStatisticsService {
    /**
    *查询tParkStatistics信息(分页)
    * @param tParkStatistics
    * @param page
    * @param limit
    * @return
    */
    PageBean<TParkStatistics> getTParkStatisticsbyPage(TParkStatistics tParkStatistics, Integer page, Integer limit);

    /**
    * 查询tParkStatistics信息
    * @param tParkStatistics
    * @return
    */
    List<TParkStatistics> getTParkStatistics(TParkStatistics tParkStatistics);

    /**
    * 更新tParkStatistics信息
    * @param tParkStatistics
    * @return
    */
    String UpdateTParkStatistics(TParkStatistics tParkStatistics);

    /**
    * 删除tParkStatistics信息
    * @param tParkStatistics
    * @return
    */
    String DeleteTParkStatistics(TParkStatistics tParkStatistics);

    /**
    * 根据ID查询tParkStatistics信息
    * @param id
    * @return
    */
    TParkStatistics getTParkStatisticsByID(Integer id);

    TParkStatistics getTParkStatisticsbyOne(TParkStatistics tParkStatistics);

}