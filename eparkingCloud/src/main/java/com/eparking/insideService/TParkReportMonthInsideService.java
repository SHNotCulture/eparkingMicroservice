package com.eparking.insideService;

import com.common.entity.eparkingCloud.TParkReportMonth;
import com.eparking.insideService.impl.TParkReportMonthInsideServiceImpl;
import com.common.util.Common;
import com.common.entity.PageBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
* @Description: TParkReportMonthInsideService接口
* @author 谢轩然
* @date 2020/08/03 16:21
*/
@FeignClient(value = "eparkingCloudData",fallback = TParkReportMonthInsideServiceImpl.class)
public interface TParkReportMonthInsideService {
    String client = Common.Feign_eparkingCloudData;
    /**
    * 查询tParkReportMonth
    * @param tParkReportMonth
    * @return
    */
    @PostMapping(value = client+"/tParkReportMonth/getTParkReportMonth")
    List<TParkReportMonth> getTParkReportMonth(@RequestBody TParkReportMonth tParkReportMonth, @RequestParam("beginTime") String beginTime, @RequestParam("endTime") String endTime);

    /**
    *查询(分页)tParkReportMonth
    * @param tParkReportMonth
    * @param page
    * @param limit
    * @return
    */
    @PostMapping(value = client+"/tParkReportMonth/getTParkReportMonthbyPage")
    PageBean<TParkReportMonth> getTParkReportMonthbyPage(@RequestBody TParkReportMonth tParkReportMonth, @RequestParam("beginTime") String beginTime, @RequestParam("endTime") String endTime, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit);

    /**
    * 更新tParkReportMonth
    * @param tParkReportMonth
    * @return
    */
    @PostMapping(value = client+"/tParkReportMonth/updateTParkReportMonth")
    String UpdateTParkReportMonth(@RequestBody TParkReportMonth tParkReportMonth);

    /**
    * 删除tParkReportMonth
    * @param tParkReportMonth
    * @return
    */
    @PostMapping(value = client+"/tParkReportMonth/deleteTParkReportMonth")
    String DeleteTParkReportMonth(@RequestBody TParkReportMonth tParkReportMonth);
}