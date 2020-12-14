package com.eparkingdb.service;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TPropertyHouseholdBill;

import java.util.List;

/**
* @Description: TPropertyHouseholdBillService接口
* @author 谢轩然
* @date 2020/04/29 15:37
*/
public interface TPropertyHouseholdBillService {
    /**
    *查询(分页)TPropertyHouseholdBill
    * @param TPropertyHouseholdBill
    * @param page
    * @param limit
    * @returnc
    */
    PageBean<TPropertyHouseholdBill> getTPropertyHouseholdBillbyPage(TPropertyHouseholdBill TPropertyHouseholdBill, List<Integer> list, Integer page, Integer limit);

    /**
    * 查询TPropertyHouseholdBill
    * @param TPropertyHouseholdBill
    * @return
    */
    List<TPropertyHouseholdBill> getTPropertyHouseholdBill(TPropertyHouseholdBill TPropertyHouseholdBill, List<Integer> list);

    /**
    * 更新TPropertyHouseholdBill
    * @param TPropertyHouseholdBill
    * @return
    */
    String UpdateTPropertyHouseholdBill(TPropertyHouseholdBill TPropertyHouseholdBill);

    /**
    * 删除TPropertyHouseholdBill
    * @param TPropertyHouseholdBill
    * @return
    */
    String DeleteTPropertyHouseholdBill(TPropertyHouseholdBill TPropertyHouseholdBill);

    /**
    * 根据ID查询TPropertyHouseholdBill
    * @param id
    * @return
    */
    TPropertyHouseholdBill getTPropertyHouseholdBillByID(Integer id);

}