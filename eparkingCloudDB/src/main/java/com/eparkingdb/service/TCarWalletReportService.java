package com.eparkingdb.service;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TCarWalletReport;

import java.util.List;

/**
* @Description: TCarWalletReportService接口
* @author 李书瀚
* @date 2020/10/26 23:19
*/
public interface TCarWalletReportService {
    /**
    *查询tCarWalletReport信息(分页)
    * @param tCarWalletReport
    * @param page
    * @param limit
    * @return
    */
    PageBean<TCarWalletReport> getTCarWalletReportbyPage(TCarWalletReport tCarWalletReport, String beginTime, String endTime, Integer page, Integer limit);

    /**
    * 查询tCarWalletReport信息
    * @param tCarWalletReport
    * @return
    */
    List<TCarWalletReport> getTCarWalletReport(TCarWalletReport tCarWalletReport, String beginTime, String endTime);

    /**
    * 更新tCarWalletReport信息
    * @param tCarWalletReport
    * @return
    */
    String UpdateTCarWalletReport(TCarWalletReport tCarWalletReport);

    /**
    * 删除tCarWalletReport信息
    * @param tCarWalletReport
    * @return
    */
    String DeleteTCarWalletReport(TCarWalletReport tCarWalletReport);

    /**
    * 根据ID查询tCarWalletReport信息
    * @param id
    * @return
    */
    TCarWalletReport getTCarWalletReportByID(Integer id);

}