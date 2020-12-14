package com.eparkingdb.controller;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TMonthBindingCar;
import com.common.annotation.HttpLog;
import com.eparkingdb.service.TMonthBindingCarService;
import com.common.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author 谢轩然
 * @Description: TMonthBindingCarController类
 * @date 2020/05/26 17:04
 */
@RestController
@RequestMapping("/tMonthBindingCar")
public class TMonthBindingCarController {

    private static final Logger logger = LoggerFactory.getLogger(TMonthBindingCarController.class);

    @Autowired
    private TMonthBindingCarService tMonthBindingCarService;

    /**
     * 查询TMonthBindingCar信息
     *
     * @return
     * @paramtMonthBindingCar
     */
    @PostMapping(value = "/getTMonthBindingCar")
    @HttpLog(operationType = "0", modularTypeName = "查询TMonthBindingCar")
    public List<TMonthBindingCar> getTMonthBindingCar(@RequestBody TMonthBindingCar tMonthBindingCar) {
        return tMonthBindingCarService.getTMonthBindingCar(tMonthBindingCar);
    }

    /**
     * 查询TMonthBindingCar信息(分页)
     *
     * @return
     * @paramtMonthBindingCar
     */
    @PostMapping(value = "/getTMonthBindingCarbyPage")
    @HttpLog(operationType = "0", modularTypeName = "查询TMonthBindingCar(分页)")
    public PageBean<TMonthBindingCar> getTMonthBindingCarbyPage(@RequestBody TMonthBindingCar tMonthBindingCar, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit) {
        return tMonthBindingCarService.getTMonthBindingCarbyPage(tMonthBindingCar, page, limit);
    }

    /**
     * 更新TMonthBindingCar信息
     *
     * @return
     * @paramtMonthBindingCar
     */
    @PostMapping(value = "/updateTMonthBindingCar")
    @HttpLog(operationType = "1", modularTypeName = "更新TMonthBindingCar信息")
    public String UpdateTMonthBindingCar(@RequestBody TMonthBindingCar tMonthBindingCar) {
        return tMonthBindingCarService.UpdateTMonthBindingCar(tMonthBindingCar);
    }

    /**
     * 删除TMonthBindingCar信息
     *
     * @param tMonthBindingCar
     * @return
     */
    @PostMapping(value = "/deleteTMonthBindingCar")
    @HttpLog(operationType = "1", modularTypeName = "删除TMonthBindingCar信息")
    public String DeleteTMonthBindingCar(@RequestBody TMonthBindingCar tMonthBindingCar) {
        return tMonthBindingCarService.DeleteTMonthBindingCar(tMonthBindingCar);
    }

    /**
     * 根据ID查tMonthBindingCar信息
     *
     * @param id
     * @return
     */
    @PostMapping(value = "getTMonthBindingCarById")
    @HttpLog(operationType = "0", modularTypeName = "根据ID查tMonthBindingCar信息")
    public TMonthBindingCar getTMonthBindingCarById(@RequestParam("id") Integer id) {
        return tMonthBindingCarService.getTMonthBindingCarByID(id);
    }
}