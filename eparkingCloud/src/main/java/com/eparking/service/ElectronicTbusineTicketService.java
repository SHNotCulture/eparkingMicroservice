package com.eparking.service;

import com.common.entity.eparkingCloud.TBusineTicket;
import com.common.entity.PageBean;

import java.util.List;

public interface ElectronicTbusineTicketService {
    PageBean<TBusineTicket> getTBusineTicketByPage(TBusineTicket tBusineTicket, Integer parkId, Integer page, Integer limit);
    /*查询商户拥有可用的电子券种类*/
    List<Integer> getIdsByUsable(TBusineTicket tBusineTicket);
    /*查询商户拥有可用的电子券*/
    List<TBusineTicket> getTBusineTicket(TBusineTicket tBusineTicket);
    /*根据电子券id查询对应的电子券信息*/
//    TBusineTicket getIeparkingdbsTBusineTicket(Integer id);
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
    //根据id查询电子券
    TBusineTicket getTBusineTicketByID(Integer id);
}