package com.eparkingdb.controller;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.THoliday;
import com.common.annotation.HttpLog;
import com.eparkingdb.service.THolidayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tHoliday")
public class THolidayController {
    private static final Logger logger = LoggerFactory.getLogger(THolidayController.class);

    @Autowired
    private THolidayService tHolidayService;

    /**
     * 查询THoliday信息
     *
     * @param tHoliday
     * @return
     */
    @PostMapping(value = "/getTHoliday")
    @HttpLog(operationType = "0", modularTypeName = "查询THoliday")
    public List<THoliday> getTHoliday(@RequestBody THoliday tHoliday) {
        return tHolidayService.getTHoliday(tHoliday);
    }

    /**
     * 查询THoliday信息(分页)
     *
     * @param tHoliday
     * @return
     */
    @PostMapping(value = "/getTHolidaybyPage")
    @HttpLog(operationType = "0", modularTypeName = "查询THoliday(分页)")
    public PageBean<THoliday> getTHolidaybyPage(@RequestBody THoliday tHoliday, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit) {
        return tHolidayService.getTHolidaybyPage(tHoliday, page, limit);
    }

    /**
     * 更新THoliday信息
     *
     * @param tHoliday
     * @return
     */
    @PostMapping(value = "/updateTHoliday")
    @HttpLog(operationType = "1", modularTypeName = "更新THoliday信息")
    public String UpdateTHoliday(@RequestBody THoliday tHoliday) {
        return tHolidayService.UpdateTHoliday(tHoliday);
    }

    /**
     * 删除THoliday信息
     *
     * @param tHoliday
     * @return
     */
    @PostMapping(value = "/deleteTHoliday")
    @HttpLog(operationType = "1", modularTypeName = "删除THoliday信息")
    public String DeleteTHoliday(@RequestBody THoliday tHoliday) {
        return tHolidayService.DeleteTHoliday(tHoliday);
    }
}
