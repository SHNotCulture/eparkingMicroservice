package com.eparking.insideService;

import com.common.entity.ActionRsp;
import com.common.entity.ControllerRsp;
import com.common.entity.eparkingCloud.THouseholdFeedback;
import com.eparking.insideService.impl.THouseholdFeedbackInsideServiceImpl;
import com.common.util.Common;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
* @Description: THouseholdFeedbackInsideService接口
* @author 谢轩然
* @date 2020/04/29 15:30
*/
@FeignClient(value = "eparkingCloudData",fallback = THouseholdFeedbackInsideServiceImpl.class)
public interface THouseholdFeedbackInsideService {
    String client= Common.Feign_eparkingCloudData;
    /**
    * 查询THouseholdFeedback
    * @param THouseholdFeedback
    * @return
    */
    @PostMapping(value = client+"/THouseholdFeedback/getTHouseholdFeedback")
    ActionRsp getTHouseholdFeedback(@RequestBody THouseholdFeedback THouseholdFeedback);

    /**
    *查询(分页)THouseholdFeedback
    * @param THouseholdFeedback
    * @param page
    * @param limit
    * @return
    */
    @PostMapping(value = client+"/THouseholdFeedback/getTHouseholdFeedbackbyPage")
    ControllerRsp getTHouseholdFeedbackbyPage(@RequestBody THouseholdFeedback THouseholdFeedback, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit);

    /**
    * 更新THouseholdFeedback
    * @param THouseholdFeedback
    * @return
    */
    @PostMapping(value = client+"/THouseholdFeedback/updateTHouseholdFeedback")
    ActionRsp UpdateTHouseholdFeedback(@RequestBody THouseholdFeedback THouseholdFeedback);

    /**
    * 删除THouseholdFeedback
    * @param THouseholdFeedback
    * @return
    */
    @PostMapping(value = client+"/THouseholdFeedback/deleteTHouseholdFeedback")
    ActionRsp DeleteTHouseholdFeedback(@RequestBody THouseholdFeedback THouseholdFeedback);
}