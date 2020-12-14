package com.eparkingdb.service;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TPropertyBillItems;

import java.util.List;

/**
* @Description: TPropertyBillItemsService接口
* @author 谢轩然
* @date 2020/04/29 10:33
*/
public interface TPropertyBillItemsService {
    /**
    *查询(分页)tPropertyBillItems
    * @param tPropertyBillItems
    * @param page
    * @param limit
    * @return
    */
    PageBean<TPropertyBillItems> getTPropertyBillItemsbyPage(TPropertyBillItems tPropertyBillItems, Integer page, Integer limit);

    /**
    * 查询tPropertyBillItems
    * @param tPropertyBillItems
    * @return
    */
    List<TPropertyBillItems> getTPropertyBillItems(TPropertyBillItems tPropertyBillItems);

    /**
    * 更新tPropertyBillItems
    * @param tPropertyBillItems
    * @return
    */
    String UpdateTPropertyBillItems(TPropertyBillItems tPropertyBillItems);

    /**
    * 删除tPropertyBillItems
    * @param tPropertyBillItems
    * @return
    */
    String DeleteTPropertyBillItems(TPropertyBillItems tPropertyBillItems);

    /**
    * 根据ID查询tPropertyBillItems
    * @param id
    * @return
    */
    TPropertyBillItems getTPropertyBillItemsByID(Integer id);

}