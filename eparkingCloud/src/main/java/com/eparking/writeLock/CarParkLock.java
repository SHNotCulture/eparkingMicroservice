package com.eparking.writeLock;

import com.common.entity.eparkingCloud.TCompanyPark;

import java.util.List;

public interface CarParkLock  extends BaseLock<TCompanyPark> {
    void setTCompanyPark(TCompanyPark tCompanyPark);
    List<TCompanyPark> getTCompanyPark();
}
