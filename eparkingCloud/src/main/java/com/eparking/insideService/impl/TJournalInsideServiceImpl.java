package com.eparking.insideService.impl;

import com.common.entity.eparkingCloud.TJournal;
import com.eparking.insideService.TJournalInsideService;
import com.common.entity.PageBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;


/**
* @Description: TJournalInsideService接口熔断器
* @author 谢轩然
* @date 2020/05/25 16:01
*/
@Component
public class TJournalInsideServiceImpl  implements TJournalInsideService {

    private static final Logger logger = LoggerFactory.getLogger(TJournalInsideServiceImpl.class);

    /**
    * 查询tJournal
    * @param tJournal
    * @return
    */
    @Override
    public List<TJournal> getTJournal(TJournal tJournal, String beginTime, String endTime) {
        String str = "查询TJournal失败！";
        logger.info("查询TJournal失败！");
        return null;
    }

    /**
    *查询(分页)tJournal
    * @param tJournal
    * @param page
    * @param limit
    * @return
    */
    @Override
    public PageBean<TJournal> getTJournalbyPage(TJournal tJournal, String beginTime, String endTime, Integer page, Integer limit) {
        String str = "查询TJournal分页失败！";
        logger.info("查询TJournal分页失败！");
        return null;
    }

    /**
    * 更新tJournal
    * @param tJournal
    * @return
    */
    @Override
    public String UpdateTJournal(TJournal tJournal){
        String str = "更新TJournal失败！";
        logger.info("更新TJournal失败！");
        return str;
    }

    /**
    * 删除tJournal
    * @param tJournal
    * @return
    */
    @Override
    public String DeleteTJournal(TJournal tJournal){
        String str = "删除TJournal失败！";
        logger.info("删除TJournal失败！");
        return str;
    }
}