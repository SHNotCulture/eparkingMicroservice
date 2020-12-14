package com.eparkingdb.service;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.THoliday;

import java.util.List;

public interface THolidayService {
    /**
     * 查询(分页)tHoliday
     *
     * @param tHoliday
     * @param page
     * @param limit
     * @return
     */
    PageBean<THoliday> getTHolidaybyPage(THoliday tHoliday, Integer page, Integer limit);

    /**
     * 查询tHoliday
     *
     * @param tHoliday
     * @return
     */
    List<THoliday> getTHoliday(THoliday tHoliday);

    /**
     * 更新tHoliday
     *
     * @param tHoliday
     * @return
     */
    String UpdateTHoliday(THoliday tHoliday);

    /**
     * 删除tHoliday
     *
     * @param tHoliday
     * @return
     */
    String DeleteTHoliday(THoliday tHoliday);

}
