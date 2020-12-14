package com.eparking.insideService;

import com.common.entity.eparkingCloud.TDicOuttype;
import com.common.entity.eparkingCloud.TDicPrepayType;
import com.common.entity.eparkingCloud.TParkPort;
import com.eparking.insideService.impl.BaseDicInsideServiceImpl;
import com.common.util.Common;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "eparkingCloudData", fallback = BaseDicInsideServiceImpl.class)
public interface BaseDicInsideService {
    String client = Common.Feign_eparkingCloudData;

    @PostMapping(value = client + "/BaseDic/getPortName")
    List<TParkPort> selectParkPort(@RequestParam("portType") String portType, @RequestParam("parkId") Integer parkId);

    @PostMapping(value = client + "/BaseDic/selectOutType")
    List<TDicOuttype> selectOutType();

    @PostMapping(value = client + "/BaseDic/selectgetPayType")
    List<TDicPrepayType> selectgetPayType();
}
