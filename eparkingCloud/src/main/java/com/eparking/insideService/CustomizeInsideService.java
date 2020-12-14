package com.eparking.insideService;

import com.common.entity.eparkingCloud.*;
import com.eparking.insideService.impl.CustomizeInsideServiceImpl;
import com.common.util.Common;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(value = "eparkingCloudData", fallback = CustomizeInsideServiceImpl.class)
public interface CustomizeInsideService {
    String client = Common.Feign_eparkingCloudData;

    @PostMapping(value = client + "/customize/insetParktable")
    void insetParktable(@RequestParam("parkId") Integer parkId, @RequestParam("parkCarId") Integer parkCarId, @RequestBody ParkCarTask parkCarTask, @RequestParam("computerIndex") String computerIndex);

    @PostMapping(value = client + "/customize/insertExcel")
    Integer insertExcel(@RequestParam("tableName") String tableName, @RequestParam("column") String column, @RequestParam("insertSql") String insertSql);

    @PostMapping(value = client + "/customize/getMaturityNo")
    Integer getMaturityNo(@RequestParam("parkid") String parkid);

    @PostMapping(value = client + "/customize/getCarNum")
    TCustomize getCarNum(@RequestParam("parkid") String parkid);

    @PostMapping(value = client + "/customize/selectReportSum")
    String selectReportSum(@RequestParam("parkId") Integer parkId, @RequestParam("beginTime") String beginTime, @RequestParam("endTime") String endTime, @RequestParam("tabelName") String tabelName);

    @PostMapping(value = client + "/customize/paymentForDay")
    Integer paymentForDay();

    @PostMapping(value = client + "/customize/selectPresentCar")
    List<TParkInOut> selectPresentCar(@RequestParam("parkId") String parkId, @RequestParam("sql") String sql);

    @PostMapping(value = client + "/customize/selectPresentCarByCarplate")
    TParkInOut selectPresentCarByCarplate(@RequestParam("parkId") String parkId, @RequestParam("carplate") String carplate);

    @PostMapping(value = client + "/customize/selectPresentCarLikeCarplate")
    List<String> selectPresentCarLikeCarplate(@RequestParam("parkId") Integer parkId, @RequestParam("carplate") String carplate);

    @PostMapping(value = client + "/customize/selectNum")
    String selectNum(@RequestParam("parkId") String parkId);

    @PostMapping(value = client + "/customize/getCouponPayMonth")
    List<Map> getCouponPayMonth(@RequestBody TBusinesCoupon tBusinesCoupon, @RequestParam("year") String year);

    @PostMapping(value = client + "/customize/getCouponPayDay")
    List<Map> getCouponPayDay(@RequestBody TBusinesCoupon tBusinesCoupon, @RequestParam("year") String year, @RequestParam("month") String month);

    @PostMapping(value = client + "/customize/getCouponSumPay")
    Double getCouponSumPay(@RequestBody TBusinesCoupon tBusinesCoupon);

    @PostMapping(value = client + "/customize/getCouponNeedPay")
    Double getCouponNeedPay(@RequestBody TBusinesCoupon tBusinesCoupon);

    @PostMapping(value = client + "/customize/selectMainNum")
    TCustomize selectMainNum(@RequestParam("indexParkId") String indexParkId);

    @PostMapping(value = client + "/customize/selectHistogram")
    String selectHistogram(@RequestParam("parkid") String parkid, @RequestParam("year") String year, @RequestParam("type") String type);

    @PostMapping(value = client + "/customize/selectCompanyPark")
    String selectCompanyPark(@RequestParam("indexParkId") String indexParkId);

    @PostMapping(value = client + "/customize/selectSevenDays")
    String selectSevenDays(@RequestParam("parkid") String parkid);

    @PostMapping(value = client + "/customize/selectTotalNum")
    Integer selectTotalNum(@RequestParam("parkid") String parkid);

    @PostMapping(value = client + "/customize/selectPayByType")
    String selectPayByType(@RequestParam("parkid") String parkid);

    @PostMapping(value = client + "/customize/selectePayType")
    String selectePayType(@RequestParam("parkid") String parkid);

    @PostMapping(value = client + "/customize/selectCarNum")
    String selectCarNum(@RequestParam("parkid") String parkid);

    @PostMapping(value = client + "/customize/selectMainAllNum")
    String selectMainAllNum(@RequestParam("parkid") String parkid);

    @PostMapping(value = client + "/customize/updateCompanyPark")
    Integer updateCompanyPark(@RequestParam("indexParkId") String indexParkId, @RequestParam("column") String column, @RequestParam("msg") Integer msg);

    @PostMapping(value = client + "/customize/getTBusineTicketExpireSoon")
    Integer getTBusineTicketExpireSoon(@RequestParam("busineId") Integer busineId, @RequestParam("ticketId") Integer ticketId);

    @PostMapping(value = client+"/customize/getTParkCarByCarplate")
    List<TParkCar> getTParkCarByCarplate(@RequestParam("parkId") Integer parkId, @RequestParam("carplate") String carplate);

    @PostMapping(value = client+"/customize/getTBlacklistByCarplate")
    List<TBlacklist> getTBlacklistByCarplate(@RequestParam("parkId") Integer parkId, @RequestParam("carplate") String carplate);
}
