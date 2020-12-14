package com.eparking.insideService;

import com.common.entity.eparkingCloud.TElectronPayment;
import com.eparking.insideService.impl.ElectronPaymentServiceInsideServiceImpl;
import com.common.util.Common;
import com.common.entity.PageBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "eparkingCloudData",fallback = ElectronPaymentServiceInsideServiceImpl.class)
public interface ElectronPaymentServiceInsideService {
    String client= Common.Feign_eparkingCloudData;
    /**
     * 查询tElectronPayment
     * @param tElectronPayment
     * @return
     */
    @PostMapping(value = client+"/tElectronPayment/getTElectronPayment")
    List<TElectronPayment> getTElectronPaymentList(@RequestBody TElectronPayment tElectronPayment, @RequestParam("beginTime") String beginTime, @RequestParam("endTime") String endTime);

    /**
     *查询(分页)tElectronPayment
     * @param tElectronPayment
     * @param page
     * @param limit
     * @return
     */
    @PostMapping(value = client+"/tElectronPayment/getTElectronPaymentbyPage")
    PageBean<TElectronPayment> getTElectronPaymentbyPage(@RequestBody TElectronPayment tElectronPayment, @RequestParam("beginTime") String beginTime, @RequestParam("endTime") String endTime, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit);

    /**
     * 更新tElectronPayment
     * @param tElectronPayment
     * @return
     */
    @PostMapping(value = client+"/tElectronPayment/updateTElectronPayment")
    String UpdateTElectronPayment(@RequestBody TElectronPayment tElectronPayment);
}
