package com.eparkingdb.service;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TPropertyHouseholdInfo;

import java.util.List;

/**
* @Description: TPropertyHouseholdInfoService接口
* @author 谢轩然
* @date 2020/04/28 15:20
*/
public interface TPropertyHouseholdInfoService {
    /**
    *查询(分页)tPropertyHouseholdInfo
    * @param tPropertyHouseholdInfo
    * @param page
    * @param limit
    * @return
    */
    PageBean<TPropertyHouseholdInfo> getTPropertyHouseholdInfobyPage(TPropertyHouseholdInfo tPropertyHouseholdInfo, Integer page, Integer limit);

    /**
    * 查询tPropertyHouseholdInfo
    * @param tPropertyHouseholdInfo
    * @return
    */
    List<TPropertyHouseholdInfo> getTPropertyHouseholdInfo(TPropertyHouseholdInfo tPropertyHouseholdInfo);

    /**
    * 更新tPropertyHouseholdInfo
    * @param tPropertyHouseholdInfo
    * @return
    */
    String UpdateTPropertyHouseholdInfo(TPropertyHouseholdInfo tPropertyHouseholdInfo);

    /**
    * 删除tPropertyHouseholdInfo
    * @param tPropertyHouseholdInfo
    * @return
    */
    String DeleteTPropertyHouseholdInfo(TPropertyHouseholdInfo tPropertyHouseholdInfo);

    /**
    * 根据ID查询tPropertyHouseholdInfo
    * @param id
    * @return
    */
    TPropertyHouseholdInfo getTPropertyHouseholdInfoByID(Integer id);

}