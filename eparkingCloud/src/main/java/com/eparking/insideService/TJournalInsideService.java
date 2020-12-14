package com.eparking.insideService;

import com.common.entity.eparkingCloud.TJournal;
import com.eparking.insideService.impl.TJournalInsideServiceImpl;
import com.common.util.Common;
import com.common.entity.PageBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
* @Description: TJournalInsideService接口
* @author 谢轩然
* @date 2020/05/25 16:01
*/
@FeignClient(value = "eparkingCloudData",fallback = TJournalInsideServiceImpl.class)
public interface TJournalInsideService {
    String client= Common.Feign_eparkingCloudData;
    /**
    * 查询tJournal
    * @param tJournal
    * @return
    */
    @PostMapping(value = client+"/tJournal/getTJournal")
    List<TJournal> getTJournal(@RequestBody TJournal tJournal, @RequestParam("beginTime") String beginTime, @RequestParam("endTime") String endTime);

    /**
    *查询(分页)tJournal
    * @param tJournal
    * @param page
    * @param limit
    * @return
    */
    @PostMapping(value = client+"/tJournal/getTJournalbyPage")
    PageBean<TJournal> getTJournalbyPage(@RequestBody TJournal tJournal, @RequestParam("beginTime") String beginTime, @RequestParam("endTime") String endTime, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit);

    /**
    * 更新tJournal
    * @param tJournal
    * @return
    */
    @PostMapping(value = client+"/tJournal/updateTJournal")
    String UpdateTJournal(@RequestBody TJournal tJournal);

    /**
    * 删除tJournal
    * @param tJournal
    * @return
    */
    @PostMapping(value = client+"/tJournal/deleteTJournal")
    String DeleteTJournal(@RequestBody TJournal tJournal);
}