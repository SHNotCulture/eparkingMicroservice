package com.eparkingdb.controller;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TParkInOutToday;
import com.common.annotation.HttpLog;
import com.eparkingdb.service.TParkInOutTodayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 徐名佳
 * @Description: TParkInOutTodayController类
 * @date 2020/12/04 15:23
 */
@RestController
@RequestMapping("/tParkInOutToday")
public class TParkInOutTodayController {

    private static final Logger logger = LoggerFactory.getLogger(TParkInOutTodayController.class);

    @Autowired
    private TParkInOutTodayService tParkInOutTodayService;

    /**
     * 查询TParkInOutToday信息
     *
     * @return
     * @paramtParkInOutToday
     */
    @PostMapping(value = "/getTParkInOutToday")
    @HttpLog(operationType = "0", modularTypeName = "查询TParkInOutToday")
    public List<TParkInOutToday> getTParkInOutToday(@RequestBody TParkInOutToday tParkInOutToday, HttpServletRequest request) {
        return tParkInOutTodayService.getTParkInOutToday(tParkInOutToday);
    }

    /**
     * 查询TParkInOutToday信息byId
     *
     * @param id
     * @return
     */
    @PostMapping(value = "/getTParkInOutTodaybyId")
    @HttpLog(operationType = "0", modularTypeName = "查询TParkInOutTodaybyId")
    public TParkInOutToday getTParkInOutTodaybyId(@RequestParam Integer id, HttpServletRequest request) {
        return tParkInOutTodayService.getTParkInOutTodaybyId(id);
    }

    /**
     * 查询TParkInOutToday信息(分页)
     *
     * @return
     * @paramtParkInOutToday
     */
    @PostMapping(value = "/getTParkInOutTodaybyPage")
    @HttpLog(operationType = "0", modularTypeName = "查询TParkInOutToday(分页)")
    public PageBean<TParkInOutToday> getTParkInOutTodaybyPage(@RequestBody TParkInOutToday tParkInOutToday, HttpServletRequest request, Integer page, Integer limit) {
        return tParkInOutTodayService.getTParkInOutTodaybyPage(tParkInOutToday, page, limit);
    }

    /**
     * 更新TParkInOutToday信息
     *
     * @return
     * @paramtParkInOutToday
     */
    @PostMapping(value = "/updateTParkInOutToday")
    @HttpLog(operationType = "0", modularTypeName = "更新TParkInOutToday信息")
    public String updateTParkInOutToday(@RequestBody TParkInOutToday tParkInOutToday, HttpServletRequest request) {
        return tParkInOutTodayService.updateTParkInOutToday(tParkInOutToday);
    }

    /**
     * 删除TParkInOutToday信息
     *
     * @param tParkInOutToday
     * @return
     */
    @PostMapping(value = "/deleteTParkInOutToday")
    @HttpLog(operationType = "1", modularTypeName = "删除TParkInOutToday信息")
    public String DeleteTParkInOutToday(@RequestBody TParkInOutToday tParkInOutToday) {
        return tParkInOutTodayService.DeleteTParkInOutToday(tParkInOutToday);
    }
}