package com.eparking.insideService;

import com.common.entity.ActionRsp;
import com.common.entity.ControllerRsp;
import com.common.entity.eparkingCloud.TPropertyHouseholdInfo;
import com.eparking.insideService.impl.TPropertyHouseholdInfoInsideServiceImpl;
import com.common.util.Common;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
* @Description: TPropertyHouseholdInfoInsideService接口
* @author 谢轩然
* @date 2020/04/28 15:20
*/
@FeignClient(value = "eparkingCloudData",fallback = TPropertyHouseholdInfoInsideServiceImpl.class)
public interface TPropertyHouseholdInfoInsideService {
    String client= Common.Feign_eparkingCloudData;
    /**
    * 查询tPropertyHouseholdInfo
    * @param tPropertyHouseholdInfo
    * @return
    */
    @PostMapping(value = client+"/tPropertyHouseholdInfo/getTPropertyHouseholdInfo")
    ActionRsp getTPropertyHouseholdInfo(@RequestBody TPropertyHouseholdInfo tPropertyHouseholdInfo);

    /**
    *查询(分页)tPropertyHouseholdInfo
    * @param tPropertyHouseholdInfo
    * @param page
    * @param limit
    * @return
    */
    @PostMapping(value = client+"/tPropertyHouseholdInfo/getTPropertyHouseholdInfobyPage")
    ControllerRsp getTPropertyHouseholdInfobyPage(@RequestBody TPropertyHouseholdInfo tPropertyHouseholdInfo, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit);

    /**
    * 更新tPropertyHouseholdInfo
    * @param tPropertyHouseholdInfo
    * @return
    */
    @PostMapping(value = client+"/tPropertyHouseholdInfo/updateTPropertyHouseholdInfo")
    ActionRsp UpdateTPropertyHouseholdInfo(@RequestBody TPropertyHouseholdInfo tPropertyHouseholdInfo);

    /**
    * 删除tPropertyHouseholdInfo
    * @param tPropertyHouseholdInfo
    * @return
    */
    @PostMapping(value = client+"/tPropertyHouseholdInfo/deleteTPropertyHouseholdInfo")
    ActionRsp DeleteTPropertyHouseholdInfo(@RequestBody TPropertyHouseholdInfo tPropertyHouseholdInfo);
}