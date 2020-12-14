package com.eparking.writeLock;

import com.common.entity.eparkingCloud.TBusine;

import java.util.List;

public interface MonthlyRechargeLock extends BaseLock<TBusine> {
    void setTheBusineRechage(TBusine tBusine);
    List<TBusine> getTheBusineRechage();
}
