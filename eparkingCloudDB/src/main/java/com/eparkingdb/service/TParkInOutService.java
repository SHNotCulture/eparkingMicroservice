package com.eparkingdb.service;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TParkInOut;

import java.util.List;

public interface TParkInOutService {
    List<TParkInOut> getTParkInOutUnlimit(TParkInOut tParkInOut);
    PageBean<TParkInOut> getTParkinoutUnlimitByPage(TParkInOut tParkInOut, Integer page, Integer limit);
    String UpdateTParkInOut(TParkInOut tParkInOut);
    TParkInOut getTParkInOutbyorderId(String orderId, Integer parkId);
    List<TParkInOut> getTParkInOutForStatistics(TParkInOut tParkInOut, String outTimeBegin, String outTimeEnd);
}
