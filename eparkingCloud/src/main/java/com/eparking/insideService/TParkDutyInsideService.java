package com.eparking.insideService;

import com.common.entity.eparkingCloud.TParkDuty;
import com.eparking.insideService.impl.TParkDutyInsideServiceImpl;
import com.common.util.Common;
import com.common.entity.PageBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "eparkingCloudData",fallback = TParkDutyInsideServiceImpl.class)
public interface TParkDutyInsideService {
    String client= Common.Feign_eparkingCloudData;
    /**
     * 查询tParkDuty
     * @param tParkDuty
     * @return
     */
    @PostMapping(value = client+"/tParkDuty/getTParkDuty")
    List<TParkDuty> getTParkDuty(@RequestBody TParkDuty tParkDuty, @RequestParam("beginTime") String beginTime, @RequestParam("endTime") String endTime);

    /**
     *查询(分页)tParkDuty
     * @param tParkDuty
     * @param page
     * @param limit
     * @return
     */
    @PostMapping(value = client+"/tParkDuty/getTParkDutybyPage")
    PageBean<TParkDuty> getTParkDutybyPage(@RequestBody TParkDuty tParkDuty, @RequestParam("beginTime") String beginTime, @RequestParam("endTime") String endTime, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit);

    /**
     * 更新tParkDuty
     * @param tParkDuty
     * @return
     */
    @PostMapping(value = client+"/tParkDuty/updateTParkDuty")
    String UpdateTParkDuty(@RequestBody TParkDuty tParkDuty);

    /**
     * 删除tParkDuty
     * @param tParkDuty
     * @return
     */
    @PostMapping(value = client+"/tParkDuty/deleteTParkDuty")
    String DeleteTParkDuty(@RequestBody TParkDuty tParkDuty);
}
