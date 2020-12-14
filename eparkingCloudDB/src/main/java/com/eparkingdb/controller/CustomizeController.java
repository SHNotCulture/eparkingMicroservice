package com.eparkingdb.controller;

import com.common.entity.eparkingCloud.*;
import com.common.annotation.HttpLog;
import com.eparkingdb.service.CustomizeService;
import com.common.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/customize")
public class CustomizeController {

    private static final Logger logger = LoggerFactory.getLogger(CustomizeController.class);

    @Autowired
    private CustomizeService customizeService;

    @PostMapping(value = "/insetParktable")
    @HttpLog(operationType = "1", modularTypeName = "月租车下发任务")
    public void insetParktable(@RequestParam("parkId") Integer parkId, @RequestParam("parkCarId") Integer parkCarId, @RequestBody ParkCarTask parkCarTask, @RequestParam("computerIndex") String computerIndex) {
        customizeService.insetParktable(parkId, parkCarId, parkCarTask, computerIndex);
    }

    @PostMapping(value = "/insertExcel")
    @HttpLog(operationType = "1", modularTypeName = "Excel导入文件批量插入数据库")
    public Integer insertExcel(@RequestParam("tableName") String tableName, @RequestParam("column") String column, @RequestParam("insertSql") String insertSql) {
        return customizeService.insertExcel(tableName, column, insertSql);
    }

    @PostMapping(value = "/getMaturityNo")
    @HttpLog(operationType = "0", modularTypeName = "查询到期数量")
    public Integer getMaturityNo(@RequestParam("parkid") String parkid) {
        return customizeService.getMaturityNo(parkid);
    }

    @PostMapping(value = "/getCarNum")
    @HttpLog(operationType = "0", modularTypeName = "查询框体中的数量")
    public TCustomize getCarNum(@RequestParam("parkid") String parkid) {
        return customizeService.getCarNum(parkid);
    }

    @PostMapping(value = "/selectReportSum")
    @HttpLog(operationType = "0", modularTypeName = "日报对查询的时间范围进行统计")
    public String selectReportSum(@RequestParam("parkId") Integer parkId, @RequestParam("beginTime") String beginTime, @RequestParam("endTime") String endTime, @RequestParam("tabelName") String tabelName) {
        return customizeService.selectReportSum(parkId, beginTime, endTime, tabelName);
    }

    @PostMapping(value = "/paymentForDay")
    @HttpLog(operationType = "1", modularTypeName = "每日从日报表取数据至电子对账表")
    public Integer paymentForDay() {
        return customizeService.paymentForDay();
    }

    @PostMapping(value = "/selectPresentCar")
    @HttpLog(operationType = "0", modularTypeName = "查询此车场在场的 临时车")
    public List<TParkInOut> selectPresentCar(@RequestParam("parkId") String parkId, @RequestParam("sql") String sql) {
        return customizeService.selectPresentCar(parkId, sql);
    }

    @PostMapping(value = "/selectPresentCarByCarplate")
    @HttpLog(operationType = "0", modularTypeName = "搜索在场记录（根据车牌）")
    public TParkInOut selectPresentCarByCarplate(@RequestParam("parkId") String parkId, @RequestParam("carplate") String carplate) {
        return customizeService.selectPresentCarByCarplate(parkId, carplate);
    }

    @PostMapping(value = "/selectPresentCarLikeCarplate")
    @HttpLog(operationType = "0", modularTypeName = "模糊查询车牌")
    public List<String> selectPresentCarLikeCarplate(@RequestParam("parkId") Integer parkId, @RequestParam("carplate") String carplate) {
        return customizeService.selectPresentCarLikeCarplate(parkId, carplate);
    }

    @PostMapping(value = "/selectNum")
    @HttpLog(operationType = "0", modularTypeName = "查询停车记录")
    public String selectNum(@RequestParam("parkId") String parkId) {
        String aa = JsonUtil.mapToJson(customizeService.selectNum(parkId));
        return aa;
    }

    @PostMapping(value = "/getCouponPayMonth")
    @HttpLog(operationType = "0", modularTypeName = "getCouponPayMonth")
    public List<Map> getCouponPayMonth(@RequestBody TBusinesCoupon tBusinesCoupon, @RequestParam("year") String year) {
        return customizeService.getCouponPayMonth(tBusinesCoupon, year);
    }

    @PostMapping(value = "/getCouponPayDay")
    @HttpLog(operationType = "0", modularTypeName = "getCouponPayDay")
    public List<Map> getCouponPayDay(@RequestBody TBusinesCoupon tBusinesCoupon, @RequestParam("year") String year, @RequestParam("month") String month) {
        return customizeService.getCouponPayDay(tBusinesCoupon, year, month);
    }

    @PostMapping(value = "/getCouponSumPay")
    @HttpLog(operationType = "0", modularTypeName = "getCouponSumPay")
    public Double getCouponSumPay(@RequestBody TBusinesCoupon tBusinesCoupon) {
        return customizeService.getCouponSumPay(tBusinesCoupon);
    }

