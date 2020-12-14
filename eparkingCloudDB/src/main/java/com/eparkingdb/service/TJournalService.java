package com.eparkingdb.service;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TJournal;

import java.util.List;

/**
* @Description: TJournalService接口
* @author 谢轩然
* @date 2020/04/09 15:17
*/
public interface TJournalService {
    /**
    *查询(分页)tJournal
    * @param tJournal
    * @param page
    * @param limit
    * @return
    */
    PageBean<TJournal> getTJournalbyPage(TJournal tJournal, String beginTime, String endTime, Integer page, Integer limit);

    /**
    * 查询tJournal
    * @param tJournal
    * @return
    */
    List<TJournal> getTJournal(TJournal tJournal, String beginTime, String endTime);

    /**
    * 更新tJournal
    * @param tJournal
    * @return
    */
    String UpdateTJournal(TJournal tJournal);

    /**
    * 删除tJournal
    * @param tJournal
    * @return
    */
    String DeleteTJournal(TJournal tJournal);

    /**
    * 根据ID查询tJournal
    * @param id
    * @return
    */
    TJournal getTJournalByID(Integer id);

}