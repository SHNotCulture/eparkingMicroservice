package com.eparking.service;

import com.common.entity.eparkingCloud.TParkPort;
import com.common.entity.PageBean;

import java.util.List;

public interface ParkPortService {
    PageBean<TParkPort> getParkPortbyPage(TParkPort tParkPort, Integer page, Integer limit);
    List<TParkPort> getParkPort(TParkPort tParkPort);
    String updateParkPort(TParkPort tParkPort);
    String deleteParkPort(TParkPort tParkPort);
}
