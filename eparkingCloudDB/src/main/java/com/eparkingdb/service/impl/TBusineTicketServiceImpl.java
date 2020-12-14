package com.eparkingdb.service.impl;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.*;
import com.eparkingdb.dao.CustomizeMapper;
import com.eparkingdb.dao.TBusinePayMapper;
import com.eparkingdb.dao.TBusineTicketMapper;
import com.eparkingdb.dao.TCompanyParkMapper;
import com.eparkingdb.service.TBusineService;
import com.eparkingdb.service.TBusineTicketService;
import com.eparkingdb.service.TElectronicTicketService;
import com.common.util.DateUtil;
import com.common.util.StringUtil;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author 谢轩然
 * @Description: TBusineTicketService接口实现类
 * @date 2020/04/09 14:36
 */
@Service
public class TBusineTicketServiceImpl implements TBusineTicketService {

    private static final Logger logger = LoggerFactory.getLogger(TBusineTicketServiceImpl.class);
    @Autowired
    private TBusineTicketMapper tBusineTicketMapper;
    @Autowired
    private TElectronicTicketService tElectronicTicketService;
    @Autowired
    private TBusineService tBusineService;
    @Autowired
    private TCompanyParkMapper tCompanyParkMapper;
    @Autowired
    private TBusinePayMapper tBusinePayMapper;
    @Autowired
    private CustomizeMapper customizeMapper;

    /**
     * 设置查询条件
     *
     * @param tBusineTicket
     * @return
     */
    private TBusineTicketCriteria setCriteria(TBusineTicket tBusineTicket, List<Integer> busineIds) {
        TBusineTicketCriteria tBusineTicketCriteria = new TBusineTicketCriteria();
        if (tBusineTicket != null) {
            TBusineTicketCriteria.Criteria criteria = tBusineTicketCriteria.createCriteria();
            TBusineTicketCriteria.Criteria criteria1 = tBusineTicketCriteria.or();

            if (tBusineTicket.getId() != null) {
                criteria.andIdEqualTo(tBusineTicket.getId());
            }
            if (tBusineTicket.getBusineId() != null) {
                criteria.aneparkingdbusineIdEqualTo(tBusineTicket.getBusineId());
                criteria1.aneparkingdbusineIdEqualTo(tBusineTicket.getBusineId());
            }
            if(tBusineTicket.getCompanyId()!=null){
                criteria.andCompanyIdEqualTo(tBusineTicket.getCompanyId());
                criteria1.andCompanyIdEqualTo(tBusineTicket.getCompanyId());
            }
            if (tBusineTicket.getTicketId() != null) {
                criteria.andTicketIdEqualTo(tBusineTicket.getTicketId());
                criteria1.andTicketIdEqualTo(tBusineTicket.getTicketId());
            }
            if (tBusineTicket.getTicketNum() != null) {
                criteria.andTicketNumGreaterThan(0);
                criteria1.andTicketNumGreaterThan(0);
            }
            if (tBusineTicket.getExpiryDate() != null) {
                criteria.andExpiryDateGreaterThanOrEqualTo(tBusineTicket.getExpiryDate());
                criteria1.andExpiryDateEqualTo("0");
            }
            if (tBusineTicket.getTicketName() != null) {
                criteria.andTicketNameLike("%" + tBusineTicket.getTicketName() + "%");
                criteria1.andTicketNameLike("%" + tBusineTicket.getTicketName() + "%");
            }
            if (busineIds != null && busineIds.size()>0) {
                criteria.aneparkingdbusineIdIn(busineIds);
                criteria1.aneparkingdbusineIdIn(busineIds);
            }
            if (tBusineTicket.getIsPay() != null) {
                criteria.andIsPayEqualTo(tBusineTicket.getIsPay());
                criteria1.andIsPayEqualTo(tBusineTicket.getIsPay());
            }
        }
        return tBusineTicketCriteria;
    }

