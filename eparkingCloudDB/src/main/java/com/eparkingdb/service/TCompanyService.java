package com.eparkingdb.service;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TCompany;

import java.util.List;

/**
* @Description: TCompanyService接口
* @author 谢轩然
* @date 2020/04/09 14:42
*/
public interface TCompanyService {
    /**
    *查询(分页)tCompany
    * @param tCompany
    * @param page
    * @param limit
    * @return
    */
    PageBean<TCompany> getTCompanybyPage(TCompany tCompany, Integer page, Integer limit);

    /**
    * 查询tCompany
    * @param tCompany
    * @return
    */
    List<TCompany> getTCompany(TCompany tCompany);

    /**
    * 更新tCompany
    * @param tCompany
    * @return
    */
    String UpdateTCompany(TCompany tCompany);

    /**
    * 删除tCompany
    * @param tCompany
    * @return
    */
    String DeleteTCompany(TCompany tCompany);

    /**
    * 根据ID查询tCompany
    * @param id
    * @return
    */
    TCompany getTCompanyByID(Integer id);

    List<TCompany> getCompanyDistnict(TCompany tCompany);
}