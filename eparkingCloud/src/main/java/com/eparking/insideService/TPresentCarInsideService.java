package com.eparking.insideService;

import com.common.entity.eparkingCloud.TParkInOut;
import com.eparking.insideService.impl.TPresentCarInsideServiceImpl;
import com.common.util.Common;
import com.common.entity.PageBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "eparkingCloudData",fallback = TPresentCarInsideServiceImpl.class)
public interface TPresentCarInsideService{
    String client= Common.Feign_eparkingCloudData;
    /**
     *查询(分页)在场车辆
     * @param tParkInOut
     * @param page
     * @param limit
     * @return
     */
    @PostMapping(value = client+"/Present/getTPresentCarbyPage")
    PageBean<TParkInOut> getTPresentCarbyPage(@RequestBody TParkInOut tParkInOut, @RequestParam("parkId") String parkId, @RequestParam("timeType") String timeType, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit);

    @PostMapping(value = client+"/Present/getTPresentCar")
    List<TParkInOut> getTPresentCar(@RequestBody TParkInOut tParkInOut, @RequestParam("parkId") String parkId, @RequestParam("timeType") String timeType);

}
