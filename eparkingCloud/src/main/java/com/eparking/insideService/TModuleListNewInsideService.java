package com.eparking.insideService;

import com.common.entity.eparkingCloud.TModuleListNew;
import com.eparking.insideService.impl.TModuleListNewInsideServiceImpl;
import com.common.util.Common;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "eparkingCloudData", fallback = TModuleListNewInsideServiceImpl.class)
public interface TModuleListNewInsideService {
    String client = Common.Feign_eparkingCloudData;

    @PostMapping(value = client + "/tModuleListNew/getModuleListNewForLayuiMenu")
    List<TModuleListNew> getModuleListNewForLayuiMenu(@RequestParam("tModuleListNew") List<Integer> tModuleListNew);

    @PostMapping(value = client + "/tModuleListNew/getModuleList")
    List<TModuleListNew> getModuleList(@RequestBody TModuleListNew tModuleListNew);

    @PostMapping(value = client + "/tModuleListNew/getModuleListForZtree")
    List<TModuleListNew> getModuleListForZtree(@RequestBody TModuleListNew tModuleListNew);

    @PostMapping(value = client + "/tModuleListNew/getModuleListNewForSecond")
    List<TModuleListNew> getModuleListNewForSecond(@RequestParam("pid") Integer pid, @RequestParam("tModuleListNew") List<Integer> tModuleListNew);
}
