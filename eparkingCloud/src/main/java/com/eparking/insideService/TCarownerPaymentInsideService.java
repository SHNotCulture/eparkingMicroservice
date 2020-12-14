package com.eparking.insideService;

import com.common.entity.eparkingCloud.TCarownerPayment;
import com.common.entity.eparkingCloud.TCarownerPaymentCus;
import com.eparking.insideService.impl.TCarownerPaymentInsideServiceImpl;
import com.common.util.Common;
import com.common.entity.PageBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
* @Description: TCarownerPaymentInsideService接口
* @author 谢轩然
* @date 2020/09/11 16:29
*/
@FeignClient(value = "eparkingCloudData",fallback = TCarownerPaymentInsideServiceImpl.class)
public interface TCarownerPaymentInsideService {
    String client= Common.Feign_eparkingCloudData;
    /**
    * 查询tCarownerPayment
    * @param tCarownerPayment
    * @return
    */
    @PostMapping(value = client+"/tCarownerPayment/getTCarownerPayment")
    List<TCarownerPayment> getTCarownerPayment(@RequestBody TCarownerPayment tCarownerPayment, @RequestParam("beginDate") String beginDate, @RequestParam("endDate") String endDate);

    /**
    *查询(分页)tCarownerPayment
    * @param tCarownerPayment
    * @param page
    * @param limit
    * @return
    */
    @PostMapping(value = client+"/tCarownerPayment/getTCarownerPaymentNewbyPage")
    PageBean<TCarownerPayment> getTCarownerPaymentbyPage(@RequestBody TCarownerPaymentCus tCarownerPaymentCus, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit);

    /**
    * 更新tCarownerPayment
    * @param tCarownerPayment
    * @return
    */
    @PostMapping(value = client+"/tCarownerPayment/updateTCarownerPayment")
    String UpdateTCarownerPayment(@RequestBody TCarownerPayment tCarownerPayment);

    /**
    * 删除tCarownerPayment
    * @param tCarownerPayment
    * @return
    */
    @PostMapping(value = client+"/tCarownerPayment/deleteTCarownerPayment")
    String DeleteTCarownerPayment(@RequestBody TCarownerPayment tCarownerPayment);

    @PostMapping(value = client+"/tCarownerPayment/getTCarownerPaymentByID")
    TCarownerPayment getTCarownerPaymentByID(@RequestParam("id") Integer id);
}