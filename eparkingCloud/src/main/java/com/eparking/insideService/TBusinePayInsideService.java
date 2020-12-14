package com.eparking.insideService;

import com.common.entity.eparkingCloud.TBusinePay;
import com.eparking.insideService.impl.TBusinePayInsideServiceImpl;
import com.common.util.Common;
import com.common.entity.PageBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
* @Description: TBusinePayInsideService接口
* @author 谢轩然
* @date 2020/04/09 10:50
*/
@FeignClient(value = "eparkingCloudData",fallback = TBusinePayInsideServiceImpl.class)
public interface TBusinePayInsideService {
    String client= Common.Feign_eparkingCloudData;
    /**
    * 查询tBusinePay
    * @param tBusinePay
    * @return
    */
    @PostMapping(value = client+"/tBusinePay/getTBusinePay")
    List<TBusinePay> getTBusinePay(@RequestBody TBusinePay tBusinePay, @RequestParam("busineId") Integer busineId, @RequestParam("payTimeBegin") String payTimeBegin, @RequestParam("payTimeEnd") String payTimeEnd);

    /**
    *查询(分页)tBusinePay
    * @param tBusinePay
    * @param page
    * @param limit
    * @return
    */
    @PostMapping(value = client+"/tBusinePay/getTBusinePaybyPage")
    PageBean<TBusinePay> getTBusinePaybyPage(@RequestBody TBusinePay tBusinePay, @RequestParam("busineId") Integer busineId, @RequestParam("payTimeBegin") String payTimeBegin, @RequestParam("payTimeEnd") String payTimeEnd, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit);

    /**
    * 更新tBusinePay
    * @param tBusinePay
    * @return
    */
    @PostMapping(value = client+"/tBusinePay/updateTBusinePay")
    String UpdateTBusinePay(@RequestBody TBusinePay tBusinePay);

    /**
    * 删除tBusinePay
    * @param tBusinePay
    * @return
    */
    @PostMapping(value = client+"/tBusinePay/deleteTBusinePay")
    String DeleteTBusinePay(@RequestBody TBusinePay tBusinePay);

    /**
     * 导出商户充值记录
     * @param tBusinePay
     * @return
     */
    @PostMapping(value = client+"/tBusinePay/exportListforBusinePay")
    void exportListforBusinePay(@RequestBody TBusinePay tBusinePay, @RequestParam("busineId") Integer busineId, @RequestParam("payTimeBegin") String payTimeBegin, @RequestParam("payTimeEnd") String payTimeEnd, @RequestParam("response") HttpServletResponse response);
}