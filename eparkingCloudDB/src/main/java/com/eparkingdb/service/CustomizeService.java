package com.eparkingdb.service;

import com.common.entity.eparkingCloud.*;

import java.util.List;
import java.util.Map;

public interface CustomizeService {
    void insetParktable(Integer parkId, Integer parkCarId, ParkCarTask parkCarTask, String computerIndex);

    Integer insertExcel(String tableName, String column, String insertSql);

    Integer getMaturityNo(String parkid);

    TCustomize getCarNum(String parkid);

    String selectReportSum(Integer parkId, String beginTime, String endTime, String tabelName);

    Integer paymentForDay();

    List<TParkInOut> selectPresentCar(String parkId, String sql);

    TParkInOut selectPresentCarByCarplate(String parkId, String carplate);

    List<String> selectPresentCarLikeCarplate(Integer parkId, String carplate);

    Map selectNum(String parkid);

    List<Map> getCouponPayMonth(TBusinesCoupon tBusinesCoupon, String year);

    List<Map> getCouponPayDay(TBusinesCoupon tBusinesCoupon, String year, String month);

    Double getCouponSumPay(TBusinesCoupon tBusinesCoupon);

    Double getCouponNeedPay(TBusinesCoupon tBusinesCoupon);

    TCustomize selectMainNum(String indexParkId);

    String selectHistogram(String parkid, String year, String type);

    String selectCompanyPark(String indexParkId);

    String selectSevenDays(String parkid);

    Integer selectTotalNum(String parkid);

    String selectPayByType(String parkid);

    String selectePayType(String parkid);

    String selectCarNum(String parkid);

    String selectMainAllNum(String parkid);

    Integer updateCompanyPark(String indexParkId, String column, Integer msg);

    Integer getTBusineTicketExpireSoon(Integer busineId, Integer ticketId);

    Integer getTBusineTicketExpireSoonAPI(BusineCouponGetTicketInfo busineCouponGetTicketInfo);

    List<TParkCar> getTParkCarByCarplate(Integer parkId, String carplate);

    List<TBlacklist> getTBlacklistByCarplate(Integer parkId, String carplate);

    List<Map> chargeReportStatistics(Integer parkId, String beginDate, String endDate);

    List<Map> getDutyStatistics(Integer parkId, String dutyPerson, String beginTime, String endTime);

    Integer getDutyStatisticsNum(Integer parkId, String dutyPerson, String beginTime, String endTime);

    Integer insetCouponble(Integer parkId, Shop shop, String computerIndex);

    List<Map<String,String>> selectCouponble(Integer parkId);

    Integer DeleteCouponble(Integer parkId, String taskId);

}
