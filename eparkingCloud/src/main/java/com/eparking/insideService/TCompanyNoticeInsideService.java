package com.eparking.insideService;

import com.common.entity.ActionRsp;
import com.common.entity.ControllerRsp;
import com.common.entity.eparkingCloud.TCompanyNotice;
import com.eparking.insideService.impl.TCompanyNoticeInsideServiceImpl;
import com.common.util.Common;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
* @Description: TCompanyNoticeInsideService接口
* @author 谢轩然
* @date 2020/04/29 14:33
*/
@FeignClient(value = "eparkingCloudData",fallback = TCompanyNoticeInsideServiceImpl.class)
public interface TCompanyNoticeInsideService {
    String client= Common.Feign_eparkingCloudData;
    /**
    * 查询tCompanyNotice
    * @param tCompanyNotice
    * @return
    */
    @PostMapping(value = client+"/tCompanyNotice/getTCompanyNotice")
    ActionRsp getTCompanyNotice(@RequestBody TCompanyNotice tCompanyNotice);

    /**
    *查询(分页)tCompanyNotice
    * @param tCompanyNotice
    * @param page
    * @param limit
    * @return
    */
    @PostMapping(value = client+"/tCompanyNotice/getTCompanyNoticebyPage")
    ControllerRsp getTCompanyNoticebyPage(@RequestBody TCompanyNotice tCompanyNotice, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit);

    /**
    * 更新tCompanyNotice
    * @param tCompanyNotice
    * @return
    */
    @PostMapping(value = client+"/tCompanyNotice/updateTCompanyNotice")
    ActionRsp UpdateTCompanyNotice(@RequestBody TCompanyNotice tCompanyNotice);

    /**
    * 删除tCompanyNotice
    * @param tCompanyNotice
    * @return
    */
    @PostMapping(value = client+"/tCompanyNotice/deleteTCompanyNotice")
    ActionRsp DeleteTCompanyNotice(@RequestBody TCompanyNotice tCompanyNotice);
}