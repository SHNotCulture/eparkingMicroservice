package com.eparkingdb.service;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TParkReportMonth;

import java.util.List;

/**
* @Description: TParkReportMonthService接口
* @author 谢轩然
* @date 2020/08/03 16:21
*/
public interface TParkReportMonthService {
    /**
    *查询(分页)tParkReportMonth
    * @param tParkReportMonth
    * @param page
    * @param limit
    * @return
    */
    PageBean<TParkReportMonth> getTParkReportMonthbyPage(TParkReportMonth tParkReportMonth, String beginTime, String endTime, Integer page, Integer limit);

    /**
    * 查询tParkReportMonth
    * @param tParkReportMonth
    * @return
    */
    List<TParkReportMonth> getTParkReportMonth(TParkReportMonth tParkReportMonth, String beginTime, String endTime);

    /**
    * 更新tParkReportMonth
    * @param tParkReportMonth
    * @return
    */
    String UpdateTParkReportMonth(TParkReportMonth tParkReportMonth);

    /**
    * 删除tParkReportMonth
    * @param tParkReportMonth
    * @return
    */
    String DeleteTParkReportMonth(TParkReportMonth tParkReportMonth);

    /**
    * 根据ID查询tParkReportMonth
    * @param id
    * @return
    */
    TParkReportMonth getTParkReportMonthByID(Integer id);

}