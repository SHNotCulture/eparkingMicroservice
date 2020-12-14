package com.eparking.insideService;

import com.common.entity.eparkingCloud.TCarWalletReport;
import com.eparking.insideService.impl.TCarWalletReportInsideServiceImpl;
import com.common.util.Common;
import com.common.entity.PageBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
* @Description: TCarWalletReportInsideService接口
* @author 李书瀚
* @date 2020/10/26 23:19
*/
@FeignClient(value = "eparkingCloudData",fallback = TCarWalletReportInsideServiceImpl.class)
public interface TCarWalletReportInsideService {
    String client= Common.Feign_eparkingCloudData;
    /**
    * 查询tCarWalletReport信息
    * @param tCarWalletReport
    * @return
    */
    @PostMapping(value = client+"/tCarWalletReport/getTCarWalletReport")
    List<TCarWalletReport> getTCarWalletReport(@RequestBody TCarWalletReport tCarWalletReport, @RequestParam("beginTime") String beginTime, @RequestParam("endTime") String endTime);

    /**
    *查询tCarWalletReport信息(分页)
    * @param tCarWalletReport
    * @param page
    * @param limit
    * @return
    */
    @PostMapping(value = client+"/tCarWalletReport/getTCarWalletReportbyPage")
    PageBean<TCarWalletReport> getTCarWalletReportbyPage(@RequestBody TCarWalletReport tCarWalletReport, @RequestParam("beginTime") String beginTime, @RequestParam("endTime") String endTime, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit);

    /**
    * 更新tCarWalletReport信息
    * @param tCarWalletReport
    * @return
    */
    @PostMapping(value = client+"/tCarWalletReport/updateTCarWalletReport")
    String UpdateTCarWalletReport(@RequestBody TCarWalletReport tCarWalletReport);

    /**
    * 删除tCarWalletReport信息
    * @param tCarWalletReport
    * @return
    */
    @PostMapping(value = client+"/tCarWalletReport/deleteTCarWalletReport")
    String DeleteTCarWalletReport(@RequestBody TCarWalletReport tCarWalletReport);
}