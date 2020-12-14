package com.eparkingdb.service;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TIpc;

import java.util.List;

/**
* @Description: TIpcService接口
* @author 谢轩然
* @date 2020/07/13 18:31
*/
public interface TIpcService {
    /**
    *查询(分页)tIpc
    * @param tIpc
    * @param page
    * @param limit
    * @return
    */
    PageBean<TIpc> getTIpcbyPage(TIpc tIpc, Integer page, Integer limit);

    /**
    * 查询tIpc
    * @param tIpc
    * @return
    */
    List<TIpc> getTIpc(TIpc tIpc);

    /**
    * 更新tIpc
    * @param tIpc
    * @return
    */
    String UpdateTIpc(TIpc tIpc);

    /**
    * 删除tIpc
    * @param tIpc
    * @return
    */
    String DeleteTIpc(TIpc tIpc);

    /**
    * 根据ID查询tIpc
    * @param id
    * @return
    */
    TIpc getTIpcByID(Integer id);

}