package com.eparkingdb.service;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TParkInOut;

import java.util.List;

public interface TParkingRecordService {
    PageBean<TParkInOut>  getParkinoutbyPage(TParkInOut tParkInOut, String outTimeBegin, String outTimeEnd, Integer page, Integer limit);
    PageBean<TParkInOut> getParkinoutDetailbyPage(TParkInOut tParkInOut, String parkId, Integer page, Integer limit);
    PageBean<TParkInOut> getInCarbyPage(TParkInOut tParkInOut, Integer page, Integer limit);
    //String findSumByColumntype(String columntype,String chargeType,String sqlbytime);
    List<TParkInOut> getParkinout(TParkInOut tParkInOut, String outTimeBegin, String outTimeEnd);

//    void exportList(TParkInOut tParkInOut, String title, HttpServletResponse response);
    String UpdateTParkInOut(TParkInOut tParkInOut);
}
