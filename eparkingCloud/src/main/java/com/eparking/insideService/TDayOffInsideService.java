package com.eparking.insideService;

import com.common.entity.eparkingCloud.TDayOff;
import com.eparking.insideService.impl.TDayOffInsideServiceImpl;
import com.common.util.Common;
import com.common.entity.PageBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "eparkingCloudData", fallback = TDayOffInsideServiceImpl.class)
public interface TDayOffInsideService {

    String client = Common.Feign_eparkingCloudData;

    @PostMapping(value = client + "/tDayOff/getTDayOff")
    List<TDayOff> getTHoliday(@RequestBody TDayOff tDayOff);

    @PostMapping(value = client + "/tDayOff/getTDayOffbyPage")
    PageBean<TDayOff> getTDayOffbyPage(@RequestBody TDayOff tDayOff, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit);

    @PostMapping(value = client + "/tDayOff/updateTDayOff")
    String UpdateTDayOff(@RequestBody TDayOff tDayOff);

    @PostMapping(value = client + "/tDayOff/deleteTDayOff")
    String DeleteTDayOff(@RequestBody TDayOff tDayOff);
}
