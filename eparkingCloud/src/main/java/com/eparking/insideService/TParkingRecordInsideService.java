package com.eparking.insideService;

import com.common.entity.eparkingCloud.TParkInOut;
import com.eparking.insideService.impl.TParkingRecordInsideServiceImpl;
import com.common.util.Common;
import com.common.entity.PageBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "eparkingCloudData", fallback = TParkingRecordInsideServiceImpl.class)
public interface TParkingRecordInsideService {
    String client = Common.Feign_eparkingCloudData;

    @PostMapping(value = client + "/parkingRecord/getParkinoutbyPage")
    PageBean<TParkInOut> getParkinoutbyPage(@RequestBody TParkInOut tParkInOut, @RequestParam("outTimeBegin") String outTimeBegin, @RequestParam("outTimeEnd") String outTimeEnd, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit);

    @PostMapping(value = client + "/parkingRecord/getParkInOut")
    List<TParkInOut> getParkInOut(@RequestBody TParkInOut tParkInOut, @RequestParam("outTimeBegin") String outTimeBegin, @RequestParam("outTimeEnd") String outTimeEnd);

    @PostMapping(value = client + "/parkingRecord/updateParkInOut")
    String updateParkInOut(@RequestBody TParkInOut tParkInOut);

    @PostMapping(value = client + "/parkingRecord/getParkinoutDetailbyPage")
    PageBean<TParkInOut> getParkinoutDetailbyPage(@RequestBody TParkInOut tParkInOut, @RequestParam("parkId") String parkId, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit);

    @PostMapping(value = client + "/parkingRecord/getInCarbyPage")
    PageBean<TParkInOut> getInCarbyPage(@RequestBody TParkInOut tParkInOut, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit);

    @PostMapping(value = client + "/tParkInOut/getTParkInOutForStatistics")
    List<TParkInOut> getTParkInOutForStatistics(@RequestBody TParkInOut tParkInOut, @RequestParam("outTimeBegin") String outTimeBegin, @RequestParam("outTimeEnd") String outTimeEnd);

    @PostMapping(value = client + "/tParkInOut/getTParkinoutUnlimitByPage")
    PageBean<TParkInOut> getTParkinoutUnlimitByPage(@RequestBody TParkInOut tParkInOut, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit);

    @PostMapping(value = client + "/tParkInOut/getTParkInOutUnlimit")
    List<TParkInOut> getTParkInOutUnlimit(@RequestBody TParkInOut tParkInOut);
}
