package com.eparking.service;

import com.common.entity.eparkingCloud.TCompanyPark;
import com.common.entity.eparkingCloud.ZtreeRsp;
import com.common.entity.PageBean;

import java.util.List;

/**
 * @Author lishuhan
 * @Description:
 * @Date Create in 14:482018-9-14
 * @Modified By:
 */
public interface CarParkService {
    PageBean<TCompanyPark> getCarParkbyPage(TCompanyPark tCompanyPark, Integer page, Integer limit);
    List<TCompanyPark> getCarParkbyIDIn(String ids);
    List<TCompanyPark> getCarPark(TCompanyPark tCompanyPark);
    List<ZtreeRsp> getCarParkZtree(TCompanyPark tCompanyParkSelect);
    String UpdateCarPark(TCompanyPark tCompanyPark);
    String DeleteCarPark(Integer id);
    TCompanyPark getCarParkbyId(TCompanyPark tCompanyPark);
}
