package com.eparkingdb.service;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TParkCar;

import java.util.List;

/**
* @Description: TParkCarService接口
* @author 谢轩然
* @date 2020/05/14 15:43
*/
public interface TParkCarService {
    /**
    *查询(分页)tParkCar
    * @param tParkCar
    * @param page
    * @param limit
    * @return
    */
    PageBean<TParkCar> getTParkCarbyPage(TParkCar tParkCar, String endDateSelectStart, String endDateSelectEnd, Integer page, Integer limit);

    PageBean<TParkCar> getOwnerCarbyPage(TParkCar tParkCar, String endDateSelectStart, String endDateSelectEnd, Integer page, Integer limit);

    /**
    * 查询tParkCar
    * @param tParkCar
    * @return
    */
    List<TParkCar> getTParkCar(TParkCar tParkCar, String endDateSelectStart, String endDateSelectEnd);

    List<TParkCar> getOwnerCar(TParkCar tParkCar, String endDateSelectStart, String endDateSelectEnd);

    /**
    * 更新tParkCar
    * @param tParkCar
    * @return
    */
    String UpdateTParkCar(TParkCar tParkCar);

    /**
    * 删除tParkCar
    * @param tParkCar
    * @return
    */
    String DeleteTParkCar(TParkCar tParkCar);

    /**
    * 根据ID查询tParkCar
    * @param id
    * @return
    */
    TParkCar getTParkCarByID(Integer id);

    List<TParkCar> getCloseParkCar(String closeType, String payRule, String parkid);

    PageBean<TParkCar> getCloseParkCarbyPage(String closeType, String payRule, String parkid, Integer page, Integer limit);

    String Resetport(String portids, String ids);

    Integer insertTParkCarMap(TParkCar tParkCar);

    List<TParkCar> whetherTParkCar(Integer parkId, String carPlate, String endDateSelectStart, String endDateSelectEnd);

    Integer getPresentCarNum(String parkIDlist);

    List<TParkCar> getTParkCarForStatistics(TParkCar tParkCar, String date);

    PageBean<TParkCar> getTParkCarFuzzybyPage(TParkCar tParkCar, String parkCarIdList, String endDateSelectStart, String endDateSelectEnd, Integer page, Integer limit);

    TParkCar fineparkingdbyCarPlate(String inCarPlate, Integer parkId);
}