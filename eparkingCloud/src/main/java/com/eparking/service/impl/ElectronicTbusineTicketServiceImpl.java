package com.eparking.service.impl;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TBusineTicket;
import com.common.entity.eparkingCloud.TCompanyUser;
import com.eparking.enums.ActionRspEnum;
import com.eparking.exception.ActionRspException;
import com.eparking.insideService.CustomizeInsideService;
import com.eparking.insideService.TBusineTicketInsideService;
import com.eparking.service.BusineService;
import com.eparking.service.ElectronicTbusineTicketService;
import com.eparking.service.ElectronicTicketService;
import com.common.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @Author jin
 * @Data 2019/4/28 15:47
 **/
@Service
public class ElectronicTbusineTicketServiceImpl implements ElectronicTbusineTicketService {
    @Autowired
    private TBusineTicketInsideService tBusineTicketInsideService;
    @Autowired
    private ElectronicTicketService electronicTicketService;
    @Autowired
    private BusineService busineService;
    @Autowired
    private CustomizeInsideService customizeInsideService;

    @Override
    public PageBean<TBusineTicket> getTBusineTicketByPage(TBusineTicket tBusineTicket, Integer parkId, Integer page, Integer limit) {
/*        TBusine tBusine = new TBusine();
        tBusine.setParkId(SessionUtil.getParkId());
        List<TBusine> tBusineList = busineService.getBusine(tBusine);
        List<Integer> busineIds = new ArrayList<>();
        for (TBusine tBusineTraversing : tBusineList){
            busineIds.add(tBusineTraversing.getId());
        }
        PageHelper.startPage(page, limit,"expiry_date asc");
        List<TBusineTicket> tBusineTicketList=getTBusineTicket(tBusineTicket,busineIds);
        Integer countNums =getCount(tBusineTicket,busineIds);
        PageBean<TBusineTicket> pageData = new PageBean<>(page, limit, countNums);
        pageData.setItems(tBusineTicketList);
        return pageData;*/
        return tBusineTicketInsideService.getTBusineTicketbyPage(tBusineTicket, parkId, page, limit);
    }

    @Override
    public List<Integer> getIdsByUsable(TBusineTicket tBusineTicket) {
        tBusineTicket.setTicketNum(0);
        if (tBusineTicket.getExpiryDate()==null){
            tBusineTicket.setExpiryDate(DateUtil.getCurDateTime());
        }
//        List<TBusineTicket> tBusineTickets = tBusineTicketMapper.selectByExample(getTBusineTicketCriteria(tBusineTicket,null));
        List<TBusineTicket> tBusineTickets = tBusineTicketInsideService.getTBusineTicket(tBusineTicket,"");
        List<Integer> integerList = new ArrayList<>();
        for (TBusineTicket busineTicket : tBusineTickets){
            integerList.add(busineTicket.getTicketId());
        }
        HashSet h = new HashSet(integerList);
        integerList.clear();
        integerList.addAll(h);
        return integerList;
    }

    @Override
    public List<TBusineTicket> getTBusineTicket(TBusineTicket tBusineTicket) {
        tBusineTicket.setTicketNum(0);
        if (tBusineTicket.getExpiryDate()==null){
            tBusineTicket.setExpiryDate(DateUtil.getCurDateTime());
        }
        List<TBusineTicket> tBusineTickets = tBusineTicketInsideService.getTBusineTicket(tBusineTicket,"");

        return tBusineTickets;
    }


    @Override
    public Integer getTBusineTicketExpireSoon(Integer busineId, Integer ticketId) {
//        return customizeMapper.getTBusineTicketExpireSoon(busineId,ticketId);
        return customizeInsideService.getTBusineTicketExpireSoon(busineId, ticketId);
    }

