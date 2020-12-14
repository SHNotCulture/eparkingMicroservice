package com.eparking.insideService;

import com.common.entity.eparkingCloud.TParkReportYear;
import com.eparking.insideService.impl.TParkReportYearInsideServiceImpl;
import com.common.util.Common;
import com.common.entity.PageBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
* @Description: TParkReportYearInsideService接口
* @author 谢轩然
* @date 2020/08/04 10:38
*/
@FeignClient(value = "eparkingCloudData",fallback = TParkReportYearInsideServiceImpl.class)
public interface TParkReportYearInsideService {
    String client= Common.Feign_eparkingCloudData;
    /**
    * 查询tParkReportYear
    * @param tParkReportYear
    * @return
    */
    @PostMapping(value = client+"/tParkReportYear/getTParkReportYear")
    List<TParkReportYear> getTParkReportYear(@RequestBody TParkReportYear tParkReportYear, @RequestParam("beginTime") String beginTime, @RequestParam("endTime") String endTime);

    /**
    *查询(分页)tParkReportYear
    * @param tParkReportYear
    * @param page
    * @param limit
    * @return
    */
    @PostMapping(value = client+"/tParkReportYear/getTParkReportYearbyPage")
    PageBean<TParkReportYear> getTParkReportYearbyPage(@RequestBody TParkReportYear tParkReportYear, @RequestParam("beginTime") String beginTime, @RequestParam("endTime") String endTime, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit);

    /**
    * 更新tParkReportYear
    * @param tParkReportYear
    * @return
    */
    @PostMapping(value = client+"/tParkReportYear/updateTParkReportYear")
    String UpdateTParkReportYear(@RequestBody TParkReportYear tParkReportYear);

    /**
    * 删除tParkReportYear
    * @param tParkReportYear
    * @return
    */
    @PostMapping(value = client+"/tParkReportYear/deleteTParkReportYear")
    String DeleteTParkReportYear(@RequestBody TParkReportYear tParkReportYear);
}