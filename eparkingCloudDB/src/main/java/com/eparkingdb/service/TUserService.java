package com.eparkingdb.service;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TUser;

import java.util.List;

/**
* @Description: TUserService接口
* @author 谢轩然
* @date 2020/04/10 16:11
*/
public interface TUserService {
    /**
    *查询(分页)tUser
    * @param tUser
    * @param page
    * @param limit
    * @return
    */
    PageBean<TUser> getTUserbyPage(TUser tUser, Integer page, Integer limit);

    /**
    * 查询tUser
    * @param tUser
    * @return
    */
    List<TUser> getTUser(TUser tUser);

    /**
    * 更新tUser
    * @param tUser
    * @return
    */
    String UpdateTUser(TUser tUser);

    /**
    * 删除tUser
    * @param tUser
    * @return
    */
    String DeleteTUser(TUser tUser);

    /**
    * 根据ID查询tUser
    * @param id
    * @return
    */
    TUser getTUserByID(Integer id);

}