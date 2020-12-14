package com.eparking.insideService;

import com.common.entity.eparkingCloud.TCarPayment;
import com.eparking.insideService.impl.TCarPaymentInsideServiceImpl;
import com.common.util.Common;
import com.common.entity.PageBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
* @Description: TCarPaymentInsideService接口
* @author 谢轩然
* @date 2020/05/14 16:54
*/
@FeignClient(value = "eparkingCloudData",fallback = TCarPaymentInsideServiceImpl.class)
public interface TCarPaymentInsideService {
    String client= Common.Feign_eparkingCloudData;
    /**
    * 查询tCarPayment
    * @param tCarPayment
    * @return
    */
    @PostMapping(value = client+"/tCarPayment/getTCarPayment")
    List<TCarPayment> getTCarPayment(@RequestBody TCarPayment tCarPayment, @RequestParam("beginData") String beginData, @RequestParam("endData") String endData);

    /**
    *查询(分页)tCarPayment
    * @param tCarPayment
    * @param page
    * @param limit
    * @return
    */
    @PostMapping(value = client+"/tCarPayment/getTCarPaymentbyPage")
    PageBean<TCarPayment> getTCarPaymentbyPage(@RequestBody TCarPayment tCarPayment, @RequestParam("beginData") String beginData, @RequestParam("endData") String endData, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit);

    /**
    * 更新tCarPayment
    * @param tCarPayment
    * @return
    */
    @PostMapping(value = client+"/tCarPayment/updateTCarPayment")
    String UpdateTCarPayment(@RequestBody TCarPayment tCarPayment);

    /**
    * 删除tCarPayment
    * @param tCarPayment
    * @return
    */
    @PostMapping(value = client+"/tCarPayment/deleteTCarPayment")
    String DeleteTCarPayment(@RequestBody TCarPayment tCarPayment);
}