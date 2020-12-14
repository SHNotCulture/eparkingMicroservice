package com.eparking.service;

import com.common.entity.eparkingCloud.THoliday;
import com.common.entity.PageBean;

import java.util.List;

/**
 * @Author lishuhan
 * @Description:
 * @Date Create in 16:522018-11-6
 * @Modified By:
 */
public interface HolidayService {
    PageBean<THoliday> getHolidaybyPage(THoliday tHoliday, Integer page, Integer limit);
    List<THoliday> getHoliday(THoliday tHoliday);
    String UpdateHoliday(THoliday tHoliday);
    String DeleteHoliday(Integer id);

}
