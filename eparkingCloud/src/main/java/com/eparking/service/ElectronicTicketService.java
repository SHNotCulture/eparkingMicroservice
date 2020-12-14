package com.eparking.service;

import com.common.entity.eparkingCloud.TElectronicTicket;
import com.common.entity.PageBean;

import java.util.List;

public interface ElectronicTicketService {
    PageBean<TElectronicTicket> geteTicketListbyPage(TElectronicTicket tElectronicTicket, Integer page, Integer limit);
    String updateEticket(TElectronicTicket tElectronicTicket);
    String deleteEticket(TElectronicTicket tElectronicTicket);
    TElectronicTicket getElectronicTicketByPrimaryKey(Integer id);
    PageBean<TElectronicTicket> geteTicketListbyPageAndIds(String ids, Integer page, Integer limit);

    List<TElectronicTicket> FindElectronicTicketByIds(String ids);
}