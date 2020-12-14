package com.eparkingdb.service;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TGlobalInfo;

import java.util.List;

/**
* @Description: TGlobalInfoService接口
* @author 谢轩然
* @date 2020/04/09 15:04
*/
public interface TGlobalInfoService {
    /**
    *查询(分页)tGlobalInfo
    * @param tGlobalInfo
    * @param page
    * @param limit
    * @return
    */
    PageBean<TGlobalInfo> getTGlobalInfobyPage(TGlobalInfo tGlobalInfo, Integer page, Integer limit);

    /**
    * 查询tGlobalInfo
    * @param tGlobalInfo
    * @return
    */
    List<TGlobalInfo> getTGlobalInfo(TGlobalInfo tGlobalInfo);

    /**
    * 更新tGlobalInfo
    * @param tGlobalInfo
    * @return
    */
    String UpdateTGlobalInfo(TGlobalInfo tGlobalInfo);

    /**
    * 删除tGlobalInfo
    * @param tGlobalInfo
    * @return
    */
    String DeleteTGlobalInfo(TGlobalInfo tGlobalInfo);

    /**
    * 根据ID查询tGlobalInfo
    * @param id
    * @return
    */
    TGlobalInfo getTGlobalInfoByID(Integer id);

}