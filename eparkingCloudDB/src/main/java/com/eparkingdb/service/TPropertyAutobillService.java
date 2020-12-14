package com.eparkingdb.service;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TPropertyAutobill;

import java.util.List;

/**
* @Description: TPropertyAutobillService接口
* @author 谢轩然
* @date 2020/04/28 16:27
*/
public interface TPropertyAutobillService {
    /**
    *查询(分页)tPropertyAutobill
    * @param tPropertyAutobill
    * @param page
    * @param limit
    * @return
    */
    PageBean<TPropertyAutobill> getTPropertyAutobillbyPage(TPropertyAutobill tPropertyAutobill, Integer page, Integer limit);

    /**
    * 查询tPropertyAutobill
    * @param tPropertyAutobill
    * @return
    */
    List<TPropertyAutobill> getTPropertyAutobill(TPropertyAutobill tPropertyAutobill);

    /**
    * 更新tPropertyAutobill
    * @param tPropertyAutobill
    * @return
    */
    String UpdateTPropertyAutobill(TPropertyAutobill tPropertyAutobill);

    /**
    * 删除tPropertyAutobill
    * @param tPropertyAutobill
    * @return
    */
    String DeleteTPropertyAutobill(TPropertyAutobill tPropertyAutobill);

    /**
    * 根据ID查询tPropertyAutobill
    * @param id
    * @return
    */
    TPropertyAutobill getTPropertyAutobillByID(Integer id);

}