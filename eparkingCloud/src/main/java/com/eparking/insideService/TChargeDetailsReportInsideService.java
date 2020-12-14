package com.eparking.insideService;

import com.common.entity.eparkingCloud.TChargeDetailsReport;
import com.eparking.insideService.impl.TChargeDetailsReportInsideServiceImpl;
import com.common.util.Common;
import com.common.entity.PageBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
* @Description: TChargeDetailsReportInsideService接口
* @author 谢轩然
* @date 2020/11/03 17:02
*/
@FeignClient(value = "eparkingCloudData",fallback = TChargeDetailsReportInsideServiceImpl.class)
public interface TChargeDetailsReportInsideService {
    String client= Common.Feign_eparkingCloudData;
    /**
    * 查询tChargeDetailsReport
    * @param tChargeDetailsReport
    * @return
    */
    @PostMapping(value = client+"/tChargeDetailsReport/getTChargeDetailsReport")
    List<TChargeDetailsReport> getTChargeDetailsReport(@RequestBody TChargeDetailsReport tChargeDetailsReport, @RequestParam("beginDate") String beginDate, @RequestParam("endDate") String endDate);

    /**
    *查询(分页)tChargeDetailsReport
    * @param tChargeDetailsReport
    * @param page
    * @param limit
    * @return
    */
    @PostMapping(value = client+"/tChargeDetailsReport/getTChargeDetailsReportbyPage")
    PageBean<TChargeDetailsReport> getTChargeDetailsReportbyPage(@RequestBody TChargeDetailsReport tChargeDetailsReport, @RequestParam("beginDate") String beginDate, @RequestParam("endDate") String endDate, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit);

    /**
    * 更新tChargeDetailsReport
    * @param tChargeDetailsReport
    * @return
    */
    @PostMapping(value = client+"/tChargeDetailsReport/updateTChargeDetailsReport")
    String UpdateTChargeDetailsReport(@RequestBody TChargeDetailsReport tChargeDetailsReport);

    /**
    * 删除tChargeDetailsReport
    * @param tChargeDetailsReport
    * @return
    */
    @PostMapping(value = client+"/tChargeDetailsReport/deleteTChargeDetailsReport")
    String DeleteTChargeDetailsReport(@RequestBody TChargeDetailsReport tChargeDetailsReport);

    @PostMapping(value = client+"/tChargeDetailsReport/chargeReportStatistics")
    String chargeReportStatistics(@RequestBody TChargeDetailsReport tChargeDetailsReport, @RequestParam("beginDate") String beginDate, @RequestParam("endDate") String endDate);

    @PostMapping(value = client+"/tChargeDetailsReport/getDutyStatisticsbyPage")
    PageBean<TChargeDetailsReport> getDutyStatisticsbyPage(@RequestBody TChargeDetailsReport tChargeDetailsReport, @RequestParam("beginTime") String beginTime, @RequestParam("endTime") String endTime, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit);
}