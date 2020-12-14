package com.eparkingdb.service;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TParkDuty;

import java.util.List;

public interface CashPaymentService {
    /**
     *查询(分页)tParkDuty
     * @param tParkDuty
     * @param page
     * @param limit
     * @return
     */
    PageBean<TParkDuty> getTParkDutybyPage(TParkDuty tParkDuty, String beginTime, String endTime, Integer page, Integer limit);

    /**
     * 查询tParkDuty
     * @param tParkDuty
     * @return
     */
    List<TParkDuty> getTParkDuty(TParkDuty tParkDuty, String beginTime, String endTime);

    /**
     * 更新tParkDuty
     * @param tParkDuty
     * @return
     */
    String UpdateTParkDuty(TParkDuty tParkDuty);

    /**
     * 删除tParkDuty
     * @param tParkDuty
     * @return
     */
    String DeleteTParkDuty(TParkDuty tParkDuty);
}