    @Override
    public String eleAuthorization(TBusineTicket tBusineTicket) {
        String msg = "授权成功";
        tBusineTicket.setUpdateTime(DateUtil.getCurDateTime());
        tBusineTicket.setTicketInitialNum(tBusineTicket.getTicketNum());
        if (tBusineTicket.getId()!=null){
            //修改
        }else {
            //新增
            tBusineTicket.setTicketInitialNum(0);
            //TBusine tBusine = busineService.selectByPrimaryKey(tBusineTicket.getBusineId());
            //TCompanyPark tCompanyPark=tCompanyParkMapper.selectByPrimaryKey(tBusine.getParkId());
           /* double balance = tBusine.getBalance();
            double needPay = electronicTicketService.getElectronicTicketByPrimaryKey(tBusineTicket.getTicketId()).getTicketPay()*tBusineTicket.getTicketNum();*/
            //if (balance>=needPay){
//                Integer i =tBusineTicketMapper.insertSelective(tBusineTicket);
            tBusineTicketInsideService.UpdateTBusineTicket(tBusineTicket);
                /*if (i==0){
                    msg = "购买失败";
                }*/
                /*else {
                    tBusine.setBalance(balance-needPay);
                    busineService.UpdateBusine(tBusine);
                    //添加充值记录（购买记录）
                    TBusinePay tBusinePay=new TBusinePay();
                    tBusinePay.setBusineId(tBusine.getId());
                    tBusinePay.setParkId(tBusine.getParkId());
                    tBusinePay.setCompanyId(tBusine.getCompanyId());
                    tBusinePay.setBusineName(tBusine.getBusineName());
                    tBusinePay.setNeedPay(needPay*(-1));
                    tBusinePay.setActualPay(needPay*(-1));
                    tBusinePay.setOrderNumber(StringUtil.RandomOrder());
                    tBusinePay.setPayTime(DateUtil.getCurDateTime());
                    tBusinePay.setBalance(tBusine.getBalance());
                    tBusinePay.setParkName(tCompanyPark.getParkName());
                    tBusinePay.setRemark("购买电子券");
                    tBusinePayMapper.insertSelective(tBusinePay);
                }*/
            /*}else {
                throw new ActionRspException(ActionRspEnum.BusineInsufficientBalance_ERROR);
            }*/


        }
        return msg;
    }

    @Override
    public String buyBackBusineTicket(TBusineTicket tBusineTicket) {
/*        if (tBusineTicket.getId()!=null){
            Integer ticketNum=(Integer)tBusineTicket.getTicketNum();
            if (ticketNum!=0){
                tBusineTicket.setUpdateTime(DateUtil.getCurDateTime());
                tBusineTicketMapper.updateByPrimaryKeySelective(tBusineTicket);
            }else {
                tBusineTicketMapper.deleteByPrimaryKey(tBusineTicket.getId());
            }
            msg="电子券使用记录更新成功";
        }else
        {msg="电子券使用记录更新失败";}*/

        return tBusineTicketInsideService.buyBackBusineTicket(tBusineTicket);
    }

    @Override
    public String eleBuy(TBusineTicket tBusineTicket,Integer ticketNumNew) {
        String msg="";
//        System.out.println(tBusineTicket.toString());
        TBusineTicket busineTicket = getTBusineTicketByID(tBusineTicket.getId());
/*        String msg;
        TElectronicTicket tElectronicTicket = electronicTicketService.getElectronicTicketByPrimaryKey(tBusineTicket.getTicketId());
        tBusineTicket = tBusineTicketMapper.selectByPrimaryKey(tBusineTicket.getId());
        double needpay = tElectronicTicket.getTicketPay()*ticketNumNew;
        TBusine tBusine = busineService.selectByPrimaryKey(tBusineTicket.getBusineId());
        double balance = tBusine.getBalance();
        if (balance>=needpay){
            tBusineTicket.setTicketNum(tBusineTicket.getTicketNum()+ticketNumNew);
            tBusineTicketMapper.updateByPrimaryKeySelective(tBusineTicket);
            TCompanyPark tCompanyPark=tCompanyParkMapper.selectByPrimaryKey(tBusine.getParkId());
            tBusine.setBalance(balance-needpay);
            busineService.UpdateBusine(tBusine);
            //添加充值记录（购买记录）
            TBusinePay tBusinePay=new TBusinePay();
            tBusinePay.setBusineId(tBusine.getId());
            tBusinePay.setParkId(tBusine.getParkId());
            tBusinePay.setCompanyId(tBusine.getCompanyId());
            tBusinePay.setBusineName(tBusine.getBusineName());
            tBusinePay.setNeedPay(needpay*(-1));
            tBusinePay.setActualPay(needpay*(-1));
            tBusinePay.setOrderNumber(StringUtil.RandomOrder());
            tBusinePay.setPayTime(DateUtil.getCurDateTime());
            tBusinePay.setBalance(tBusine.getBalance());
            tBusinePay.setParkName(tCompanyPark.getParkName());
            tBusinePay.setRemark("购买电子券");
            tBusinePayMapper.insertSelective(tBusinePay);
            msg = "购买成功";
        }else {
            throw new ActionRspException(ActionRspEnum.BusineInsufficientBalance_ERROR);
        }
        return msg;*/
        //判断是否为商户。商户执行自购权限限制判断
        TCompanyUser tCompanyUser = SessionUtil.getUser();
        if(tCompanyUser.getEntityType()==2){
            if(busineTicket.getIsPay()==0){
                msg="无自购权限";
            }else{
                msg = tBusineTicketInsideService.eleBuy(busineTicket, ticketNumNew);
                if(msg.equals("商户余额不足")){
                    throw new ActionRspException(ActionRspEnum.BusineInsufficientBalance_ERROR);
                }else if(msg.equals("此电子券已删除")){
                    throw new ActionRspException(ActionRspEnum.TicketExist_ERROR);
                }
            }
        }else{
            msg = tBusineTicketInsideService.eleBuy(busineTicket, ticketNumNew);
            if(msg.equals("商户余额不足")){
                throw new ActionRspException(ActionRspEnum.BusineInsufficientBalance_ERROR);
            }else if(msg.equals("此电子券已删除")){
                throw new ActionRspException(ActionRspEnum.TicketExist_ERROR);
            }
        }
        return msg;
    }

