package com.eparkingdb.service;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TParkDuty;

import java.util.List;

public interface TParkDutyService {
    List<TParkDuty> getTParkDuty(TParkDuty tParkDuty, String beginTime, String endTime);
    PageBean<TParkDuty> getTParkDutyListbyPage(TParkDuty tParkDuty, String beginTime, String endTime, Integer page, Integer limit);
    String UpdateTParkDuty(TParkDuty tParkDuty);
    String DeleteTParkDuty(TParkDuty tParkDuty);
}
