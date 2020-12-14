package com.eparkingdb.service;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TBusineTicket;

import java.util.List;

/**
* @Description: TBusineTicketService接口
* @author 谢轩然
* @date 2020/04/09 14:36
*/
public interface TBusineTicketService {
    /**
    *查询(分页)tBusineTicket
    * @param tBusineTicket
    * @param page
    * @param limit
    * @return
    */
    PageBean<TBusineTicket> getTBusineTicketbyPage(TBusineTicket tBusineTicket, Integer parkId, Integer page, Integer limit);

    /**
    * 查询tBusineTicket
    * @param tBusineTicket
    * @return
    */
    List<TBusineTicket> getTBusineTicket(TBusineTicket tBusineTicket, List<Integer> busineIds);

    /**
    * 更新tBusineTicket
    * @param tBusineTicket
    * @return
    */
    String UpdateTBusineTicket(TBusineTicket tBusineTicket);

    /**
    * 删除tBusineTicket
    * @param tBusineTicket
    * @return
    */
    String DeleteTBusineTicket(TBusineTicket tBusineTicket);

    /**
    * 根据ID查询tBusineTicket
    * @param id
    * @return
    */
    TBusineTicket getTBusineTicketByID(Integer id);

    List<Integer> getIdsByUsable(TBusineTicket tBusineTicket);
    /*根据电子券id查询最接近过期的电子券批次号*/
    Integer getTBusineTicketExpireSoon(Integer busineId, Integer ticketId);
    /*授权电子券*/
    String eleAuthorization(TBusineTicket tBusineTicket);
    /*回购电子券*/
    String buyBackBusineTicket(TBusineTicket tBusineTicket);
    /*购买电子券*/
    String eleBuy(TBusineTicket tBusineTicket, Integer ticketNumNew);
    /*修改电子劵批次权限*/
    String changeIsPay(TBusineTicket tBusineTicket);
}