    /**
     * 获取数据总量
     *
     * @param tBusineTicket
     * @return
     */
    private Integer getCount(TBusineTicket tBusineTicket, List<Integer> busineIds) {
        Integer total = (int) tBusineTicketMapper.countByExample(setCriteria(tBusineTicket, busineIds));
        return total;
    }

    /**
     * 查询tBusineTicket(分页)
     *
     * @param tBusineTicket
     * @param page
     * @param limit
     * @return
     */
    public PageBean<TBusineTicket> getTBusineTicketbyPage(TBusineTicket tBusineTicket, Integer parkId, Integer page, Integer limit) {
        TBusine tBusine = new TBusine();
        tBusine.setParkId(parkId);
        List<TBusine> tBusineList = tBusineService.getTBusine(tBusine);
        List<Integer> busineIds = new ArrayList<>();
        if(tBusineList.size()>0){
            for (TBusine tBusineTraversing : tBusineList) {
                busineIds.add(tBusineTraversing.getId());
            }
        }
        PageHelper.startPage(page, limit, "expiry_date desc");
        List<TBusineTicket> tBusineTickets = getTBusineTicket(tBusineTicket, busineIds);
        Integer countNums = getCount(tBusineTicket, busineIds);
        PageBean<TBusineTicket> pageData = new PageBean<>(page, limit, countNums);
        pageData.setItems(tBusineTickets);
        return pageData;
    }

    /**
     * 查询tBusineTicket
     *
     * @param tBusineTicket
     * @return
     */
    public List<TBusineTicket> getTBusineTicket(TBusineTicket tBusineTicket, List<Integer> busineIds) {
        List<TBusineTicket> tBusineTickets = tBusineTicketMapper.selectByExample(setCriteria(tBusineTicket, busineIds));
        return tBusineTickets;
    }

    /**
     * 更新tBusineTicket
     *
     * @param tBusineTicket
     * @return
     */
    public String UpdateTBusineTicket(TBusineTicket tBusineTicket) {
        String msg = "";
        try {
            if (tBusineTicket.getId() != null) {
                tBusineTicketMapper.updateByPrimaryKeySelective(tBusineTicket);
                msg = "更新TBusineTicket成功";
            } else {
                tBusineTicketMapper.insertSelective(tBusineTicket);
                msg = "新建TBusineTicket成功";
            }
        } catch (Exception e) {

        }
        return msg;
    }

    /**
     * 删除tBusineTicket
     *
     * @param tBusineTicket
     * @return
     */
    public String DeleteTBusineTicket(TBusineTicket tBusineTicket) {
        String msg = "";
        if (tBusineTicket.getId() != null) {
            tBusineTicketMapper.deleteByPrimaryKey(tBusineTicket.getId());
            msg = "删除TBusineTicket成功";
        }
        return msg;
    }

