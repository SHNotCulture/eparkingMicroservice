package com.eparkingdb.service;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TParkPort;

import java.util.List;

/**
* @Description: TParkPortService接口
* @author 谢轩然
* @date 2020/05/29 15:05
*/
public interface TParkPortService {
    /**
    *查询(分页)tParkPort
    * @param tParkPort
    * @param page
    * @param limit
    * @return
    */
    PageBean<TParkPort> getTParkPortbyPage(TParkPort tParkPort, Integer page, Integer limit);

    /**
    * 查询tParkPort
    * @param tParkPort
    * @return
    */
    List<TParkPort> getTParkPort(TParkPort tParkPort);

    /**
    * 更新tParkPort
    * @param tParkPort
    * @return
    */
    String UpdateTParkPort(TParkPort tParkPort);

    /**
    * 删除tParkPort
    * @param tParkPort
    * @return
    */
    String DeleteTParkPort(TParkPort tParkPort);

    /**
    * 根据ID查询tParkPort
    * @param id
    * @return
    */
    TParkPort getTParkPortByID(Integer id);

    /**
     * 根据两字段查询
     * @param parkId
     * @param porId
     * @return
     */
    TParkPort getTParkPortByParkIdAndPortId(Integer parkId, Integer porId);

    List<TParkPort> getTParkPortOutPort(TParkPort tParkPort);

    List<TParkPort> getPortNameListForZtree(Integer parkId, String portType);
}