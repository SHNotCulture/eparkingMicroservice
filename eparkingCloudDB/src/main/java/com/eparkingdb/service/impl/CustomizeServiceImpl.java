package com.eparkingdb.service.impl;

import com.common.entity.eparkingCloud.*;
import com.common.entity.eparkingCloud.BusineCouponGetTicketInfo;
import com.eparkingdb.dao.CustomizeMapper;
import com.eparkingdb.service.CustomizeService;
import com.common.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomizeServiceImpl implements CustomizeService {

    private static final Logger logger = LoggerFactory.getLogger(CustomizeServiceImpl.class);
    @Autowired
    private CustomizeMapper customizeMapper;

    @Override
    public void insetParktable(Integer parkId, Integer parkCarId, ParkCarTask parkCarTask, String computerIndex) {
        customizeMapper.insetParktable(parkId, parkCarId, parkCarTask, computerIndex);
    }

    @Override
    public Integer insertExcel(String tableName, String column, String insertSql) {
        return customizeMapper.insertExcel(tableName, column, insertSql);
    }

    @Override
    public Integer getMaturityNo(String parkid) {
        return customizeMapper.getMaturityNo(parkid);
    }

    @Override
    public TCustomize getCarNum(String parkid) {
        TCustomize tCustomize = customizeMapper.selectMonthlyNum(parkid);
        return tCustomize;
    }

    @Override
    public String selectReportSum(Integer parkId, String beginTime, String endTime, String tabelName) {
        return JsonUtil.mapToJson(customizeMapper.selectReportSum(parkId, beginTime, endTime, tabelName));
    }

    @Override
    public Integer paymentForDay() {
        return customizeMapper.paymentForDay();
    }

    @Override
    public List<TParkInOut> selectPresentCar(String parkId, String sql) {
        List<TParkInOut> list = customizeMapper.selectPresentCar(parkId, sql);
        return list;
    }

    @Override
    public TParkInOut selectPresentCarByCarplate(String parkId, String carplate) {
        TParkInOut tParkInOut = customizeMapper.selectPresentCarByCarplate(parkId, carplate);
        return tParkInOut;
    }

    @Override
    public List<String> selectPresentCarLikeCarplate(Integer parkId, String carplate) {
        List<String> carlist = customizeMapper.selectPresentCarLikeCarplate(parkId, carplate);
        return carlist;
    }

    @Override
    public Map selectNum(String parkid) {
        Map map = new HashMap();
        map = customizeMapper.selectParkingRecordNum(parkid);
        return map;
    }

    @Override
    public List<Map> getCouponPayMonth(TBusinesCoupon tBusinesCoupon, String year) {
        List<Map> tCouponPayMonth = customizeMapper.getCouponPayMonth(tBusinesCoupon, year);
        return tCouponPayMonth;
    }

    @Override
    public List<Map> getCouponPayDay(TBusinesCoupon tBusinesCoupon, String year, String month) {
        List<Map> tCouponPayDay = customizeMapper.getCouponPayDay(tBusinesCoupon, year, month);
        return tCouponPayDay;
    }

    @Override
    public Double getCouponSumPay(TBusinesCoupon tBusinesCoupon) {
        Double couponSumPay = customizeMapper.getCouponSumPay(tBusinesCoupon);
        return couponSumPay;
    }

    @Override
    public Double getCouponNeedPay(TBusinesCoupon tBusinesCoupon) {
        Double couponNeedPay = customizeMapper.getCouponNeedPay(tBusinesCoupon);
        return couponNeedPay;
    }

    @Override
    public TCustomize selectMainNum(String indexParkId) {
        TCustomize tCustomize = customizeMapper.selectMainNum(indexParkId);
        return tCustomize;
    }

    @Override
    public String selectHistogram(String parkid, String year, String type) {
        String aa = JsonUtil.listToJson(customizeMapper.selectHistogram(parkid, year, type));
        return aa;
    }

    @Override
    public String selectCompanyPark(String indexParkId) {
        return JsonUtil.mapToJson(customizeMapper.selectCompanyPark(indexParkId));
    }

    @Override
    public String selectSevenDays(String parkid) {
        List<Map> maps = customizeMapper.selectSevenDays(parkid);
        return JsonUtil.listToJson(maps);
    }

    @Override
    public Integer selectTotalNum(String parkid) {
        return customizeMapper.selectTotalNum(parkid);
    }

    @Override
    public String selectPayByType(String parkid) {
        return JsonUtil.mapToJson(customizeMapper.selectPayByType(parkid));
    }

    @Override
    public String selectePayType(String parkid) {
        return JsonUtil.mapToJson(customizeMapper.selectePayType(parkid));
    }

    @Override
    public String selectCarNum(String parkid) {
        return JsonUtil.mapToJson(customizeMapper.selectCarNum(parkid));
    }

    @Override
    public String selectMainAllNum(String parkid) {
        return JsonUtil.mapToJson(customizeMapper.selectMainAllNum(parkid));
    }

    @Override
    public Integer updateCompanyPark(String indexParkId, String column, Integer msg) {
        return customizeMapper.updateCompanyPark(indexParkId,column,msg);
    }

    @Override
    public Integer getTBusineTicketExpireSoon(Integer busineId, Integer ticketId) {
        return customizeMapper.getTBusineTicketExpireSoon(busineId,ticketId);
    }
    @Override
    public Integer getTBusineTicketExpireSoonAPI(BusineCouponGetTicketInfo busineCouponGetTicketInfo) {
        return customizeMapper.getTBusineTicketExpireSoonAPI(busineCouponGetTicketInfo);
    }

    @Override
    public List<TParkCar> getTParkCarByCarplate(Integer parkId, String carplate) {
        return customizeMapper.getTParkCarByCarplate(parkId, carplate);
    }

    @Override
    public List<TBlacklist> getTBlacklistByCarplate(Integer parkId, String carplate) {
        return customizeMapper.getTBlacklistByCarplate(parkId, carplate);
    }

    @Override
    public List<Map> chargeReportStatistics(Integer parkId,String beginDate,String endDate) {
        return customizeMapper.chargeReportStatistics(parkId,beginDate,endDate);
    }

    @Override
    public List<Map> getDutyStatistics(Integer parkId,String dutyPerson, String beginTime, String endTime) {
        return customizeMapper.getDutyStatistics(parkId,dutyPerson,beginTime,endTime);
    }

    @Override
    public Integer getDutyStatisticsNum(Integer parkId,String dutyPerson, String beginTime, String endTime) {
        return customizeMapper.getDutyStatisticsNum(parkId,dutyPerson,beginTime,endTime);
    }

    public Integer insetCouponble(Integer parkId, Shop shop, String computerIndex){
        return customizeMapper.insetCouponble(parkId,shop,computerIndex);
    }

    public  List<Map<String,String>> selectCouponble(Integer parkId){
        return customizeMapper.selectCouponble(parkId);
    }
    public Integer DeleteCouponble(Integer parkId, String taskId){
        return customizeMapper.DeleteCouponble(parkId,taskId);
    }

}
