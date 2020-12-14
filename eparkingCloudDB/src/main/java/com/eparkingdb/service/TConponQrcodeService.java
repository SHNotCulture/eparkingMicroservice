package com.eparkingdb.service;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TConponQrcode;

import java.util.List;

/**
* @Description: TConponQrcodeService接口
* @author 谢轩然
* @date 2020/04/09 14:55
*/
public interface TConponQrcodeService {
    /**
    *查询(分页)tConponQrcode
    * @param tConponQrcode
    * @param page
    * @param limit
    * @return
    */
    PageBean<TConponQrcode> getTConponQrcodebyPage(TConponQrcode tConponQrcode, Integer page, Integer limit);

    /**
    * 查询tConponQrcode
    * @param tConponQrcode
    * @return
    */
    List<TConponQrcode> getTConponQrcode(TConponQrcode tConponQrcode);

    /**
    * 更新tConponQrcode
    * @param tConponQrcode
    * @return
    */
    String UpdateTConponQrcode(TConponQrcode tConponQrcode);

    /**
    * 删除tConponQrcode
    * @param tConponQrcode
    * @return
    */
    String DeleteTConponQrcode(TConponQrcode tConponQrcode);

    /**
    * 根据ID查询tConponQrcode
    * @param id
    * @return
    */
    TConponQrcode getTConponQrcodeByID(Integer id);

/*    void existTConponQrcodeName(TConponQrcode tConponQrcode);

    void lessDiscountAmount(TConponQrcode tConponQrcode,Integer busineId);*/

    List<TConponQrcode> getPreciseTConponQrcode(TConponQrcode tConponQrcode);
}