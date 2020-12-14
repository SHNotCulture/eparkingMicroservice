package com.eparkingdb.service;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TTruckSpace;

import java.util.List;

/**
* @Description: TTruckSpaceService接口
* @author 谢轩然
* @date 2020/05/15 11:22
*/
public interface TTruckSpaceService {
    /**
    *查询(分页)tTruckSpace
    * @param tTruckSpace
    * @param page
    * @param limit
    * @return
    */
    PageBean<TTruckSpace> getTTruckSpacebyPage(TTruckSpace tTruckSpace, Integer page, Integer limit);

    /**
    * 查询tTruckSpace
    * @param tTruckSpace
    * @return
    */
    List<TTruckSpace> getTTruckSpace(TTruckSpace tTruckSpace);

    /**
    * 更新tTruckSpace
    * @param tTruckSpace
    * @return
    */
    String UpdateTTruckSpace(TTruckSpace tTruckSpace);

    /**
    * 删除tTruckSpace
    * @param tTruckSpace
    * @return
    */
    String DeleteTTruckSpace(TTruckSpace tTruckSpace);

    /**
    * 根据ID查询tTruckSpace
    * @param id
    * @return
    */
    TTruckSpace getTTruckSpaceByID(Integer id);

}