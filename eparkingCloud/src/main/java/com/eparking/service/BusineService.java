package com.eparking.service;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TBusine;
import com.common.entity.eparkingCloud.TBusinePay;

import java.util.List;

/**
 * @Author lishuhan
 * @Description:
 * @Date Create in 17:372018-9-14
 * @Modified By:
 */
public interface BusineService {
    PageBean<TBusine> getBusinebyPage(TBusine tBusine, Integer page, Integer limit);
    List<TBusine> getBusine(TBusine tBusine);
    String DeleteBusine(TBusine tBusine);
    String BusineRecharge(TBusinePay tBusinePay, String type);
    String UpdateBusine(TBusine tBusine);
    TBusine selectByPrimaryKey(Integer id);
    TBusine selectByAccount(String account);
    String monthlyRecharge(TBusine tBusineUpdate);
}

