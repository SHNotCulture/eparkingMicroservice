package com.eparking.service;

import com.common.entity.eparkingCloud.YuncsisResult;

import java.util.Map;

public interface YuncsisApiService {
    YuncsisResult updateblackcar(Map map);
    YuncsisResult reservedcar(Map map);
    YuncsisResult vircarplate(Map map);
    YuncsisResult prepay(Map map);
    YuncsisResult coupon(Map map);
    YuncsisResult updatemember(Map map);
    YuncsisResult updatemembercar(Map map);
    YuncsisResult calfee(Map map);
    YuncsisResult updateLaneinfo(Map map);
    YuncsisResult updateParkParams(Map map, Integer flag);
    YuncsisResult updateTempchargerules(Map map);
}
