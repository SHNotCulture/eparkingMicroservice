package com.eparking.insideService;

import com.common.entity.ActionRsp;
import com.common.entity.ControllerRsp;
import com.common.entity.eparkingCloud.TCompanyArea;
import com.eparking.insideService.impl.TCompanyAreaInsideServiceImpl;
import com.common.util.Common;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
* @Description: TCompanyAreaInsideService接口
* @author 谢轩然
* @date 2020/04/23 17:28
*/
@FeignClient(value = "eparkingData",fallback = TCompanyAreaInsideServiceImpl.class)
public interface TCompanyAreaInsideService {
    String client= Common.Feign_eparkingCloudData;
    /**
    * 查询tCompanyArea
    * @param tCompanyArea
    * @return
    */
    @PostMapping(value = client+"/tCompanyArea/getTCompanyArea")
    ActionRsp getTCompanyArea(@RequestBody TCompanyArea tCompanyArea);

    /**
    *查询(分页)tCompanyArea
    * @param tCompanyArea
    * @param page
    * @param limit
    * @return
    */
    @PostMapping(value = client+"/tCompanyArea/getTCompanyAreabyPage")
    ControllerRsp getTCompanyAreabyPage(@RequestBody TCompanyArea tCompanyArea, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit);

    /**
    * 更新tCompanyArea
    * @param tCompanyArea
    * @return
    */
    @PostMapping(value = client+"/tCompanyArea/updateTCompanyArea")
    ActionRsp UpdateTCompanyArea(@RequestBody TCompanyArea tCompanyArea);

    /**
    * 删除tCompanyArea
    * @param tCompanyArea
    * @return
    */
    @PostMapping(value = client+"/tCompanyArea/deleteTCompanyArea")
    ActionRsp DeleteTCompanyArea(@RequestBody TCompanyArea tCompanyArea);
}