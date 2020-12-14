package com.eparkingdb.service;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TCompanyNotice;

import java.util.List;

/**
* @Description: TCompanyNoticeService接口
* @author 谢轩然
* @date 2020/04/09 14:46
*/
public interface TCompanyNoticeService {
    /**
    *查询(分页)tCompanyNotice
    * @param tCompanyNotice
    * @param page
    * @param limit
    * @return
    */
    PageBean<TCompanyNotice> getTCompanyNoticebyPage(TCompanyNotice tCompanyNotice, Integer page, Integer limit);

    /**
    * 查询tCompanyNotice
    * @param tCompanyNotice
    * @return
    */
    List<TCompanyNotice> getTCompanyNotice(TCompanyNotice tCompanyNotice);

    /**
    * 更新tCompanyNotice
    * @param tCompanyNotice
    * @return
    */
    String UpdateTCompanyNotice(TCompanyNotice tCompanyNotice);

    /**
    * 删除tCompanyNotice
    * @param tCompanyNotice
    * @return
    */
    String DeleteTCompanyNotice(TCompanyNotice tCompanyNotice);

    /**
    * 根据ID查询tCompanyNotice
    * @param id
    * @return
    */
    TCompanyNotice getTCompanyNoticeByID(Integer id);

}