    @Override
    public String changeIsPay(TBusineTicket tBusineTicket) {
        return tBusineTicketInsideService.changeIsPay(tBusineTicket);
/*        tBusineTicketMapper.updateByPrimaryKeySelective(tBusineTicket);
        return "修改成功";*/
    }

    @Override
    public TBusineTicket getTBusineTicketByID(Integer id) {
        return tBusineTicketInsideService.getTBusineTicketByID(id);
    }

/*    @Override
    public TBusineTicket getIeparkingdbsTBusineTicket(Integer id){
        return tBusineTicketMapper.selectByPrimaryKey(id);
    }*/

/*    public Integer getCount(TBusineTicket tBusineTicket,List<Integer> busineIds){
        return (int)tBusineTicketMapper.countByExample(getTBusineTicketCriteria(tBusineTicket,busineIds));
    }*/
    public List<TBusineTicket> getTBusineTicket(TBusineTicket tBusineTicket,List<Integer> busineIds){
//        return tBusineTicketMapper.selectByExample(getTBusineTicketCriteria(tBusineTicket,busineIds));
        return tBusineTicketInsideService.getTBusineTicket(tBusineTicket, JsonUtil.listToJson(busineIds));
    }
/*    public TBusineTicketCriteria getTBusineTicketCriteria(TBusineTicket tBusineTicket,List<Integer> busineIds){
        TBusineTicketCriteria tBusineTicketCriteria = new TBusineTicketCriteria();
        TBusineTicketCriteria.Criteria criteria = tBusineTicketCriteria.createCriteria();
        criteria.andExpiryDateGreaterThanOrEqualTo(DateUtil.getCurDateTime());
        if (tBusineTicket.getBusineId()!=null){
                criteria.aneparkingdbusineIdEqualTo(tBusineTicket.getBusineId());
        }if (tBusineTicket.getTicketId()!=null){
            criteria.andTicketIdEqualTo(tBusineTicket.getTicketId());
        }if (tBusineTicket.getTicketNum()!=null){
            criteria.andTicketNumGreaterThan(0);
        }if (tBusineTicket.getExpiryDate()!=null){
            criteria.andExpiryDateGreaterThanOrEqualTo(tBusineTicket.getExpiryDate());
        }if (tBusineTicket.getTicketName()!=null){
            criteria.andTicketNameLike("%"+tBusineTicket.getTicketName()+"%");
        }if (busineIds!=null){
            criteria.aneparkingdbusineIdIn(busineIds);
        }if (tBusineTicket.getIsPay()!=null){
            criteria.andIsPayEqualTo(tBusineTicket.getIsPay());
        }
        return tBusineTicketCriteria;
    }*/

}
