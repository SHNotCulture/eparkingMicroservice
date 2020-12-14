package com.eparkingdb.controller;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TParkCar;
import com.common.annotation.HttpLog;
import com.eparkingdb.service.TParkCarService;
import com.common.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author 谢轩然
 * @Description: TParkCarController类
 * @date 2020/05/14 15:43
 */
@RestController
@RequestMapping("/tParkCar")
public class TParkCarController {

    private static final Logger logger = LoggerFactory.getLogger(TParkCarController.class);

    @Autowired
    private TParkCarService tParkCarService;

    /**
     * 查询TParkCar信息
     *
     * @return
     * @paramtParkCar
     */
    @PostMapping(value = "/getTParkCar")
    @HttpLog(operationType = "0", modularTypeName = "查询TParkCar")
    public List<TParkCar> getTParkCar(@RequestBody TParkCar tParkCar,@RequestParam("endDateSelectStart") String endDateSelectStart,@RequestParam("endDateSelectEnd") String endDateSelectEnd) {
        return tParkCarService.getTParkCar(tParkCar,endDateSelectStart,endDateSelectEnd);
    }

    @PostMapping(value = "/getOwnerCar")
    @HttpLog(operationType = "0", modularTypeName = "查询OwnerCar")
    public List<TParkCar> getOwnerCar(@RequestBody TParkCar tParkCar,@RequestParam("endDateSelectStart") String endDateSelectStart,@RequestParam("endDateSelectEnd") String endDateSelectEnd) {
        return tParkCarService.getOwnerCar(tParkCar,endDateSelectStart,endDateSelectEnd);
    }

    /**
     * 查询TParkCar信息(分页)
     *
     * @return
     * @paramtParkCar
     */
    @PostMapping(value = "/getTParkCarbyPage")
    @HttpLog(operationType = "0", modularTypeName = "查询TParkCar(分页)")
    public PageBean<TParkCar> getTParkCarbyPage(@RequestBody TParkCar tParkCar, @RequestParam("endDateSelectStart") String endDateSelectStart, @RequestParam("endDateSelectEnd") String endDateSelectEnd, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit) {
        return tParkCarService.getTParkCarbyPage(tParkCar,endDateSelectStart,endDateSelectEnd, page, limit);
    }

    @PostMapping(value = "/getOwnnerCarbyPage")
    @HttpLog(operationType = "0", modularTypeName = "查询OwnerCar(分页)")
    public PageBean<TParkCar> getOwnnerCarbyPage(@RequestBody TParkCar tParkCar,@RequestParam("endDateSelectStart") String endDateSelectStart,@RequestParam("endDateSelectEnd") String endDateSelectEnd, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit) {
        return tParkCarService.getOwnerCarbyPage(tParkCar,endDateSelectStart,endDateSelectEnd, page, limit);
    }
    /**
     * 更新TParkCar信息
     *
     * @return
     * @paramtParkCar
     */
    @PostMapping(value = "/updateTParkCar")
    @HttpLog(operationType = "1", modularTypeName = "更新TParkCar信息")
    public String UpdateTParkCar(@RequestBody TParkCar tParkCar) {
        return tParkCarService.UpdateTParkCar(tParkCar);
    }

    /**
     * 删除TParkCar信息
     *
     * @param tParkCar
     * @return
     */
    @PostMapping(value = "/deleteTParkCar")
    @HttpLog(operationType = "1", modularTypeName = "删除TParkCar信息")
    public String DeleteTParkCar(@RequestBody TParkCar tParkCar) {
        return tParkCarService.DeleteTParkCar(tParkCar);
    }

    /**
     * 根据ID查TParkCar信息
     *
     * @param id
     * @return
     */
    @PostMapping(value = "/getTParkCarById")
    @HttpLog(operationType = "0", modularTypeName = "根据ID查TParkCar信息")
    public TParkCar getTParkCarById(@RequestParam("id") Integer id) {
        return tParkCarService.getTParkCarByID(id);
    }

    @PostMapping(value = "/getCloseParkCar")
    @HttpLog(operationType = "0", modularTypeName = "查询接近到期的月租车")
    public List<TParkCar> getCloseParkCar(@RequestParam("closeType") String closeType, @RequestParam("payRule") String payRule, @RequestParam("parkid") String parkid) {
        return tParkCarService.getCloseParkCar(closeType, payRule, parkid);
    }

    @PostMapping(value = "/getCloseParkCarbyPage")
    @HttpLog(operationType = "0", modularTypeName = "查询接近到期的月租车分页")
    public PageBean<TParkCar> getCloseParkCarbyPage(@RequestParam("closeType") String closeType, @RequestParam("payRule") String payRule, @RequestParam("parkid") String parkid, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit) {
        return tParkCarService.getCloseParkCarbyPage(closeType, payRule, parkid, page, limit);
    }

    @PostMapping(value = "/Resetport")
    @HttpLog(operationType = "1", modularTypeName = "重置通道权限")
    public String Resetport(@RequestParam("portids") String portids, @RequestParam("ids") String ids) {
        return tParkCarService.Resetport(portids, ids);
    }

    @PostMapping(value = "/insertTParkCarMap")
    @HttpLog(operationType = "1",modularTypeName = "插入TParkCar表")
    public Integer insertTParkCarMap(@RequestBody TParkCar tParkCar){
        return tParkCarService.insertTParkCarMap(tParkCar);
    }

    @PostMapping(value = "/whetherTParkCar")
    @HttpLog(operationType = "0", modularTypeName = "查询有效月租车")
    public List<TParkCar> whetherTParkCar(@RequestParam("parkId") Integer parkId,@RequestParam("carPlate") String carPlate,@RequestParam("endDateSelectStart") String endDateSelectStart,@RequestParam("endDateSelectEnd") String endDateSelectEnd) {
        return tParkCarService.whetherTParkCar(parkId, carPlate,endDateSelectStart,endDateSelectEnd);
    }

    @PostMapping(value = "/getPresentCarNum")
    @HttpLog(operationType = "0",modularTypeName = "getPresentCarNum")
    public Integer getPresentCarNum(@RequestParam("parkIDlist") String parkIDlist){
        return tParkCarService.getPresentCarNum(parkIDlist);
    }

    @PostMapping(value = "/getTParkCarForStatistics")
    @HttpLog(operationType = "0", modularTypeName = "财务报表统计登记车")
    public List<TParkCar> getTParkCarForStatistics(@RequestBody TParkCar tParkCar,@RequestParam("date") String date) {
        return tParkCarService.getTParkCarForStatistics(tParkCar,date);
    }

    @PostMapping(value = "/getTParkCarFuzzybyPage")
    @HttpLog(operationType = "0", modularTypeName = "模糊查询TParkCar(分页)")
    public PageBean<TParkCar> getTParkCarFuzzybyPage(@RequestBody TParkCar tParkCar,@RequestParam("parkCarIdList") String parkCarIdList,@RequestParam("endDateSelectStart") String endDateSelectStart,@RequestParam("endDateSelectEnd") String endDateSelectEnd, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit) {
        return tParkCarService.getTParkCarFuzzybyPage(tParkCar,parkCarIdList,endDateSelectStart,endDateSelectEnd, page, limit);
    }

    @PostMapping(value = "/fineparkingdbyCarPlate")
    @HttpLog(operationType = "0", modularTypeName = "根据车牌，车场id查询车辆")
    public TParkCar fineparkingdbyCarPlate(@RequestParam("inCarPlate") String inCarPlate,@RequestParam("parkId") Integer parkId){
        return tParkCarService.fineparkingdbyCarPlate(inCarPlate,parkId);
    }
}