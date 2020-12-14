package com.eparking.insideService;

import com.common.entity.ActionRsp;
import com.common.entity.ControllerRsp;
import com.common.entity.eparkingCloud.TPropertyHouseholdBill;
import com.common.util.Common;
import com.eparking.insideService.impl.TPropertyHouseholdBillInsideServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
* @Description: TPropertyHouseholdBillInsideService接口
* @author 谢轩然
* @date 2020/04/29 15:37
*/
@FeignClient(value = "eparkingCloudData",fallback = TPropertyHouseholdBillInsideServiceImpl.class)
public interface TPropertyHouseholdBillInsideService {
    String client= Common.Feign_eparkingCloudData;
    /**
    * 查询tPropertyHouseholdBill
    * @param tPropertyHouseholdBill
    * @return
    */
    @PostMapping(value = client+"/tPropertyHouseholdBill/getTPropertyHouseholdBill")
    ActionRsp getTPropertyHouseholdBill(@RequestBody TPropertyHouseholdBill tPropertyHouseholdBill);

    /**
    *查询(分页)tPropertyHouseholdBill
    * @param tPropertyHouseholdBill
    * @param page
    * @param limit
    * @return
    */
    @PostMapping(value = client+"/tPropertyHouseholdBill/getTPropertyHouseholdBillbyPage")
    ControllerRsp getTPropertyHouseholdBillbyPage(@RequestBody TPropertyHouseholdBill tPropertyHouseholdBill, @RequestParam("householdIdStr") String householdIdStr, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit);

    /**
    * 更新tPropertyHouseholdBill
    * @param tPropertyHouseholdBill
    * @return
    */
    @PostMapping(value = client+"/tPropertyHouseholdBill/updateTPropertyHouseholdBill")
    ActionRsp UpdateTPropertyHouseholdBill(@RequestBody TPropertyHouseholdBill tPropertyHouseholdBill);

    /**
    * 删除tPropertyHouseholdBill
    * @param tPropertyHouseholdBill
    * @return
    */
    @PostMapping(value = client+"/tPropertyHouseholdBill/deleteTPropertyHouseholdBill")
    ActionRsp DeleteTPropertyHouseholdBill(@RequestBody TPropertyHouseholdBill tPropertyHouseholdBill);
}