package com.eparkingdb.service;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TApikey;

import java.util.List;

/**
* @Description: TApikeyService接口
* @author 谢轩然
* @date 2020/04/08 17:46
*/
public interface TApikeyService {
    /**
    *查询(分页)tApikey
    * @param tApikey
    * @param page
    * @param limit
    * @return
    */
    PageBean<TApikey> getTApikeybyPage(TApikey tApikey, Integer page, Integer limit);

    /**
    * 查询tApikey
    * @param tApikey
    * @return
    */
    List<TApikey> getTApikey(TApikey tApikey);

    /**
    * 更新tApikey
    * @param tApikey
    * @return
    */
    String UpdateTApikey(TApikey tApikey);

    /**
    * 删除tApikey
    * @param tApikey
    * @return
    */
    String DeleteTApikey(TApikey tApikey);

    /**
    * 根据ID查询tApikey
    * @param id
    * @return
    */
    TApikey getTApikeyByID(Integer id);

}