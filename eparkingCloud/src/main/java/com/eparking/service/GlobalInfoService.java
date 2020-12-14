package com.eparking.service;

import com.common.entity.eparkingCloud.GlobalInfo;
import com.common.entity.eparkingCloud.TGlobalInfo;

public interface GlobalInfoService {
    GlobalInfo getGlobalInfo(TGlobalInfo tGlobalInfo);
    String updateGlobalInfo(GlobalInfo globalInfo);
}
