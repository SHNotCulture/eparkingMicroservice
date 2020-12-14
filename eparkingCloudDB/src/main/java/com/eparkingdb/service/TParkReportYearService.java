package com.eparkingdb.service;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TParkReportYear;

import java.util.List;

/**
* @Description: TParkReportYearService接口
* @author 谢轩然
* @date 2020/08/04 10:38
*/
public interface TParkReportYearService {
    /**
    *查询(分页)tParkReportYear
    * @param tParkReportYear
    * @param page
    * @param limit
    * @return
    */
    PageBean<TParkReportYear> getTParkReportYearbyPage(TParkReportYear tParkReportYear, String beginTime, String endTime, Integer page, Integer limit);

    /**
    * 查询tParkReportYear
    * @param tParkReportYear
    * @return
    */
    List<TParkReportYear> getTParkReportYear(TParkReportYear tParkReportYear, String beginTime, String endTime);

    /**
    * 更新tParkReportYear
    * @param tParkReportYear
    * @return
    */
    String UpdateTParkReportYear(TParkReportYear tParkReportYear);

    /**
    * 删除tParkReportYear
    * @param tParkReportYear
    * @return
    */
    String DeleteTParkReportYear(TParkReportYear tParkReportYear);

    /**
    * 根据ID查询tParkReportYear
    * @param id
    * @return
    */
    TParkReportYear getTParkReportYearByID(Integer id);

}