package com.eparkingdb.controller;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TJournal;
import com.common.annotation.HttpLog;
import com.eparkingdb.service.TJournalService;
import com.common.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* @Description: TJournalController类
* @author 谢轩然
* @date 2020/04/09 15:17
*/
@RestController
@RequestMapping("/tJournal")
public class TJournalController {

    private  static final Logger logger= LoggerFactory.getLogger(TJournalController.class);

    @Autowired
    private TJournalService tJournalService;

    /**
    * 查询TJournal信息
    * @paramtJournal
    * @return
    */
    @PostMapping(value = "/getTJournal")
    @HttpLog(operationType = "0",modularTypeName = "查询TJournal")
    public List<TJournal> getTJournal(@RequestBody TJournal tJournal, @RequestParam("beginTime") String beginTime, @RequestParam("endTime") String endTime){
    return tJournalService.getTJournal(tJournal,beginTime,endTime);
    }

    /**
    * 查询TJournal信息(分页)
    * @paramtJournal
    * @return
    */
    @PostMapping(value = "/getTJournalbyPage")
    @HttpLog(operationType = "0",modularTypeName = "查询TJournal(分页)")
    public PageBean<TJournal> getTJournalbyPage(@RequestBody TJournal tJournal, @RequestParam("beginTime") String beginTime, @RequestParam("endTime") String endTime, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit){
    return tJournalService.getTJournalbyPage(tJournal,beginTime,endTime,page,limit);
    }

    /**
    * 更新TJournal信息
    * @paramtJournal
    * @return
    */
    @PostMapping(value = "/updateTJournal")
    @HttpLog(operationType = "1",modularTypeName = "更新TJournal信息")
    public String UpdateTJournal(@RequestBody TJournal tJournal)
    {
        return tJournalService.UpdateTJournal(tJournal);
    }

    /**
    * 删除TJournal信息
    * @param tJournal
    * @return
    */
    @PostMapping(value = "/deleteTJournal")
    @HttpLog(operationType = "1",modularTypeName = "删除TJournal信息")
    public String DeleteTJournal(@RequestBody TJournal tJournal){
    return tJournalService.DeleteTJournal(tJournal);
    }
}