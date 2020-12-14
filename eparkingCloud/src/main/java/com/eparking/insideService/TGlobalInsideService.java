package com.eparking.insideService;

import com.common.entity.eparkingCloud.TGlobalInfo;
import com.eparking.insideService.impl.TGlobalInsideServiceImpl;
import com.common.util.Common;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@FeignClient(value = "eparkingCloudData",fallback = TGlobalInsideServiceImpl.class)
public interface TGlobalInsideService {
    String client= Common.Feign_eparkingCloudData;
    /**
     * 查询TGlobalInfo
     * @param tGlobalInfo
     * @return
     */
    @PostMapping(value = client+"/tGlobalInfo/getTGlobalInfo")
    List<TGlobalInfo> getTGlobalInfo(@RequestBody TGlobalInfo tGlobalInfo);

    @PostMapping(value = client+"/tGlobalInfo/updateTGlobalInfo")
    String UpdateTGlobalInfo(@RequestBody TGlobalInfo tGlobalInfo);


    @PostMapping(value = client+"/tGlobalInfo/deleteTGlobalInfo")
    String DeleteTGlobalInfo(@RequestBody TGlobalInfo tGlobalInfo);
}
