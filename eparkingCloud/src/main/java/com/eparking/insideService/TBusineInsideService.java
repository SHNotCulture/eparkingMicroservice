package com.eparking.insideService;

import com.common.entity.PageBean;
import com.common.util.Common;
import com.common.entity.eparkingCloud.TBusine;
import com.common.entity.eparkingCloud.TBusinePay;
import com.eparking.insideService.impl.TBusineInsideServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
* @Description: TBusineInsideService接口
* @author 谢轩然
* @date 2020/04/08 18:35
*/
@FeignClient(value = "eparkingCloudData",fallback = TBusineInsideServiceImpl.class)
public interface TBusineInsideService {
    String client= Common.Feign_eparkingCloudData;
    /**
    * 查询tBusine
    * @param tBusine
    * @return
    */
    @PostMapping(value = client+"/tBusine/getTBusine")
    List<TBusine> getTBusine(@RequestBody TBusine tBusine);

    /**
    *查询(分页)tBusine
    * @param tBusine
    * @param page
    * @param limit
    * @return
    */
    @PostMapping(value = client+"/tBusine/getTBusinebyPage")
    PageBean<TBusine> getTBusinebyPage(@RequestBody TBusine tBusine, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit);

    /**
    * 更新tBusine
    * @param tBusine
    * @return
    */
    @PostMapping(value = client+"/tBusine/updateTBusine")
    String UpdateTBusine(@RequestBody TBusine tBusine);

    /**
    * 删除tBusine
    * @param tBusine
    * @return
    */
    @PostMapping(value = client+"/tBusine/deleteTBusine")
    String DeleteTBusine(@RequestBody TBusine tBusine);

    @PostMapping(value = client+"/tBusine/getBusineRecharge")
    String BusineRecharge(@RequestBody TBusinePay tBusinePay, @RequestParam("type") String type);

    @PostMapping(value = client+"/tBusine/selectByAccount")
    TBusine selectByAccount(@RequestParam("account") String account);

    @PostMapping(value = client+"/tBusine/selectById")
    TBusine selectById(@RequestParam("id") Integer id);
}