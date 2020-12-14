package com.eparkingdb.service;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TParkInOutToday;

import java.util.List;

/**
 * @author 徐名佳
 * @Description: TParkInOutTodayInsideService接口
 * @date 2020/12/04 15:23
 */
public interface TParkInOutTodayService {

    /**
     * 查询tParkInOutToday信息
     *
     * @param tParkInOutToday
     * @return
     */
    List<TParkInOutToday> getTParkInOutToday(TParkInOutToday tParkInOutToday);

    /**
     * 查询TParkInOutToday信息byId
     *
     * @return
     */
    TParkInOutToday getTParkInOutTodaybyId(Integer id);

    /**
     * 查询tParkInOutToday信息(分页)
     *
     * @param tParkInOutToday
     * @param page
     * @param limit
     * @return
     */
    PageBean<TParkInOutToday> getTParkInOutTodaybyPage(TParkInOutToday tParkInOutToday, Integer page, Integer limit);

    /**
     * 更新tParkInOutToday信息
     *
     * @param tParkInOutToday
     * @return
     */
    String updateTParkInOutToday(TParkInOutToday tParkInOutToday);

    /**
     * 删除tParkInOutToday信息
     *
     * @param tParkInOutToday
     * @return
     */
    String DeleteTParkInOutToday(TParkInOutToday tParkInOutToday);
}