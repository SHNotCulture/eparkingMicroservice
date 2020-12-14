package com.eparking.insideService;

import com.common.entity.eparkingCloud.TParkPort;
import com.eparking.insideService.impl.TParkPortInsideServiceImpl;
import com.common.util.Common;
import com.common.entity.PageBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
* @Description: TParkPortInsideService接口
* @author 谢轩然
* @date 2020/05/29 15:05
*/
@FeignClient(value = "eparkingCloudData",fallback = TParkPortInsideServiceImpl.class)
public interface TParkPortInsideService {
    String client= Common.Feign_eparkingCloudData;
    /**
    * 查询tParkPort
    * @param tParkPort
    * @return
    */
    @PostMapping(value = client+"/tParkPort/getTParkPort")
    List<TParkPort> getTParkPort(@RequestBody TParkPort tParkPort);

    /**
    *查询(分页)tParkPort
    * @param tParkPort
    * @param page
    * @param limit
    * @return
    */
    @PostMapping(value = client+"/tParkPort/getTParkPortbyPage")
    PageBean<TParkPort> getTParkPortbyPage(@RequestBody TParkPort tParkPort, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit);

    /**
    * 更新tParkPort
    * @param tParkPort
    * @return
    */
    @PostMapping(value = client+"/tParkPort/updateTParkPort")
    String UpdateTParkPort(@RequestBody TParkPort tParkPort);

    /**
    * 删除tParkPort
    * @param tParkPort
    * @return
    */
    @PostMapping(value = client+"/tParkPort/deleteTParkPort")
    String DeleteTParkPort(@RequestBody TParkPort tParkPort);

    @PostMapping(value = client+"/tParkPort/getTParkPortOutPort")
    List<TParkPort> getTParkPortOutPort(@RequestBody TParkPort tParkPort);

    @PostMapping(value = client+"/tParkPort/getPortNameListForZtree")
    List<TParkPort> getPortNameListForZtree(@RequestParam("parkId") Integer parkId, @RequestParam("portType") String portType);
}