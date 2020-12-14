package com.eparking.service;

import com.common.entity.eparkingCloud.TDicOuttype;
import com.common.entity.eparkingCloud.TDicPrepayType;
import com.common.entity.eparkingCloud.TParkPort;
import com.common.entity.eparkingCloud.ZtreeRsp;

import java.util.List;

public interface BaseDicService {
    List<TParkPort> selectParkPort(String portType, Integer parkId);
    List<TDicOuttype> selectOutType();
    List<TDicPrepayType> selectgetPayType();
    List<ZtreeRsp> getPortNameListForZtree(String portType);
    List<ZtreeRsp> getPortNameListForZtreeByParkId(String parkId);
}
