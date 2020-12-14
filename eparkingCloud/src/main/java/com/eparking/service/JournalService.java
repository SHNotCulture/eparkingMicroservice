package com.eparking.service;

import com.common.entity.eparkingCloud.TJournal;
import com.common.entity.PageBean;

import java.util.List;

/**
 * @Author lishuhan
 * @Description:
 * @Date Create in 17:322018-10-19
 * @Modified By:
 */
public interface JournalService {
    PageBean<TJournal> getTJournalByPage(TJournal tJournal, String beginTime, String endTime, Integer page, Integer limit);
    List<TJournal> getTJournal(TJournal tJournal, String beginTime, String endTime);
    String UpdateTJournal(TJournal tJournal);
}
