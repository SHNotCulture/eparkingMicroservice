package com.eparkingdb.service;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TCompanyUser;

import java.util.List;

/**
* @Description: TCompanyUserService接口
* @author 李书瀚
* @date 2020/04/08 17:24
*/
public interface TCompanyUserService {
    /**
    *查询(分页)tCompanyUser
    * @param tCompanyUser
    * @param page
    * @param limit
    * @return
    */
    PageBean<TCompanyUser> getTCompanyUserbyPage(TCompanyUser tCompanyUser, Integer page, Integer limit);

    /**
    * 查询tCompanyUser
    * @param tCompanyUser
    * @return
    */
    List<TCompanyUser> getTCompanyUser(TCompanyUser tCompanyUser);

    /**
    * 更新tCompanyUser
    * @param tCompanyUser
    * @return
    */
    String UpdateTCompanyUser(TCompanyUser tCompanyUser);

    /**
    * 删除tCompanyUser
    * @param tCompanyUser
    * @return
    */
    String DeleteTCompanyUser(TCompanyUser tCompanyUser);

    /**
    * 根据ID查询tCompanyUser
    * @param id
    * @return
    */
    TCompanyUser getTCompanyUserByID(Integer id);

    TCompanyUser CheckPassword(TCompanyUser tCompanyUser);
}