package com.eparking.insideService;

import com.common.entity.ActionRsp;
import com.common.entity.ControllerRsp;
import com.common.entity.eparkingCloud.TPropertyAutobill;
import com.eparking.insideService.impl.TPropertyAutobillInsideServiceImpl;
import com.common.util.Common;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
* @Description: TPropertyAutobillInsideService接口
* @author 谢轩然
* @date 2020/04/28 16:27
*/
@FeignClient(value = "eparkingCloudData",fallback = TPropertyAutobillInsideServiceImpl.class)
public interface TPropertyAutobillInsideService {
    String client= Common.Feign_eparkingCloudData;
    /**
    * 查询tPropertyAutobill
    * @param tPropertyAutobill
    * @return
    */
    @PostMapping(value = client+"/tPropertyAutobill/getTPropertyAutobill")
    ActionRsp getTPropertyAutobill(@RequestBody TPropertyAutobill tPropertyAutobill);

    /**
    *查询(分页)tPropertyAutobill
    * @param tPropertyAutobill
    * @param page
    * @param limit
    * @return
    */
    @PostMapping(value = client+"/tPropertyAutobill/getTPropertyAutobillbyPage")
    ControllerRsp getTPropertyAutobillbyPage(@RequestBody TPropertyAutobill tPropertyAutobill, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit);

    /**
    * 更新tPropertyAutobill
    * @param tPropertyAutobill
    * @return
    */
    @PostMapping(value = client+"/tPropertyAutobill/updateTPropertyAutobill")
    ActionRsp UpdateTPropertyAutobill(@RequestBody TPropertyAutobill tPropertyAutobill);

    /**
    * 删除tPropertyAutobill
    * @param tPropertyAutobill
    * @return
    */
    @PostMapping(value = client+"/tPropertyAutobill/deleteTPropertyAutobill")
    ActionRsp DeleteTPropertyAutobill(@RequestBody TPropertyAutobill tPropertyAutobill);
}