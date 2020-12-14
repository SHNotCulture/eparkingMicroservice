package com.eparkingdb.service;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TElectronicTicket;

import java.util.List;

/**
* @Description: TElectronicTicketService接口
* @author 谢轩然
* @date 2020/04/09 15:01
*/
public interface TElectronicTicketService {
    /**
    *查询(分页)tElectronicTicket
    * @param tElectronicTicket
    * @param page
    * @param limit
    * @return
    */
    PageBean<TElectronicTicket> getTElectronicTicketbyPage(TElectronicTicket tElectronicTicket, Integer page, Integer limit);

    /**
    * 查询tElectronicTicket
    * @param tElectronicTicket
    * @return
    */
    List<TElectronicTicket> getTElectronicTicket(TElectronicTicket tElectronicTicket);

    /**
    * 更新tElectronicTicket
    * @param tElectronicTicket
    * @return
    */
    String UpdateTElectronicTicket(TElectronicTicket tElectronicTicket);

    /**
    * 删除tElectronicTicket
    * @param tElectronicTicket
    * @return
    */
    String DeleteTElectronicTicket(TElectronicTicket tElectronicTicket);

    /**
    * 根据ID查询tElectronicTicket
    * @param id
    * @return
    */
    TElectronicTicket getTElectronicTicketByID(Integer id);

    PageBean<TElectronicTicket> geteTicketListbyPageAndIds(List<Integer> ids, Integer page, Integer limit);

    TElectronicTicket getElectronicTicketByPrimaryKey(Integer id);

    List<TElectronicTicket> FindElectronicTicketByIds(List<Integer> ids);
}