    /**
     * 根据ID查询tBusineTicket
     *
     * @param id
     * @return
     */
    public TBusineTicket getTBusineTicketByID(Integer id) {
        return tBusineTicketMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Integer> getIdsByUsable(TBusineTicket tBusineTicket) {
        tBusineTicket.setTicketNum(0);
        if (tBusineTicket.getExpiryDate() == null) {
            tBusineTicket.setExpiryDate(DateUtil.getCurDateTime());
        }
        List<TBusineTicket> tBusineTickets = tBusineTicketMapper.selectByExample(setCriteria(tBusineTicket, null));
        List<Integer> integerList = new ArrayList<>();
        for (TBusineTicket busineTicket : tBusineTickets) {
            integerList.add(busineTicket.getTicketId());
        }
        HashSet h = new HashSet(integerList);
        integerList.clear();
        integerList.addAll(h);
        return integerList;
    }

    @Override
    public Integer getTBusineTicketExpireSoon(Integer busineId, Integer ticketId) {
        return customizeMapper.getTBusineTicketExpireSoon(busineId, ticketId);
    }

    @Override
    public String eleAuthorization(TBusineTicket tBusineTicket) {
        String msg = "授权成功";
        tBusineTicket.setUpdateTime(DateUtil.getCurDateTime());
        tBusineTicket.setTicketInitialNum(tBusineTicket.getTicketNum());
        if (tBusineTicket.getId() != null) {
            //修改
        } else {
            //新增
            tBusineTicket.setTicketInitialNum(0);
            Integer i = tBusineTicketMapper.insertSelective(tBusineTicket);
        }
        return msg;
    }

    @Override
    public String buyBackBusineTicket(TBusineTicket tBusineTicket) {
        String msg = "";
        if (tBusineTicket.getId() != null) {
            Integer ticketNum = (Integer) tBusineTicket.getTicketNum();
            if (ticketNum != 0) {
                tBusineTicket.setUpdateTime(DateUtil.getCurDateTime());
                tBusineTicketMapper.updateByPrimaryKeySelective(tBusineTicket);
            } else {
                tBusineTicketMapper.deleteByPrimaryKey(tBusineTicket.getId());
            }
            msg = "电子券使用记录更新成功";
        } else {
            msg = "电子券使用记录更新失败";
        }
        return msg;
    }

    @Override
    public String eleBuy(TBusineTicket tBusineTicket, Integer ticketNumNew) {
        String msg="";
        TElectronicTicket tElectronicTicket = tElectronicTicketService.getElectronicTicketByPrimaryKey(tBusineTicket.getTicketId());
        tBusineTicket = tBusineTicketMapper.selectByPrimaryKey(tBusineTicket.getId());
        //不具备自购权限，（购买权限限制移到eparking业务层）
/*        if(tBusineTicket.getIsPay()==0){
            msg="无自购权限";
        }else if(tBusineTicket.getIsPay()==1){*/
            if(tElectronicTicket!=null){
                double needpay = tElectronicTicket.getTicketPay() * ticketNumNew;
                TBusine tBusine = tBusineService.getTBusineByID(tBusineTicket.getBusineId());
                double balance = tBusine.getBalance();
                if (balance >= needpay) {
                    tBusineTicket.setTicketNum(tBusineTicket.getTicketNum() + ticketNumNew);
                    tBusineTicket.setTicketInitialNum(tBusineTicket.getTicketInitialNum() + ticketNumNew);
                    tBusineTicketMapper.updateByPrimaryKeySelective(tBusineTicket);
                    TCompanyPark tCompanyPark = tCompanyParkMapper.selectByPrimaryKey(tBusine.getParkId());
                    tBusine.setBalance(balance - needpay);
                    tBusineService.UpdateTBusine(tBusine);
                    //添加充值记录（购买记录）
                    TBusinePay tBusinePay = new TBusinePay();
                    tBusinePay.setBusineId(tBusine.getId());
                    tBusinePay.setParkId(tBusine.getParkId());
                    tBusinePay.setCompanyId(tBusine.getCompanyId());
                    tBusinePay.setBusineName(tBusine.getBusineName());
                    tBusinePay.setNeedPay(needpay * (-1));
                    tBusinePay.setActualPay(needpay * (-1));
                    tBusinePay.setOrderNumber(StringUtil.RandomOrder());
                    tBusinePay.setPayTime(DateUtil.getCurDateTime());
                    tBusinePay.setBalance(tBusine.getBalance());
                    tBusinePay.setParkName(tCompanyPark.getParkName());
                    tBusinePay.setRemark("购买电子券");
                    tBusinePayMapper.insertSelective(tBusinePay);
                    msg = "购买成功";
                } else {
//            throw new ActionRspException(ActionRspEnum.BusineInsufficientBalance_ERROR);
                    msg = "商户余额不足";
                }
            }else{
                msg = "此电子券已删除";
            }
//        }
        return msg;
    }

    @Override
    public String changeIsPay(TBusineTicket tBusineTicket) {
        tBusineTicketMapper.updateByPrimaryKeySelective(tBusineTicket);
        return "修改成功";
    }
}
