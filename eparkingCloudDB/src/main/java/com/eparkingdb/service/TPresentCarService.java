package com.eparkingdb.service;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TParkInOut;

import java.util.List;

public interface TPresentCarService {
    PageBean<TParkInOut> getTPresentCarbyPage(TParkInOut tParkInOut, String parkId, String timeType, Integer page, Integer limit);
    List<TParkInOut> getTPresentCar(TParkInOut tParkInOut, String timeType, String parkId);
}
