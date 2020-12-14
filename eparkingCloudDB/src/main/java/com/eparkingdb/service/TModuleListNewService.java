package com.eparkingdb.service;

import com.common.entity.eparkingCloud.TModuleListNew;

import java.util.List;

public interface TModuleListNewService {
    List<TModuleListNew> getModuleListNewForLayuiMenu(List<Integer> tModuleListNew);

    List<TModuleListNew> getModuleList(TModuleListNew tModuleListNew);

    List<TModuleListNew> getModuleListForZtree(TModuleListNew tModuleListNew);

    List<TModuleListNew> getModuleListNewForSecond(Integer pid, List<Integer> tModuleListNew);
}