    @PostMapping(value = "/getCouponNeedPay")
    @HttpLog(operationType = "0", modularTypeName = "getCouponNeedPay")
    public Double getCouponNeedPay(@RequestBody TBusinesCoupon tBusinesCoupon) {
        return customizeService.getCouponNeedPay(tBusinesCoupon);
    }

    @PostMapping(value = "/selectMainNum")
    @HttpLog(operationType = "0", modularTypeName = "selectMainNum")
    public TCustomize selectMainNum(@RequestParam("indexParkId") String indexParkId) {
        return customizeService.selectMainNum(indexParkId);
    }

    @PostMapping(value = "/selectHistogram")
    @HttpLog(operationType = "0", modularTypeName = "selectHistogram")
    public String selectHistogram(@RequestParam("parkid") String parkid, @RequestParam("year") String year, @RequestParam("type") String type) {
        return customizeService.selectHistogram(parkid, year, type);
    }

    @PostMapping(value = "/selectCompanyPark")
    @HttpLog(operationType = "0", modularTypeName = "selectCompanyPark")
    public String selectCompanyPark(@RequestParam("indexParkId") String indexParkId) {
        return customizeService.selectCompanyPark(indexParkId);
    }

    @PostMapping(value = "/selectSevenDays")
    @HttpLog(operationType = "0", modularTypeName = "selectSevenDays")
    public String selectSevenDays(@RequestParam("parkid") String parkid) {
        return customizeService.selectSevenDays(parkid);
    }

    @PostMapping(value = "/selectTotalNum")
    @HttpLog(operationType = "0", modularTypeName = "selectTotalNum")
    public Integer selectTotalNum(@RequestParam("parkid") String parkid) {
        return customizeService.selectTotalNum(parkid);
    }

    @PostMapping(value = "/selectPayByType")
    @HttpLog(operationType = "0", modularTypeName = "selectPayByType")
    public String selectPayByType(@RequestParam("parkid") String parkid) {
        return customizeService.selectPayByType(parkid);
    }

    @PostMapping(value = "/selectePayType")
    @HttpLog(operationType = "0", modularTypeName = "selectePayType")
    public String selectePayType(@RequestParam("parkid") String parkid) {
        return customizeService.selectePayType(parkid);
    }

    @PostMapping(value = "/selectCarNum")
    @HttpLog(operationType = "0", modularTypeName = "selectCarNum")
    public String selectCarNum(@RequestParam("parkid") String parkid) {
        return customizeService.selectCarNum(parkid);
    }

    @PostMapping(value = "/selectMainAllNum")
    @HttpLog(operationType = "0", modularTypeName = "selectMainAllNum")
    public String selectMainAllNum(@RequestParam("parkid") String parkid) {
        return customizeService.selectMainAllNum(parkid);
    }

    @PostMapping(value = "/updateCompanyPark")
    @HttpLog(operationType = "1", modularTypeName = "updateCompanyPark")
    public Integer updateCompanyPark(@RequestParam("indexParkId") String indexParkId,@RequestParam("column") String column,@RequestParam("msg") Integer msg) {
        return customizeService.updateCompanyPark(indexParkId,column,msg);
    }
    @PostMapping(value = "/getTParkCarByCarplate")
    @HttpLog(operationType = "0", modularTypeName = "根据车牌查询该车牌已存在")
    public List<TParkCar> getTParkCarByCarplate(@RequestParam("parkId") Integer parkId, @RequestParam("carplate") String carplate) {
        return customizeService.getTParkCarByCarplate(parkId,carplate);
    }

    @PostMapping(value = "/getTBlacklistByCarplate")
    @HttpLog(operationType = "0", modularTypeName = "黑名单新增根据车牌查询该车牌已存在")
    public List<TBlacklist> getTBlacklistByCarplate(@RequestParam("parkId") Integer parkId, @RequestParam("carplate") String carplate) {
        return customizeService.getTBlacklistByCarplate(parkId,carplate);
    }
    @PostMapping(value = "/insetCouponble")
    @HttpLog(operationType = "0", modularTypeName = "insetCouponble")
    public Integer insetCouponble(@RequestBody  Shop shop,@RequestParam("parkId") Integer parkId,  @RequestParam("computerIndex")String computerIndex) {
        return customizeService.insetCouponble(parkId,shop,computerIndex);
    }
    @PostMapping(value = "/selectCouponble")
    @HttpLog(operationType = "0", modularTypeName = "根据车牌查询该车牌已存在")
    public  List<Map<String,String>> selectCouponble(@RequestParam("parkId") Integer parkId) {
        return customizeService.selectCouponble(parkId);
    }

    @PostMapping(value = "/DeleteCouponble")
    @HttpLog(operationType = "0", modularTypeName = "黑名单新增根据车牌查询该车牌已存在")
    public Integer DeleteCouponble(@RequestParam("parkId") Integer parkId, @RequestParam("taskId")String taskId) {
        return customizeService.DeleteCouponble(parkId,taskId);
    }

}
