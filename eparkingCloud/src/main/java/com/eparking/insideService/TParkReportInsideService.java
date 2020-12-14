package com.eparking.insideService;

import com.common.entity.eparkingCloud.TParkReport;
import com.eparking.insideService.impl.TParkReportInsideServiceImpl;
import com.common.util.Common;
import com.common.entity.PageBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "eparkingCloudData",fallback = TParkReportInsideServiceImpl.class)
public interface TParkReportInsideService {
    String client= Common.Feign_eparkingCloudData;
    /**
     * 查询tParkReport
     * @param tParkReport
     * @return
     */
    @PostMapping(value = client+"/tParkReport/getTParkReport")
    List<TParkReport> getTParkReport(@RequestBody TParkReport tParkReport, @RequestParam("beginTime") String beginTime, @RequestParam("endTime") String endTime);

    /**
     *查询(分页)tParkReport
     * @param tParkReport
     * @param page
     * @param limit
     * @return
     */
    @PostMapping(value = client+"/tParkReport/getTParkReportbyPage")
    PageBean<TParkReport> getTParkReportbyPage(@RequestBody TParkReport tParkReport, @RequestParam("beginTime") String beginTime, @RequestParam("endTime") String endTime, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit);

    /**
     * 更新tParkReport
     * @param tParkReport
     * @return
     */
    @PostMapping(value = client+"/tParkReport/updateTParkReport")
    String UpdateTParkReport(@RequestBody TParkReport tParkReport);

    /**
     * 删除tParkReport
     * @param tParkReport
     * @return
     */
    @PostMapping(value = client+"/tParkReport/deleteTParkReport")
    String DeleteTParkReport(@RequestBody TParkReport tParkReport);
}
