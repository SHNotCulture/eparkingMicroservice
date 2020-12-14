package com.eparking.insideService;

import com.common.entity.ActionRsp;
import com.common.entity.ControllerRsp;
import com.common.entity.eparkingCloud.TPropertyBillItems;
import com.eparking.insideService.impl.TPropertyBillItemsInsideServiceImpl;
import com.common.util.Common;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
* @Description: TPropertyBillItemsInsideService接口
* @author 谢轩然
* @date 2020/04/29 10:33
*/
@FeignClient(value = "eparkingCloudData",fallback = TPropertyBillItemsInsideServiceImpl.class)
public interface TPropertyBillItemsInsideService {
    String client= Common.Feign_eparkingCloudData;
    /**
    * 查询tPropertyBillItems
    * @param tPropertyBillItems
    * @return
    */
    @PostMapping(value = client+"/tPropertyBillItems/getTPropertyBillItems")
    ActionRsp getTPropertyBillItems(@RequestBody TPropertyBillItems tPropertyBillItems);

    /**
    *查询(分页)tPropertyBillItems
    * @param tPropertyBillItems
    * @param page
    * @param limit
    * @return
    */
    @PostMapping(value = client+"/tPropertyBillItems/getTPropertyBillItemsbyPage")
    ControllerRsp getTPropertyBillItemsbyPage(@RequestBody TPropertyBillItems tPropertyBillItems, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit);

    /**
    * 更新tPropertyBillItems
    * @param tPropertyBillItems
    * @return
    */
    @PostMapping(value = client+"/tPropertyBillItems/updateTPropertyBillItems")
    ActionRsp UpdateTPropertyBillItems(@RequestBody TPropertyBillItems tPropertyBillItems);

    /**
    * 删除tPropertyBillItems
    * @param tPropertyBillItems
    * @return
    */
    @PostMapping(value = client+"/tPropertyBillItems/deleteTPropertyBillItems")
    ActionRsp DeleteTPropertyBillItems(@RequestBody TPropertyBillItems tPropertyBillItems);
}