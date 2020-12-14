package com.eparking.service;

import com.common.entity.eparkingCloud.LayuiMenu;
import com.common.entity.eparkingCloud.TModuleListNew;
import com.common.entity.eparkingCloud.ZtreeRsp;

import java.util.List;

/**
 * @Author lishuhan
 * @Description:
 * @Date Create in 18:282018-9-14
 * @Modified By:
 */
public interface ModuleListService {
    List<TModuleListNew> getModuleList(TModuleListNew tModuleListNew);
    List<ZtreeRsp> getModuleListForZtree(TModuleListNew tModuleListNew);
    List<LayuiMenu> getModuleListNewForLayuiMenu(List<Integer> tModuleListNew);
}
