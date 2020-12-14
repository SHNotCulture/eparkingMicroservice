package com.eparkingdb.service;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TDayOff;

import java.util.List;

public interface TDayOffService {
    PageBean<TDayOff> getTDayOffbyPage(TDayOff tDayOff, Integer page, Integer limit);
    List<TDayOff> getTDayOff(TDayOff tDayOff);
    String UpdateTDayOff(TDayOff tDayOff);
    String DeleteTDayoff(TDayOff tDayOff);
}
