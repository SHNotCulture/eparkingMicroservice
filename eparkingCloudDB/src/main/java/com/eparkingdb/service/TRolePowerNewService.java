package com.eparkingdb.service;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TRolePowerNew;

import java.util.List;

/**
* @Description: TRolePowerNewService接口
* @author 谢轩然
* @date 2020/05/15 15:20
*/
public interface TRolePowerNewService {
    /**
    *查询(分页)tRolePowerNew
    * @param tRolePowerNew
    * @param page
    * @param limit
    * @return
    */
    PageBean<TRolePowerNew> getTRolePowerNewbyPage(TRolePowerNew tRolePowerNew, Integer page, Integer limit);

    /**
    * 查询tRolePowerNew
    * @param tRolePowerNew
    * @return
    */
    List<TRolePowerNew> getTRolePowerNew(TRolePowerNew tRolePowerNew);

    /**
    * 更新tRolePowerNew
    * @param tRolePowerNew
    * @return
    */
    String UpdateTRolePowerNew(TRolePowerNew tRolePowerNew);

    /**
    * 删除tRolePowerNew
    * @param tRolePowerNew
    * @return
    */
    String DeleteTRolePowerNew(TRolePowerNew tRolePowerNew);

    /**
    * 根据ID查询tRolePowerNew
    * @param id
    * @return
    */
    TRolePowerNew getTRolePowerNewByID(Integer id);

    /**
     * 通过ID查询角色信息
     * @param tRolePowerNew
     * @return
     */
    List<TRolePowerNew> getRolePowerById(TRolePowerNew tRolePowerNew);
}