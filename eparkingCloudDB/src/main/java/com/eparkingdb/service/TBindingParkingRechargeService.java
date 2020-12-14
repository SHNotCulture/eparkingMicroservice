package com.eparkingdb.service;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TBindingParkingRecharge;

import java.util.List;

/**
* @Description: TBindingParkingRechargeService接口
* @author 谢轩然
* @date 2020/04/08 18:25
*/
public interface TBindingParkingRechargeService {
    /**
    *查询(分页)tBindingParkingRecharge
    * @param tBindingParkingRecharge
    * @param page
    * @param limit
    * @return
    */
    PageBean<TBindingParkingRecharge> getTBindingParkingRechargebyPage(TBindingParkingRecharge tBindingParkingRecharge, Integer page, Integer limit);

    /**
    * 查询tBindingParkingRecharge
    * @param tBindingParkingRecharge
    * @return
    */
    List<TBindingParkingRecharge> getTBindingParkingRecharge(TBindingParkingRecharge tBindingParkingRecharge);

    /**
    * 更新tBindingParkingRecharge
    * @param tBindingParkingRecharge
    * @return
    */
    String UpdateTBindingParkingRecharge(TBindingParkingRecharge tBindingParkingRecharge);

    /**
    * 删除tBindingParkingRecharge
    * @param tBindingParkingRecharge
    * @return
    */
    String DeleteTBindingParkingRecharge(TBindingParkingRecharge tBindingParkingRecharge);

    /**
    * 根据ID查询tBindingParkingRecharge
    * @param id
    * @return
    */
    TBindingParkingRecharge getTBindingParkingRechargeByID(Integer id);

}