package com.eparking.writeLock;

import com.common.entity.eparkingCloud.TCompanyPark;

import java.util.List;

public interface ParkReportLock extends BaseLock<TCompanyPark> {
    void setTParkReport(TCompanyPark tCompanyPark);
    List<TCompanyPark> getTParkReport();
}
