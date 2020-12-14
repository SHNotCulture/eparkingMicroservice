package com.eparking.insideService;

import com.common.entity.eparkingCloud.TCompanyPark;
import com.eparking.insideService.impl.TCompanyParkInsideServiceImpl;
import com.common.util.Common;
import com.common.entity.PageBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "eparkingCloudData",fallback = TCompanyParkInsideServiceImpl.class)
public interface TCompanyParkInsideService {
    String client= Common.Feign_eparkingCloudData;

    /**
     * 查询tCompanyPark
     * @param tCompanyPark
     * @return
     */
    @PostMapping(value = client+"/tCompanyPark/getTCompanyPark")
    List<TCompanyPark> getTCompanyPark(@RequestBody TCompanyPark tCompanyPark);

    /**
     *查询(分页)tCompanyPark
     * @param tCompanyPark
     * @param page
     * @param limit
     * @return
     */
    @PostMapping(value = client+"/tCompanyPark/getTCompanyParkbyPage")
    PageBean<TCompanyPark> getTCompanyParkbyPage(@RequestBody TCompanyPark tCompanyPark, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit);

    /**
     * 更新tCompanyPark
     * @param tCompanyPark
     * @return
     */
    @PostMapping(value = client+"/tCompanyPark/updateTCompanyPark")
    String UpdateTCompanyPark(@RequestBody TCompanyPark tCompanyPark);

    /**
     * 删除tCompanyPark
     * @param tCompanyPark
     * @return
     */
    @PostMapping(value = client+"/tCompanyPark/deleteTCompanyPark")
    String DeleteTCompanyPark(@RequestBody TCompanyPark tCompanyPark);

    /**
     * 根据id查询tCompanyPark
     * @param tCompanyPark
     * @return
     */
    @PostMapping(value = client+"/tCompanyPark/getTCompanyParkByID")
    TCompanyPark getTCompanyParkByID(@RequestBody TCompanyPark tCompanyPark);

    @PostMapping(value = client+"/tCompanyPark/getCarParkbyIDIn")
    List<TCompanyPark> getCarParkbyIDIn(@RequestParam("ids") String ids);
}
