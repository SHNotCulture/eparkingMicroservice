package com.eparking.insideService;

import com.common.entity.eparkingCloud.TPaymentDetails;
import com.eparking.insideService.impl.TPaymentDetailsInsideServiceImpl;
import com.common.util.Common;
import com.common.entity.PageBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
* @Description: TPaymentDetailsInsideService接口
* @author 谢轩然
* @date 2020/10/13 17:31
*/
@FeignClient(value = "eparkingCloudData",fallback = TPaymentDetailsInsideServiceImpl.class)
public interface TPaymentDetailsInsideService {
    String client= Common.Feign_eparkingCloudData;
    /**
    * 查询tPaymentDetails
    * @param tPaymentDetails
    * @return
    */
    @PostMapping(value = client+"/tPaymentDetails/getTPaymentDetails")
    List<TPaymentDetails> getTPaymentDetails(@RequestBody TPaymentDetails tPaymentDetails);

    /**
    *查询(分页)tPaymentDetails
    * @param tPaymentDetails
    * @param page
    * @param limit
    * @return
    */
    @PostMapping(value = client+"/tPaymentDetails/getTPaymentDetailsbyPage")
    PageBean<TPaymentDetails> getTPaymentDetailsbyPage(@RequestBody TPaymentDetails tPaymentDetails, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit);

    /**
    * 更新tPaymentDetails
    * @param tPaymentDetails
    * @return
    */
    @PostMapping(value = client+"/tPaymentDetails/updateTPaymentDetails")
    String UpdateTPaymentDetails(@RequestBody TPaymentDetails tPaymentDetails);

    /**
    * 删除tPaymentDetails
    * @param tPaymentDetails
    * @return
    */
    @PostMapping(value = client+"/tPaymentDetails/deleteTPaymentDetails")
    String DeleteTPaymentDetails(@RequestBody TPaymentDetails tPaymentDetails);

}