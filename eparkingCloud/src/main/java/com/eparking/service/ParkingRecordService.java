package com.eparking.service;

import com.common.entity.eparkingCloud.TParkInOut;
import com.common.entity.PageBean;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public interface ParkingRecordService {
    PageBean<TParkInOut>  getParkinoutbyPage(TParkInOut tParkInOut, String outTimeBegin, String outTimeEnd, Integer page, Integer limit);
    PageBean<TParkInOut> getParkinoutDetailbyPage(TParkInOut tParkInOut, String parkId, Integer page, Integer limit);
    PageBean<TParkInOut> getInCarbyPage(TParkInOut tParkInOut, Integer page, Integer limit);
    //String findSumByColumntype(String columntype,String chargeType,String sqlbytime);
    List<TParkInOut> getParkInOut(TParkInOut tParkInOut, String outTimeBegin, String outTimeEnd);
    Map selectNum(String parkid);
    void exportList(TParkInOut tParkInOut, String title, HttpServletResponse response);
    void exportPayFeeRecord(TParkInOut tParkInOut, String outTimeBegin, String outTimeEnd, String title, HttpServletResponse response);
    String advanceNotice(Integer parkId, String inCarPlate, String cloudOrderId, Double needPay, Double actualPay, Integer ePayType);
    String updateParkInOut(TParkInOut tParkInOut);
    List<TParkInOut> getTParkInOutForStatistics(TParkInOut tParkInOut, String outTimeBegin, String outTimeEnd);
    PageBean<TParkInOut> getParkinoutUnlimitByPage(TParkInOut tParkInOut, Integer page, Integer limit);
}
