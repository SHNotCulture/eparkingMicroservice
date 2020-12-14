package com.eparking.insideService.impl;

import com.common.entity.eparkingCloud.*;
import com.eparking.insideService.CustomizeInsideService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class CustomizeInsideServiceImpl implements CustomizeInsideService {
    private static final Logger logger = LoggerFactory.getLogger(CustomizeInsideServiceImpl.class);

    @Override
    public void insetParktable(Integer parkId, Integer parkCarId, ParkCarTask parkCarTask, String computerIndex) {
        logger.info("月租车下发任务失败！");
    }

    @Override
    public Integer insertExcel(String tableName, String column, String insertSql) {
        String str = "Excel导入文件批量插入数据库失败！";
        logger.info("Excel导入文件批量插入数据库失败！");
        return null;
    }

    @Override
    public Integer getMaturityNo(String parkid) {
        String str = "Excel导入文件批量插入数据库失败！";
        logger.info("Excel导入文件批量插入数据库失败！");
        return null;
    }

    @Override
    public TCustomize getCarNum(String parkid) {
        String str = "查询框体中的数量失败！";
        logger.info("查询框体中的数量失败！");
        return null;
    }

    @Override
    public String selectReportSum(Integer parkId, String beginTime, String endTime, String tabelName) {
        String str = "日报对查询的时间范围进行统计失败！";
        logger.info("日报对查询的时间范围进行统计失败！");
        return str;
    }

    @Override
    public Integer paymentForDay() {
        String str = "每日从日报表取数据至电子对账表失败！";
        logger.info("每日从日报表取数据至电子对账表失败！");
        return null;
    }

    @Override
    public List<TParkInOut> selectPresentCar(String parkId, String sql) {
        String str = "查询此车场在场的 临时车失败！";
        logger.info("查询此车场在场的 临时车失败！");
        return null;
    }

    @Override
    public TParkInOut selectPresentCarByCarplate(String parkId, String carplate) {
        String str = "搜索在场记录（根据车牌）失败！";
        logger.info("搜索在场记录（根据车牌）失败！");
        return null;
    }

    @Override
    public List<String> selectPresentCarLikeCarplate(Integer parkId, String carplate) {
        String str = "模糊查询车牌失败！";
        logger.info("模糊查询车牌失败！");
        return null;
    }

    @Override
    public String selectNum(String parkId) {
        String str = "查询停车记录失败！";
        logger.info("查询停车记录失败！");
        return str;
    }

    @Override
    public List<Map> getCouponPayMonth(TBusinesCoupon tBusinesCoupon, String year) {
        String str = "getCouponPayMonth失败！";
        logger.info("getCouponPayMonth失败！");
        return null;
    }

    @Override
    public List<Map> getCouponPayDay(TBusinesCoupon tBusinesCoupon, String year, String month) {
        String str = "getCouponPayDay失败！";
        logger.info("getCouponPayDay失败！");
        return null;
    }

    @Override
    public Double getCouponSumPay(TBusinesCoupon tBusinesCoupon) {
        String str = "getCouponSumPay失败！";
        logger.info("getCouponSumPay失败！");
        return null;
    }

    @Override
    public Double getCouponNeedPay(TBusinesCoupon tBusinesCoupon) {
        String str = "getCouponNeedPay失败！";
        logger.info("getCouponNeedPay失败！");
        return null;
    }

    @Override
    public TCustomize selectMainNum(String indexParkId) {
        String str = "selectMainNum失败！";
        logger.info("selectMainNum失败！");
        return null;
    }

    @Override
    public String selectHistogram(String parkid, String year, String type) {
        String str = "selectHistogram失败！";
        logger.info("selectHistogram失败！");
        return null;
    }

    @Override
    public String selectCompanyPark(String indexParkId) {
        String str = "selectCompanyPark失败！";
        logger.info("selectCompanyPark失败！");
        return str;
    }

    @Override
    public String selectSevenDays(String parkid) {
        String str = "selectSevenDays失败！";
        logger.info("selectSevenDays失败！");
        return str;
    }

    @Override
    public Integer selectTotalNum(String parkid) {
        String str = "selectTotalNum失败！";
        logger.info("selectTotalNum失败！");
        return null;
    }

    @Override
    public String selectPayByType(String parkid) {
        String str = "selectPayByType失败！";
        logger.info("selectPayByType失败！");
        return str;
    }

    @Override
    public String selectePayType(String parkid) {
        String str = "selectePayType失败！";
        logger.info("selectePayType失败！");
        return str;
    }

    @Override
    public String selectCarNum(String parkid) {
        String str = "selectCarNum失败！";
        logger.info("selectCarNum失败！");
        return str;
    }

    @Override
    public String selectMainAllNum(String parkid) {
        String str = "selectMainAllNum失败！";
        logger.info("selectMainAllNum失败！");
        return str;
    }

    @Override
    public Integer updateCompanyPark(String indexParkId, String column, Integer msg) {
        String str = "updateCompanyPark失败！";
        logger.info("updateCompanyPark失败！");
        return null;
    }

    @Override
    public Integer getTBusineTicketExpireSoon(Integer busineId, Integer ticketId) {
        String str = "getTBusineTicketExpireSoon失败！";
        logger.info("getTBusineTicketExpireSoon失败！");
        return null;
    }

    @Override
    public List<TParkCar> getTParkCarByCarplate(Integer parkId, String carplate) {
        String str = "getTParkCarByCarplate失败！";
        logger.info("getTParkCarByCarplate失败！");
        return null;
    }

    @Override
    public List<TBlacklist> getTBlacklistByCarplate(Integer parkId, String carplate) {
        String str = "getTBlacklistByCarplate失败！";
        logger.info("getTBlacklistByCarplate失败！");
        return null;
    }
}
