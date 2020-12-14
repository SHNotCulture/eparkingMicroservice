package com.eparkingdb.service;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TBlacklist;

import java.util.List;

/**
* @Description: TBlacklistService接口
* @author 谢轩然
* @date 2020/05/14 16:29
*/
public interface TBlacklistService {
    /**
    *查询(分页)tBlacklist
    * @param tBlacklist
    * @param page
    * @param limit
    * @return
    */
    PageBean<TBlacklist> getTBlacklistbyPage(TBlacklist tBlacklist, Integer page, Integer limit);

    /**
    * 查询tBlacklist
    * @param tBlacklist
    * @return
    */
    List<TBlacklist> getTBlacklist(TBlacklist tBlacklist);

    /**
    * 更新tBlacklist
    * @param tBlacklist
    * @return
    */
    String UpdateTBlacklist(TBlacklist tBlacklist);

    /**
    * 删除tBlacklist
    * @param tBlacklist
    * @return
    */
    String DeleteTBlacklist(TBlacklist tBlacklist);

    /**
    * 根据ID查询tBlacklist
    * @param id
    * @return
    */
    TBlacklist getTBlacklistByID(Integer id);

}